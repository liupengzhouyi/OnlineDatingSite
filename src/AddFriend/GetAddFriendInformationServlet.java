package AddFriend;

import AddFriend.JavaBean.ApplyFriend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetAddFriendInformationServlet")
public class GetAddFriendInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        HttpSession httpSession = request.getSession();
        this.setMyId((String) httpSession.getAttribute("user_id"));
        this.setFriendId(request.getParameter("friend_id"));
        this.setFriendName(request.getParameter("friend_name"));
        //检验用户输入


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

    public boolean verifyInput() {
        boolean returnKey = false;


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
