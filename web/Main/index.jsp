<%--
  Created by IntelliJ IDEA.
  User: liupeng
  Date: 2018/11/2
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td>
            <a href="/AddFriend/GetApplyFriendInformation/GetApplyFriendInformationServlet">
                新的好友申请消息
            </a>
        </td>
        <td>
            <a href="/EndOfAddFriend/GetAddFriendFruitServlet">
                我的好友申请结果
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="/AddFriend/index.jsp">
                添加好友
            </a>
        </td>
        <td>
            <a href="/DeleteFriend/DeleteFriendServlet">
                删除好友
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="/Chat/GetNewInformation/ShowNewInformationTieServlet">
                新信息
            </a>
        </td>
        <td>
            <a href="/Chat/ShowNewInformation/ShowChatNumbersServlet">
                我的新信息
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="/Chat/FindFriendToChat/ChatServlet">
                聊天
            </a>
        </td>
        <td>
            <a href="/Friends/SelectFriends/SelectMyFriendsServlet">
                我的好友
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="/Setting/index.jsp">
                设置
            </a>
        </td>
        <td>
            <a href="/Out/index.jsp">
                退出
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="">
                联系我们
            </a>
        </td>
        <td>
            <a href="/About/about.jsp">
                关于
            </a>
        </td>
    </tr>
</table>
</body>
</html>
