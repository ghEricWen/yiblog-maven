<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有评论</title>
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
		<div class="row">
			<div class="col-xs-1">
				<s:url action="comment_getNewComment" var="newCommentUrl">
					<s:param name="toUser.id" value="%{#session.currentUser.id}" />
				</s:url>
				<a class="btn btn-primary"
					href="<s:property value="#newCommentUrl"/>">新评论</a>
			</div>
			<div class="col-xs-1">
				<s:url action="comment_getComments" var="allComments"
					escapeAmp="false">
					<s:param name="page" value="1" />
					<s:param name="commentPerPage" value="10" />
					<s:param name="toUser.id" value="%{#session.currentUser.id}" />
				</s:url>
				<a class="btn btn-primary active" href="<s:property value="#allComments"/>">全部评论</a>
			</div>
	
			<div class="col-xs-10"></div>
		</div>
		<br>
	
		<ul class="list-group">
			<s:iterator value="%{#request.listComment}" var="comment">
				<li class="list-group-item"><span><s:property value="#comment.commenttime"/>&nbsp;来自
				<s:property	value="#comment.fromUser.name" />的评论:</span><br>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<s:property	value="#comment.content" /></li>
			</s:iterator>
		</ul>
		<input type="hidden" id="inputCurrentPage" value="<s:property value="#request.currentPage"/>">
		<input type="hidden" id="inputPageCount" value="<s:property value="#request.pageCount"/>">
		<ul id="ulPage" title="页数" class="breadcrumb">
			<li id="lastPageLi">
				<s:url escapeAmp="false" var="lastPageUrl" value="comment_getComments"><s:param name="toUser.id" value="%{#session.currentUser.id}"/><s:param name="page" value="%{#request.lastPage}" /><s:param name="commentPerPage" value="%{commentPerPage}"/></s:url>
				<a href="<s:property value="#lastPageUrl"/>"> 
					上一页
				</a>
			</li>
			<s:iterator value="%{#request.mapPage}" var="page">
				<li>
					<s:url escapeAmp="false" var="url" value="comment_getComments">
						<s:param name="page" value="%{#page.value}" />
						<s:param name="commentPerPage" value="%{commentPerPage}"></s:param>
						<s:param name="toUser.id" value="%{#session.currentUser.id}" />
					</s:url> 
					<a href="<s:property value="#url"/>">
						<s:property	value="#page.key" />
					</a>
				</li>
			</s:iterator>
			<li id="nextPageLi">
				<s:url escapeAmp="false" var="nextPageUrl" value="comment_getComments"><s:param name="toUser.id" value="%{#session.currentUser.id}"/><s:param name="page" value="%{#request.nextPage}" /><s:param name="commentPerPage" value="%{commentPerPage}"/></s:url>
				<a href="<s:property value="#nextPageUrl"/>"> 
					下一页
				</a>
			</li>
			<li>
				<s:url escapeAmp="false" var="totalPageUrl" value="comment_getComments"><s:param name="toUser.id" value="%{#session.currentUser.id}"/><s:param name="page" value="%{#request.pageCount}" /><s:param name="commentPerPage" value="%{commentPerPage}"/></s:url>
				共&nbsp;<a href="<s:property value="#totalPageUrl"/>">${requestScope.pageCount }</a>&nbsp;页
			</li>
		</ul>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>
</body>
</html>