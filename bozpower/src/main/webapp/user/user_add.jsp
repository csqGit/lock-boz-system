<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/default.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/add_edit.css" />
<body>
	<script type="text/javascript" src="../js/Calendar3.js"></script>

	<div id="navi">
		<div id='naviDiv'>
			<span><img src="../images/arror.gif" width="7" height="11"
				border="0" alt=""></span>&nbsp;用户管理<span>&nbsp;</span> <span><img
				src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
			<a href="javascript:void(0)">用户新增</a><span>&nbsp;</span>
		</div>
	</div>
	<%-- <div id="tips">
		<strong>新增用户</strong>
	</div> --%>
	<div id="mainContainer">

		<form name="addForm" id="addForm" action="<%=path%>/pcUser/insertUser"
			method="post">
			<input type="hidden" name="id" value="">
			<table>

				<tr>
					<td width="30%">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td>
					<td><input type="text" name="realname" placeholder="请输入姓名"
						value="" /></td>
					<td></td>
				</tr>
				<tr>
					<td width="30%">电话号码</td>
					<td><input type="text" name="phone" id="phone" maxlength="11" required
						 placeholder="请输入电话号码" value="" /></td>
					<td><span id="remind"></span></td>
				</tr>
				<tr>
					<td width="30%">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</td>
					<td><input type="password" name="password" min="6"
						placeholder="请输入密码6~11位" value="" /></td>
					<td></td>
				</tr>


				<tr>
					<td width="30%">电压等级</td>
					<td><input type="text" name="voltage"
						value="${admin.voltage }" readonly="readonly"></td>
					<td></td>
				</tr>

				<tr>
					<td width="30%">变&nbsp;电&nbsp;站</td>
					<td><select name="transformersubId.id" id="transformsub">
							<option value="">请选择</option>
							<s:forEach var="trans" items="${transformerSubList }">
								<option value="${trans.id }">${trans.transformersub }</option>
							</s:forEach>
					</select></td>
					<td></td>
				</tr>

				<tr>
					<td colspan="3" align="center">
						<button class="button" type="button">提交</button> <input
						class="button" type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript">
			/*onblur="checkme(this)" 
			function checkme(obj) {
				var val = document.getElementById('phone').value;
				var mobilevalid = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;
				if (!mobilevalid.test(val)) {
					alert('请输入正确的手机号码！')
					return false;
				}
			} */
			
			
			$(function() {
				$("button").click(function() {
					var phone = $("input[name='phone']");
					var realname = $("input[name='realname']");
					var password = $("input[name='password']");
					var trans = $("#transformsub");
					
					var val = document.getElementById('phone').value;
					var mobilevalid = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;
					
					
					if (realname.val().trim().length == 0) {
						//alert('姓名不能为空！');
						realname.css({'border': '1px solid red'});
						return false;
					}else {
						realname.css({'border': 'none'});
					}

					if (!mobilevalid.test(val)) {
						//alert('请输入正确的手机号码！')
						$("input[name='phone']").val('');
						
						phone.css({'border': '1px solid red'});
						return false;
					}else {
						phone.css({'border': 'none'});
					}
				

					if (password.val().trim().length == 0 ) {
						//alert('密码不能为空！');
						password.css({'border': '1px solid red'});
						return false;
					}else if(password.val().trim().length < 6){
						//alert('密码不能小于6位！');
						password.val('');
						
						return false;
					}else {
						password.css({'border': 'none'});
					}

					if (trans.val().trim().length == 0) {
						//alert('请选择变电站！');
						trans.css({'border': '1px solid red'});
						return false;
					}else {
						trans.css({'border': 'none'});
					}

					$('#addForm').submit();
				});
			})
		</script>


	</div>
</body>
</html>