<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/07/01
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page  import="java.sql.*,dao.*,entity.*" %>
<html>
<head>
    <title>Title</title>
    <style>
        .form-group{
            margin-bottom: 0;
            margin-top: 20px;
        }
        .form-group label{
            padding-right: 5px;
            font-size: 16px;
            color: #333;
            text-align: right;
            white-space: nowrap;
            width: 255px;
            float: left;
            margin-bottom: 0;
            padding-top: 9px;
            padding-left: 10px;
        }
        .form-input{
            width: 350px;
            white-space: nowrap;
            float: left;
            padding-left: 15px;
            padding-right: 15px;
            position: relative;
            min-height: 1px;
            box-sizing: border-box;
        }
        .form-input label{
            padding-top: 9px;
            line-height: 22px;
            padding-bottom: 5px;
            padding-right: 5px;
            color: #a2a2a2;
            width: 100%;
            font-size: 14px;
            text-align: left;
        }
        .form-button{
            margin-left: 355px;
            float: left;
            position: relative;
            min-height: 1px;
            padding-left: 15px;
            padding-right: 15px;
        }
        .form-button a{
            color: #ffffff;
            background-color: #009999;
            border-color: #009999;
            font-size: 14px;
            width: 101px;
            display:block;
            box-sizing: content-box;
            transition: all .2s;
            padding: 10px 12px;
            border-radius:3px;
            text-decoration: none;
        }
    </style>
    <jsp:useBean id="conn" class="net.chat.BaseConn" scope="page" />
	<%
	//在这里我们定义了一个msgString，它保存页面显示的所有聊天信息，最后在show.jsp
	//中显示这个字符串
	UserDao userDao = new UserDao();
	User user = userDao.findbyusername(session.getAttribute("user_mail").toString());
	String patient_id = user.getPatient_id();
	String patient_name = "";
	String patient_telephone = "";
	String patient_sex = "";
	String patient_address = "";
	try {
		PatientDao patientDao = new PatientDao();
		Patient patient = patientDao.findbypatient_id(patient_id);
		patient_name = patient.getName();
		patient_telephone = patient.getTelephone();
		patient_address = patient.getAddress();
		if(patient.getSex()==0){
			patient_sex = "男";
		}else if(patient.getSex()==1){
			patient_sex = "女";
		}
	} catch (Exception ex) {
		ex.printStackTrace();
		out.println("系统维护");
	} finally {
		conn.closeDB();
	}
	%>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="form-group">
    <label>姓名</label>
    <div class="form-input">
        <label id ="name">
            loading
        </label>
    </div><br/>
</div>
<div class="form-group">
    <label>性别</label>
    <div class="form-input">
        <label id="sex">
            loading
        </label>
    </div><br/>
</div>
<div class="form-group">
    <label>手机号</label>
    <div class="form-input">
        <label id="telephone">
            loading
        </label>
    </div><br/>
</div>
<div class="form-group">
    <label>地址</label>
    <div class="form-input">
        <label id ="address">
            loading
        </label>
    </div><br/>
</div>
<div class="form-group" style="margin-top: 40px">
    <div class="form-button">
        <a href="modifyInfo.jsp" style="text-align: center;">修改</a>
    </div>
    <div class="form-button" style="margin-left: 0">
        <a href="login.jsp" style="text-align: center;">登出</a>
    </div>
</div>
</body>
<script language="javascript">
	var Name = document.getElementById("name");
	var Telephone = document.getElementById("telephone");
	var Address = document.getElementById("address");
	var Sex = document.getElementById("sex");
  	Name.innerText = '<%=patient_name%>';
  	Telephone.innerText = '<%=patient_telephone%>';
  	Address.innerText = '<%=patient_address%>';
  	Sex.innerText = '<%=patient_sex%>';
</script>
</html>
