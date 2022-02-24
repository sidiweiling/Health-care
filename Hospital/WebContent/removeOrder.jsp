<%@ page language="java" import="java.util.*,net.chat.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发送消息</title>    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
  </head>
  <%@ page import="dao.*,entity.*,java.util.List" %>
  <body>
   <%
   String app_id = request.getParameter("app_id");
   AppointmentDao appointmentDao = new AppointmentDao();
   appointmentDao.delete(app_id);
   response.sendRedirect("orderList.jsp");
   %>
  </body>
</html>