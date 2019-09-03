<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/default.css" />
	<link rel="stylesheet" type="text/css" href="../css/add_edit.css" />
	<script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.min.js"></script>
	
	</head>
<body>


<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;变电站管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
		<a href="<%=path%>/transformersub/transformersub_add.jsp">变电站新增</a><span>&nbsp;</span>
	</div>
</div>
<%-- <div id="tips">
<strong>新增变电站</strong>
</div> --%>
<div id="mainContainer">

<form name="addForm" id="addForm" action="<%=path%>/pcTransformersub/insertTransformerSub" method="post">
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
    	<input type="text" name="voltage" value="${admin.voltage }" readonly="readonly">
    	</td>
  </tr>
  <tr>
    <td width="30%">变电站名称：</td>
    <td><input type="text" id="transformersubname" placeholder="请输入变电站名称" name="transformersub" /></td>
  </tr>


  <tr>
    <td colspan="2" align="center">
    
    <button class="button" type="button">提交</button>
    <input class="button"
						type="reset" value="重置"></td>
   
  </tr>
</table>
</form>

<script type="text/javascript">
	$(function(){
		$("button").click(function(){
			//校验变电站名称是否输入
			var trans = $('#transformersubname');
			if(trans.val().trim().length == 0){
				//alert('请输入变电站名称！')
				trans.css({'border': '1px solid red'});
				return false;
			}else {
				trans.css({'border': 'none'});
			}
			
			$("#addForm").submit();
		});
	})
</script>
</div>
</body>
</html>