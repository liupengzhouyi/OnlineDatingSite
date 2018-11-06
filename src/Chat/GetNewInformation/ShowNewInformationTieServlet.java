package Chat.GetNewInformation;

import Chat.FindNewInformation.HaveNewInformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ShowNewInformationTieServlet")
public class ShowNewInformationTieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        //response.setContentType("");
        //初始化函数
        this.init(request);
        //获取PrintWirter对象
        PrintWriter printWriter = response.getWriter();
        //输出提示信息
        this.printInformation(printWriter);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 初始化函数
     * @param request
     */
    public void init(HttpServletRequest request) {
        //获取Session对象
        HttpSession httpSession = request.getSession();
        //获取和设置用户的用户的账号
        this.setUser_id((String) httpSession.getAttribute("user_id"));

        try {
            //获取 查看新信息工具类 对象
            HaveNewInformation haveNewInformation = new HaveNewInformation(this.getUser_id());
            //获取设置 新信息数量
            this.setNumber(haveNewInformation.getNumber());
            //获取设置 新信息编号
            this.setTies(haveNewInformation.getTies());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出新信息通知编号
     * @param printWriter
     */
    public void printInformation(PrintWriter printWriter) {
        printWriter.println("<table border=\"1\">\n" +
                            "    <tr>\n" +
                            "        <th>\n" +
                            "            新信息提示\n" +
                            "        </th>\n" +
                            "    </tr>");
        String number = "";
        for (int i = 0;i<this.getNumber();i++) {
            number = this.getTies()[i];
            printWriter.println("<tr>\n" +
                                "        <td>\n" +
                                "            <a href=\"/\"><button>查看</button></a>\n" +
                                "        </td>\n" +
                                "    </tr>");
        }
            printWriter.println("</table>");
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String[] getTies() {
        return ties;
    }

    public void setTies(String[] ties) {
        this.ties = ties;
    }

    private String[] ties ;

    private int number = -1;

    private String user_id = "";
}
