<%--
  Created by IntelliJ IDEA.
  User: liupeng
  Date: 2018/11/26
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    HttpSession httpSession = request.getSession();
    httpSession.invalidate();
    response.sendRedirect("/index.jsp");
%>

</body>
</html>
