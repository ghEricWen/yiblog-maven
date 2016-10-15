<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if($("#inputPageCount").val()=="0"){
			$("#ulPage").hide();
		}
		if($("#inputCurrentPage").val()=="1"){
			$("#lastPageLi").hide();
		}
		if($("#inputCurrentPage").val()==$("#inputPageCount").val()){
			$("#nextPageLi").hide();
		}
	});
</script>
</head>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>
	<div class="container-fluid container">
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>用户名</th>
				<th>邮箱</th>
				<th>电话号码</th>
				<th>性别</th>
				<th>生日</th>
				<th>注册日期</th>
				<th>最后登录时间</th>
			</tr>
		</thead>

		<tbody>
			<s:iterator value="listUser" var="user">
				<tr>
					<td><s:property value="#user.id" /></td>
					<td><s:a action="user_getById?user.id=%{#user.id}"><s:property value="#user.name" /></s:a></td>
					<td><s:property value="#user.email" /></td>
					<td><s:property value="#user.phonenumber" /></td>
					<td><s:property value="#user.sex" /></td>
					<td><s:property value="#user.birthDate" /></td>
					<td><s:property value="#user.registerDate" /></td>
					<td><s:property value="#user.lastLoginDate" /></td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
	<input type="hidden" id="inputCurrentPage" value="<s:property value="#request.currentPage"/>">
	<input type="hidden" id="inputPageCount" value="<s:property value="#request.pageCount"/>">
	<ul id="ulPage" title="页数" class="breadcrumb">
		<li id="lastPageLi">
			<s:url escapeAmp="false" var="lastPageUrl" value="user_viewUsers"><s:param name="page" value="%{#request.lastPage}" /><s:param name="userPerPage" value="%{userPerPage}"/></s:url>
			<a href="<s:property value="#lastPageUrl"/>"> 
				上一页
			</a>
		</li>
		<s:iterator value="%{#request.pageMap}" var="page">
			<li><s:url escapeAmp="false" var="url" value="user_viewUsers">
					<s:param name="page" value="%{#page.value}" />
					<s:param name="userPerPage" value="%{userPerPage}"></s:param>
				</s:url> <a href="<s:property value="#url"/>"> <s:property
						value="#page.key" />
			</a></li>
		</s:iterator>
		<li id="nextPageLi">
				<s:url escapeAmp="false" var="nextPageUrl" value="user_viewUsers"><s:param name="page" value="%{#request.nextPage}" /><s:param name="userPerPage" value="%{userPerPage}"/></s:url>
				<a href="<s:property value="#nextPageUrl"/>"> 
					下一页
				</a>
			</li>
			<li>
				<s:url escapeAmp="false" var="totalPageUrl" value="user_viewUsers"><s:param name="page" value="%{#request.pageCount}" /><s:param name="userPerPage" value="%{userPerPage}"/></s:url>
				共&nbsp;<a href="<s:property value="#totalPageUrl"/>">${requestScope.pageCount }</a>&nbsp;页
		</li>
	</ul>
	
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>

</body>
</html>