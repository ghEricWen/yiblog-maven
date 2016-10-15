<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>

<script type="text/javascript">
	function generateDay(){
		var runnian = 0;
		var dayCount = 0;
		var year = $("#selYear").val();
		var month = $("#selMonth").val();
		if(year % 400 == 0 || (year % 4 == 0) && (year % 100 != 0) ){
			runnian = 1;
		}
		switch(month){
		case "1":
		case "3":
		case "5":
		case "7":
		case "8":
		case "10":
		case "12":
			dayCount=31;break;
		case "4":
		case "6":
		case "9":
		case "11":
			dayCount=30;break;
		case "2":
			dayCount=(runnian==1?29:28);break;
		}
		
		for(var i=1;i<=dayCount;i++){
			var _option = document.createElement("option");
			_option.setAttribute("value",i)
			_option.appendChild(document.createTextNode(i));
			$("#selDay").append(_option);
		}
	}
	
	function setInputBirthDateVal(){
		var monthStr = $("#selMonth").val();
		var dayStr = $("#selDay").val();
		
		if(monthStr.length == 1){
			monthStr = "0" + monthStr;
		}
		if(dayStr.length == 1){
			dayStr = "0" + dayStr;
		}
		
		var birthDateString = $("#selYear").val() + "-" + monthStr + "-" +dayStr;
		
		$("#inputBirthDate").val(birthDateString);
		
	}

	$(document).ready(function(){
		for(var i=1900;i<=2020;i++){
			var _option = document.createElement("option");
			_option.setAttribute("value",i);
			_option.appendChild(document.createTextNode(i));
			$("#selYear").append(_option);
		}		
		
		for(var i=1;i<=12;i++){
			var _option = document.createElement("option");
			_option.setAttribute("value",i);
			_option.appendChild(document.createTextNode(i));
			$("#selMonth").append(_option);
		}
		
		$("#selYear").val(1990);
		
		generateDay();
		
		var birthDate = $("#inputBirthDate").val();
		if(birthDate.length != 0){
			birthDate = birthDate.split(" ")[0];
			
			var arr = birthDate.split("-");
			
			if(arr[1].charAt(0)=='0'){
				arr[1] = arr[1].substr(1);
			}
			
			if(arr[2].charAt(0)=='0'){
				arr[2] = arr[2].substr(1);
			}
			$("#selYear").val(arr[0]);
			$("#selMonth").val(arr[1]);
			$("#selDay").val(arr[2]);
		}
		
		setInputBirthDateVal();
		
		$("#selYear").change(function(){
			$("#selDay").empty();
			generateDay();
			setInputBirthDateVal();
		});
		
		$("#selMonth").change(function(){
			$("#selDay").empty();
			generateDay();
			setInputBirthDateVal();
		});
		
		$("#selDay").change(function(){
			setInputBirthDateVal();
		});
		
	});
	
	$(document).ready(function(){
		$("#inputUserName").blur(function(){
			if($("#inputUserName").val() == ""){
				$("#pName").html("不能为空");
				return;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/user_checkName?user.name=" + $("#inputUserName").val(),
				type : "GET",
				dataType : "text",
				success : function(text){
					if($("#inputUserName").val()==$("#currentUserName").val()){
						$("#info").html("与原用户名相同");	
						return;
					}						
					if(text=="valid"){
						$("#info").html("可用");
					}else if(text=="unvalid"){
						$("#info").html("已被使用");
					}
				},
				error : function(){
					alert("error");
				}
			});
		});
	});
</script>
</head>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>
	<input type="hidden" id="currentUserId"
		value="${sessionScope.currentUser.id }">
	<input type="hidden" id="currentUserName" value="${sessionScope.currentUser.name }"> 
	<div class="container-fluid container">
		<form action="${pageContext.request.contextPath }/user_submitUpdate">
			<input type="hidden" name="user.id" value="${userGet.id }">
			<table class="table" style="width:60%;margin:auto;">
				<tr>
					<td>用户名</td>
					<td><input id="inputUserName" class="form-control" name="user.name"
						value="${userGet.name}"></td>
					<td id="info"></td>
				</tr>
				<tr>
					<td>电话号码</td>
					<td><input class="form-control" name="user.phonenumber"
						value="${userGet.phonenumber }"></td>
				</tr>
				<tr>
					<td>性别</td>
					<td><select name="user.sex">
							<option value="1">男</option>
							<option value="2">女</option>
							<option value="0">保密</option>
					</select></td>
				</tr>
				<tr>
					<td>生日</td>
					<td>
						<div class="row">
							<div class="col-xs-4"><select id="selYear" class="form-control"></select></div>
							<div class="col-xs-4"><select id="selMonth" class="form-control"></select></div>
							<div class="col-xs-4"><select id="selDay" class="form-control"></select></div>
						</div>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="确认修改" class="btn btn-primary"></td>
				</tr>
			</table>
			<input id="inputBirthDate" type="hidden" class="form-control" name="user.birthDate"
				value="${userGet.birthDate }">
		</form>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/bootstrap.css"></script>
</body>
</html>