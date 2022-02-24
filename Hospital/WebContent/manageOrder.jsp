<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/06/30
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" language="java" %>
<%@ page  import="java.sql.*" %>
<%@ page import="dao.*,entity.*,java.util.List" %>
<%@ page  pageEncoding = "UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="css/ydc.css"/>
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
</head>
<jsp:useBean id="conn" class="net.chat.BaseConn" scope="page" />
<%
	//在这里我们定义了一个msgString，它保存页面显示的所有聊天信息，最后在show.jsp
	//中显示这个字符串
	UserDao userDao = new UserDao();
	User user = userDao.findbyusername(session.getAttribute("user_mail").toString());
	String appString = "";
	String doctor_id = user.getDoctor_id();
	try {
		AppointmentDao appDao =  new AppointmentDao();
		List<Appointment> appList = appDao.findbydoctor_id(doctor_id);
		for(int i = 0; i < appList.size(); i++) {
			String app_id = appList.get(i).getApp_id().toString();
			String app_time = appList.get(i).getApp_time().toString();
			String patient_id = appList.get(i).getPatient_id();
			int isLive = appList.get(i).getLive();
			PatientDao patientDao = new PatientDao();
			Patient patient = patientDao.findbypatient_id(patient_id);
			String patient_name = patient.getName();
			String patient_telephone = patient.getTelephone();
			if(isLive == 1){
				appString = appString + "<tr align=\"center\"><td width=\"10%\" align=\"left\"><span class=\"doctor_msg\">"
							+ patient_name + "</span></td><td width=\"12%\">"+ patient_telephone + "</td><td width=\"24%\">"
							+ app_time + "</td><td width=\"10%\"><a href=\"confirmOrder.jsp?appId=" + app_id + "\">确认</a></td></tr>";
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();
		out.println("系统维护");
	} finally {
		conn.closeDB();
	}
%>
<body>
<section>
    <div class="ydc-content-slide ydc-body">
        <div class="ydc-flex">
            <div class="ydc-column ydc-column-2">
                <div class="ydc-menu">
                    <ul>
                        <li class="ydc-menu-item">
                                    <span class="ydc-menu-sub-title" style="text-align: center;">
                                        <i>后台中心</i>
                                    </span>
                            <ul>
                                <li><a href="manageOrder.jsp" style="text-align: center;">预约管理</a></li>
                                <li><a href="doctorInfo.jsp" style="text-align: center;">我的信息</a></li>
                                <li><a href="manageMessagesServlet" style="text-align: center;">我的咨询</a> </li>
                                <li><a href="manageConsultationServlet" style="text-align: center">问诊记录</a> </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="ydc-column ydc-column-8">
                <div class="ydc-tabPanel ydc-tabPanel-release">
                    <div class="ydc-release-tab-head">
                        <ul>
                            <li class="hit">预约信息</li>
                        </ul>
                    </div>
                    <table width="100%" border=0 cellspacing="0" cellpadding="0" class="listdoctor">
                        <tbody>
                        <tr>
                            <td width = 10% class="bold" style="border-bottom:none;">病人姓名</td>
                            <td width = 12% align="center" class="bold" style="border-bottom: none;">手机号码</td>
                            <td width = 24% align="center" class="bold" style="border-bottom: none;">预约时间</td>
                            <td width = 10% align="center" class="bold" style="border-bottom: none;">操作</td>
                        </tr>
                        </tbody>
                    </table>
                    <table border="0" cellspacing="0" cellpadding="0" class="listdoctor">
                        <tbody id = "apps">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
</body>

<script language="javascript">
	var Apps = document.getElementById("apps");
	var appString = '<%=appString%>';
  	Apps.innerHTML = appString;
</script>
</html>
