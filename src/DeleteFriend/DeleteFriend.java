package DeleteFriend;

import Tools.LinkDatabase.linkDatabases;

import java.sql.SQLException;

public class DeleteFriend {

    public DeleteFriend(String my_id, String friend_id) throws SQLException, ClassNotFoundException {
        //初始化
        this.init();
        //设置好友的账号
        this.setFriend_id(friend_id);
        //设置我的账号
        this.setMy_id(my_id);
        //设置SQL语句
        this.setSql();
        this.setSqlI();
        //开始删除好友
        this.processingInformation();
    }

    /**
     * 处理信息
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void processingInformation() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        lpLinkDatabases.updateData(this.getSql());
        lpLinkDatabases.saveData(this.getSqlI());
    }

    /**
     * 初始化函数
     */
    public void init() {
        this.friend_id = new String();
        this.my_id = new String();
        this.sql = new String();
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    public String getSql() {
        return sql;
    }

    /**
     * 生成数据库更新语句
     */
    public void setSql() {
        this.sql = "update my_friends set friendship = 0 where " +
                "(friend_id = \'" + this.getFriend_id() + "\' and my_id = \'" + this.getMy_id() + "\' ) " +
                "or (friend_id = \'" + this.getMy_id() + "\' and my_id = \'" + this.getFriend_id() + "\');";
    }

    public String getSqlI() {
        return sqlI;
    }

    public void setSqlI() {
        this.sqlI = sqlI;
    }

    //我的账号
    private String my_id = null;
    //好友账号
    private String friend_id = null;
    //SQL语句
    private String sql = null;
    private String sqlI = null;

}
