package Setting.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetNewPasswordServlet")
public class GetNewPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        this.init();
        //获取用户名
        this.createUserId(request);
        //获取你的旧密码
        this.createOrdPassword(request);
        //获取你的新密码
        this.createNewPassword(request);
        try {
            //验证你的旧密码
            GetUserPasswordValue getUserPasswordValue = new GetUserPasswordValue(this.getUser_id());
            System.out.println(this.getOrd_password());
            System.out.println(getUserPasswordValue.getPasswordValue());
            VerifyUserOrdPassword verifyUserOrdPassword = new VerifyUserOrdPassword(this.getOrd_password(),getUserPasswordValue.getPasswordValue());
            boolean ordPasswordKey = verifyUserOrdPassword.isKey();
            if (ordPasswordKey) {
                //判断你的新密码
                VerifyTwoPasswords verifyTwoPasswords = new VerifyTwoPasswords(this.getNew_passwordI(), this.getNew_passwordII());
                boolean newPasswordKey = verifyTwoPasswords.isKey();
                if(newPasswordKey) {
                    System.out.println("可以修改密码");
                    UpdateUserPassword updateUserPassword = new UpdateUserPassword(this.getUser_id(), this.getNew_passwordI());
                    updateUserPassword.updatePasswordValue();
                    System.out.println("重新登录");

                    response.sendRedirect("/index.jsp");
                } else {
                    System.out.println("你的密码不一致");
                    response.sendRedirect("/Setting/Password/ErrorPage/passwordErrorII.jsp");
                }
            } else {
                System.out.println("你的密码不正确");
                response.sendRedirect("/Setting/Password/ErrorPage/passwordErrorI.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 获取用户
     * @param request
     */
    public void createUserId(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        this.setUser_id((String) httpSession.getAttribute("user_id"));
    }

    /**
     * 获取旧密码
     * @param request
     */
    public void createOrdPassword(HttpServletRequest request) {
        this.setOrd_password(request.getParameter("ord_password"));
    }

    /**
     * 获取新密码
     * @param request
     */
    public void createNewPassword(HttpServletRequest request) {
        this.setNew_passwordI(request.getParameter("new_passwordI"));
        this.setNew_passwordII(request.getParameter("new_passwordII"));
    }

    public void init() {
        this.new_passwordI = new String();
        this.new_passwordII = new String();
        this.user_id = new String();
        this.ord_password = new String();
    }

    public String getOrd_password() {
        return ord_password;
    }

    public void setOrd_password(String ord_password) {
        this.ord_password = ord_password;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String ord_password = null;

    private String new_passwordI = null;

    private String new_passwordII = null;

    private String user_id = null;
}
