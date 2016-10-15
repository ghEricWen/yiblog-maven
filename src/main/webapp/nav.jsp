<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="dist/css/navbar.css" rel="stylesheet">

    
    <script type="text/javascript">
		$(document).ready(function() {
			
			if ($("#currentUserPower").attr("value") == 1) {
				$("#liComment").show();
				$("#liReply").show();
				$("#lisignin").hide();
				$("#lisignup").hide();
				$("#lilogout").show();
				$("#liViewUsers").show();
				$("#liAddPassage").show();
				$("#liAddCates").show();
				$("#liMyProfile").show();
			} else if ($("#currentUserPower").attr("value") == 2) {
				$("#liReply").show();
				$("#liNotification").show();
				$("#lisignin").hide();
				$("#lisignup").hide();
				$("#lilogout").show();
				$("#liMyProfile").show();
			}		
		});
		
		$(document).ready(function(){
			if($("#currentUserPower").val()==""){
				return;
			}
			$.ajax({
				url:$("#inputCommentUrl").val(),
				type:"GET",
				dataType:"text",
				success:function(text){
					$("#spanComment").html("(" + text + ")");
				},
				error:function(){}
			});
			$.ajax({
				url:$("#inputReplyUrl").val(),
				type:"GET",
				dataType:"text",
				success:function(text){
					$("#spanReply").html("(" + text + ")");
				},
				error:function(){
				}
			});
		});
	</script>
    
  </head>

  <body>
	<input type="hidden" id="currentUserPower"
		value="${sessionScope.currentUser.power.id }">
	<input type="hidden" id="inputCommentUrl" value="${pageContext.request.contextPath }/comment_getCount?toUser.id=${sessionScope.currentUser.id}">
	<input type="hidden" id="inputReplyUrl" value="${pageContext.request.contextPath}/reply_getCount?toUser.id=${sessionScope.currentUser.id}">
	<div class="container-fluid container">
      <!-- Static navbar -->
      <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">WJY的笔记本</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
            	<li><a href="${pageContext.request.contextPath }/about.jsp">关于</a></li>
            	<li class="dropdown">
            	  <a href="#" class="dropdown-toggle" data-toggle="dropdown">分类<span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <s:iterator value="%{#request.listCategory}" var="category"><s:url action="pass_page" var="categoryUrl" escapeAmp="false"><s:param name="page" value="1"/><s:param name="passagePerPage" value="10"/><s:param name="category.id" value="%{#category.id}"/></s:url><li><a href="<s:property value="#categoryUrl"/>"><s:property value="#category.name"/></a></li></s:iterator>
                  </ul>
            </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li id="lisignin"><a
						href="${pageContext.request.contextPath }/signin.jsp">登录</a></li>
					<li id="lisignup"><a
						href="${pageContext.request.contextPath }/signup.jsp">注册</a></li>
					<li id="liViewUsers" style="display:none;"><s:a action="user_viewUsers?page=1&userPerPage=10">查看用户</s:a></li>
					<li id="liAddPassage" style="display:none;"><a href="${pageContext.request.contextPath}/admin/addPass.jsp">写文章</a></li>
					<li id="liAddCates" style="display:none;"><a href="${pageContext.request.contextPath }/admin/addCates.jsp">添加分类/子分类</a></li>
					<s:url action="user_getById" var="url">
						<s:param name="user.id" value="%{#session.currentUser.id}"/>
					</s:url>
					<li id="liMyProfile" style="display:none;"><a href="<s:property value="#url"/>">我的资料</a></li>
					<s:url action="comment_getNewComment" var="newCommentUrl">
						<s:param name="toUser.id" value="%{#session.currentUser.id}"/>
					</s:url>
					<li id="liComment" style="display:none;"><a href="<s:property value="#newCommentUrl"/>">新评论<span id="spanComment" class="text-danger">(0)</span></a></li>
					<s:url action="reply_getNewReply" var="newReplyUrl">
						<s:param name="toUser.id" value="%{#session.currentUser.id}"/>
					</s:url>
					<li id="liReply" style="display:none;"><a href="<s:property value="#newReplyUrl"/>">新回复<span id="spanReply" class="text-danger">(0)</span></a></li>
					<li id="lilogout" style="display:none;"><s:a action="user_logout">退出</s:a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
	</div>

  </body>
</html>
