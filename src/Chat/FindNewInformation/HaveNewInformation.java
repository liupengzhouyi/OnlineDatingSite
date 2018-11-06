package Chat.FindNewInformation;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HaveNewInformation {

    public HaveNewInformation(String user_id) throws SQLException, ClassNotFoundException {
        this.setUser_id(user_id);
        //初始化函数
        this.init();
        //设置新信息数量
        this.getNewInformationNumber();
    }

    /**
     * 初始化函数
     */
    public void init() {
        //生成SQL语句
        this.setSql(this.getUser_id());
    }

    /**
     * 获取新信息数量
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getNewInformationNumber() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabses = new linkDatabases();
        ResultSet resultSet = lpLinkDatabses.getInformation(this.getSql());
        while(resultSet.next()) {
            //新信息数量 +1
            this.setNumber(this.getNumber() + 1);
        }
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String user_id) {
        this.sql = "select * from char_tie where is_new = 1 and user_id = \'" + user_id + "\';";
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private String sql = "";

    private String user_id = "";

    private int number = -1;
}
