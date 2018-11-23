package Tools.CreateCiphertext;

import Tools.Password.CreateValues;
import Tools.Password.DIYCode;
import Tools.Password.Encryption;
import Tools.Password.password;

public class CreateCiphertext {

    public CreateCiphertext(String user_email, String text) {
        //初始化函数
        this.init(user_email, text);

        //加密
        this.mainFunction();

    }

    public void mainFunction() {
        String user = "liupeng.0@outlook.com";
        String code = "核心价值观";

        System.out.println("用户：" + user);

        System.out.println("明文：" + code);

        String password = new password(this.getUser_email()).getPrivatePassword();
        System.out.println("私钥：" + password);

        DIYCode diyCode = new DIYCode();

        String unicode = diyCode.unicode(this.getText());
        System.out.println("转化为unicode：" + unicode);

        String myUnicode = diyCode.unicodeForMe(unicode);
        System.out.println("我的unicode：" + myUnicode);

        String yourUnicode = new CreateValues(password, myUnicode).getCoreValues();
        System.out.println("与密钥结合：" + yourUnicode);

        String valuees = new Encryption(yourUnicode).getText();
        System.out.println("你的密文：" + valuees);

        this.setCiphertext(valuees);
    }

    public void init(String user_email, String text) {
        this.user_email = new String();
        this.ciphertext = new String();
        this.text = new String();
        this.setUser_email(user_email);
        this.setText(text);
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    private String user_email = null;

    private String text = null;

    private String ciphertext = null;
}
