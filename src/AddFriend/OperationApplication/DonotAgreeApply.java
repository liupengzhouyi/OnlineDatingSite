package AddFriend.OperationApplication;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DonotAgreeApply {

    public DonotAgreeApply(String apply_id) throws SQLException, ClassNotFoundException {
        //初始化
        init();
        //获取好友申请ID
        this.setapply_id(apply_id);
        // 同时获取我的ID，好友的ID
        this.setSql();
        //准备好友添加成功通知的SQL语句
        this.setNewSql();
        //数据入库
        this.saveData();
    }

    /**
     * 数据1：拒绝好友关系的通知
     * 数据入库
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void saveData() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        lpLinkDatabases.saveData(this.getNewSql());
    }

    public void init() {
        this.apply_id = new String();
        this.friend_id = new String();
        this.my_id = new String();
        this.newSql = new String();
    }

    public String getapply_id() {
        return apply_id;
    }

    public void setapply_id(String apply_id) {
        this.apply_id = apply_id;
    }


    /**
     * 获取用户信息
     * 信息1：好友ID
     * 信息2：我的ID
     */
    public void setSql() {
        String sql = "select * from apply_for_friend where apply_id = \'" + this.getapply_id() + "\';";
        try {
            linkDatabases lpLinkDatabases = new linkDatabases();
            ResultSet resultSet = lpLinkDatabases.getInformation(sql);
            while (resultSet.next()) {
                //获取申请者ID
                this.setMy_id(resultSet.getString("my_number"));
                //获取我的ID
                this.setFriend_id(resultSet.getString("friend_number"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public String getNewSql() {
        return newSql;
    }

    public void setNewSql() {
        //第一次
        /*this.newSql = "insert into apply_return(return_id, my_id, friend_id, can_make) " +
                "values (1, \'" + this.getMy_id() + "\', \'" + this.getFriend_id() + "\', 0);";*/
        //第1+n次
        this.newSql = "insert into apply_return(my_id, friend_id, can_make) " +
                "values (\'" + this.getMy_id() + "\', \'" + this.getFriend_id() + "\', 0);";
    }

    private String apply_id = null;

    private String my_id = null;

    private String friend_id = null;

    private String newSql = null;
}
