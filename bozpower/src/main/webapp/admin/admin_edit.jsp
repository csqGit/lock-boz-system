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
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/add_edit.css" />

<body>
<script type="text/javascript" src="../js/Calendar3.js"></script>

<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;管理员管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
		<a href="javascript:void(0)">管理员编辑</a><span>&nbsp;</span>
	</div>
</div>
<%-- <div id="tips">
<strong style="margin: 0 auto; paddig: 10px 0; text-align: center; display: block;">管理员编辑</strong>
</div> --%>
<div id="mainContainer">



<form name="addForm" action="<%=path%>/pcAdmin/updateAdmin" method="post">
<input type="hidden"  name="id" value="${admin.id }">
<table width="400" >

  <tr>
    <td width="30%">用户名</td>
    <td><input type="text" name="username" value="${admin.username }" /></td>
  </tr>
  <tr>
    <td width="30%">真实姓名</td>
    <td><input type="text" name="realname" value="${admin.realname }" /></td>
  </tr>
    <tr>
    <td width="30%">密码</td>
    <td><input type="password" name="password" value="${admin.password }" /></td>
  </tr>
  
    <tr>
    <td width="30%">电话</td>
    <td><input type="text" name="telephone" value="${admin.telephone}" /></td>
  </tr>
  
<tr>
    <td>单位名称：</td>
    <td>
    	<select name="companyId.id">
    		<option value="${admin.companyId.id }" selected="selected">${admin.companyId.companyname }</option>
    	</select>
    </td>
  </tr>
  
  <tr>
    <td width="30%">电压等级</td>
    <td>
    	<input type="text" name="voltage" value="${admin.voltage }" readonly="readonly"> 
    </td>
  </tr>
  
    <%-- <tr>
    <td width="30%">类型</td>
    <td>
    	
    	<input type="radio" name="type" value="1" ${admin.type == 1 ? "checked" : ""} ><span style="font-size: 13px;">超级用户</span>
    	<input type="radio" name="type" value="2"  ${admin.type == 2 ? "checked" : ""}><span style="font-size: 13px;">一般用户</span>
    	<input type="radio" name="type" value="3" ${admin.type == 3 ? "checked" : ""}><span style="font-size: 13px;">普通用户</span>
    </td>
  </tr> 
  
    <tr>
    <td width="30%">工号</td>
    <td><input type="text" name="jobnumber" value="${admin.jobnumber }" /></td>
  </tr>
 --%>
  <tr>
    <td colspan="2" align="center"><input class="button" type="submit" value="提交"></td>
  </tr>
</table>
</form>


</div>
</body>
</html>