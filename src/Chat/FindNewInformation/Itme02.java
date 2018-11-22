package Chat.FindNewInformation;

public class Itme02 {

    public Itme02(String my_friend_id, String datetime, String information) {
        this.my_friend_id = my_friend_id;
        this.datetime = datetime;
        this.information = information;
    }

    public String getMy_friend_id() {
        return my_friend_id;
    }

    public void setMy_friend_id(String my_friend_id) {
        this.my_friend_id = my_friend_id;
    }

    public String getDatetimr() {
        return datetime;
    }

    public void setDatetimr(String datetimr) {
        this.datetime = datetimr;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    private String my_friend_id = "";
    private String datetime = "";
    private String information = "";
}
