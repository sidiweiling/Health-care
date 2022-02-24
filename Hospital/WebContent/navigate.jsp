<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/06/25
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导航</title>
    <meta charset="UTF-8">
    <title>导航</title>
    <style type="text/css">
        div {
            display: block;
        }
        .sites {
            width: 700px;
            margin: 15px auto;
            padding: 4px;
            background: #fff;
            border: 2px solid #f2f2f2;
        }
        .sites dl {
            height: 36px;
            line-height: 36px;
            margin: 0;
            display: block;
        }
        .sites dt {
            width: 60px;
        }
        .sites dt, .sites dd {
            text-align: center;
            display: block;
            float: left;
        }
        .sites dl.alt {
            background: #f8f8f8;
            border-top: 1px solid #f2f2f2;
            border-bottom: 1px solid #f2f2f2;
        }
        body {
            font: normal 14px Tahoma, Simsun;
            margin: 0;
            padding: 25px 0 0;
            vertical-align: baseline;
            background: #fff;
        }
        a {
            color: #0d63e3;
        }
        .consult{
            position: fixed;
            right: -100px;
            transition: right 1s;
        }
        .consult:hover{
            right:0;
        }
        .consult-content{
            width: 50px;
            height: 80px;
            text-align: center;
            border: #ebebeb solid 1px;
            padding: 0px 0px;
            line-height: 40px;
            display: block;
            float:left;
        }
        .doctor-list{
            width: 100px;
            border: #ebebeb solid 1px;
            float:left;
            background: #ffffff;
        }
        .list{
            overflow: hidden;
            width: 90%;
            line-height: 40px;
            margin:0px auto;
            border-bottom:dashed 1px #ebebeb;
        }
        .list a{
            display: block;
            color: #000000;
        }
        .list span{
            float: left;
            width:100px;
            text-align: center;
            padding-right: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="sites">
    <dl>
        <dt>内科</dt>
        <dd>
            <a href="https://baike.baidu.com/item/%E6%85%A2%E6%80%A7%E4%B9%99%E5%9E%8B%E7%97%85%E6%AF%92%E6%80%A7%E8%82%9D%E7%82%8E/4285659?fromtitle=%E4%B9%99%E8%82%9D&fromid=202614&fr=aladdin" target="_blank">乙 肝</a>
        </dd>
        <dd>
            <a href="https://baike.baidu.com/item/%E7%B3%96%E5%B0%BF%E7%97%85" target="_blank">糖尿病</a>
        </dd>
        <dd>
            <a href="https://baike.baidu.com/item/%E7%94%B2%E7%8A%B6%E8%85%BA%E5%8A%9F%E8%83%BD%E4%BA%A2%E8%BF%9B%E7%97%87/1122468?fromtitle=%E7%94%B2%E4%BA%A2&fromid=408503" target="_blank">甲 亢</a>
        </dd>
    </dl>
    <dl class="alt">
        <dt>外科</dt>
        <dd>
            <a href="https://baike.baidu.com/item/%E6%B0%94%E8%83%B8/111943?fr=aladdin" target="_blank">气 胸</a>
        </dd>
    </dl>
    <dl>
        <dt>心脏</dt>
        <dd>
            <a href="https://baike.baidu.com/item/%E5%86%A0%E7%8A%B6%E5%8A%A8%E8%84%89%E7%B2%A5%E6%A0%B7%E7%A1%AC%E5%8C%96%E6%80%A7%E5%BF%83%E8%84%8F%E7%97%85?fromtitle=%E5%86%A0%E5%BF%83%E7%97%85&fromid=547914" target="_blank">冠心病</a>
        </dd>
    </dl>
    <dl class="alt">
        <dt>妇科</dt>
        <dd>
            <a href="https://baike.baidu.com/item/%E4%B8%8D%E5%AD%95%E7%97%87?fromtitle=%E4%B8%8D%E5%AD%95%E4%B8%8D%E8%82%B2&fromid=1087041" target="_blank">不孕症</a>
        </dd>
    </dl>
    <dl>
        <dt>儿科</dt>
        <dd>
            <a href="https://baike.baidu.com/item/%E5%B0%8F%E5%84%BF%E5%93%AE%E5%96%98" target="_blank">小儿哮喘</a>
        </dd>
    </dl>
    <dl class="alt">
        <dt>肿瘤</dt>
        <dd>
            <a href="https://baike.baidu.com/item/%E8%82%BA%E7%99%8C" target="_blank">肺 癌</a>
        </dd>
    </dl>
    <dl>
        <dt>其他</dt>
        <dd>
            <a href="https://baike.baidu.com/item/%E9%9D%92%E5%85%89%E7%9C%BC" target="_blank">青光眼</a>
        </dd>
    </dl>
</div>
<div>
    <div class="consult">
        <div class="consult-content">
            帮<br/>
            助
        </div>
        <div class="doctor-list">
            <div class="list">
                <a href="${pageContext.request.contextPath}/chat.jsp">
                    <span>咨询</span>
                </a>
                <a href="${pageContext.request.contextPath}/orderDoctor.jsp">
                    <span>预约</span>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
