<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true" %>
<html>
<head>
    <title>404错误友好提示页面</title>
    <!-- 3秒钟后自动跳转回首页 -->
    <meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/index.jsp">
</head>
<body>
<img alt="对不起，你要访问的页面没有找到，请联系管理员处理!"
     src="${pageContext.request.contextPath}/img/404Error.jpg"/><br/>
3秒钟后自动跳转回首页，如果没有跳转，请点击<a href="${pageContext.request.contextPath}/login.jsp">这里</a><br/>
异常信息如下:<%=exception.getMessage()%>
</body>
</html>