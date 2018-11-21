package Login;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserPasswordValue {

    public GetUserPasswordValue(String user_id) throws SQLException, ClassNotFoundException {
        this.init();
        this.user_id = user_id;
        this.setSql(user_id);
        this.innerGetUserPasswordvalue();
    }

    public void innerGetUserPasswordvalue() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        while (resultSet.next()) {
            String passwordValue = resultSet.getString("user_password_value");
            this.setPasswordValue(passwordValue);
        }
    }

    public void init() {
        this.sql = new String();
        this.user_id = new String();
        this.passwordValue = new String();
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

    public void setSql(String user_id) {
        this.sql = "select * from user where user_id = \'" + user_id + "\';";
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    private String user_id = null;

    private String passwordValue = null;

    private String sql = null;
}
