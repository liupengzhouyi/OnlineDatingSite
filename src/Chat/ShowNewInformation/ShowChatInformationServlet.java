package Chat.ShowNewInformation;

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

@WebServlet(name = "ShowChatInformationServlet")
public class ShowChatInformationServlet extends HttpServlet {
    /**
     * 需要 用户账号
     * 需要 所选条目
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化函数
        this.init(request);
        //设置编码格式
        response.setContentType("text/html;charset=UTF-8");
        try {
            //获取信息
            this.getDataInformation();
            //显示信息
            // 获取Printwriter对象
            PrintWriter printWriter = response.getWriter();
            // 显示数据
            this.showChatInformation(printWriter);
            //消除最新状态
            // 1。 消除信息的最新状态
            // 2。 消除提示信息的最新状态
            //设置数据库跟新语句
            this.setUpdateSqlI();
            //this.setUpdateSqlII();
            //数据库更新
            this.updateData(this.getUpdateSqlI());
            this.updateData(this.getUpdateSqlII());
        } catch (ClassNotFoundException e) {
            //数据库中获取信息出错
            //出错地址：获取聊天信息

            e.printStackTrace();
        } catch (SQLException e) {
            //数据库中获取信息出错
            //出错地址：获取聊天信息

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化函数
     * 1。 获取用户账号
     * 2。 获取所要信息的条目编号
     * @param request
     */
    public void init(HttpServletRequest request) {
        //获取Session对象
        HttpSession httpSession = request.getSession();
        //获取用户账号
        this.setUser_id((String) httpSession.getAttribute("user_id"));
        //设置所要获取信息的条目
        this.setNumber(Integer.parseInt(request.getParameter("number")));
        //设置信息获取语句
        this.setSql(this.getUser_id());
    }


    /**
     * 设置要获取的信息
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getDataInformation() throws ClassNotFoundException, SQLException {
        //获取数据库连接对象
        linkDatabases lpLinkDatabases = new linkDatabases();
        //获取数据
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        int tempNumber = 0;
        while (resultSet.next()) {
            tempNumber = tempNumber + 1;
            if (tempNumber == this.getNumber()) {
                //获取数据条目的编号
                this.setSql_id(resultSet.getString("char_id"));
                //获取数据信息
                this.setInformation(resultSet.getString("text"));
                //获取好友
                this.setFriend_id(resultSet.getString("friend_id"));
                break;
            }
        }
    }

    /**
     * 显示聊天信息
     * @param printWriter
     */
    public void showChatInformation(PrintWriter printWriter) {
        printWriter.println("<table border=\"1\">\n" +
                            "    <tr>\n" +
                            "        <th colspan=\"1\">\n" +
                            "            <a href=\"\">\n" +
                            "                <button>\n" +
                            "                    返回\n" +
                            "                </button>\n" +
                            "            </a>\n" +
                            "        </th>\n" +
                            "        <th>\n" +
                            "\n" +
                            "        </th>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <th colspan=\"2\">\n" +
                            this.getFriend_id() +
                            "        </th>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td colspan=\"2\">\n" +
                            this.getInformation() +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td colspan=\"2\">\n" +
                            "            <textarea name=\"text\" id=\"\" cols=\"30\" rows=\"10\"></textarea>\n" +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <th colspan=\"1\">\n" +
                            "            <button type=\"reset\">重置</button>\n" +
                            "        </th>\n" +
                            "        <th colspan=\"1\">\n" +
                            "            <a href=\"/\">\n" +
                            "                <button>\n" +
                            "                    回复\n" +
                            "                </button>\n" +
                            "            </a>\n" +
                            "        </th>\n" +
                            "    </tr>\n" +
                            "</table>");
    }

    /**
     * 数据库中虽居的更新
     * @param sql
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateData(String sql) throws ClassNotFoundException, SQLException {
        //获取数据库连接对象
        linkDatabases lpLinkDatabases = new linkDatabases();
        //数据更新
        lpLinkDatabases.updateData(sql);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String friend_id) {
        this.sql = "select * from char_information where friend_id = \'" + friend_id + "\' and is_new = 1;";
    }

    public String getSql_id() {
        return sql_id;
    }

    public void setSql_id(String sql_id) {
        this.sql_id = sql_id;
    }

    public String getUpdateSqlI() {
        return updateSqlI;
    }

    /**
     * 设置聊天信息数据库中的数据的更新
     */
    public void setUpdateSqlI() {
        this.updateSqlI = "update char_information set is_new = 0 where char_id = " + this.getSql_id() + ";";
    }

    public String getUpdateSqlII() {
        return updateSqlII;
    }

    public void setUpdateSqlII(String updateSqlII) {
        this.updateSqlII = updateSqlII;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    //所要获取的语句编号
    private int number = -1;
    //用户
    private String user_id = null;
    //获取数据的SQL语句
    private String sql = "";
    //数据库中数据编号
    private String sql_id = "";
    //数据库更新语句I
    private String updateSqlI = "";
    //数据库跟新语句II
    private String updateSqlII = "";
    //数据信息
    private String information = "";
    //好友编号
    private String friend_id = "";
}
