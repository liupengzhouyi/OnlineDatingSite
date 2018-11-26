package Setting.Password;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserPasswordValue {

    public GetUserPasswordValue(String user_id) throws SQLException, ClassNotFoundException {

        //初始化
        this.init();
        //设置你的用户ID
        this.setUser_id(user_id);
        //设置你的提取用户密码的SQL语句
        this.setSql();
        //设置你的密码值
        this.setPasswordValue();
    }

    /**
     * 初始化函数
     */
    public void init() {
        this.user_id = new String();
        this.passwordValue = new String();
        this.sql = new String();
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

    /**
     * 设置你的获取密码的SQL语句
     */
    public void setSql() {
        this.sql = "select * from user where user_id = \'" + this.getUser_id() + "\';";
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    /**
     * 设置你的密码值
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void setPasswordValue() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        while(resultSet.next()) {
            this.passwordValue = resultSet.getString("user_password_value");
        }
    }

    private String user_id = null;

    private String sql = null;

    private String passwordValue = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GetUserPasswordValue getUserPasswordValue = new GetUserPasswordValue("201811232700203");
        System.out.println(getUserPasswordValue.getPasswordValue());
    }
}
