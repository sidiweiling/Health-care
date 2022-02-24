<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/06/29
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.*,entity.*,java.util.List" %>
<!DOCTYPE html>
<html>
<head></head>
<jsp:useBean id="conn" class="net.chat.BaseConn" scope="page" />
<%
	UserDao userDao = new UserDao();
	User user = userDao.findbyusername(session.getAttribute("user_mail").toString());
	String appString = "";
	String patient_id = user.getPatient_id();
	try {
		AppointmentDao appDao =  new AppointmentDao();
		List<Appointment> appList = appDao.findbypatient_id(patient_id);
		for(int i = 0; i < appList.size(); i++) {
			String app_id = appList.get(i).getApp_id().toString();
			String app_time = appList.get(i).getApp_time().toString();
			String doctor_id = appList.get(i).getDoctor_id();
			DoctorDao doctorDao = new DoctorDao();
			Doctor doctor = doctorDao.findbydoctor_id(doctor_id);
			String doctor_name = doctor.getName();
			String doctor_department = doctor.getDepartment();
			int isLive = appList.get(i).getLive();
			UserDao talkToDao = new UserDao();
			User talkTo = userDao.findbydoctor_id(doctor_id);
			if(isLive == 1){
				appString = appString + "<tr align=\"center\"><td width=\"33%\"><span class=\"doctor_msg\">" 
							+ doctor_name + "</span></td><td width=\"34%\">" + doctor_department + 
							"<br/></td><td width=\"33%\"><a href=\"chat.jsp?talkTo=" + talkTo.getUsername() + "\">咨询</a><br/>"
							+ "<a href=\"removeOrder.jsp?app_id=" + app_id + "\">取消</a></td></tr>";
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();
		out.println("系统维护");
	} finally {
		conn.closeDB();
	}
%>
<style>
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
</style>
<body>
<jsp:include page="top.jsp"></jsp:include>
<h3 style="font-size: 14px;">确认预约信息</h3>
<table width="100%" border=0 cellspacing="0" cellpadding="0" class="listdoctor2">
    <tbody>
    <tr>
        <td width = 33% align="center" class="bold" style="border-bottom:none;">医生姓名</td>
        <td width = 34% align="center" class="bold" style="border-bottom: none;">医院科室</td>
        <td width = 33% align="center" class="bold" style="border-bottom: none;">操作</td>
    </tr>
    </tbody>
</table>
<table border="0" cellspacing="0" cellpadding="0" class="listdoctor">
    <tbody id = "appString">
    </tbody>
</table>
</body>
<script language="javascript">
	var AppString = document.getElementById("appString");
	var appString = '<%=appString%>';
  	AppString.innerHTML = appString;
</script>
</html>
