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
	<title>公司信息</title>
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var pagesStr = $("#pages").text();
		var currentPageStr = $("#currentPage").text();
		var pages = parseInt(pagesStr);
		var currentPage = parseInt(currentPageStr);
		$("#first").click(function(){
			if(currentPage == 1){
				return false;
			}
			window.location.href = "<%=path %>/pcCompany/selectCompanyList?page=1";
		});
		
		$("#prePage").click(function(){
			if(currentPage == 1){
				return false;
			}
			currentPage --;
			window.location.href="<%=path %>/pcCompany/selectCompanyList?page=" + currentPage;
		});
		$("#next").click(function(){
			if(currentPage == pages){
				return false;
			}
			currentPage ++;
			window.location.href = "<%=path %>/pcCompany/selectCompanyList?page=" + currentPage;
		});
		$("#end").click(function(){
			if(currentPage == pages){
				return false;
			}
			window.location.href = "<%=path %>/pcCompany/selectCompanyList?page=" + pages;
		});
	})
	
</script>
<body>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;单位管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
		<a href="<%=path %>/pcCompany/selectCompanyList?page=1">单位列表</a><span>&nbsp;</span>
	</div>
</div>
<%-- <div id="tips">
<strong>单位列表</strong>
	<div id="buttonGroup">
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a href="<%=path%>/company/company_add.jsp">添加单位</a>
		</div>
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a href="<%=path %>/company/company_add.jsp">查找单位</a>
		</div>
	</div>
</div> --%>
<div id="mainContainer">
<!-- 从session中获取学生集合 -->

<table class="default" width="60%">
	<col width="20%">
	<col width="35%">
	<col width="20%">
	<col width="20%">
	<tr class="title" height="40px">
		<td>编号</td>
		<td>单位名称</td>
		<td>电压等级</td>
		<td>操作</td>
	</tr>
	
	<!-- 遍历开始 -->
	<c:forEach var="company" items="${companyList }" varStatus="i">
		<tr class="tit" height="30" >
			<td>${i.index + 1 }</td>
			<td>${company.companyname }</td>
			<td>${company.voltage }</td>
			<td><a href="<%=path %>/pcCompany/selectCompanyById?param=update&id=${company.id }">修改</a>&nbsp;&nbsp;
			<a href="<%=path%>/pcCompany/deleteCompany?id=${company.id }" onclick="javascript:return confirm('真的要删除吗？');">删除</a></td>
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
</body>
</html>