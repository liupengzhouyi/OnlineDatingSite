<%@ page import="com.sun.net.httpserver.HttpPrincipal" %><%--
  Created by IntelliJ IDEA.
  User: liupeng
  Date: 2018/11/2
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession httpSession = request.getSession();
    String user_id = (String) httpSession.getAttribute("user_id");
%>
<html>
<head>
    <title>添加好友</title>
</head>
<body>
<form action="/AddFriend/GetAddFriendInformationServlet" method="post">
    <table>
        <tr>
            <th colspan="2">
                添加好友
            </th>
        </tr>
        <tr>
            <th colspan="1">
                好友编号
            </th>
            <td colspan="1">
                <input type="text" name="friend_id">
            </td>
        </tr>
        <tr>
            <th colspan="1">
                好友昵称
            </th>
            <td colspan="1">
                <input type="text" name="friend_name">
            </td>
        </tr>
        <tr>
            <td colspan="1">
                <input type="reset" value="重置">
            </td>
            <td colspan="1">
                <input type="submit" value="添加">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
