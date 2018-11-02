<%--
  Created by IntelliJ IDEA.
  User: liupeng
  Date: 2018/11/2
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>加密交友网（beat）</title>
</head>
<body>

<%
  HttpSession httpSession = request.getSession();
  String user_id = "-1";
  if (httpSession.isNew()) {
    user_id = "-1";
  } else {
    user_id = (String) httpSession.getAttribute("user_id");
  }
%>

<table width="1300" border="0" align="center">
  <tr>
    <td colspan="2" style="background-color:#7479ff;">
      <h1 align="center">加密交友网（Beat版本）</h1>
    </td>
  </tr>
</table>
<a href="/Login/index.jsp"><button type="button">登录</button></a>
<a href="/Signin/index.jsp"><button type="button">注册</button></a>
</body>
</html>