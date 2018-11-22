package Chat.FindNewInformation;

public class Itme02 {

    public Itme02(String chat_id, String my_friend_id, String datetime, String information) {
        this.my_friend_id = my_friend_id;
        this.datetime = datetime;
        this.information = information;
        this.chat_id = chat_id;
    }

    public String getMy_friend_id() {
        return my_friend_id;
    }

    public void setMy_friend_id(String my_friend_id) {
        this.my_friend_id = my_friend_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetimr) {
        this.datetime = datetimr;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    private String chat_id = "";
    private String my_friend_id = "";
    private String datetime = "";
    private String information = "";
}
