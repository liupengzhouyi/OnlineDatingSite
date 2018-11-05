package Chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetChatInformationServlet")
public class GetChatInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化函数
        init(request);
        //准备SQL语句
        //  1。 聊天信息入库
        //  2。 新信息提示入库

        //数据入库

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化函数
     * @param request
     */
    public void init(HttpServletRequest request) {
        //获取当前Session对象
        HttpSession httpSession = request.getSession();
        this.setMy_id((String) httpSession.getAttribute("user_id"));
        this.setFriend_id(request.getParameter("friend_id"));
        this.setChat(request.getParameter("charInformation"));
    }


    public createSQLI() {

    }

    public void createSQLII() {

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

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    //我的ID
    private String my_id = "";
    //朋友的ID
    private String friend_id = "";
    //聊天信息
    private String chat = "";

    private String sqlI = "";

    private String sqlII = "";
}
