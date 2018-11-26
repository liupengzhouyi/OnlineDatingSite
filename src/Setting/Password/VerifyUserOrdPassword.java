package Setting.Password;

import javafx.css.CssParser;

public class VerifyUserOrdPassword {

    public VerifyUserOrdPassword(String password, String passwordValue) {

        //初始化函数
        this.init();
        //赋值
        this.setPassword(password);
        this.setPasswordValue(passwordValue);
        //得到判断值
        this.createKey();
    }

    /**
     * 初始化函数
     */
    public void init() {
        this.password = new String();
        this.passwordValue = new String();
    }

    /**
     * 验证你的密码
     */
    public void createKey() {
        String passwordValue = this.getPassword().hashCode() + "";
        this.setPassword(passwordValue);
        if (this.getPasswordValue().equals(this.getPassword())) {
            this.setKey(true);
        } else {
            this.setKey(false);
        }
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    private String password = null;

    private String passwordValue = null;

    private boolean key = false;


    public static void main(String[] args) {
        VerifyUserOrdPassword verifyUserOrdPassword = new VerifyUserOrdPassword("123456", "1450575459");
        System.out.println(verifyUserOrdPassword.isKey());
    }
}
