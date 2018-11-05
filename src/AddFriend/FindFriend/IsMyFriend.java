package AddFriend.FindFriend;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IsMyFriend {

    public IsMyFriend() {
    }

    public IsMyFriend(String user_id, String friend_id) {
        this.user_id = user_id;
        this.friend_id = friend_id;
    }

    public boolean isMyFriend() {
        boolean returnKey = false;
        linkDatabases lpLinkDatabases = null;
        try {
            lpLinkDatabases = new linkDatabases();
            //准备SQL 语句
            String sql = "select * from my_friends where " +
                    "(my_friend_id = \'" + this.getUser_id() + "\' and my_id = \'" + this.getFriend_id() + "\') " +
                    "or (my_id = \'" + this.getUser_id() + "\' and my_friend_id = \'" + this.getFriend_id() + "\') and friendship = 1;";
            //获取数据
            ResultSet resultSet = lpLinkDatabases.getInformation(sql);
            //如果有记录，存在好友关系
            while(resultSet.next()) {
                returnKey = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnKey;
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

    //我的ID
    private String user_id;
    //好友ID
    private String friend_id;

    public static void main(String[] args) {
        new IsMyFriend("1111", "1111").isMyFriend();
    }
}
