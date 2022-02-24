<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page  import="dao.*" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style>
        .order-table{
            border:3px solid #f4f4f4;
            border:none;
            padding:5px 0px 0px 10px;
        }
        table.listdoctor2{
            line-height: 18px;
            width: 92%;
            margin:auto;
            border:1px solid #c0e8fd;
            background-color: #f4f9fe;
            font-size: 14px;
        }
        table.listdoctor2 td{
            padding: 10px 10px;
        }
        .bold{
            font-weight: bold;
        }
        table.listdoctor{
            line-height: 18px;
            width: 92%;
            margin:auto;
        }
        table.listdoctor td{
            padding:15px 5px;
            border-bottom: 1px dashed#cee7f4;
        }
        table.listdoctor .jg_td_bg{
            background-color: #f3f7f9;
        }
        span{
            border:0pt none;
            margin:0pt;
            padding: 0pt;
        }
        span.doctor_msg{
            padding-left: 20px;
            display: block;
        }
        #search-bar{
            border: 1px solid #91c7e0;
            margin-bottom: 10px;
        }
        .tiaojian {
            background-color: #f2f7fd;
            border: none;
            padding: 5px 10px 0;
        }
        ul,li{
            list-style: none;
        }
        .nav{
            width:960px;
            margin:auto;
            background-color: #ffffff;
        }
        .jieguo {
            border: 3px solid #f4f4f4;
            border-top: none;
            padding: 5px 0px 10px 0px;
        }
        .search {
            width: 700px;
            margin: 15px auto;
            padding: 8px 4px;
            text-align: center;
        }
        .btn_wr {
            width: 97px;
            height: 34px;
            vertical-align: top;
            _padding-top: 1px;
            display: inline-block;
        }
    </style>
</head>
<script>
    function setValue(value) {
        document.getElementById("department").setAttribute("value",value);
    }
    var x =0
    function search(){
    	
    }
</script>
<%
    String flag=(String)request.getAttribute("flag");
    if("null".equals(flag)){%>
    <script>
        alert("找不到医生！");
	</script>
<% }%>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="nav">
<h2 style="text-align: center;">预约系统</h2>
<div class="search">
    <table>
        <tbody>
            <tr>
            	<form action="doctorListServlet" method="post">
                <input type="text" name="keyword" style="width: 360px;height: 24px">
                <span class="btn_wr">
                    <input type="submit" value="查找" >
                </span>
                </form>
            </tr>
        </tbody>
    </table>
</div>
<div id="search-bar" style="_height:90px;" *overflow:hidden; _overflow:visible>
    <ul class="tiaojian">
        <li style="border-bottom: none;">
            <input id="department" style="display: none;" value=""></input>
            <span class="bold">科室：</span>
            &nbsp;<a href="doctorListServlet?de=all">全部</a>
             <a href="doctorListServlet?de=内科">内科</a>
            &nbsp;<a href="doctorListServlet?de=外科">外科</a>
             <a href="doctorListServlet?de=妇科">妇科</a>
             <a href="doctorListServlet?de=儿科">儿科</a>
        </li>
    </ul>
</div>
    <c:out value="${keshi}"></c:out>
<div class = "jieguo">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listdoctor2">
    <tbody>
    <tr>
        <td width = 25% class="bold" style="border-bottom: none;">医生序号</td>
        <td width = 25% align="center" class="bold" style="border-bottom:none;">医生姓名</td>
        <td width = 25% align="center" class="bold" style="border-bottom: none;">医院科室</td>
        <td width = 25% align="center" class="bold" style="border-bottom: none;">操作</td>
    </tr>
    </tbody>
</table>
<table border="0" cellspacing="0" cellpadding="0" class="listdoctor">
    <tbody>
    <jsp:useBean id="doctorList" scope="request" type="java.util.List"/>
    <c:forEach var="doctor" items="${doctorList}">
            <tr align="center"
                    <c:if test="${s.index%2==1}">
                        <c:out value="class=jg_td_bg"></c:out>
                    </c:if>>
                <td width="25%" align="left">
                        ${doctor.getDoctor_id()}
                </td>
                <td width="25%">
                    <span class="doctor_msg">${doctor.getName()}</span>
                </td>
                <td width="25%">
                    ${doctor.getDepartment()}
                </td>
                <td width="25%">
                    <form action="orderDoctorServlet" method="post">
                        <input name="doctor_id" value="${doctor.getDoctor_id()}" style="display: none">
                        <input name="department" value="${doctor.getDepartment()}" style="display: none">
                        <input type="submit" name="yuyue" value="立刻预约">
                    </form>
                </td>
            </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</div>
</body>
</html>
