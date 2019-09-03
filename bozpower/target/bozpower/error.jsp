<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误页面</title>
<style type="text/css">
*{margin: 0; padding: 0;}
	#main{width: 100%; height: 500px;  }
	#main img{border-radius: 10px; border: 1px solid white;}
	#flush{position: relative; top: -40%; text-align: center;}
	a:link{color: rgb(35, 189, 29); text-decoration: none;}
	a:active{color: rgb(35, 189, 29); text-decoration: none;}
	a:hover{color: green;}
</style>
</head>
<body>
<!-- <h1>访问出错了，刷新重试</h1> -->
<div id="main"><img src="<%=path %>/images/error.jpg" width="100%" height="100%"/>
	<div id="flush"><a href="<%=path %>/user/User_login_main.jsp" target="MainFrame">点击刷新</a></div>
</div>
</body>
<script type="text/javascript">
document.getElementById('main').style.height=document.documentElement.clientHeight + 12+"px";
document.getElementById('main').style.width=window.screen.width-232+"px";
</script>
</html>