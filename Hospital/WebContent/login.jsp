<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/06/23
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        #container{
            width:33%;
            height: 310px;
            position: absolute;
        }
        input{
            font-size: inherit;
        }
    </style>
</head>
<body>
<div id="container">
    <form action="loginServlet" method="post">
        <p>
            用户名：
            <input type="text" name="id" value="" align="left" required="required" placeholder="注册邮箱">
        </p>
        <p>
            密&emsp;码：
            <input type="password" name="password" value="" align="left" required="required">
        </p>
        <p>
            <input type="radio" name="status" id="patient" checked="checked" value="0"/>用户
            <input type="radio" name="status" id="doctor" value="1"/>医生
        </p>
        <p>
            <input type="submit" value="登录" name="login">
            <input type="reset" value="重置">
        </p>
    </form>
    <form action="register.jsp">
        <input type="submit" value="注册账户">
    </form>
</div>
<script>
    window.onload=function(){
        var box_hight;
        var box_width;
        var con=document.getElementById("container");

        //位置赋值
        con.style.left="50%";
        con.style.top="50%";
        box_width=con.offsetWidth;  //获取盒子宽度s
        box_hight=con.offsetHeight;  //获取盒子高度
        con.style.marginTop=-box_hight/2+"px";
        con.style.marginLeft=-box_width/2+"px";
    }
</script>
</body>
</html>
