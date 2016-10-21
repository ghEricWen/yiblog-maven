<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>反馈</title>
<link rel="stylesheet" href="dist/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="container">
<h3 id="message"><s:property value="#request.message"/></h3>
<form id="feedbackForm" action="${pageContext.request.contextPath }/feedback_save" method="POST" style="width:50%">
  <textarea id="feedbackContent" rows="2" cols="40" name="feedback.feedbackContent" placeholder="在这输入反馈" class="form-control"></textarea><br>
  <input type="text" name="feedback.username" placeholder="留个名吧（选填）" class="form-control"><br>
  <input type="button" value="提交" class="form-control btn-primary" id="submitButton">
</form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
	  $("#submitButton").click(function(){
		  if($("#feedbackContent").val()==""){
			  $("#message").html("请输入feedback内容");
			  return;
		  }
		  $("#feedbackForm").submit();  
	  });
  });
</script>
</body>
</html>