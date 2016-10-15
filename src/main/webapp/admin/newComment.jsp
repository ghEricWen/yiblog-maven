<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新评论</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript">

	function onReplyButtonClick(toUserId,commentId){
		$("#toUserId").val(toUserId);
		$("#commentId").val(commentId);
		$("#replyForm").show();
	}


	$(document).ready(function(){
		$("#buttonHideForm").click(function(){
			$("#replyForm").hide();
		});
		
		$("#submitReply").click(function(){
			//alert($("#toUserId").attr("name") + "=" + $("#toUserId").val());
			//alert($("#fromUserId").attr("name") + "=" + $("#fromUserId").val());
			//alert($("#replyContent").attr("name") + "=" + $("#replyContent").val());
			//alert($("#commentId").attr("name") + "=" + $("#commentId").val());
			if($("#replyContent").val()==""){
				alert("回复内容为空");
				return;
			}
				
			var submitUrl = $("#replyForm").attr("action") + "?" + 
					$("#toUserId").attr("name") + "=" + $("#toUserId").val() + "&" +
					$("#fromUserId").attr("name") + "=" + $("#fromUserId").val() + "&" +
					$("#replyContent").attr("name") + "=" + $("#replyContent").val() + "&" + 
					$("#commentId").attr("name") + "=" + $("#commentId").val();
			$.ajax({
				url:submitUrl,
				type:"GET",
				dataType:"text",
				success : function(text){
					alert("已回复");
				},
				error:function(){}
			});
		});
	});
</script>
</head>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>
	<div class="container-fluid container">
		<div class="row">
			<div class="col-xs-1">
				<s:url action="comment_getNewComment" var="newCommentUrl">
					<s:param name="toUser.id" value="%{#session.currentUser.id}"/>
				</s:url>
				<a class="btn btn-primary active" href="<s:property value="#newCommentUrl"/>">新评论</a>
			</div>
			<div class="col-xs-1">
				<s:url action="comment_getComments" var="allComments" escapeAmp="false">
						<s:param name="page" value="1"/>
						<s:param name="commentPerPage" value="10"/>
						<s:param name="toUser.id" value="%{#session.currentUser.id}"/>
					</s:url>
				<a class="btn btn-primary" href="<s:property value="#allComments"/>">全部评论</a>
			</div>
			
			<div class="col-xs-10"></div>
		</div>
		<br>
		
		<div>
			<form id="replyForm" action="${pageContext.request.contextPath }/reply_onlySave" style="display:none;margin-bottom:20px;">
				<a href="#" id="buttonHideForm" class="btn btn-primary">收起</a><br>
				<input type="hidden" id="toUserId" name="toUser.id" value="">
				<input type="hidden" id="commentId" name="comment.id" value="">
				<input type="hidden" id="fromUserId" name="fromUser.id" value="${sessionScope.currentUser.id }">
				<textarea id="replyContent" class="form-control" name="reply.content" rows="2" cols="40"></textarea>
				<a class="btn btn-primary" href="#" id="submitReply">提交</a> 
			</form>
		</div>
		
		<div style="width:50%;">
			<ul class="list-group">
				<s:iterator value="%{#request.newComments}" var="comment">
					<li class="list-group-item">
						<div class="row">
							<div class="col-xs-8">
								<span><s:property value="#comment.commenttime"/>来自<s:property value="#comment.fromUser.name" />的评论:</span><br>
							</div>
							<s:url action="comment_markRead" var="markReadUrl" escapeAmp="false">
								<s:param name="comment.id" value="%{#comment.id}"/>
								<s:param name="toUser.id" value="%{#session.currentUser.id}"/>
							</s:url>
							<div class="col-xs-4">
								<button class="btn btn-primary" id="buttonReply" onclick="onReplyButtonClick(<s:property value="#comment.fromUser.id"/>,<s:property value="#comment.id"/>)">回复</button>
								<a class="btn btn-primary" href="<s:property value="#markReadUrl"/>">标记为已读</a>
							</div>
						</div>
						
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:property	value="#comment.content" />
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>

	<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>
</body>
</html>