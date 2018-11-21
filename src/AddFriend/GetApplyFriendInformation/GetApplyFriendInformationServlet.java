package AddFriend.GetApplyFriendInformation;

import Tools.LinkDatabase.linkDatabases;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "GetApplyFriendInformationServlet")
public class GetApplyFriendInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession httpSession = request.getSession();
        //获取用户ID
        String user_id = (String) httpSession.getAttribute("user_id");
        System.out.println("用户ID：" + user_id);
        //获取PrintWriter对象
        PrintWriter printWriter = response.getWriter();

        //判断是否拥有好友
        try {
            if (this.hasFriend(user_id)) {
                //拥有好友
                //对好友进行列表
                this.showMyApplyInformation(user_id, printWriter);
            } else {
                //没有好友，页面跳转
                System.out.println("没有好友申请！");
            }
        } catch (ClassNotFoundException e) {
            //数据库连接查询失败
            System.out.println("数据库连接失败，在查询是否有好友进行好友申请的时候");

            e.printStackTrace();
        } catch (SQLException e) {
            //数据库连接查询失败


            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void init() {

    }

    /**
     * 判断是否有好友申请
      * @param user_id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean hasFriend(String user_id) throws ClassNotFoundException, SQLException {
        boolean returnKey = false;
        //连接数据库
        linkDatabases lpLinkDatabases = new linkDatabases();
        //准备SQL语句
        String sql = "select * from apply_for_friend where my_number = \'" + user_id + "\';";
        ResultSet resultSet = lpLinkDatabases.getInformation(sql);
        while(resultSet.next()) {
            returnKey = true;
            System.out.println("判断： 确定有好友申请");
        }
        return returnKey;
    }

    /**
     * 对好友进行列表输出
     * @param user_id
     * @param printWriter
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void showMyApplyInformation(String user_id, PrintWriter printWriter) throws ClassNotFoundException, SQLException {
        //连接数据库
        linkDatabases lpLinkDatabases = new linkDatabases();
        //准备SQL语句
        String sql = "select * from apply_for_friend where my_number = \'" + user_id + "\';";
        ResultSet resultSet = lpLinkDatabases.getInformation(sql);
            printWriter.println("<table border=\"1\">\n" +
                                "    <tr>\n" +
                                "        <th>\n" +
                                "            好友账号\n" +
                                "        </th>\n" +
                                "        <th>\n" +
                                "            同意\n" +
                                "        </th>\n" +
                                "        <th>\n" +
                                "            拒绝\n" +
                                "        </th>\n" +
                                "    </tr>");
        while(resultSet.next()) {
            //申请编号
            int temp_apply_id = resultSet.getInt("apply_id");
            //申请好友ID
            String temp_apply_my_id = resultSet.getString("my_number");
            printWriter.println("    <tr>\n" +
                                "        <td>\n" +
                                temp_apply_my_id +
                                "        </td>\n" +
                                "        <td>\n" +
                                "            <a href=\"/AddFriend/OperationApplication/OperationApplyServlet?apply_id=" + temp_apply_id + "&operation=1\"><button>同意</button></a>\n" +
                                "        </td>\n" +
                                "        <td>\n" +
                                "            <a href=\"/AddFriend/OperationApplication/OperationApplyServlet?apply_id=" + temp_apply_id + "&operation=0\"><button>拒绝</button></a>\n" +
                                "        </td>\n" +
                                "    </tr>");
        }
            printWriter.println("</table>");
    }
}