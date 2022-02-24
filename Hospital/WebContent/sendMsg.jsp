<%@ page language="java" import="java.util.*,net.chat.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发送消息</title>    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
  </head>
  <jsp:useBean id="message" class="net.chat.Message"/>
  <body>
   <%
   request.setCharacterEncoding("utf-8");
   String msgContent=(String)request.getParameter("msg");
   String msgFrom = session.getAttribute("user_mail").toString();
   String msgTo =session.getAttribute("_TALKTO").toString();
   //message对象，用于抽象(保存)一条聊天信息
   message.setMsgFrom(msgFrom);
   message.setMsgTo(msgTo);
   message.setMsgContent(msgContent);
   //将聊天信息保存到数据库中
   message.saveToDataBase();
   //将input.jsp的聊天信息输入框清空
   out.println("<script>parent.document.chatForm.msg.value = \"\"</script>");
   %>
  </body>
</html>