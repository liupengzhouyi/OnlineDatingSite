package Chat.FindNewInformation;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTieNumber {

    public GetTieNumber(String user_id, int number) {
        //初始化函数
        this.init(user_id, number);
        //连接数据库，获取提示的编号
        this.getTieNumber();
    }



    /**
     * 初始化函数
     * @param user_id
     * @param number
     */
    public void init(String user_id, int number) {
        this.user_id = new String();
        this.sql = new String();
        this.tieNumber = new String();
        this.setUser_id(user_id);
        this.setNumber(number);
        this.createSql();
    }

    /**
     * 连接数据库，获取新信息提示的编号
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getTeiNumber() throws ClassNotFoundException, SQLException {
        linkDatabases linkDatabases = new linkDatabases();
        ResultSet resultSet = linkDatabases.getInformation(this.getSql());
        int i = 0;
        while(resultSet.next()) {
            String tie = resultSet.getString("tie_id");
            if (i == this.getNumber()) {
                this.setTieNumber(tie);
                break;
            }
            i = i + 1;
        }
    }

    /**
     * 设置设个 数据查询的SQL语句
     */
    public void createSql() {
        String str = "select * from char_tie where user_id = \'" + this.getUser_id() + "\' and is_new = 1;";
        this.setSql(str);
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

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTieNumber() {
        return tieNumber;
    }

    public void setTieNumber(String tieNumber) {
        this.tieNumber = tieNumber;
    }

    private String tieNumber = null;

    private String user_id = null;

    private String sql = null;

    private int number = 0;
}
