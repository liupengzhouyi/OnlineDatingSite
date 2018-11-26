<%--
  Created by IntelliJ IDEA.
  User: liupeng
  Date: 2018/11/26
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>

    <%
        HttpSession httpSession = request.getSession();
        String user_id = (String) httpSession.getAttribute("user_id");
    %>

    <form action="/Setting/Password/GetNewPasswordServlet" method="post">
        <table border="1">
            <tr>
                <th colspan="2">
                    修改密码
                </th>
            </tr>
            <tr>
                <th>
                    请输入你的密码：
                </th>
                <td>
                    <input type="password" name="ord_password">
                </td>
            </tr>
            <tr>
                <th>
                    请输入你的新密码：
                </th>
                <td>
                    <input type="password" name="new_passwordI">
                </td>
            </tr>
            <tr>
                <th>
                    请再一次输入你的新密码：
                </th>
                <td>
                    <input type="password" name="new_passwordII">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="reset" value="重置">
                </td>
                <td>
                    <input type="submit" value="确定">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
