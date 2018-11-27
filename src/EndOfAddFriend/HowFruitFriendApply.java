package EndOfAddFriend;

import Chat.FindNewInformation.HaveNewInformation;
import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HowFruitFriendApply {

    public HowFruitFriendApply(String user_id) throws SQLException, ClassNotFoundException {
        this.init();
        this.user_id = user_id;
        //设置SQL语句
        this.setSql();
        //得到好友申请结果
        this.whatFruitForYourApply();

    }

    /**
     * 我的申请怎么样，会有什么结果
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void whatFruitForYourApply() throws ClassNotFoundException, SQLException {
        String temp_friend_id = "";
        int temp_new_key = 0;
        int temp_can = 0;
        linkDatabases lpLinkDatabases = new linkDatabases();
        ResultSet resultSet = lpLinkDatabases.getInformation(this.getSql());
        while (resultSet.next()) {
            temp_friend_id = resultSet.getString("friend_id");
            temp_new_key = resultSet.getInt("new_key");
            temp_can = resultSet.getInt("can");
            Itme itme = new Itme(temp_friend_id, temp_new_key, temp_can);
            this.addList(itme);
        }
    }

    /**
     * 初始化函数
     */
    public void init() {
       this.sql = new String();
       this.user_id = new String();
       this.list = new ArrayList<Itme>();
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

    public void setSql() {
        this.sql = "select apply_for_friend.friend_number friend_id, apply_for_friend.new_key new_key, apply_return.can_make can from apply_for_friend, apply_return where apply_for_friend.my_number = \'" + this.getUser_id() + "\' and apply_for_friend.apply_id = apply_return.return_id;";
        System.out.println("查看你的获取申请好友结果：");
        System.out.println(this.sql);
    }

    public ArrayList<Itme> getList() {
        return list;
    }

    public void setList(ArrayList<Itme> list) {
        this.list = list;
    }

    /**
     * 添加好友申请结果到一个列表中
     * @param itme
     */
    public void addList(Itme itme) {
        this.list.add(itme);
    }

    private String user_id = null;

    private String sql = null;

    private ArrayList<Itme> list = null;


    public static void main(String[] args) {
        try {
            //HaveNewInformation haveNewInformation = new HaveNewInformation("201811211800102");
            System.out.println(new HowFruitFriendApply("201811211800102").getList().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*System.out.println(new HowFruitFriendApply('201811211800102').getList().toString());*/

    }
}
