<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/07/05
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #container{
            width:33%;
            height: 310px;
            position: absolute;
        }

    </style>
</head>
<body>
<div id="container">
    <form action="modifyConsultationServlet" method="post">
        <p>
            问诊记录：<br/>
            <textarea rows="10" cols="30" name="text_rec"></textarea>
        </p>
        <input name="con_id" value="<%=request.getParameter("con_id")%>" style="display: none">
        <p>
            <input type="submit" name="modify" value="修改">
            <input type="reset" value="重置">
        </p>
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
