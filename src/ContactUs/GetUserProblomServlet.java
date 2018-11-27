package ContactUs;

import Tools.LinkDatabase.linkDatabases;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetUserProblomServlet")
public class GetUserProblomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        this.init();
        //获取用户名
        HttpSession httpSession = request.getSession();
        this.setUser_id((String) httpSession.getAttribute("user_id"));
        //获取问题描述
        this.setProblom(new String(request.getParameter("problem").getBytes("ISO-8859-1"),"utf-8"));

        //创建SQL语句
        this.createSql();
        //上传
        try {
            this.sendProblom();
            //提示
            response.sendRedirect("/ContactUs/Success/index.jsp");
        } catch (ClassNotFoundException e) {
            System.out.println("发送失败");
            response.sendRedirect("/ContactUs/ErrorPage/index.jsp");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("发送失败");
            response.sendRedirect("/ContactUs/ErrorPage/index.jsp");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    /**
     * 初始化
     */
    public void init() {
        this.problom = new String();
        this.sql = new String();
        this.user_id = new String();
    }

    /**
     * 创建SQL语句
     */
    public void createSql() {
        String str = "insert into user_problom(user_id, problom) values (\'" + this.getUser_id() + "\', \'" + this.getProblom() + "\');";
        this.setSql(str);
    }

    /**
     * 发送用户问题数据到服务器
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void sendProblom() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        lpLinkDatabases.saveData(this.getSql());
    }

    private String user_id = null;

    private String problom = null;

    private String sql = null;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProblom() {
        return problom;
    }

    public void setProblom(String problom) {
        this.problom = problom;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
