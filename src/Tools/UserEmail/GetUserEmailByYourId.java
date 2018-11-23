package Tools.UserEmail;

public class GetUserEmailByYourId {

    public GetUserEmailByYourId(String user_id) {
        //初始化
        this.init(user_id);



    }

    /**
     * 初始化
     * @param user_id
     */
    public void init(String user_id) {
        this.email = new String();
        this.setUser_id(user_id);
        this.user_id = new String();
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

    public void setEmail(String email) {
        this.email = email;
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

}
