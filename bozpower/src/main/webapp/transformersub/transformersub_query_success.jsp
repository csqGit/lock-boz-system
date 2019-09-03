<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/navi_query.css" />
	<title>公司信息</title>
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;变电站管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
		<a href="<%=path %>/pcTransformersub/selectTransformersubList?page=1">变电站列表</a><span>&nbsp;</span>
	</div>
</div>
<!-- <div id="tips">
<strong>变电站列表</strong>
</div> -->
<div id="mainContainer">
<!-- 从session中获取学生集合 -->

<table class="default" width="100%">
	
	<tr class="title" height="40px">
		<td>编号</td>
		<td>单位名称</td>
		<td>变电站名称</td>
		<td>电压等级</td>
		<td>操作</td>
	</tr>
	
	<!-- 遍历开始 -->
	<c:forEach var="transformersub" items="${transformersub }" varStatus="i">
		<tr class="tit" height="30" >
			<td>${i.index + 1 }</td>
			<td>${transformersub.companyId.companyname }</td>
			<td>${transformersub.transformersub }</td>
			<td>${transformersub.voltage }</td>
			<td><a href="<%=path %>/pcTransformersub/selectTransformersubById?id=${transformersub.id }">修改</a>&nbsp;&nbsp;
			<a href="<%=path%>/pcTransformersub/deleteTransformerSubById?id=${transformersub.id }" onclick="javascript:return confirm('真的要删除吗？');">删除</a></td>
		</tr>
	</c:forEach>
	<!-- 遍历结束 -->
</table>
	
	<div id="bottom">
		<ul>
			<li><font id="first">首页</font></li>
			<li><font id="prePage">上一页</font></li>
			<li><font id="currentPage">${pageData.currentPage }</font>/<font id="pages">${pageData.pages }</font></li>
			<li><font id="next">下一页</font></li>
			<li><font id="end">尾页</font></li>
			<li><font id="count">总条数：${pageData.count }&nbsp;</font></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
$(function(){
	var url = '<%=path %>/pcTransformersub/selectTransformersubList?currentPage=';
	var pagesStr = $("#pages").text();
	var currentPageStr = $("#currentPage").text();
	var pages = parseInt(pagesStr);
	var currentPage = parseInt(currentPageStr);

	$("#first").click(function(){
		
		if(currentPage == 1){
			return false;
		}
		currentPage = 1;
		window.location.href = url + currentPage;
	});
	
	$("#prePage").click(function(){
		if(currentPage == 1){
			return false;
		}
		currentPage --;
		window.location.href = url + currentPage;
	});
	$("#next").click(function(){
		
		if(currentPage >= pages){
			return false;
		}
		currentPage ++;
		window.location.href = url + currentPage;
	});
	$("#end").click(function(){
		if(currentPage == pages){
			return false;
		}
		currentPage = pages;
		window.location.href = url + currentPage;
	});
		
})

	
</script>
</body>
</html>