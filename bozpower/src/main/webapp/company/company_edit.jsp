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
		<a href="<%=path %>/pcCompany/selectCompanyList?page=1">单位列表</a><span>&nbsp;</span>
	</div>
</div>
<div id="tips">
<strong style="margin: 0 auto;">修改单位信息</strong>
</div>
<div id="mainContainer">


<br>
<br>
<form name="addForm" action="<%=path%>/pcCompany/updateCompany" method="post">
<input type="hidden" name="id" value="${company.id }">
<table width="400" >
  
   <tr>
    <td width="30%">电压等级：</td>
    <td>
    	<%-- <select name="voltage">
    		<s:forEach var="voltage" items="${voltageList }" >
    			<option  value="${voltage.voltagename }" ${voltage.voltagename == company.voltage ? "selected" : "" }>${voltage.voltagename }</option>
    		</s:forEach>
    	</select> --%>
    	<input type="text" name="voltage" value="${admin.voltage }" readonly="readonly">
    	</td>
  </tr>
  <tr>
    <td width="30%">单位名称：</td>
    <td><input type="text" name="companyname" value="${company.companyname }" /></td>
  </tr>
  


  <tr>
    <td colspan="2" align="center"><input class="button" type="submit" value="提交"></td>
  </tr>
</table>
</form>


</div>
</body>
</html>