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
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt="">
		</span>&nbsp;智能锁管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt="">
		</span>&nbsp;<a href="javascript:void(0)">智能锁编辑</a><span>&nbsp;</span>
	</div>
</div>
<%-- <div id="tips">
<strong>编辑智能锁</strong>
</div> --%>
<div id="mainContainer">

<form name="addForm" action="<%=path%>/pcLock/updateLock" method="post">
<input type="hidden"  name="id" value="${lock.id }">
<table>
<tr>
    <td>单位名称：</td>
    <td>
    	<select name="companyId.id">
    		<option value="${admin.companyId.id }">${admin.companyId.companyname }</option>
    	</select>
    </td>
  </tr>
    <tr>
    <td width="30%">电压等级</td>
    <td>
    	<input type="text" name="voltage" value="${admin.voltage }" readonly="readonly"> 
    </td>
  </tr>
  
   <tr>
    <td>变电站：</td>
    	<td><select name="transformersubId.id" id="transformsub">
								<option value="">请选择</option>
							<s:forEach var="trans" items="${transformerSubList }">
								<option value="${trans.id }" ${trans.transformersub == lock.transformersubId.transformersub ? 'selected' : '' } >${trans.transformersub }</option>
							</s:forEach>
					</select></td>
  </tr>
  
  
  <tr>
    <td width="30%">did：</td>
    <td><input type="text" name="did" value="${lock.did }" /></td>
  </tr>
  <tr>
    <td width="30%">mac：</td>
    <td><input type="text" name="mac" value="${lock.mac }" /></td>
  </tr>
    <tr>
    <td width="30%">passcode：</td>
    <td><input type="text" name="passcode" value="${lock.passcode }" /></td>
  </tr>
  
  
  <tr>
    <td colspan="2" align="center"><input class="button" type="submit" value="提交"></td>
  </tr>
</table>
</form>


</div>
</body>
</html>