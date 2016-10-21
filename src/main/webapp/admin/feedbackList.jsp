<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>反馈列表</title>
<link rel="stylesheet" href="dist/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
</head>
<body>
<jsp:include page="../nav.jsp"></jsp:include>

<div class="container">
<h2>反馈列表</h2>
<div class="row">
<div class="xs-col-7">
  <table class="table">
    <thead>
      <tr>
        <th>用户名</th>
        <th>日期</th>
        <th>内容</th>
      </tr>
    </thead>
    <tbody>
	  <s:iterator value="%{#request.listFeedback}" var="feedback">
	    <tr>
	      <td><s:property value="#feedback.username"/></td>
	      <td><s:property value="#feedback.feedbackDate"/></td>
	      <td><s:property value="#feedback.feedbackContent"/></td>
	      <td></td>
	    </tr>    
	  </s:iterator>
    </tbody>
  </table>
</div>
<div class="xs-col-5"></div>
</div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>
</body>
</html>