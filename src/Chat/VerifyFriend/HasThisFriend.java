package Chat.VerifyFriend;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HasThisFriend {


    public HasThisFriend(String user_id, String friend_id) throws SQLException, ClassNotFoundException {
        //初始化
        this.init(user_id, friend_id);
        //设置是否有好友
        // true --》 有
        // false --》 没有
        this.findFriend();


    }


    /**
     * 找到我的好友中，是否有他
     * 1。 获取我的所有好友
     *
     * 2。 在我的好友里面找到我的好友
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void findFriend() throws ClassNotFoundException, SQLException {
        boolean hasFriend = false;
        linkDatabases lpLinkDatabases = new linkDatabases();
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        while(resultSet.next()) {
            hasFriend = true;
        }
        this.setHas(hasFriend);
    }


    /**
     * 初始化函数
     * @param user_id
     * @param friend_id
     */
    public void init(String user_id, String friend_id) {
        this.user_id = new String();
        this.friend_id = new String();
        this.sql = new String();
        this.setUser_id(user_id);
        this.setFriend_id(friend_id);
        // 设置SQL语句
        this.createSQL();
    }


    /**
     * 创建sql语句
     */
    public void createSQL() {
        String str = "select * from my_friends where " +
                "(my_id = \'" + this.getUser_id() + "\' or " +
                "my_friend_id = \'" + this.getFriend_id() + "\') " +
                "and (my_id = \'" + this.getFriend_id() + "\' or " +
                "my_friend_id = \'" + this.getUser_id() + "\') and friendship = 1;";
        this.setSql(str);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public boolean isHas() {
        return has;
    }

    public void setHas(boolean has) {
        this.has = has;
    }

    private String user_id = null;

    private String friend_id = null;

    private String sql = null;

    private boolean has = false;

}
