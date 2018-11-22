<%--
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
    String number = request.getParameter("number");
%>

<h1>
    <center>
        <%=number%>
    </center>
</h1>
</body>
</html>
