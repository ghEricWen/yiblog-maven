<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
</head>

<jsp:include page="nav.jsp"></jsp:include>
<body>

	<div class="container-fluid container">
		<h1 class="text-center">登录</h1>
		<h2 class="text-danger text-center"><s:property value="#request.loginError"/></h2>
		
		<form action="${pageContext.request.contextPath }/user_login" method="POST">
			
			<div class="row">
				<div class="col-xs-0 col-md-3"></div>
				<div class="col-xs-10 col-md-6">
					<table class="table">
				<tr>
					<td><input type="text" class="form-control" name="user.name" placeholder="用户名/邮箱"></td>
				</tr>
				<tr>
					<td><input type="password" class="form-control" name="user.password" placeholder="密码"></td>
				</tr>
				
				<tr>
					<td><input class="btn btn-block btn-primary" type="submit" value="登录"></td>
				</tr>
				
			</table>
				</div>
				<div class="col-xs-0 col-md-3"></div>
			</div>
			
		</form>
	</div>
</body>
</html>