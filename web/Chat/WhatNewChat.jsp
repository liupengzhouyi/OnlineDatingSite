<%@ page import="Chat.FindNewInformation.Itme02" %>
<%@ page import="Chat.FindNewInformation.GetUserNewInformation" %>
<%@ page import="Chat.UpdateOneChatInformation.UpdateOneChatInformation" %>
<%@ page import="Tools.decode.Decode" %>
<%@ page import="Tools.UserEmail.GetUserEmailByYourId" %><%--
  Created by IntelliJ IDEA.
  User: liupeng
  Date: 2018/11/22
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的新信息</title>
</head>
<body>
<%
    //获取新消息编号
    String number = request.getParameter("number");
    //获取用户账号
    String user_id = "";
    System.out.println("number:" + number);
    HttpSession httpSession = request.getSession();
    user_id = (String) httpSession.getAttribute("user_id");
    //获取新信息
    GetUserNewInformation getUserNewInformation = new GetUserNewInformation(user_id);
    Itme02 itme02 = getUserNewInformation.getChatInformation(Integer.parseInt(number) - 1);
    //消除信息的未读状态
    String chat_id = itme02.getChat_id();
    UpdateOneChatInformation updateOneChatInformation = new UpdateOneChatInformation(user_id, chat_id, number);
    // 解密
    // 获取用户邮箱
    String email = "";
    GetUserEmailByYourId getUserEmailByYourId = new GetUserEmailByYourId(user_id);
    email = getUserEmailByYourId.getEmail();
        System.out.println("获取解密用户的电子邮件地址");
    //开始解密
    Decode decode = new Decode(email, itme02.getInformation());
    //设置明文
    itme02.setInformation(decode.getText());
    System.out.println("解密后的明文：" + itme02.getInformation());
%>

<table border="1">
    <tr>
        <th>
            好友账号
        </th>
        <td>
            <%=itme02.getMy_friend_id()%>
        </td>
    </tr>
    <tr>
        <th>
            时间
        </th>
        <td>
            <%=itme02.getDatetime()%>
        </td>
    </tr>
    <tr>
        <th>
            信息
        </th>
        <td>
            <%=itme02.getInformation()%>
        </td>
    </tr>
    <tr>
        <th>
            是否回复
        </th>
        <td>

        </td>
    </tr>
</table>

</body>
</html>
