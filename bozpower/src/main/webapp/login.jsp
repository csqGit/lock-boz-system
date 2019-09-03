<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 这是一个html5 doctype声明 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>智能锁后台管理系统</title>
<link rel="shortcut icon" href="<%=path%>/images/lock.png"
	type="image/gif" />
<link rel="stylesheet" href="<%=path%>/css/layui.css" media="all">
<link rel="stylesheet" href="<%=path %>/css/login.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/js/layui.js" charset="utf-8"></script>

<style type="text/css">

#footer{margin-top: 20px;}

</style>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<div id="logo"></div>
			<div id="heading">
				<div id="title">智能锁后台管理系统</div>
			</div>
		</div>
		<div id="main">
			<div id="mainPanel" style="width: 450px; height: 320px;">
				<form class="layui-form" id="myForm" action="<%=path%>/login"
					method="post">
					<div id="mainTitle">
						管理员登录<span>Login Admin</span>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-block">
							<input type="text" name="username" value="${username }"
								lay-verify="title" autocomplete="off" placeholder="请输入用户名"
								class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
						<div class="layui-input-block">
							<input type="password" name="password" value="${password }"
								lay-verify="title" autocomplete="off" placeholder="请输入密码"
								class="layui-input">
						</div>
					</div>

					<!--  <div class="layui-form-item">
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">电压等级</label>
		      <div class="layui-input-block">
		        <select name="modules" lay-verify="required" lay-search="">
		          <option value="">直接选择或搜索选择</option>
		          <option value="1">layer</option>
		          <option value="2">form</option>
		          <option value="3">layim</option>
		          <option value="4">element</option>
		          <option value="5">laytpl</option>
		          <option value="6">upload</option>
		          <option value="7">laydate</option>
		          <option value="8">laypage</option>
		        </select>
		      </div>
		    </div>
		  </div> -->

					<div class="layui-form-item">
						<label class="layui-form-label">验证码</label>
						<div class="layui-input-inline">
							<input type="text" placeholder="请输入验证码" id="inputCode"
								lay-verify="yzm" autocomplete="off" class="layui-input">
						</div>

						<div class="layui-input-inline yzm" id="verificationCode"></div>
					</div>

					<div class="layui-form-item">
						<div class="rememberMe">
		      <c:if
							test="${requestScope.err != null || requestScope.err != '' }">
							<span style="color: red">${requestScope.err }</span>
						</c:if>
		    </div> 

						<div class="forgetPass">
							<a href="<%=path %>/forgetpass.jsp">忘记密码</a>
						</div>
					</div>

					<div class="layui-form-item">
						<button class="layui-btn" lay-submit="" lay-filter="submitForm">登&nbsp;&nbsp;录</button>
					</div>
					<div id="error">
						<!-- 显示表单验证的出错信息 -->

						
					</div>
				</form>
			</div>
			<div id="footer">
				<div id="text"></div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var clientHeight = document.documentElement.clientHeight;
		var clientWidth = document.documentElement.clientWidth;

		var header = document.getElementById("header");
		var main = document.getElementById("main");
		var mainPanel = document.getElementById("mainPanel");
		var mainPanelWidthStr = mainPanel.style.width;
		var mainPanelHeightStr = mainPanel.style.height;
		var mainPanelWidth = mainPanelWidthStr.substring(0,
				mainPanelWidthStr.length - 2);
		var mainPanelHeight = mainPanelHeightStr.substring(0,
				mainPanelHeightStr.length - 2);
		header.style.height = (clientHeight / 2) + 'px';
		main.style.height = (clientHeight / 2) + 'px';
		//alert(mainPanelWidth)
		//mainPanel.style.left = ((clientWidth - mainPanelWidth) / 2) + 'px';
		//mainPanel.style.top = (-(clientHeight - mainPanelHeight) / 2) + 'px';
		mainPanel.style.top = (-( mainPanelHeight) / 2) + 'px';
	</script>

	<script type="text/javascript" src="<%=path%>/js/footer.js"></script>
	<script type="text/javascript" src="<%=path%>/js/login.js"></script>
</body>
</html>