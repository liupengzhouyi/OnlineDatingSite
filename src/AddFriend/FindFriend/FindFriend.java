package AddFriend.FindFriend;

public class FindFriend {

    public FindFriend(String friendId) {
        init(friendId);


    }

    public void init(String friendId) {
        this.setFriendId(friendId);
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    private String friendId = null;

}
