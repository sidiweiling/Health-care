<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style>
        .order-table{
            border:3px solid #f4f4f4;
            border:none;
            padding:5px 0px 0px 10px;
        }
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
        .bold{
            font-weight: bold;
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
        span{
            border:0pt none;
            margin:0pt;
            padding: 0pt;
        }
        span.doctor_msg{
            padding-left: 20px;
            display: block;
        }
        #search-bar{
            border: 1px solid #91c7e0;
            margin-bottom: 10px;
        }
        .tiaojian {
            background-color: #f2f7fd;
            border: none;
            padding: 5px 10px 0;
        }
        ul,li{
            list-style: none;
        }
        .nav{
            width:960px;
            margin:auto;
            background-color: #ffffff;
        }
        .jieguo {
            border: 3px solid #f4f4f4;
            border-top: none;
            padding: 5px 0px 10px 0px;
        }
    </style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="nav">
<h2 style="text-align: center;">预约系统</h2>
<div id="search-bar" style="_height:90px;" *overflow:hidden; _overflow:visible>
    <ul class="tiaojian">
        <li style="border-bottom: none;">
            <span class="bold">科室：</span>
            &nbsp;<a href="javascript:void(0)">全部</a>
            &nbsp;<a href="javascript:void(0)">内科</a>
        </li>
    </ul>
</div>
<div class = "jieguo">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listdoctor2">
    <tbody>
    <tr>
        <td width = 10% class="bold" style="border-bottom:none;">医生姓名</td>
        <td width = 12% align="center" class="bold" style="border-bottom: none;">医院科室</td>
        <td width = 34% align="center" class="bold" style="border-bottom: none;">网上预约条件</td>
        <td width = 24% align="center" class="bold" style="border-bottom: none;">可预约时间</td>
        <td width = 10% align="center" class="bold" style="border-bottom: none;">操作</td>
    </tr>
    </tbody>
</table>
<table border="0" cellspacing="0" cellpadding="0" class="listdoctor">
    <tbody>
    <tr align="center">
        <td width="10%" align="left">
            <span class="doctor_msg">李医生</span>
        </td>
        <td width="12%">
            广州中山医院<br/>血管外科<br/>
        </td>
        <td width="34%">
            疾病要求：只接收以下疾病: 颈动脉狭窄、糖尿病足、下肢静脉血栓、静脉曲张
        </td>
        <td width="24%">
            星期四上午
        </td>
        <td width="10%">
            <a href="javascript:void(0)">立刻预约</a>
        </td>
    </tr>
    <tr align="center" class="jg_td_bg">
        <td width="10%" align="left">
            <span class="doctor_msg">李医生</span>
        </td>
        <td width="12%">
            广州中山医院<br/>血管外科<br/>
        </td>
        <td width="34%">
            疾病要求：只接收以下疾病: 颈动脉狭窄、糖尿病足、下肢静脉血栓、静脉曲张
        </td>
        <td width="24%">
            星期四上午
        </td>
        <td width="10%">
            <a href="javascript:void(0)">立刻预约</a>
        </td>
    </tr>
    </tbody>
</table>
</div>
</div>
</body>
</html>
