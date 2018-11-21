package AddFriend.OperationApplication;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AgreeApply {

    public AgreeApply(String apply_id) throws SQLException, ClassNotFoundException {
        //初始化
        init();
        //获取好友申请ID
        this.setapply_id(apply_id);
        //获取添加好友SQL语句
        // 同时获取我的ID，好友的ID
        this.setSql();
        //准备好友添加成功通知的SQL语句
        this.setNewSql();
        //数据入库
        this.saveData();
    }


    /**
     * 数据1：同意好友关系
     * 数据2：同意好友关系的通知
     * 数据入库
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void saveData() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        lpLinkDatabases.saveData(this.getSql());
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

    public String getSql() {
        return sql;
    }

    /**
     * 获取SQL语句
     */
    public void setSql() {
        String sqlI = "";
        String str = "select * from apply_for_friend where apply_id = \'" + this.getapply_id() + "\';";
        try {
            linkDatabases lpLinkDatabases = new linkDatabases();
            ResultSet resultSet = lpLinkDatabases.getInformation(str);
            while (resultSet.next()) {
                System.out.println("数据库信息：" + resultSet.toString());
                sqlI = resultSet.getString("my_sql");
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
        this.sql = sqlI;
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
        this.newSql = "insert into apply_return(my_id, friend_id, can_make) " +
                "values (\'" + this.getMy_id() + "\', \'" + this.getFriend_id() + "\', 1);";
        System.out.println("添加好友关系语句：");
        System.out.println(this.newSql);
    }

    private String apply_id = null;

    private String sql = null;

    private String my_id = null;

    private String friend_id = null;

    private String newSql = null;
}
