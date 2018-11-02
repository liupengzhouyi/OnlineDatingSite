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


        init(request);

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
