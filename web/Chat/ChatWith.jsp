<%--
  Created by IntelliJ IDEA.
  User: liupeng
  Date: 2018/11/6
  Time: 7:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
<head>
    <title>Chat</title>
</head>
<%
    //获取好友账号
    String friend_id_to_chat = request.getParameter("friend_id_to_chat");
    //判断好友
    HttpSession httpSession = request.getSession();
    httpSession.setAttribute("friend_id_to_chat", friend_id_to_chat);
    System.out.println(friend_id_to_chat);
%>
<body>
<form action="/Chat/GetChatInformationServlet" method="post">
    <table border="1">
        <tr>
            <th colspan="2">
                <h2>
                    <center>
                        发送给<%="  " + friend_id_to_chat%>
                    </center>
                </h2>
            </th>
        </tr>
        <tr>
            <td colspan="2">
                <textarea name="charInformation"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="1">
                <input type="reset" value="放弃">
            </td>
            <td colspan="1">
                <input type="submit" value="发送">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
