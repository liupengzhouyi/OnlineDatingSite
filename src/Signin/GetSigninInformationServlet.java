package Signin;

import Tools.CreateUserNumber.GetProviceNumber;
import Tools.CreateUserNumber.createID;
import Tools.GetTime.GetNowTime;
import Tools.LinkDatabase.linkDatabases;

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
        boolean keyOfInput = this.verifyInput();
        if (keyOfInput) {
            //校验数据
            this.verifyData(response);
            //转化数据
            try {
                this.transformationData();
                //生成用户ID
                try {
                    this.setUser_id();
                    System.out.println(this.getUser_id());
                    //数据入库
                    this.saveInformationIntoDatabase();
                    //用户IDSession
                    this.saveSession(request);
                    //操作提示
                    //页面跳转
                    response.sendRedirect("/Success/signin/index.jsp");
                } catch (SQLException e) {
                    //生产用户ID 出错

                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    //生成用户ID出错

                    e.printStackTrace();
                }
            } catch (SQLException e) {
                //省份转数字失败

                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                //省份转数字失败

                e.printStackTrace();
            }

        } else {
           //错误提示：数据输入不完整

        }

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
        String user_privince  = request.getParameter("privince");
        this.setPrivince(user_privince);
        //System.out.println(user_privince);
        //获取用户验证码
        String verify_code = request.getParameter("verify_code");
        this.setVerify_code(verify_code);
        //原来的验证码
        HttpSession httpSession = request.getSession();
        String vcode = (String) httpSession.getAttribute("verify_code_server");
        this.setV_code(verify_code);
    }

    /**
     * 判断输入数据是否 完整
     * @return
     */
    public boolean verifyInput() {
        boolean returnKey = false;
        // 判断电子邮箱
        boolean keyI = this.isNull(this.getEmail());
        //判断姓名
        boolean keyII = this.isNull(this.getName());
        //判断密码
        boolean keyIII = this.isNull(this.getPasswordI());
        //判断重复密码
        boolean keyIV = this.isNull(this.getPasswordII());
        // 判断电话号码
        boolean keyV = this.isNull(this.getPhone());
        //判断性别
        boolean keyVI = this.isNull(this.getSex());
        //判断验证码
        boolean keyVII = this.isNull(this.getV_code());
        if (keyI || keyII || keyIII || keyIV || keyV || keyVI || keyVII) {
            returnKey = false;
        } else {
            returnKey = true;
        }
        return returnKey;
    }

    /**
     * 判断是否为空
     * @param value
     * @return
     */
    public boolean isNull(String value) {
        boolean returnKey = false;
        /*if (value.isEmpty()) {
            returnKey = true;
        }
        if (value.equals("")) {
            returnKey = true;
        }*/
        if (value == "") {
            returnKey = true;
        }
        return returnKey;
    }

    //校验数据正确性
    public void verifyData(HttpServletResponse response) throws IOException {
        boolean keyII = this.verifyVerifyCode();
        if (keyII) {
            //验证码没有问题
        } else {
            boolean keyI = this.verifyPassword();
            if (keyI == false) {
                //错误页面，密码不一致
                response.sendRedirect("/");
            } else{
                //密码一致
            }
        }
    }

    /**
     * 校验密码是否一致
     * @return
     */
    public boolean verifyPassword() {
        boolean returnKey = false;
        if (this.getPasswordI().equals(this.getPasswordII())) {
            returnKey = true;
        }
        if (this.getPasswordI() == this.getPasswordII()) {
            returnKey = true;
        }
        return returnKey;
    }

    /**
     * 校验校验码是否一致
     * @return
     */
    public boolean verifyVerifyCode() {
        boolean returnKey = false;
        if (this.getVerify_code().equals(this.getV_code())) {
            returnKey = true;
        }
        if (this.getVerify_code() == this.getV_code()) {
            returnKey = true;
        }
        return returnKey;
    }

    /**
     * 转化数据
      */
    public void transformationData() throws SQLException, ClassNotFoundException {
        //密码哈希
        this.setPasswordI(this.getPasswordII().hashCode() + "");
        //省份转数字
        //this.setPrivince(new GetProviceNumber().getProviceNumber(this.getPrivince()));
        //性别
        String user_sex = this.getSex();
        if (user_sex.equals("0")) {
            user_sex = "男";
        } else {
            user_sex = "女";
        }
        this.setSex(user_sex);
    }

    /**
     * 保存数据入库
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void saveInformationIntoDatabase() throws ClassNotFoundException, SQLException {
        String data = new GetNowTime().getDate();
        String time = new GetNowTime().getTime();

        String sql = "insert into user" +
                "(user_id, user_name, user_password_value, user_email, " +
                "user_sex, user_phone, user_privince, user_create_date," +
                " user_create_time) " +
                "values (" +
                "\'" + this.getUser_id() + "\', " +
                "\'" + this.getName() + "\', " +
                "\'" + this.getPasswordI() + "\', " +
                "\'" + this.getEmail() + "\', " +
                "\'" + this.getSex() + "\', " +
                "\'" + this.getPhone() + "\', " +
                "\'" + this.getPrivince() + "\', " +
                "\'" + data + "\', " +
                "\'" + time + "\');";

        linkDatabases lpLinkDatabases = new linkDatabases();
        lpLinkDatabases.saveData(sql);
    }

    /**
     * 新ID保存到Session
     * @param request
     */
    public void saveSession(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("new_user_id", this.getUser_id());
    }

    /**
     * 为降低程序耦合性 ， 多出来的工序
     * @param number
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String provinceNumberToProvinceName(String number) throws SQLException, ClassNotFoundException {
        GetProvinces getProvinces = new GetProvinces();
        return getProvinces.getPrivinceByNumber(number);
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id() throws SQLException, ClassNotFoundException {
        //System.out.println("省份名称：" + this.getPrivince());
        //为降低程序耦合性： 多一道工序
        //省份编号-》省份名称
        createID lpCreateID = new createID(this.provinceNumberToProvinceName(this.getPrivince()), this.getSex());
        this.user_id = lpCreateID.getID();
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
    //用户验证码来自于 Session
    private String v_code = null;
    //user_id
    private String user_id = "";
}
