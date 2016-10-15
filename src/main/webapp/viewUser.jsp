<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if ($("#profileId").html() == $("#currentUserId").attr("value")) {
			$("#aEditProfile").removeAttr("disabled");
			$("#aEditProfile").show();
			$("#trEmail").show();
			$("#trPhone").show();
			$("#trPassword").show();
		} else {
		}
	});
</script>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<input type="hidden" id="currentUserId"
		value="${sessionScope.currentUser.id }">
	<div class="container-fuild container">
		<table class="table">
			<tr>
				<td>id</td>
				<td id="profileId"><s:property value="#request.viewUser.id"/></td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><s:property value="#request.viewUser.name"/></td>
			</tr>
			<tr id="trEmail" style="display:none;">
				<td>邮箱</td>
				<td><s:property value="#request.viewUser.email"/></td>
			</tr>
			<tr id="trPassword" style="display:none;">
				<td>密码</td>
				<td><s:property value="#request.viewUser.password"/></td>
			</tr>
			<tr id="trPhone" style="display:none;">
				<td>电话号码</td>
				<td><s:property value="#request.viewUser.phonenumber"/></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><s:property value="#request.viewUser.sex"/></td>
			</tr>
			<tr>
				<td>生日</td>
				<td><s:property value="#request.viewUser.birthDate"/></td>
			</tr>
			<tr>
				<td>注册时间</td>
				<td><s:property value="#request.viewUser.registerDate"/></td>
			</tr>
			<tr>
				<td>最后登录时间</td>
				<td><s:property value="#request.viewUser.lastLoginDate"/></td>
			</tr>
			<tr>
				<td colspan="2"><a disabled="disabled" style="display:none;"
					href="${pageContext.request.contextPath }/user_editProfile?user.id=<s:property value="#request.viewUser.id"/>"
					id="aEditProfile" class="btn btn-primary">修改资料</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/bootstrap.css"></script>
</body>
</html>