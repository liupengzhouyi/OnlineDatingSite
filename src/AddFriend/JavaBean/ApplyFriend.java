package AddFriend.JavaBean;

public class ApplyFriend {
    public void init() {
        this.myId = new String();
        this.friendId = new String();
        this.friendName = new String();
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
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

    //我的编号
    private String myId = null;
    //好友的编号
    private String friendId = null;
    //好友的昵称
    private String friendName = null;
}
