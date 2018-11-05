package AddFriend;

import AddFriend.FindFriend.FindFriend;
import AddFriend.FindFriend.IsMyFriend;
import AddFriend.JavaBean.ApplyFriend;

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
                        response.sendRedirect("/");
                    } else {
                        //没有这个好友
                        //准备数据哭语句
                        String sql = "";
                        //发送添加好友请求

                    }
                } else {
                    //没有这个账号
                    //跳转语句
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
