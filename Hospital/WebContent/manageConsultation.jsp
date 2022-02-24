<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/07/02
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                                <li><a href="manageConsultationServlet" style="text-align: center;">问诊记录</a> </li>
                                <li><a href="manageMessagesServlet" style="text-align: center">我的咨询</a> </li>
                                <li><a href="doctorInfo.jsp" style="text-align: center;">我的信息</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="ydc-column ydc-column-8">
                <div class="ydc-tabPanel ydc-tabPanel-release">
                    <div class="ydc-release-tab-head">
                        <ul>
                            <li class="hit">咨询信息</li>
                        </ul>
                    </div>
                        <table width="100%" border=0 cellspacing="0" cellpadding="0" class="listdoctor">
                            <tbody>
                            <tr>
                                <td width = 20% class="bold" style="border-bottom:none;">病人姓名</td>
                                <td width = 20% align="center" class="bold" style="border-bottom: none;">记录 </td>
                                <td width = 20% align="center" class="bold" style="border-bottom: none;">性别</td>
                                <td width = 20% align="center" class="bold" style="border-bottom: none;">预约编号</td>
                                <td width = 20% align="center" class="bold" style="border-bottom: none;">操作</td>
                            </tr>
                            </tbody>
                        </table>
                        <table border="0" cellspacing="0" cellpadding="0" class="listdoctor">
                            <tbody>
                            <jsp:useBean id="consultations" scope="request" type="java.util.List"/>
                            <c:forEach var="consultation" items="${consultations}" varStatus="s">
                            <tr align="center"
                                <c:if test="${s.index%2==1}">
                                    <c:out value="class=jg_td_bg"></c:out>
                                </c:if>>
                                <td width="20%" align="left">
                                    <span class="doctor_msg">${consultation.getValue().getName()}</span>
                                </td>
                                <td width="20%">
                                    <c:choose>
                                        <c:when test="${consultation.getKey().getText_rec()!=null}">
                                            ${consultation.getKey().getText_rec()}
                                        </c:when>
                                        <c:otherwise>
                                            无
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td width="20%">
                                    <c:choose>
                                        <c:when test="${consultation.getValue().getSex() == 1}">
                                            女
                                        </c:when>
                                        <c:otherwise>
                                            男
                                        </c:otherwise>
                                    </c:choose>

                                </td>
                                <td width="20%">
                                        ${consultation.getKey().getApp_id()}
                                </td>
                                <td width="20%">
                                    <form action="modifyConsultation.jsp">
                                        <input name="con_id" value="${consultation.getKey().getCon_id()}" style="display: none">
                                        <input type="submit" name="submit" value="编写记录">
                                    </form>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
