<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<a href="javascript:void(0)">编辑用户</a><span>&nbsp;</span>
		</div>
	</div>
	<%-- <div id="tips">
		<strong>用户编辑</strong>
	</div> --%>
	<div id="mainContainer">

		<form name="addForm" action="<%=path%>/pcUser/updateUser"
			method="post">
			<input type="hidden" name="id" value="${user.id }">
			<table width="400">

				<%-- <tr>
					<td width="30%">旧用户名</td>
					<td><input type="text" name="oldUsername"  value="${user.username }" /></td>
				</tr> --%>
				<tr>
					<td width="30%">用户名</td>
					<td><input type="text" name="username"
						value="${user.username }" /></td>
				</tr>
				<tr>
					<td width="30%">真实姓名</td>
					<td><input type="text" name="realname"
						value="${user.realname }" /></td>
				</tr>

				<%-- <tr>
					<td width="30%">旧密码</td>
					<td><input type="password" name="oldPassword" value="${user.password }" /></td>
				</tr> --%>

				<tr>
					<td width="30%">新密码</td>
					<td><input type="password" name="password"
						value="${user.password }" placeholder="每次修改信息必须修改密码" /></td>
				</tr>
				<%-- <tr>
					<td width="30%">性别</td>
					<td>
						
						男：<input type="radio" name="gender" value="男" ${user.gender == '男'   ? "checked": ""} />
						
						 女：<input type="radio" name="gender" value="女" ${user.gender == '女'   ? "checked": "" } />
					</td>
				</tr> 
				<tr>
					<td width="30%">年龄</td>
					<td><input type="text" name="age" value="${user.age }" /></td>
				</tr>--%>

				<tr>
					<td width="30%">电话</td>
					<td><input type="text" name="phone" maxlength="11"
						value="${user.phone }" /></td>
				</tr>
				<tr>
					<td width="30%">电压等级</td>
					<td><input type="text" name="voltage" value="${user.voltage }"
						readonly="readonly" /></td>
				</tr>

				<tr>
					<td width="30%">变电站</td>
					<td><select name="transformersubId.id" id="transformsub">
							<s:forEach var="trans" items="${transformerSubList }">
								<option value="${trans.id }"
									${trans.transformersub == user.transformersubId.transformersub ? "selected" : "" }>${trans.transformersub }</option>
							</s:forEach>
					</select></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input class="button"
						type="submit" value="提交"></td>
				</tr>
			</table>
		</form>
		<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>


	</div>
</body>


</html>