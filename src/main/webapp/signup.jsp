<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign up</title>
<link rel="stylesheet" href="dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript" src="dist/js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
		var flag0 = 0,flag1 = 0,flag2 = 0,flag3=0,flag4=0;
		$("#inputEmail").keyup(function(){
			var reg = new RegExp("[a-zA-Z0-9_]+@[a-zA-Z0-9_]+.[a-zA-Z0-9_]+");
			if(!reg.test($("#inputEmail").val())){
				$("#pEmail").html("邮箱格式不符合");
				return ;
			}
			if($("#inputEmail").val()==""){
				$("#pEmail").html("不能为空");
				return ;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/user_checkEmail?user.email=" + $("#inputEmail").val(),
				type : "GET",
				dataType : "text",
				success : function(text){
					if(text=="valid"){
						$("#pEmail").html("该邮箱可用");
						flag0 = 1;
						$("#btnEmail").removeAttr("disabled");
					}else if(text=="unvalid"){
						$("#pEmail").html("该邮箱已经被注册");
						$("#btnEmail").attr("disabled","disabled");
					}
				},
				error : function(){
					alert("error");
				}
			});
		});
		
		var _verifyCode = null;
		$("#btnEmail").click(function(){
			
			
			
			var interval = window.setInterval(function(){
				settime();
			},1000);
			var countdown = 60;
			function settime(){
				
				if(countdown == 0){
					$("#btnEmail").removeAttr("disabled");
					$("#btnEmail").attr("value","再次发送");
					window.clearInterval(interval);
					return;
				}else{
					if(!$("#btnEmail").attr("disabled"))
						$("#btnEmail").attr("disabled","disabled");
					$("#btnEmail").attr("value",countdown + "秒后可重发");
					countdown--;
				}
			}
			
			$.ajax({
				url : "${pageContext.request.contextPath}/user_sendVerifyEmail?user.email=" + $("#inputEmail").val(),
				type : "GET",
				dataType : "text",
				success : function(text){
					_verifyCode = text;
				},
				error : function(){
					alert("failed");
				}
			});
		});
		
		$("#inputVerifyCode").keyup(function(){
			if($("#inputVerifyCode").val().length<6){
				return;
			}
			if($("#inputVerifyCode").val()==_verifyCode){
				$("#codeResult").html("√");
				flag1 = 1;
			}else {
				$("#codeResult").html("×");
			}
		});
		
		$("#inputName").blur(function(){
			if($("#inputName").val() == ""){
				$("#pName").html("不能为空");
				return;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/user_checkName?user.name=" + $("#inputName").val(),
				type : "GET",
				dataType : "text",
				success : function(text){
					if(text=="valid"){
						$("#pName").html("可用");
						flag2 = 1;
					}else if(text=="unvalid"){
						$("#pName").html("已被使用");
					}
				},
				error : function(){
					alert("error");
				}
			});
		});
		
		$("#inputPassword").blur(function(){
			var _password0 = $("#inputPassword").val();
			if(_password0==""){
				$("#pPassword").html("不能为空");
			}else {
				$("#pPassword").html("√");
				flag3 = 1;
			}
		});
		
		$("#inputRepeatPassword").blur(function(){
			if($("#inputRepeatPassword").val() != $("#inputPassword").val()){
				$("#pRepeatPassword").html("两次密码需相同");
			}else{
				$("#pRepeatPassword").html("√");
				flag4 = 1;
			}
		});
		
		$("#btnSubmit").click(function(){
			if( flag0 == 1 && 
					flag1 == 1 && 
					flag2 == 1 &&
					flag3 == 1 &&
					flag4 == 1){
				$("#form_signup").submit();
			}else{
				alert("请完整填写以上信息");
			}
		});
	});
</script>

</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container-fluid container">
		<h2 class="text-center">注册</h2>
		<s:form id="form_signup" action="user_save" theme="simple"
			method="POST">
			
			
			<div class="row">
				<div class="col-xs-0 col-md-3 col-lg-3"></div>
				<div class="col-xs-12 col-md-6 col-lg-6">
					<table class="table table-condensed">
						<tr>
							<td colspan="2"><input id="inputEmail" type="text" placeholder="邮箱"
								class="form-control" name="user.email"></td>
							<td><p id="pEmail" class="text-danger"></p></td>
							<td><input disabled="disabled" type="button" class="btn btn-primary" id="btnEmail" value="发送确认码"/></td>
						</tr>
						<tr>
							<td colspan="2"><input id="inputVerifyCode" type="text" placeholder="确认码" class="form-control"></td>
							<td colspan="2"><p id="codeResult" class="text-danger"></p></td>
						</tr>
						<tr>
							<td colspan="2"><input id="inputName" type="text" placeholder="用户名"
								class="form-control" name="user.name"></td>
							<td colspan="2"><p id="pName" class="text-danger"></p></td>
						</tr>
						<tr>
							<td colspan="2"><input id="inputPassword" type="password" placeholder="密码(推荐6到20位)"
								class="form-control" name="user.password"></td>
							<td colspan="2"><p id="pPassword" class="text-danger"></p></td>
						</tr>
						<tr>
							<td colspan="2"><input id="inputRepeatPassword" type="password" placeholder="重复密码"
								class="form-control"></td>
							<td colspan="2"><p id="pRepeatPassword" class="text-danger"></p></td>
						</tr>
						<tr>
							<td colspan="2"><input class="form-control btn btn-primary" type="button" id="btnSubmit" value="注册"></td>
						</tr>
					</table>
				</div>
				<div class="col-xs-0 col-md-3 col-lg-3"></div>
			</div>
			
		</s:form>
	</div>
</body>
</html>