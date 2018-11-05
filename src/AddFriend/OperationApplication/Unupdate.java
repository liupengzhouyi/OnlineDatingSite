package AddFriend.OperationApplication;

import Tools.LinkDatabase.linkDatabases;

import java.sql.SQLException;

public class Unupdate {

    public Unupdate(String apply_id) throws SQLException, ClassNotFoundException {
        //获取操作ID
        this.setApply_id(apply_id);
        //初始化
        // 构造 SQL 语句
        init();
        //操作
        this.operation();
    }

    public void init() {
        String string = "update apply_for_friend set new_key = 0 where apply_id = " + this.getApply_id() + ";";
        this.setSql(string);
    }

    public void operation() throws ClassNotFoundException, SQLException {
        //过去数据库连接对象
        linkDatabases lpLinkDatabases = new linkDatabases();
        //更新数据库数据
        lpLinkDatabases.updateData(this.getSql());
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    private String apply_id;

    private String sql;
}
