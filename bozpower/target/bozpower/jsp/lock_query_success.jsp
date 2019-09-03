<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/navi_query.css" />
	<title>智能锁信息</title>
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>

<body>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;智能锁管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;<a href="<%=path%>/pcLock/selectLockList?page=1">智能锁列表</a><span>&nbsp;</span>
	</div>
</div>
<!-- <div id="tips">
<strong>锁列表</strong>
</div> -->
<div id="mainContainer">
<!-- 从session中获取学生集合 -->

<table class="default" width="100%">
	<col width="5%">
	<col width="10%">
	<col width="15%">
	<%-- <col width="15%"> --%>
	<%-- <col width="15%"> --%>
	<col width="15%">
	<col width="15%">
	<col width="10%">
	<col width="15%"> 
	<tr class="title" height="40px">
		<td>编号</td>
		<td>单位名称</td>
		<td>变电站</td>
		<!-- <td>运检班组</td> -->
		<!-- <td>锁名称</td> -->
		<td>did</td>
		<td>mac</td>
		<!-- <td>passcode</td>  -->
		<td>操作</td>
	</tr>
	
	<!-- 遍历开始 -->
	
	<c:forEach var="obj" items="${lockList }" varStatus="i">
		<tr class="bg" height="30px;" style="line-height:30px;">
			<td>${i.index + 1 }</td>
			<td>${obj.companyId.companyname }</td>
			<td>${obj.transformersubId.transformersub }</td>
			<%-- <td>${obj.classes }</td> --%>
			<%-- <td>${obj.transformersubId.transformersub }</td> --%>
			<td>${obj.did }</td>
			<td>${obj.mac }</td>
			<%-- <td>${obj.passcode }</td> --%>
			<td>
				<a href="<%=path %>/pcLock/selectLockById?id=${obj.id}">修改</a>
				&nbsp;&nbsp;
				<a href="<%=path%>/pcLock/deleteLockById?id=${obj.id}" onclick="javascript:return confirm('真的要删除吗？');">删除</a>
			</td>
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
		var pagesStr = $("#pages").text();
		var currentPageStr = $("#currentPage").text();
		var pages = parseInt(pagesStr);
		var currentPage = parseInt(currentPageStr);
		var url = '<%=path %>/pcLock/selectLockList?currentPage=';
		$("#first").click(function(){
			if(currentPage == 1){
				return false;
			}
			window.location.href = url + 1;
		});
		
		$("#prePage").click(function(){
			if(currentPage == 1){
				return false;
			}
			currentPage --;
			window.location.href= url + currentPage;
		});
		$("#next").click(function(){
			if(currentPage == pages){
				return false;
			}
			currentPage ++;
			window.location.href = url + currentPage;
		});
		$("#end").click(function(){
			if(currentPage == pages){
				return false;
			}
			window.location.href = url + pages;
		});
	})
	
</script>
</body>
</html>