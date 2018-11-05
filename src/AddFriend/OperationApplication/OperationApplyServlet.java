package AddFriend.OperationApplication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OperationApplyServlet")
public class OperationApplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        this.init(request);
        //操作好友申请
        try {
            this.doOperation();
            //消除最新状态
            this.unupdate();
        } catch (SQLException e) {
            //数据添加申请好友信息出错

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //数据添加申请好友信息出错

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化函数
     * @param request
     */
    public void init(HttpServletRequest request){
        this.user_id = new String();
        this.apply_id = new String();
        this.operation = new String();
        HttpSession httpSession = request.getSession();
        //设置用户ID
        this.setUser_id((String) httpSession.getAttribute("user_id"));
        //设置好友申请ID
        this.setApply_id(request.getParameter("apply_id"));
        //获取操作态度
        this.setOperation(request.getParameter("operation"));
    }

    //操作数据
    public void doOperation() throws SQLException, ClassNotFoundException {
        if (this.getOperation().equals("1")) {
            //同意好友申请
            new AgreeApply(this.getApply_id());
        } else {
            //不同意好友申请
            new DonotAgreeApply(this.getApply_id());
        }
    }

    /**
     * 消除最新状态
     */
    public void unupdate() {
        try {
            new Unupdate(this.getApply_id());
        } catch (SQLException e) {
            //数据库连接失败，失败地点：消除好友申请最新状态

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //数据库连接失败，失败地点：消除好友申请最新状态

            e.printStackTrace();
        }
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    //我的ID
    private String user_id = null;
    //申请好友ID
    private String apply_id = null;
    //是否同意
    private String operation = null;
}
