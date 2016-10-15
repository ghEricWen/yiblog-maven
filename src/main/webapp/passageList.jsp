<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章列表</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		if ($("#inputPageCount").val() == "0") {
			$("#ulPage").hide();
		}

		if ($("#inputCurrentPage").val() == "1") {
			$("#lastPageLi").hide();
		}
		
		if ($("#inputCurrentPage").val() == $("#inputPageCount").val()) {
			$("#nextPageLi").hide();
		}
	});
</script>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container-fluid container">
		<div class="row">
			
			<div class="col-xs-9 col-lg-10 main">
				<div >
					<s:url action="pass_page" var="ascUrl" escapeAmp="false">
						<s:param name="page" value="1" />
						<s:param name="passagePerPage" value="%{passagePerPage}" />
						<s:param name="category.id" value="%{category.id}" />
						<s:param name="subCategory.id" value="%{subCategory.id}" />
					</s:url>
					<a class="btn btn-primary" href="<s:property value="#ascUrl"/>">时间升序</a>
					<s:url action="pass_page?order=reverse" var="descUrl" escapeAmp="false">
						<s:param name="page" value="1" />
						<s:param name="passagePerPage" value="%{passagePerPage}" />
						<s:param name="category.id" value="%{category.id}" />
						<s:param name="subCategory.id" value="%{subCategory.id}" />
					</s:url>
					<a class="btn btn-primary" href="<s:property value="#descUrl"/>">时间降序</a>
				</div>
				<div>
					<h1 class="text-danger text-center">文章列表&nbsp;:&nbsp;${requestScope.currentCate}</h1>
				</div>
				<br>
				<ul class="list-group">
					<s:iterator value="%{#request.listPassage}" var="passage">
						<s:url action="pass_get" var="toPassageurl">
							<s:param name="passage.id" value="%{#passage.id}" />
						</s:url>
						<li class="list-group-item">
							<h4>
								<a href="<s:property value="#toPassageurl"/>"> <s:property
										value="%{#passage.title}" />
								</a> <small>&nbsp;&nbsp;分类:<s:property
										value="#passage.subCategory.name" /> &nbsp;&nbsp; 撰写时间:<s:property
										value="#passage.writetime" />
								</small>
							</h4>
						</li>
						<br>
					</s:iterator>
				</ul>
				<input type="hidden" id="inputCurrentPage"
					value="<s:property value="#request.currentPage"/>"> <input
					type="hidden" id="inputPageCount"
					value="<s:property value="#request.pageCount"/>">
				<ul id="ulPage" title="页数" class="breadcrumb">
					<li id="lastPageLi"><s:url escapeAmp="false" var="lastPageUrl"
							value="pass_page">
							<s:param name="page" value="%{#request.lastPage}" />
							<s:param name="passagePerPage" value="%{passagePerPage}" />
							<s:param name="category" value="%{category.id}" />
							<s:param name="subCategory" value="%{subCategory.id}" />
							<s:param name="order" value="%{order}" />
						</s:url> <a href="<s:property value="#lastPageUrl"/>"> 上一页 </a></li>
					<s:iterator value="%{#request.mapPage}" var="page">
						<li><s:url escapeAmp="false" var="pageUrl" value="pass_page">
								<s:param name="page" value="%{#page.value}" />
								<s:param name="passagePerPage" value="%{passagePerPage}" />
								<s:param name="category.id" value="%{category.id}" />
								<s:param name="subCategory.id" value="%{subCategory.id}" />
								<s:param name="order" value="%{order}" />
							</s:url> <a href="<s:property value="#pageUrl"/>"> <s:property
									value="#page.key" />
						</a></li>
					</s:iterator>
					<li id="nextPageLi"><s:url escapeAmp="false" var="nextPageUrl"
							value="pass_page">
							<s:param name="page" value="%{#request.nextPage}" />
							<s:param name="passagePerPage" value="%{passagePerPage}" />
							<s:param name="category.id" value="%{category.id}" />
							<s:param name="subCategory.id" value="%{subCategory.id}" />
							<s:param name="order" value="%{order}" />
						</s:url> <a href="<s:property value="#nextPageUrl"/>"> 下一页 </a></li>
					<li><s:url escapeAmp="false" var="totalPageUrl"
							value="pass_page">
							<s:param name="page" value="%{#request.pageCount}" />
							<s:param name="passagePerPage" value="%{passagePerPage}" />
							<s:param name="category.id" value="%{category.id}" />
							<s:param name="subCategory.id" value="%{subCategory.id}" />
							<s:param name="order" value="%{order}" />
						</s:url> 共&nbsp;<a href="<s:property value="#totalPageUrl"/>">${requestScope.pageCount}</a>&nbsp;页</li>
				</ul>
			</div>
			<div class="col-xs-3 col-lg-2 sidebar">
				<h3>二级分类</h3>
				<ul class="nav nav-sidebar">
					<s:iterator value="%{#request.listSubCategory}" var="subCategory" status="vs">
						<s:url escapeAmp="false" var="subCategoryUrl" action="pass_page">
							<s:param name="page" value="1"/>
							<s:param name="passagePerPage" value="10"/>
							<s:param name="subCategory.id" value="%{#subCategory.id}"/>
						</s:url>
						<li><a href="<s:property value="#subCategoryUrl"/>"><s:property value="#subCategory.name"/></a></li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>
</body>
</html>