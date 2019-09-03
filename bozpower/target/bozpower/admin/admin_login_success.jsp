<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<title>智能锁后台管理</title>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/layui.css" />
	<link rel="shortcut icon" href="<%=path %>/images/lock.png" type="image/gif" />
	<script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/layui.js"></script>
	
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="logo" style="">
		<%-- <img alt="智能锁" src="<%=path %>/images/logo.png" style="width: 40px; height:50px; " /> --%>
			<div id="title">${obj.companyId.companyname }智能锁后台管理系统</div>
		</div>
		
		<!-- <div id="menu">
			<div id="menu_container">
				<ul id="menu_items">
					<li class="menu_item on" style="border-radius:8px 0 0 8px" onmouseout="this.style.backgroundColor=''" onmouseover="this.style.backgroundColor='#77D1F6';this.style.borderRadius='8px 0 0 8px'"><a>系统管理</a></li>
					<li class="menu_item" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'"><a>学生管理</a></li>
					<li class="menu_item" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'"><a>新闻管理</a></li>
					<li class="menu_item" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'"><a>网盘管理</a></li>
					<li class="menu_item" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'"><a>相册管理</a></li>
					<li class="menu_item" style="border-radius:8px 0 0 8px;border:0px;" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.borderRadius='0 8px 8px 0';this.style.fontWeight='bold'"><a>邮件管理</a></li>
				</ul>
			</div>
		</div> -->
		<div id="user_info">
			<div id="welcome">欢迎：<font color="white">${obj.realname }</font>&nbsp;</div>
			<div id="logout">
			<%-- <a href="<%=path%>/pcAdmin/requestType?param=exit">安全退出</a> --%>
			<form action="<%=path%>/logout" method="post">
				<button class="layui-btn" lay-submit="" lay-filter="submitForm">退&nbsp;出</button>
			</form>
				<!-- <input type="button" id="but" value="安全退出"> -->
			</div>
		</div>
	</div>
	<div id="navigator">
		<iframe src="<%=path %>/tree.jsp"></iframe>
        </div>
	<div id="main">
		<iframe name="MainFrame" src="<%=path %>/admin/login_main.jsp"></iframe>
	</div>
		<div id="footer"><div id="text"></div></div>
</div>
</body>

<script type="text/javascript" src="<%=path%>/js/footer.js"></script>
<script type="text/javascript">
function screenAdapter(){
	document.getElementById('footer').style.top=document.documentElement.scrollTop+document.documentElement.clientHeight- document.getElementById('footer').offsetHeight+"px";
		document.getElementById('navigator').style.height=document.documentElement.clientHeight-100+"px";
		document.getElementById('main').style.height=document.documentElement.clientHeight-100+"px";
		document.getElementById('main').style.width=window.screen.width-230+"px";
}

window.onscroll=function(){screenAdapter();};
window.onresize=function(){screenAdapter();};
window.onload=function(){screenAdapter();};

$(function(){
	$(".layui-btn").click(function(){
		layui
		.use(
				[ 'form' ],
				function() {
					var form = layui.form;

					//提交form表单
					form.on('submit(submitForm)');

				});
	});
	
	/* if (window.history && window.history.pushState) {
		$(window).on('popstate', function () {
		window.history.pushState('forward', null, '#');
		window.history.forward(1);
		});
		}
		window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
		window.history.forward(1); */
})

</script>


</html>