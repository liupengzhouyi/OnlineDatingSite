<%@ page import="java.io.PrintWriter" %>
<%@ page import="Signin.GetProvinces" %>
<%@ page import="java.sql.SQLException" %>
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
    <title>在线交友网注册</title>
</head>
<body>
<form action="/Signin/GetSigninInformationServlet" method="post">
    <table border="1">
        <tr>
            <th colspan="3">
                用户注册
            </th>
        </tr>
        <tr>
            <th colspan="1">
                请输入你的电子邮件
            </th>
            <td colspan="2">
                <input type="email" name="user_email">
            </td>
        </tr>
        <tr>
            <th colspan="1">
                用户名
            </th>
            <td colspan="2">
                <input type="text" name="user_name">
            </td>
        </tr>
        <tr>
            <th colspan="1">
                设置密码
            </th>
            <td colspan="2">
                <input type="password" name="user_passwordI">
            </td>
        </tr>
        <tr>
            <th colspan="1">
                再次输入密码
            </th>
            <td colspan="2">
                <input type="password" name="user_passwordII">
            </td>
        </tr>
        <tr>
            <th colspan="1">
                个人手机号码
            </th>
            <td colspan="2">
                <input type="text" name="user_phone">
            </td>
        </tr>
        <tr>
            <th colspan="1">
                性别
            </th>
            <td colspan="1">
                <input type="radio" value="0" name="sex" />男
            </td>
            <td colspan="1">
                <input type="radio" value="1" name="sex" />女
            </td>
        </tr>
        <tr>
            <th colspan="1">
                选择你的省份
            </th>
            <td colspan="2">
                <select name="privince">
                    <%
                        GetProvinces getProvinces = new GetProvinces();
                        String[] strings = getProvinces.getPrivices();
                        String[] numbers = getProvinces.getNumber();
                        for (int i=0;i<strings.length;i++) {
                            String name = strings[i];
                            String number = numbers[i];
                    %>
                    <option value="<%=number%>">
                        <%=name%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr >
            <th colspan="1">
                验证码
            </th>
            <th colspan="1">
                <img src="/Tools/VerifyCode/VerifyCodeServlet">
            </th>
            <td colspan="1">
                <button type="button" id="lp_button">看不清，换一张！</button>
            </td>
        </tr>
        <tr>
            <th colspan="1">
                请输入验证码
            </th>
            <td colspan="2">
                <input type="text" name="verify_code">
            </td>
        </tr>
        <tr>
            <td colspan="1.5">
                <button type="reset">重置</button>
            </td>
            <td colspan="1.5">
                <button type="submit">提交</button>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    document.getElementById("lp_button").onclick = function () {
        // 获取img元素
        // 为了让浏览器发送请求到servlet, 所以一定要改变src
        document.getElementsByTagName("img")[0].src = "/Tools/VerifyCode/VerifyCodeServlet"
            + "?time=" + new Date().getTime();
        ///day_1_12/VerifyCodeServlet?time=
    };
</script>
</body>
</html>


