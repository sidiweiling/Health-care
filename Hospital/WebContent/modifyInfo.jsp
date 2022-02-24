<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/06/30
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <style type="text/css">
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
    <p>
        注册类型：
        <input type="radio" id="patient" onclick="form1.style.display='';form2.style.display='none'" checked name="xuanze"/>用户
        <input type="radio" id="doctor" onclick="form1.style.display='none';form2.style.display=''" name="xuanze"/>医生
    </p>
    <form id="form1" action="modifyInfoServlet" method="post">
        <input style="display: none" name="status" value="0">
        <p>
            邮&emsp;箱：&emsp;
            <input name="user_mail" type="email" align="left" required="required" placeholder="请输入原邮箱">
        </p>
        <p>
            姓&emsp;名：&emsp;
            <input name="name" type="text" align="left" required="required">
        </p>
        <p>
            性&emsp;别：&emsp;
            <input name="sex" type="radio" id="woman" checked="checked" value="1"/>女
            <input name="sex" type="radio" id="man" value="0"/>男
        </p>
        <p>
            地&emsp;址：&emsp;
            <input name="address" type="text" align="left" required="required">
        </p>
        <p>
            电&emsp;话：&emsp;
            <input name="telephone" type="text" align="left" required="required">
        </p>
        <p>
            密&emsp;码：&emsp;
            <input name="password" type="password" id="pw2" onkeyup="validate1()" align="left" required="required">
        </p>
        <p>
            确认密码：</label>
            <input name="password" type="password" id="pw1" onkeyup="validate1()" align="left" required="required"><span id="tishi1"></span>
        </p>
        <p>
            <input type="reset" value="重置"/>
            <input type="submit" value="修改" id="submit1"/>
        </p>
    </form>
    <form id="form2" action="modifyInfoServlet" method="post" style="display: none">
        <input style="display: none" name="status" value="1">
        <p>
            邮&emsp;箱：&emsp;
            <input name="user_mail" type="email" align="left" required="required" placeholder="请输入原邮箱">
        </p>
        <p>
            姓&emsp;名：&emsp;
            <input name="name" type="text" align="left" required="required">
        </p>
        <p>
            部&emsp;门：&emsp;
            <input name="department" type="text" align="left" required="required">
        </p>
        <p>
            电&emsp;话：&emsp;
            <input name="telephone" type="text" align="left" required="required">
        </p>
        <p>
            密&emsp;码：&emsp;
            <input name="password" type="password" id="pw4" onkeyup="validate2()" align="left" required="required">
        </p>
        <p>
            确认密码：</label>
            <input name="password" type="password" id="pw3" onkeyup="validate2()" align="left" required="required"><span id="tishi2"></span>
        </p>
        <p>
            <input type="reset" value="重置"/>
            <input type="submit" value="修改" id="submit2"/>
        </p>
    </form>
</div>
<script>
    function  validate1() {
        var pw1 = document.getElementById("pw1").value;
        var pw2 = document.getElementById("pw2").value;
        if (pw1 != pw2){
            document.getElementById("tishi1").innerHTML="<span style='color: red; '>密码不同</span>"
            document.getElementById("submit1").disabled = true;
        }
        else{
            document.getElementById("tishi1").innerHTML="<span style='color: green; '>密码相同</span>"
            document.getElementById("submit1").disabled = false;
        }
    }

    function  validate2() {
        var pw1 = document.getElementById("pw3").value;
        var pw2 = document.getElementById("pw4").value;
        if (pw1 != pw2){
            document.getElementById("tishi2").innerHTML="<span style='color: red; '>密码不同</span>"
            document.getElementById("submit2").disabled = true;
        }
        else{
            document.getElementById("tishi2").innerHTML="<span style='color: green; '>密码相同</span>"
            document.getElementById("submit2").disabled = false;
        }
    }

    window.onload=function(){
        var box_hight;
        var box_width;
        var con=document.getElementById("container");

        //位置赋值
        con.style.left="50%";
        con.style.top="50%";
        box_width=con.offsetWidth;  //获取盒子宽度
        box_hight=con.offsetHeight;  //获取盒子高度
        con.style.marginTop=-box_hight/2+"px";
        con.style.marginLeft=-box_width/2+"px";
    }
</script>
</body>
</html>