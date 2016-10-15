<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加分类</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
</head>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>
	<div class="container-fuild container">
		<h1>添加分类</h1>
		<s:form action="cate_save" theme="simple">
			分类名 ： <s:textfield  name="category.name"/>
			<s:submit value="提交"/>
		</s:form>
		<br>
		<h1>添加子分类</h1>
		<s:form action="subCategory_save" theme="simple">
			<s:select list="%{#request.listCategory}" listKey="id" listValue="name" name="category.id"/>
			子分类名 ： <s:textfield name="subCategory.name"/>
			<s:submit value="提交"/>
		</s:form>
		<br>
		<h1>发通知</h1>
		<form action="${pageContext.request.contextPath }/announcement_save" method="POST">
			内容 : <textarea rows="2" cols="40" name="announcement.content"></textarea>
			<input type="submit" value="提交">
		</form>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>
	
</body>
</html>