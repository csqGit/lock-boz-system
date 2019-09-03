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
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/add_edit.css" />
	<script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.min.js"></script>
	
	</head>
<body>
<script type="text/javascript" src="../js/Calendar3.js"></script>

<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt="">
		</span>&nbsp;智能锁管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt="">
		</span>&nbsp;<a href="javascript:void(0)">智能锁新增</a><span>&nbsp;</span>
	</div>
</div>
<%-- <div id="tips">
<strong>新增智能锁</strong>
</div> --%>
<div id="mainContainer">

<form name="addForm" id="addForm" action="<%=path%>/pcLock/insertLock" method="post">
<table >
   <tr>
    <td>单位名称</td>
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
    <td>变电站</td>
    <td><select name="transformersubId.id" id="transformsub">
								<option value="">请选择变电站</option>
							<s:forEach var="trans" items="${transformerSubList }">
								<option value="${trans.id }" >${trans.transformersub }</option>
							</s:forEach>
					</select></td>
  </tr>
  

  <tr>
    <td width="30%">did</td>
    <td><input type="text" name="did" placeholder="请输入设备did" /></td>
  </tr>
  <tr>
    <td width="30%">mac</td>
    <td><input type="text" name="mac"  placeholder="请输入设备mac" /></td>
  </tr>
  <!--   <tr>
    <td width="30%">passcode</td>
    <td><input type="text" name="passcode"  placeholder="选填"  /></td>
  </tr> -->
 
  <tr>
    <td colspan="2" align="center"  style="background: rgb(240,240,240);" >
    <!-- <input class="button" type="submit" value="提交"> -->
    <button class="button" type="button" >提交</button>
    <input class="button"
						type="reset" value="重置"></td>
  </tr>
</table>
</form>

<script type="text/javascript">
	$(function(){
		$("button").click(function(){
			//校验变电站数据是否正确
			var trans = $('#transformsub');
			var did = $("input[name='did']");
			var mac = $("input[name='mac']");
			if(trans.val().trim().length == 0){
				//alert('请选择变电站！')
				trans.css({'border': '1px solid red'});
				return false;
			}else {
				trans.css({'border': 'none'});
			}
			
			if(did.val().trim().length == 0){
				//alert('请输入设备did！')
				did.css({'border': '1px solid red'});
				return ;
			}else {
				did.css({'border': 'none'});
			}
			if(mac.val().trim().length == 0){
				//alert('请输入mac！')
				mac.css({'border': '1px solid red'});
				return ;
			}else {
				mac.css({'border': 'none'});
			}
			
			$("#addForm").submit();
		});
	})
</script>

</div>
</body>
</html>