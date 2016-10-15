<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看文章</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/blog.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
<script type="text/javascript">
function hideForms(count){
	for(var i=0;i<count;i++){
		var id = "#replyForm" + i;
		var replyUlId = "#replyUl" + i;
		$(id).hide();
		$(replyUlId).hide();
	}
}

function showOrHideForm(count,toUser,who){
	
	var formid="#replyForm"+count;
	var buttonid = who;
	var toUserInputid="#replyToUser"+count;
	if($(buttonid).val()=="1"){
		if(!$("#currentUserId").attr("value")){
			alert("you must login first");
			return ;
		}
		$(buttonid).html("收起");
		$(buttonid).val("0");
		$(toUserInputid).attr("value",toUser);
		$(formid).show();
	}else if($(buttonid).val()==0){
		$(buttonid).html("回复");
		$(buttonid).val("1");
		$(formid).hide();
	}	
}

function showOrHideReply(count,content){
	var replyUlId = "#replyUl" + count;
	var replyButtonId = "#showReplyButton" + count;
	if($(replyButtonId).html()!="收起"){
		$(replyButtonId).html("收起");
		$(replyUlId).show();	
	}else {
		$(replyButtonId).html(content);
		$(replyUlId).hide();
	}
}

$(document).ready(function(){
	$("#commentButton").click(function(){
		if(!$("#currentUserId").attr("value")){
			alert("you must login first");
			return ;
		}
		if($("#commentButton").html()=="添加评论"){
			$("#commentButton").html("收起");
			$("#commentForm").show();
		}else if($("#commentButton").html()=="收起"){
			$("#commentButton").html("添加评论");
			$("#commentForm").hide();
		}

		
	});
	if($("#currentUserPower").attr("value") == 1){
		$("#editPassageDiv").show();
	}
});

</script>

<style type="text/css">
#divMain {
	background-color: #fff;
}

#divInfo {
	padding: 30px;
}
</style>
</head>
<body onload="hideForms(${requestScope.listCommentSize})">
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container-fluid container" id="divMain">
		<div class="row">
			<div class="col-xs-12">

				<input type="hidden" id="currentUserId" value="${sessionScope.currentUser.id }"> 
				<input type="hidden" id="currentUserPower" value="${sessionScope.currentUser.power.id }">

				<div style="display: none;" id="editPassageDiv" class="col-xs-2">
					<s:url var="url" action="pass_editPassage">
						<s:param name="passage.id" value="%{#request.passage.id}" />
					</s:url>
					<a href="<s:property value="#url"/>" class="btn btn-primary">编辑文章</a>
				</div>
				
				<div class="blog-post">
					<h2 class="blog-post-title text-center">${passage.title }</h2>
					<p class="blog-post-meta text-center"><s:property value="#request.passage.writetime"/>&nbsp;由<a href="${pageContext.request.contextPath }/about.jsp">${passage.author.name }</a>发布&nbsp;阅读次数&nbsp;${passage.readtime }</p>
					<div class="blog-main">${passage.content }</div>
				</div>
								
				<div class="row">
					<div class="col-xs-8">
						<button class="btn btn-primary" id="commentButton">添加评论</button>
						<br> <br>
						<form action="${pageContext.request.contextPath }/comment_save"
							method="POST" id="commentForm" style="display: none;">
							<input type="hidden" name="fromUser.id"	value="<s:property value="%{#session.currentUser.id}"/>" /> 
							<input type="hidden" name="toUser.id" value="<s:property value="%{#request.passage.author.id}"/>" />
							<input type="hidden" name="passage.id" value="<s:property value="%{#request.passage.id}" />" />
							<textarea class="form-control" name="comment.content" cols="50" rows="2"></textarea>
							<input type="submit" value="提交">
						</form>
						<br> <br>
						<ul class="list-group">
							<s:iterator value="%{#request.mapComment}" status="vs" var="item">
								<li class="list-group-item">
									<div class="row">
										<div class="col-xs-9">
											<s:property value="#item.key.commenttime" />
											<s:a action="user_getById?user.id=%{#item.key.fromUser.id}">
												<s:property value="#item.key.fromUser.name" />
											</s:a>
											&nbsp;:&nbsp;
											<s:property value="#item.key.content" />
										</div>
										<div class="col-xs-3">
											<button class="btn"
												id="showReplyButton<s:property value="#vs.index"/>"
												type="button"
												onclick="showOrHideReply(<s:property value="#vs.index"/>,'共<s:property value="#item.key.replyCount"/>条回复')">
												共
												<s:property value="#item.key.replyCount" />
												条回复
											</button>
											<button class="btn"
												id="replyCommentButton<s:property value="#vs.index"/>"
												type="button" value="1"
												onclick="showOrHideForm(<s:property value="#vs.index"/>,<s:property value="#item.key.fromUser.id"/>,'#replyCommentButton<s:property value="#vs.index"/>')">回复</button>
											<br>
										</div>
									</div>
									<ul class="list-group"
										id="replyUl<s:property value="#vs.index"/>">
										<s:iterator value="#item.value" var="reply" status="replyVs">
											<li class="list-group-item">
												<div class="row">
													<div class="col-xs-10">
														<s:property value="#reply.replytime" />
														<s:a action="user_getById?user.id=%{#reply.fromUser.id}">
															<s:property value="#reply.fromUser.name" />
														</s:a>
														&nbsp;回复&nbsp;
														<s:a action="user_getById?user.id=%{#reply.toUser.id}">
															<s:property value="#reply.toUser.name" />
														</s:a>
														&nbsp;:&nbsp;
														<s:property value="#reply.content" />
													</div>
													<div class="col-xs-2">
														<button class="btn"
															id="replyButton<s:property value="#replyVs.index"/>"
															type="button" value="1"
															onclick="showOrHideForm(<s:property value="#vs.index"/>,
																		<s:property value="#reply.fromUser.id"/>,
																		'#replyButton<s:property value="#replyVs.index"/>')">回复</button>
													</div>
												</div>
											</li>
										</s:iterator>
									</ul>
									<form id="replyForm<s:property value="#vs.index"/>"
										action="${pageContext.request.contextPath }/reply_save"
										method="POST">
										<input type="hidden" name="comment.id" value="<s:property value="#item.key.id"/>"> 
										<input type="hidden" name="fromUser.id"	value="${sessionScope.currentUser.id }"> 
										<input type="hidden" name="passageid" value="${passage.id }">
										<input type="hidden" id="replyToUser<s:property value="#vs.index"/>" value="" name="toUser.id">
										<textarea class="form-control" rows="2" cols="40" name="reply.content"></textarea>
										<input type="submit" value="提交">
									</form>
								</li>
							</s:iterator>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="bottom.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>
</body>
</html>