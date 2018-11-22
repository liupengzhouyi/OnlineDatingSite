package EndOfAddFriend;

import Chat.FindNewInformation.HaveNewInformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GetAddFriendFruitServlet")
public class GetAddFriendFruitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        response.setContentType("text/html;charset=UTF-8");
        //通过Session获取用户
        this.setUser_id(request);
        //查看数据库结果
        try {
            list = new HowFruitFriendApply(this.getUser_id()).getList();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //是否有好友申请
        if(list.size() == 0) {
            System.out.println("您没有好友申请！");
        } else {
            System.out.println("显示你的好友申请");
            PrintWriter printWriter = response.getWriter();
            this.showresult(printWriter);
        }

    }

    /**
     * 显示你的好友申请结果
     * @param printWriter
     */
    public void showresult(PrintWriter printWriter) {
        String temp_friend_id = "";
        int temp_new_key = 0;
        int temp_can = 0;
        ArrayList<Itme> temp_list = this.list;
        printWriter.println("<table border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>\n" +
                "            我的申请好友\n" +
                "        </th>\n" +
                "        <th>\n" +
                "            是否查看好友申请\n" +
                "        </th>\n" +
                "        <th>\n" +
                "            是否同意好友申请\n" +
                "        </th>\n" +
                "    </tr>");
        for (Itme itme : temp_list
                ) {
            temp_friend_id = itme.getFriend_id();
            temp_new_key = itme.getNew_key();
            temp_can = itme.getCan();
            String strI = "";
            String strII = "";
            if (temp_new_key == 0) {
                strI = "已经查看";
            } else {
                strI = "未查看";
            }
            if (temp_can == 1) {
                strII = "同意好友申请";
            } else {
                strII = "未通过好友申请";
            }
            printWriter.println("<tr>\n" +
                    "        <td>\n" +
                                temp_friend_id +
                    "        </td>\n" +
                    "        <td>\n" +
                                strI +
                    "        </td>\n" +
                    "        <td>\n" +
                                strII +
                    "        </td>\n" +
                    "    </tr>");
        }
        printWriter.println("</table>");
    }


    public void init() {
        this.user_id = new String();
        this.sql = new String();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public String getUser_id() {
        return user_id;
    }

    /**
     * 通过Session对象获取用户账号
     * @param request
     */
    public void setUser_id(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        String tempUser_id = (String) httpSession.getAttribute("user_id");
        this.user_id = tempUser_id;
    }

    public int getWhatFurit() {
        return whatFurit;
    }

    public void setWhatFurit(int whatFurit) {
        this.whatFurit = whatFurit;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    private ArrayList<Itme> list = null;

    private String user_id = null;

    private int whatFurit = 0;

    private String sql = null;
}
