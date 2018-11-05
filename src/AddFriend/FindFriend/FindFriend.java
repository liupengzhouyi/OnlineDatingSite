package AddFriend.FindFriend;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindFriend {

    public FindFriend(String friendId) {
        init(friendId);
    }

    //初始化函数
    public void init(String friendId) {
        this.setFriendId(friendId);
    }


    //判断好友是否存在
    public boolean friendIsExist() throws ClassNotFoundException, SQLException {
        boolean returnKey = false;
        //获取数据库连接类
        linkDatabases lpLinkDatabases = new linkDatabases();
        //准备SQL 语句
        String sql = "select * from user where user_id = \'" + this.getFriendId() + "\';";
        //获取数据
        ResultSet resultSet = lpLinkDatabases.getInformation(sql);
        while(resultSet.next()) {
            String user_id = resultSet.getString("");
            if (user_id.toString().equals(this.getFriendId().toString())) {
                returnKey = true;
                break;
            }
        }
        return returnKey;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    private String friendId = null;

    public static void main(String[] args) {
        try {
            System.out.print(new FindFriend("11").friendIsExist());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
