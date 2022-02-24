<%@ page language="java" import="java.util.*,net.chat.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
  </head>
  <%@ page import="dao.*,entity.*,java.util.List" %>
  <body>
   <%
   String app_id = request.getParameter("appId");
   AppointmentDao appointmentDao = new AppointmentDao();
   Appointment appointment = new Appointment();
   appointment.setApp_id(app_id);
   appointment.setLive(0);
   appointmentDao.update_live(appointment);
   ConsultationDao consultationDao = new ConsultationDao();
   consultationDao.app(app_id, null, null);
   response.sendRedirect("manageOrder.jsp");
   %>
  </body>
</html>