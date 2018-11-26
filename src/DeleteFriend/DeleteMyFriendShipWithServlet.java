package DeleteFriend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteMyFriendShipWithServlet")
public class DeleteMyFriendShipWithServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 初 始 化 函数
        this.init(request);
        // 删 除 好 友
        try {
            this.deleteFriend();
            //删除好友关系成功

        } catch (SQLException e) {
            //删除好友关系失败

            //response.setContentType("/");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //删除好友关系失败

            //response.setContentType("/");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化函数
     * @param request
     */
    public void init(HttpServletRequest request) {
        //获取你的Session对象
        HttpSession httpSession = request.getSession();
        //获取&设置你的账号
        this.setMy_id((String) httpSession.getAttribute("user_id"));
        //获取&设置你的好友账号
        this.setFriend_id(request.getParameter("friend_id"));
    }

    /**
     * 删除好友
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteFriend() throws SQLException, ClassNotFoundException {
        DeleteFriend deleteFriend = new DeleteFriend(this.getMy_id(), this.getFriend_id());
    }

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    //我的账号
    private String my_id = null;
    //好友账号
    private String friend_id = null;
}
