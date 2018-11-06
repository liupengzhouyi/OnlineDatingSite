package Chat.FindFriendToChat;

import Friends.ShowFriend.SelectFriend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //初始化
        this.init(request);
        //设置编码格式
        //response.setContentType("");
        //获取好友列表
        try {
            this.setFriends();
            //输出列表
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //点击聊天
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化函数
     * @param request
     */
    public void init(HttpServletRequest request) {
        //获取Session对象
        HttpSession httpSession = request.getSession();
        //设置用户的ID
        this.setMy_id((String) httpSession.getAttribute("user_id"));
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends() throws SQLException, ClassNotFoundException {
        this.friends = new SelectFriend(this.getMy_id()).getMyFriends();
    }

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    //好友列表的储存地址
    private String[] friends = null;
    //我的账号
    private String my_id = null;
}
