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
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;变电站管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
		<a href="javascript:void(0)">变电站编辑</a><span>&nbsp;</span>
	</div>
</div>
<%-- <div id="tips">
<strong>编辑变电站</strong>
</div> --%>
<div id="mainContainer">

<form name="addForm" action="<%=path%>/pcTransformersub/updateTransformerSub" method="post">
<input type="hidden" name="id" value="${transformersub.id }">
<table  >
  <tr>
    <td width="30%">单位名称：</td>
    <td>
    <select name="companyId.id">
			<option value="${admin.companyId.id }">${admin.companyId.companyname }</option>
		</select> 
    </td>
  </tr>
  <tr>
    <td width="30%">电压等级：</td>
    <td>
    	<input type="text" name="voltage" value="${transformersub.voltage }" readonly="readonly">
    	</td>
  </tr>
  <tr>
    <td width="30%">变电站名称：</td>
    <td><input type="text" id="transformersubname" name="transformersub" value="${transformersub.transformersub }" /></td>
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