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
        <th>
            好友账号
        </th>
        <th>
            同意
        </th>
        <th>
            拒绝
        </th>
    </tr>
    <tr>
        <td>

        </td>
        <td>
            <a href="/AddFriend/OperationApplication/OperationApplyServlet?apply_id=11&operation=1"><button>同意</button></a>
        </td>
        <td>
            <a href="/AddFriend/OperationApplication/OperationApplyServlet?apply_id=11&operation=0"><button>拒绝</button></a>
        </td>
    </tr>
</table>
</body>
</html>
