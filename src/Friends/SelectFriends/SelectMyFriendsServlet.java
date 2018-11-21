package Friends.SelectFriends;

import Friends.ShowFriend.SelectFriend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "SelectMyFriendsServlet")
public class SelectMyFriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码格式
        //response.setContentType("");
        //初始化
        this.init(request);
        //获取PrintWiter对象
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        try {
            //判断当前ID是否拥有好友
            boolean key = new SelectFriend(this.getUser_id()).hasFriend();
            if (key) {
                //如果有
                //获取我的好友ID数据
                this.setFriends_id();
                //显示我的好友

            } else {
                //如果没有
                //页面跳转，首页
                response.sendRedirect("/");
            }
        } catch (ClassNotFoundException e) {
            //判断是否拥有好友时候出错，页面跳转

            e.printStackTrace();
        } catch (SQLException e) {
            //判断是否拥有好友时候出错，页面跳转

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 显示我的好友
     * @param printWriter
     */
    public void showMyFriendOnTable(PrintWriter printWriter) {
        //获取我的好友ID数据
        String[] strings = this.getFriends_id();
            printWriter.println("<table border=\"1\">\n" +
                                "    <tr>\n" +
                                "        <th>\n" +
                                "            我的好友\n" +
                                "        </th>\n" +
                                "    </tr>");

        for (int i=0;i<strings.length;i++) {
            printWriter.println("<tr>\n" +
                                "        <td>\n" +
                                            strings[i] +
                                "        </td>\n" +
                                "    </tr>");
        }
            printWriter.println("</table>");
    }

    /**
     * 初始化函数
     * @param request
     */
    public void init(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        //获取用户账号
        String user_id = (String) httpSession.getAttribute("user_id");
        this.setUser_id(user_id);
        System.out.println(this.getUser_id());
    }


    public String[] getFriends_id() {
        return friends_id;
    }

    public void setFriends_id() throws SQLException, ClassNotFoundException {
        SelectFriend selectFriend = new SelectFriend(this.getUser_id());
        this.friends_id = selectFriend.getMyFriends();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String user_id = null;

    private String[] friends_id = null;
}
