package Tools.UserEmail;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserEmailByYourId {

    public GetUserEmailByYourId(String user_id) throws SQLException, ClassNotFoundException {
        //初始化
        this.init(user_id);
        //搜索&设置你的电子邮件
        this.setEmail();
    }

    /**
     * 初始化
     * @param user_id
     */
    public void init(String user_id) {
        this.email = new String();
        this.user_id = new String();
        this.setUser_id(user_id);
        this.sql = new String();
        this.createSql();
    }

    /**
     * 设置你的
     */
    public void createSql() {
        String str = "select * from user where user_id = \'" + this.getUser_id() + "\';";
        this.setSql(str);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        //System.out.println(this.getSql());
        String tempEmail = "";
        while(resultSet.next()) {
            tempEmail = resultSet.getString("user_email");
            //System.out.println(tempEmail);
        }
        this.email = tempEmail;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    private String user_id = null;

    private String email = null;

    private String sql = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GetUserEmailByYourId getUserEmailByYourId = new GetUserEmailByYourId("201811232700106");
        System.out.println(getUserEmailByYourId.getEmail());
    }

}
