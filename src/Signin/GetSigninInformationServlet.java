package Signin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetSigninInformationServlet")
public class GetSigninInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //初始化函数
        this.init();
        //赋值
        this.setValue(request);
        //判断输入格式

        //准备数据

        //数据入库

        //操作提示
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化函数
     */
    public void init() {
        this.email = new String();
        this.name = new String();
        this.verify_code = new String();
        this.passwordI = new String();
        this.passwordII = new String();
        this.sex = new String();
        this.phone = new String();
        this.v_code = new String();
        this.privince = new String();
    }

    /**
     * 获取From表单的数据
     * @param request
     */
    public void setValue(HttpServletRequest request) {
        //获取用户电子邮件
        String user_email = request.getParameter("user_email");
        this.setEmail(user_email);
        //获取用户用户名
        String user_name = request.getParameter("user_name");
        this.setName(user_name);
        //获取用户性别
        String user_sex = request.getParameter("sex");
        if (user_sex.equals("0")) {
            user_sex = "男";
        } else {
            user_sex = "女";
        }
        this.setSex(user_sex);
        //获取用户密码I
        String user_passwordI = request.getParameter("user_passwordI");
        this.setPasswordI(user_passwordI);
        //获取用户密码II
        String user_passwordII = request.getParameter("user_passwordII");
        this.setPasswordII(user_passwordII);
        //获取用户电话号码
        String user_phone = request.getParameter("user_phone");
        this.setPhone(user_phone);
        //获取用户省份number
        String user_privince = "";
        String privince_number = request.getParameter("privince");

        //user_privince = new getPrivices().getPrivinceByNumber(privince_number);


        this.setPrivince(user_privince);
        System.out.println(user_privince);
        //获取用户验证码
        String verify_code = request.getParameter("verify_code");
        this.setVerify_code(verify_code);
        //原来的验证码
        HttpSession httpSession = request.getSession();
        String vcode = (String) httpSession.getAttribute("verify_code_server");
        this.setV_code(verify_code);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPasswordI() {
        return passwordI;
    }

    public void setPasswordI(String passwordI) {
        this.passwordI = passwordI;
    }

    public String getPasswordII() {
        return passwordII;
    }

    public void setPasswordII(String passwordII) {
        this.passwordII = passwordII;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public String getV_code() {
        return v_code;
    }

    public void setV_code(String v_code) {
        this.v_code = v_code;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    //用户ID
    private String userID = null;
    //用户Email
    private String email = null;
    //用户名
    private String name = null;
    //用户性别
    private String sex = null;
    //用户密码
    private String passwordI = null;
    //用户重复密码
    private String passwordII = null;
    //用户电话号码
    private String phone = null;
    //用户省份
    private String privince = null;
    //用户验证码
    private String verify_code = null;
    //用户验证码来自于Session
    private String v_code = null;
}
