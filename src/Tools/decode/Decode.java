package Tools.decode;

import Tools.Password.*;

public class Decode {

    public Decode(String email, String ciphertext) {
        // 初始化
        this.init(email, ciphertext);

        // 解密
        this.setText();
    }

    /**
     * 初始化函数
     * @param email
     * @param ciphertext
     */
    public void init(String email, String ciphertext) {
        this.ciphertext = new String();
        this.email = new String();
        this.text = new String();
        this.setEmail(email);
        this.setCiphertext(ciphertext);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getText() {
        return text;
    }

    public void setText() {

        String password = new password(this.getEmail()).getPrivatePassword();

        String myUnicode = "";

        myUnicode = new GetYourUnicode(this.getCiphertext()).getMyUnicode();
        System.out.println("我的unicode：" + myUnicode);

        String unicodeII = new DIYCode().reUnicodeForMe(myUnicode);
        System.out.println("转化为unicode："+ unicodeII);

        String string = new DIYCode().decodeUnicode(unicodeII);
        System.out.println("明文：" + string);

        this.text = string;
    }

    private String email = null;

    private String ciphertext = null;

    private String text = null;

    public static void main(String[] args) {
        Decode decode = new Decode("liupeng.0@outlook.com", "社会主义核心价值观,公正,平等,平等,敬业,敬业,社会主义核心价值观,爱国,平等,平等,诚信,爱国,社会主义核心价值观,公正,平等,平等,敬业,和谐,社会主义核心价值观,公正,平等,平等,敬业,民主,富强,法治,友善,");
        System.out.println(decode.getText());
    }
}
