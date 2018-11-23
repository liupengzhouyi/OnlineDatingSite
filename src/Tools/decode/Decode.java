package Tools.decode;

import Tools.Password.*;

public class Decode {

    public Decode(String email, String ciphertext) {
        // 初始化
        this.init(email, ciphertext);

        // 解密

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
}
