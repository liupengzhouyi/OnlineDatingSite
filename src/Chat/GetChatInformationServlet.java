package Chat;

import Tools.GetTime.GetNowTime;
import Tools.LinkDatabase.linkDatabases;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet(name = "GetChatInformationServlet")
public class GetChatInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化函数
        init(request);
        //准备SQL语句
        //  1。 聊天信息
        //  2。 新信息提示
        this.createSQLI() ;
        this.createSQLII();
        //数据入库
        try {
            this.saveData();
        } catch (ClassNotFoundException e) {
            //数据入库出错，错误地址聊天信息出错

            e.printStackTrace();
        } catch (SQLException e) {
            //错误信息：数据入库出错，错误地址：保存聊天信息

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化 函数
     * @param request
     */
    public void init(HttpServletRequest request) throws UnsupportedEncodingException {
        //获取当前Session对象
        HttpSession httpSession = request.getSession();
        //获取用户ID
        this.setMy_id((String) httpSession.getAttribute("user_id"));
        //获取好友ID
        this.setFriend_id((String)httpSession.getAttribute("friend_id_to_chat"));
        //获取聊天信息
        String information = request.getParameter("charInformation");
        information = new String(information.getBytes("ISO-8859-1"),"utf-8");
        System.out.println(information);
        this.setChat(information);
    }


    /**
     * 生成聊天信息入库SQL 语句
     */
    public void createSQLI() {
        
        String string = "insert into char_information (user_id, friend_id, text, char_data_time, is_new) " +
                "values (\'" + this.getMy_id() + "\', \'" + this.getFriend_id() + "\', \'" + this.getChat() + "\', \'" + new GetNowTime().getDateTime() +"\', 1);";

        this.setSqlI(string);
    }

    /**
     * 生成提示好友与新信息的SQL语句
     */
    public void createSQLII() {
        //告诉朋友有新的信息


        String string = "insert into char_tie(user_id, is_new) values (\'" + this.getFriend_id() + "\', 1);";

        this.setSqlII(string);
    }

    /**
     * 保存数据
     * 数据1。保存新信息
     * 数据2。提示有新信息
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void saveData() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        lpLinkDatabases.saveData(this.getSqlI());
        lpLinkDatabases.saveData(this.getSqlII());
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

    public String getSqlI() {
        return sqlI;
    }

    public void setSqlI(String sqlI) {
        this.sqlI = sqlI;
    }

    public String getSqlII() {
        return sqlII;
    }

    public void setSqlII(String sqlII) {
        this.sqlII = sqlII;
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
