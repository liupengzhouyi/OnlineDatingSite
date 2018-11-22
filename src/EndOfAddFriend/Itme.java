package EndOfAddFriend;

public class Itme {
    public Itme(String friend_id, int new_key, int can) {
        this.friend_id = friend_id;
        this.new_key = new_key;
        this.can = can;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public int getNew_key() {
        return new_key;
    }

    public void setNew_key(int new_key) {
        this.new_key = new_key;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

    private String friend_id = null;
    private int new_key = 0;
    private int can = 0;
}
