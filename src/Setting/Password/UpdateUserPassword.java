package Setting.Password;

import Tools.LinkDatabase.linkDatabases;

import java.sql.SQLException;

public class UpdateUserPassword {

    private String password = null;

    private String passwordValue = null;

    private String sql = null;

    private String user_id = null;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    public String getSql() {
        return sql;
    }

    public void setSql() {
        this.sql = "update user set user_password_value = \'" + this.getPasswordValue() + "\' where user_id = \'" + this.getUser_id() + "\';";
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 初始化函数
     */
    public void init() {
        this.password = new String();
        this.passwordValue = new String();
        this.sql = new String();
        this.user_id = new String();
    }

    /**
     * 更新用户密码
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updatePasswordValue() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        lpLinkDatabases.updateData(this.getSql());
    }

    public UpdateUserPassword(String user_id, String password) throws SQLException, ClassNotFoundException {
        //初始化函数
        this.init();
        //赋值
        this.setUser_id(user_id);
        this.setPassword(password);
        //设置密码值
        this.setPasswordValue(this.getPassword().hashCode() + "");
        //设置SQL语句
        this.setSql();
        /*//更新密码
        this.updatePasswordValue();*/
    }
}
