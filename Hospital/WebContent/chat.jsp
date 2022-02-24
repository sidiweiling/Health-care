<%--
  Created by IntelliJ IDEA.
  User: 11862
  Date: 2020/06/25
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page  import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<style type="text/css">
.talk_con {
	width: 600px;
	height: 500px;
	border: 1px solid #666;
	margin: 50px auto 0;
	background: #f9f9f9;
}

.talk_show {
	width: 580px;
	height: 420px;
	border: 1px solid #666;
	background: #fff;
	margin: 10px auto 0;
	overflow: auto;
}

.talk_input {
	width: 580px;
	margin: 10px auto 0;
}

.whotalk {
	width: 80px;
	height: 30px;
	float: left;
	outline: none;
}

.talk_word {
	width: 420px;
	height: 26px;
	padding: 0px;
	float: left;
	margin-left: 10px;
	outline: none;
	text-indent: 10px;
}

.talk_sub {
	width: 56px;
	height: 30px;
	float: left;
	margin-left: 10px;
}

.atalk {
	margin: 10px;
}

.atalk span {
	display: inline-block;
	background: #0181cc;
	border-radius: 10px;
	color: #fff;
	padding: 5px 10px;
}

.btalk {
	margin: 10px;
	text-align: right;
}

.btalk span {
	display: inline-block;
	background: #ef8201;
	border-radius: 10px;
	color: #fff;
	padding: 5px 10px;
}
</style>
<jsp:useBean id="conn" class="net.chat.BaseConn" scope="page" />
<%
	//在这里我们定义了一个msgString，它保存页面显示的所有聊天信息，最后在show.jsp
	//中显示这个字符串
	String msgString = "";
	String talkTo = request.getParameter("talkTo");
	String talkFrom = session.getAttribute("user_mail").toString();
	session.setAttribute("_TALKTO", talkTo);
	try {
		String sql = "select top 15 * from message where (msgFrom=? and msgTo=?) or (msgFrom=? and msgTo=?)  order by msgTime DESC";
		PreparedStatement ps = conn.preparedStatement(sql);
		ps.setString(1, talkTo);
		ps.setString(2, talkFrom);
		ps.setString(3, talkFrom);
		ps.setString(4, talkTo);
		ResultSet rs = conn.executeQuery();
		//下面while循环里的程序就是实现控制聊天信息显示格式的功能
		rs.afterLast();
		while (rs.previous()) {
			String msgTime = rs.getTimestamp("msgTime").toLocaleString();
			String msgFrom = rs.getString("msgFrom");//获取聊天信息发送者
			String msgTo = rs.getString("msgTo");//获取聊天信息接收者
			String msgContent = rs.getString("msgContent");//获取聊天信息内容
			if (talkFrom.equals(msgFrom)) {
				msgString = msgString + "<div class=\"btalk\"><span>" + msgContent + "</span></div>";
			} else {
				msgString = msgString + "<div class=\"atalk\"><span>" + msgContent + "</span></div>";
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();
		out.println("系统维护");
	} finally {
		conn.closeDB();
	}
%>
<script type="text/javascript">
        window.onload = function(){
            var Words = document.getElementById("words");
            var Who = document.getElementById("who");
            var TalkWords = document.getElementById("msg");
            var TalkSub = document.getElementById("talksub");
			var Fresh = document.getElementById("fresh");
		
		function talkSub() {
			//定义空字符串
			var str = "";
			if (TalkWords.value == "") {
				// 消息为空时弹窗
				alert("消息不能为空");
				return;
			}
			str = '<div class="btalk"><span>' + TalkWords.value
					+ '</span></div>';
			Words.innerHTML = Words.innerHTML + str;
		}
		Fresh.onclick = function(){
			var msg = '<%=msgString%>';
			Words.innerHTML =  msg ;
		}
		 
	}
    
</script>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div class="talk_con">
		<div class="talk_show" id="words"></div>
		<div class="talk_input">
			<form name="chatForm" method="post" action="sendMsg.jsp"
			target="theIframe" onsubmit="setTimeout(function(){window.location.reload();},10)">
				<input type="text" class="talk_word" name="msg"> 
				<input type="submit" value="发送" class="talk_sub" name="Submit" id = "talksub" >
				<input type="button" class="talk_sub" value="刷新" id="fresh" onclick = "setTimeout(function(){window.location.reload();},10)">
				<iframe id="theIframe" name="theIframe" width="0" height="0"></iframe>
			</form>
		</div>
	</div>
</body>
<script language="javascript">
	var Words = document.getElementById("words");
	var msg = '<%=msgString%>';
  	Words.innerHTML = msg;
</script>
</html>

