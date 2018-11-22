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
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getNewInformationNumber() throws ClassNotFoundException, SQLException {
        //临时新信息提示
        String temp = "";
        //获取新的数据库连接对象
        linkDatabases lpLinkDatabses = new linkDatabases();
        //获取我的新信息提示
        ResultSet resultSet = lpLinkDatabses.getInformation(this.getSql());
        while (resultSet.next()) {
            //新信息数量 +1
            this.setNumber(this.getNumber() + 1);
            //获取新信息编号
            temp = resultSet.getInt("tie_id") + "";
            //添加新信息
            this.setTemp_string(this.getTemp_string() + temp + ",");
        }
        this.setTies();
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

    public String getTemp_string() {
        return temp_string;
    }

    public void setTemp_string(String temp_string) {
        this.temp_string = temp_string;
    }

    /**
     * 获取新信息的编号
     * @return
     */
    public String[] getTies() {
        return ties;
    }

    /**
     * 设置新信息编号
     */
    public void setTies() {
        this.ties = this.getTemp_string().split(",");
    }

    //获取新信息编号的临时储存地址
    private String temp_string = "";
    //储存新信息编号
    private String[] ties = null;

    private String sql = "";

    private String user_id = "";

    private int number = -1;
}
