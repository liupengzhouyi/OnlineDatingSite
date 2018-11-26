<%--
  Created by IntelliJ IDEA.
  User: liupeng
  Date: 2018/11/26
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>退出登录</title>
</head>
<body>
    <%
        HttpSession httpSession = request.getSession();
        String user_id = (String) httpSession.getAttribute("user_id");
    %>

    <div>
        <center>
            <table>
                <tr>
                    <h1>
                        <center>
                            尊敬的<%=user_id%>编号用户，您确定要离开吗？
                        </center>
                    </h1>
                </tr>

                <tr>
                    <td>
                        <center>
                            <a href="/Main/index.jsp">
                                <button>
                                    取消
                                </button>
                            </a>
                        </center>
                    </td>
                    <td>
                        <center>
                            <a href="/Out/deleteUserSession.jsp">
                                <button>
                                    确定
                                </button>
                            </a>
                        </center>
                    </td>
                </tr>
            </table>
        </center>
    </div>

</body>
</html>
