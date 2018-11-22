package Chat.FindNewInformation;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetUserNewInformation {

    public GetUserNewInformation(String user_id) throws SQLException, ClassNotFoundException {
        //初始化
        init();
        //设置用户账号
        this.setUser_id(user_id);
        //设置sql语句
        this.createSql();
        //提取数据
        this.getInformation();
    }

    public void init() {
        this.user_id = new String();
        this.sql = new String();
        this.list = new ArrayList<Itme02>();
    }



    /**
     * 获取新信息
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getInformation() throws ClassNotFoundException, SQLException {
        //获取数据库连接对象
        linkDatabases lpLinkDatabases = new linkDatabases();
        //获取数据库中的数据集合
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        //统计数据
        String my_friend_id = "";
        String datetime = "";
        String information = "";
        while (resultSet.next()) {
            my_friend_id = resultSet.getString("user_id");
            information = resultSet.getString("text");
            datetime = resultSet.getString("char_data_time");
            Itme02 itme02 = new Itme02(my_friend_id, datetime, information);
            this.addList(itme02);
        }
    }

    /**
     * 设置SQL语句，
     * 用来提取数据库中给我的聊天信息！
     */
    public void createSql() {
        String str= "select * from char_information where friend_id = \'" + this.getUser_id() + "\' and is_new = 1;";

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

    public String[] getChat_ids() {
        return chat_ids;
    }

    public void setChat_ids(String[] chat_ids) {
        this.chat_ids = chat_ids;
    }

    /**
     * 添加新信息字段到列表中
     * @param itme02
     */
    public void addList(Itme02 itme02) {
        this.list.add(itme02);
    }

    public ArrayList<Itme02> getList() {
        return list;
    }

    public void setList(ArrayList<Itme02> list) {
        this.list = list;
    }

    private String user_id = null;

    private String sql = null;

    private String[] chat_ids = null;

    private ArrayList<Itme02> list = null;
}
