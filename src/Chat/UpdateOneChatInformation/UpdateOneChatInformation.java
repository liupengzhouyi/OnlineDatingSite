package Chat.UpdateOneChatInformation;

import Chat.FindNewInformation.GetTieNumber;
import Tools.LinkDatabase.linkDatabases;

import java.sql.SQLException;

public class UpdateOneChatInformation {

    public UpdateOneChatInformation(String user_id, String chat_id, String tie_number) throws SQLException, ClassNotFoundException {
        //初始化函数
        this.init();
        this.setUser_id(user_id);
        this.setChat_id(chat_id);
        this.setNumber(tie_number);
        // 准备更新 聊天信息提示表 中的 数据
        this.createSqlII();
        this.updateInformation(this.getSql());
        // 准备更新 聊天信息表 中的数据
        //设置提示标号
        this.getTieNumber();
        this.createSqlI();
        this.updateInformation(this.getSql());
    }

    /**
     * 初始化函数
     */
    public void init() {
        this.user_id = new String();
        this.chat_id = new String();
        this.number  = new String();
    }

    public void getTieNumber() throws SQLException, ClassNotFoundException {
        int num = Integer.parseInt(this.getNumber());
        GetTieNumber getTieNumber = new GetTieNumber(this.getUser_id(),num);
        String tempNum = getTieNumber.getTieNumber();
        this.setTie_id(tempNum);
    }

    /**
     * 设置聊天信息  提示  表中的数据更新 的 SQL语句
     */
    public void createSqlI() {
        String str = "update char_tie set is_new = 0 where tie_id = " + this.getTie_id() + ";";
        this.setSql(str);
        System.out.println("设置聊天信息  提示  表中的数据更新 的 SQL语句" + str);
    }

    /**
     * 数据库更新方法
     * @param sql
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateInformation(String sql) throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        lpLinkDatabases.updateData(sql);
    }


    /**
     * 设置更新聊天信息表中的数据
     */
    public void createSqlII() {
        String str = "update char_information set is_new = 0 where char_id = " + this.getChat_id() + ";";
        this.setSql(str);
        System.out.println("设置更新聊天信息表中的数据: " + str);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getTie_id() {
        return tie_id;
    }

    public void setTie_id(String tie_id) {
        this.tie_id = tie_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String user_id = null;

    private String chat_id = null;

    private String tie_id = null;

    private String sql = null;

    private String number = null;
}
