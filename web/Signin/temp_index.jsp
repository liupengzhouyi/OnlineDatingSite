<%--
  Created by IntelliJ IDEA.
  User: liupeng
  Date: 11/3/2018
  Time: 11:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Signin.GetProvinces" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    <option value=0>
                        北京市
                    </option>
                    <option value=1>
                        天津市
                    </option>
                    <option value=2>
                        上海市
                    </option>
                    <option value=3>
                        重庆市
                    </option>
                    <option value=4>
                        河北省
                    </option>
                    <option value=5>
                        山西省
                    </option>
                    <option value=6>
                        辽宁省
                    </option>
                    <option value=7>
                        吉林省
                    </option>
                    <option value=8>
                        黑龙江省
                    </option>
                    <option value=9>
                        江苏省
                    </option>
                    <option value=10>
                        浙江省
                    </option>
                    <option value=11>
                        安徽省
                    </option>
                    <option value=12>
                        福建省
                    </option>
                    <option value=13>
                        江西省
                    </option>
                    <option value=14>
                        山东省
                    </option>
                    <option value=15>
                        河南省
                    </option>
                    <option value=16>
                        湖北省
                    </option>
                    <option value=17>
                        湖南省
                    </option>
                    <option value=18>
                        广东省
                    </option>
                    <option value=19>
                        海南省
                    </option>
                    <option value=20>
                        四川省
                    </option>
                    <option value=21>
                        贵州省
                    </option>
                    <option value=22>
                        云南省
                    </option>
                    <option value=23>
                        陕西省
                    </option>
                    <option value=24>
                        甘肃省
                    </option>
                    <option value=25>
                        青海省
                    </option>
                    <option value=26>
                        台湾省
                    </option>
                    <option value=27>
                        内蒙古自治区
                    </option>
                    <option value=28>
                        广西壮族自治区
                    </option>
                    <option value=29>
                        西藏自治区
                    </option>
                    <option value=30>
                        宁夏回族自治区
                    </option>
                    <option value=31>
                        新疆维吾尔自治区
                    </option>
                    <option value=32>
                        香港特别行政区
                    </option>
                    <option value=33>
                        澳门特别行政区
                    </option>
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



