<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/06/30
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page  import="java.sql.*,dao.*,entity.*" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="css/ydc.css"/>
    <jsp:useBean id="conn" class="net.chat.BaseConn" scope="page" />
	<%
	//在这里我们定义了一个msgString，它保存页面显示的所有聊天信息，最后在show.jsp
	//中显示这个字符串
	UserDao userDao = new UserDao();
	User user = userDao.findbyusername(session.getAttribute("user_mail").toString());
	String doctor_id = user.getDoctor_id();
	String doctor_name = "";
	String doctor_telephone = "";
	try {
		DoctorDao doctorDao = new DoctorDao();
		Doctor doctor = doctorDao.findbydoctor_id(doctor_id);
		doctor_name = doctor.getName();
		doctor_telephone = doctor.getTelephone();
	} catch (Exception ex) {
		ex.printStackTrace();
		out.println("系统维护");
	} finally {
		conn.closeDB();
	}
	%>
</head>

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
                                <li><a href="manageConsultationServlet" style="text-align: center">问诊记录</a> </li>
                                <li><a href="doctorInfo.jsp" style="text-align: center;">我的信息</a></li>
                                <li><a href="manageMessagesServlet" style="text-align: center">我的咨询</a> </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="ydc-column ydc-column-8">
                <div class="ydc-tabPanel ydc-tabPanel-release">
                    <div class="ydc-release-tab-head">
                        <ul>
                            <li class="hit">账号设置</li>
                        </ul>
                    </div>
                    <div class="ydc-pane">
                        <div class="ydc-pane ydc-pane-clear" style="display: block;">
                            <div class="ydc-reg-form-group clearfix">
                                <label>姓名</label>
                                <div class="ydc-reg-form-input">
                                    <label id ="name">加载中……</label>
                                </div>
                            </div>
                            <div class="ydc-reg-form-group clearfix">
                                <label>手机号码</label>
                                <div class="ydc-reg-form-input">
                                    <label id="telephone">加载中……</label>
                                </div>
                            </div>
                            <div class="ydc-reg-form-group clearfix" style="margin-top: 40px;">
                                <div class="ydc-reg-form-button" style="margin-left: 255px;">
                                    <a href="modifyInfo.jsp" style="text-align: center;">修改</a>
                                </div>
                                <div class="ydc-reg-form-button" style="margin-left: 255px">
                                    <a href="login.jsp" style="text-align: center;">登出</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script language="javascript">
	var Name = document.getElementById("name");
	var Telephone = document.getElementById("telephone");
  	Name.innerText = '<%=doctor_name%>';
  	Telephone.innerText = '<%=doctor_telephone%>';
</script>
</body>
</html>
