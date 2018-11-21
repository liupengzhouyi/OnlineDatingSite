package AddFriend;

import AddFriend.FindFriend.FindFriend;
import AddFriend.FindFriend.IsMyFriend;
import AddFriend.JavaBean.ApplyFriend;
import Tools.LinkDatabase.linkDatabases;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetAddFriendInformationServlet")
public class GetAddFriendInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        HttpSession httpSession = request.getSession();
        this.setMyId((String) httpSession.getAttribute("user_id"));
        this.setFriendId(request.getParameter("friend_id"));
        this.setFriendName(request.getParameter("friend_name"));
        //检验用户输入
        if (this.verifyInput()) {
            //全部输入，继续
            //验证用户账号正确性

            //是否有这个账号
            try {
                if (new FindFriend(this.getFriendId()).friendIsExist()) {

                    //是否已经拥有这个好友
                    if (new IsMyFriend(this.getMyId(), this.getFriendId()).isMyFriend()) {
                        //已经拥有了
                        //跳转页面
                        System.out.println("您已经拥有这个好友");
                        response.sendRedirect("/");
                    } else {
                        //没有这个好友
                        //准备数据哭语句
                        //好友申请SQL
                        String sqlI = "insert into my_friends(friend_id, my_id, my_friend_id, my_name, my_friend_name, friend_date, friendship)" +
                                " values (1, \'" + this.getMyId() + "\', \'" + this.getFriendId() + "\', \'\', \'" + this.getFriendName() + "\', now(), 1);";
                        //好友申请通知好SQL
                        String sqlII = "insert into apply_for_friend(friend_number, my_number, my_sql, new_key) " +
                                "values (\'" + this.getMyId() + "\', \'" + this.getFriendId() + "\', \'\\\'" + sqlI + "\\\', 1);";

                        ////////////////////////
                        System.out.println(sqlI);
                        System.out.println(sqlII);
                        ////////////////////////
                        //发送添加好友请求
                        //this.saveData(sqlI);
                        //this.saveData(sqlII);
                        System.out.println("添加好友信息已发出，等待好友验证！");
                        response.sendRedirect("/");
                    }
                } else {
                    //没有这个账号
                    //跳转语句
                    System.out.println("没有这个账号！");
                    response.sendRedirect("/");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                //在数据库查询是否有该账号的时候出错

            } catch (SQLException e) {
                e.printStackTrace();
                //在数据库查询是否有该账号的时候出错

            }
        } else {
            // 有没有出入的数据

            //页面跳转到错误页面
            response.sendRedirect("/");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化函数
     */
    public void init() {
        this.applyFriend = new ApplyFriend();
        this.friendId = new String();
        this.friendName = new String();
        this.myId = new String();
    }

    /**
     *判断数据的输入
     * @return
     */
    public boolean verifyInput() {
        boolean returnKey = false;
        if (this.getFriendId().isEmpty()) {
            return returnKey;
        } else {
            if (this.getFriendName().isEmpty()) {
                return returnKey;
            } else {
                returnKey = true;
            }
        }
        return returnKey;
    }

    /**
     * 执行SQL语句，保存数据
     * @param sql
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void saveData(String sql) throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();
        lpLinkDatabases.saveData(sql);
    }


    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    private ApplyFriend applyFriend = null;

    private String friendId = null;

    private String friendName = null;

    private String myId = null;
}
