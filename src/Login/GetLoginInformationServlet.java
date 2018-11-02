package Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetLoginInformationServlet")
public class GetLoginInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化函数
        init(request);
        //判断信息
        boolean key = this.veriftInformation();
        if (key) {
            //Session 保存 用户ID
            this.saveUserIdToSession(request);
            //进入主页
            response.sendRedirect("/Main/index.jsp");
        } else {
            //跳转错误信息页面
            //登录过程出错

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化函数，获取用户输入
     * @param request
     */
    public void init(HttpServletRequest request) {
        this.user_id = new String();
        this.user_verify_code = new String();
        this.user_verify_code_by_session = new String();
        // 获取用户ID
        this.setUser_id(request.getParameter("user_id"));
        // 获取用户密码
        this.setUser_password(request.getParameter("user_password"));
        // 获取验证码
        this.setUser_verify_code(request.getParameter("user_verify_code"));
        //获取Session中的验证码
        this.getSessionVeriftCode(request);
    }

    /**
     * 获取用户当前输入的验证码的值
     * @param request
     */
    public void getSessionVeriftCode(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        this.setUser_verify_code_by_session((String) httpSession.getAttribute(""));
    }

    /**
     * 判断用户输入信息是否正确
     * @return
     */
    public boolean veriftInformation() {
        boolean returnKey = false;
        //验证是否有没有出入的内容
        boolean key_I = this.veriftInput();
        //是否验证码正确
        boolean key_II = this.veriftVeriftCode();
        //是否密码正确
        boolean key_III = this.veriftPassword();
        //返回信息
        if (key_I && key_II && key_III) {
            returnKey = true;
        }
        return returnKey;
    }

    /**
     * 判断用户是否完全输入
     * @return
     */
    public boolean veriftInput() {
        boolean returnKey = false;
        //判断用户ID输入
        if (this.getUser_id().toString() == "") {
            //没有输入用户ID

        } else {
            //判断用户验证码输入
            if (this.getUser_verify_code().toString() == "") {
                //用户没有输入验证码

            } else {
                //判断用户密码输入
                if ((this.getUser_password() + "").toString() == "") {
                    //用户没有输入密码

                } else {
                    returnKey = true;
                }
            }
        }
        return returnKey;
    }

    /**
     * 判断用户输入的验证码是否正确
     * @return
     */
    public boolean veriftVeriftCode() {
        boolean returnKey = false;
        if (this.getUser_verify_code_by_session().equals(this.getUser_verify_code())) {
            returnKey = true;
        } else {
            //验证码错误

        }
        return returnKey;
    }

    /**
     * 判断用户输入的密码是否正确
     * @return
     */
    public boolean veriftPassword() {
        boolean returnKey = false;
        /*if () {

        } else {

        }*/
        return returnKey;
    }

    /**
     * 保存当前用户ID到Session
     * @param request
     */
    public void saveUserIdToSession(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("user_id", this.getUser_id());
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password.hashCode();
    }

    public String getUser_verify_code() {
        return user_verify_code;
    }

    public void setUser_verify_code(String user_verify_code) {
        this.user_verify_code = user_verify_code;
    }

    public int getKind_of_error() {
        return kind_of_error;
    }

    public void setKind_of_error(int kind_of_error) {
        this.kind_of_error = kind_of_error;
    }

    public String getUser_verify_code_by_session() {
        return user_verify_code_by_session;
    }

    public void setUser_verify_code_by_session(String user_verify_code_by_session) {
        this.user_verify_code_by_session = user_verify_code_by_session;
    }

    //获取的用户ID
    private String user_id = null;
    //密码值
    private int user_password = -1;
    //获取用户验证码
    private String user_verify_code = null;
    //错误信息
    private int kind_of_error = -1;
    //从Session获取的 验证码信息
    private String user_verify_code_by_session = null;
}
