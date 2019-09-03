<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/default.css" />
	<link rel="stylesheet" type="text/css" href="../css/add_edit.css" />
<body>
<script type="text/javascript" src="../js/Calendar3.js"></script>

<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;单位管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
		<a href="<%=path %>/pcCompany/selectCompanyList?page=1">单位新增</a><span>&nbsp;</span>
	</div>
</div>
<div id="tips">
<strong>新增单位</strong>
</div>
<div id="mainContainer">

<form name="addForm" action="<%=path%>/pcCompany/insertCompany" method="post">
<table width="400" >
  
  <tr>
    <td width="30%">电压等级：</td>
    <td>
<%--     <select name="voltage">
    		<option value="">---请选择---</option>
    		<option value="110v">110v</option>
    		<option value="220v">220v</option>
    		<option value="35kv">35kv</option>
    		<option value="110kv">110kv</option>
    		<option value="220kv">220kv</option>
    		<option value="500kv">500kv</option>
    	</select> --%>
    	<input type="text" name="voltage" value="${admin.voltage }" readonly="readonly">
    	</td>
  </tr>
  <tr>
    <td width="30%">单位名称：</td>
    <td><input type="text" id="companyname" name="companyname" /></td>
  </tr>


  <tr>
    <td colspan="2" align="center"><input class="button" type="submit" value="提交">
    <input class="button"
						type="reset" value="重置"></td>
   
  </tr>
</table>
</form>


</div>
</body>
</html>