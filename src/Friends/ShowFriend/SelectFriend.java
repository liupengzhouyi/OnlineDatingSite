package Friends.ShowFriend;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectFriend {

    public SelectFriend(String user_id) {
        this.setUser_id(user_id);
    }

    /**
     * 判断是否有好友
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean hasFriend() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        boolean returnKey = false;
        while (resultSet.next()) {
            returnKey = true;
        }
        return returnKey;
    }

    /**
     * 获取好友的ID
     * 返回字符串数组
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[] getMyFriends() throws ClassNotFoundException, SQLException {
        String string = "";
        linkDatabases lpLinkDatabases = new linkDatabases();
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        String temp_friend_id = "";
        String temp_my_id = "";
        while (resultSet.next()) {
            temp_friend_id = resultSet.getString("my_friend_id");
            temp_my_id = resultSet.getString("my_id");
            string = string + this.getMyFriend(user_id, temp_my_id, temp_friend_id) + ",";
        }
        String[] friendIds = string.split(",");
        return friendIds;
    }

    /**
     * 找到我的好友-- 测试版本
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void testFindFrinend() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        String temp_friend_id = "";
        String temp_my_id = "";
        while (resultSet.next()) {
            temp_friend_id = resultSet.getString("my_friend_id");
            temp_my_id = resultSet.getString("my_id");
            System.out.println(temp_friend_id + ":" + temp_my_id);
            System.out.println(this.getMyFriend(user_id, temp_my_id, temp_friend_id));
        }
    }

    /**
     * 找到我的好友的ID
     * 主要就是分别出我的好友的账号
     * @param user_id
     * @param my_id
     * @param friend_id
     * @return
     */
    public String getMyFriend(String user_id, String my_id, String friend_id) {
        String returnStr = "";
        if (user_id.equals(my_id)) {
            returnStr = friend_id;
        } else {
            returnStr = my_id;
        }
        return returnStr;
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
     * 设置SQL语句
     */
    public void setSql() {
        this.sql = "select * from my_friends where " +
                "(my_id = \'" + this.getUser_id() + "\' or friend_id = \'" + this.getUser_id()+ "\') " +
                "and friendship = 1;";
    }

    public String user_id = null;

    private String sql = null;
}
