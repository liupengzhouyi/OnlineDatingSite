package Chat.ShowNewInformation;

import Chat.FindNewInformation.GetUserNewInformation;
import Chat.FindNewInformation.HaveNewInformation;
import Chat.FindNewInformation.Itme02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ShowChatNumbersServlet")
public class ShowChatNumbersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        response.setContentType("text/html;charset=UTF-8");
        //初始化
        this.init(request);
        //获取PrintWrite对象
        PrintWriter printWriter = response.getWriter();
        //显示我的新信息
        try {
            this.showMyNewChatInformation(printWriter);
        } catch (SQLException e) {
            System.out.println("出现错误信息！出错地址：查找你的新信息的时候");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("出现错误信息！出错地址：查找你的新信息的时候");
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
    public void init(HttpServletRequest request) {
        //获取Session对象
        HttpSession httpSession = request.getSession();
        //获取和设置用户的用户的账号
        this.setUser_id((String) httpSession.getAttribute("user_id"));
        System.out.println("查看用户新信息页面" + this.getUser_id());
    }


    public void showMyNewChatInformation(PrintWriter printWriter) throws SQLException, ClassNotFoundException {
        GetUserNewInformation getUserNewInformation = new GetUserNewInformation(this.getUser_id());
        System.out.println(this.getUser_id() + "-------");
        printWriter.println("<table border=\"1\">\n" +
                            "    <tr>\n" +
                            "        <th>\n" +
                            "            信息新编号\n" +
                            "        </th>\n" +
                            "        <th>\n" +
                            "            查看新信息\n" +
                            "        </th>\n" +
                            "    </tr>");
        ArrayList<Itme02> list = getUserNewInformation.getList();
        int i = 1;
        String informationId = "";
        for (Itme02 itme02 : list
             ) {
            informationId = "您的好友" + itme02.getMy_friend_id() + "给您发来一条信息！";
            printWriter.println("<tr>\n" +
                                "        <td>\n" +
                                            i +
                                "        </td>\n" +
                                "        <td>\n" +
                                            informationId +
                                "        </td>\n" +
                                "</tr>");
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

    private String[] ties ;

    private int number = -1;

    private String user_id = "";
}
