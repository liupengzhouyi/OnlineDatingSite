package Setting.Password;

public class VerifyTwoPasswords {

    public VerifyTwoPasswords(String new_passwordI, String new_passwordII) {
        //初始化
        this.init();
        //赋值
        this.setNew_passwordI(new_passwordI);
        this.setNew_passwordII(new_passwordII);
        //验证你的俩个密码是否一致
        this.createKey();
    }

    /**
     * 初始化函数
     */
    public void init() {
        this.new_passwordI = new String();
        this.new_passwordII = new String();
    }

    /**
     * 验证你的俩个密码是否一致
     */
    public void createKey() {
        if (this.getNew_passwordI().equals(this.getNew_passwordII())) {
            this.setKey(true);
        } else {
            this.setKey(false);
        }
    }

    public String getNew_passwordI() {
        return new_passwordI;
    }

    public void setNew_passwordI(String new_passwordI) {
        this.new_passwordI = new_passwordI;
    }

    public String getNew_passwordII() {
        return new_passwordII;
    }

    public void setNew_passwordII(String new_passwordII) {
        this.new_passwordII = new_passwordII;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    private String new_passwordI = null;

    private String new_passwordII = null;

    private boolean key = false;

    public static void main(String[] args) {
        VerifyTwoPasswords verifyTwoPasswords = new VerifyTwoPasswords("123", "1223");
        System.out.println(verifyTwoPasswords.isKey());
    }
}
