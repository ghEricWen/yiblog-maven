<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WJY的笔记本</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<link
	href="//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
<style type="text/css">
#divAnnouncement {
	padding: 5px;
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>

	<div class="container-fluid container">
	    <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1260575777'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1260575777' type='text/javascript'%3E%3C/script%3E"));</script>
		<div class="row">
			<div class="col-sm-8 col-md-9 main">
				<h3>阅读量</h3>
				<ul class="list-group">
					<s:iterator value="%{#request.mostRead}" var="passage">
						<s:url action="pass_get" var="url">
							<s:param name="passage.id" value="#passage.id"></s:param>
						</s:url>
						<li class="list-group-item">
							<h4>
								<a href="<s:property value="#url"/>"> <s:property
										value="#passage.title" />
								</a> <small>&nbsp;&nbsp;分类:<s:property
										value="#passage.subCategory.name" /> &nbsp;&nbsp; 阅读次数:<s:property
										value="#passage.readtime" />
								</small>
							</h4>
						</li>
					</s:iterator>
				</ul>

				<h3>最近</h3>
				<ul class="list-group">
					<s:iterator value="%{#request.latest}" var="passage">
						<s:url action="pass_get" var="url">
							<s:param name="passage.id" value="#passage.id"></s:param>
						</s:url>
						<li class="list-group-item">
							<h4>
								<a href="<s:property value="#url"/>"> <s:property
										value="#passage.title" />
								</a> <small>&nbsp;&nbsp;分类:<s:property
										value="#passage.subCategory.name" /> &nbsp;&nbsp; 撰写时间:<s:property
										value="#passage.writetime" />
								</small>
							</h4>
						</li>
					</s:iterator>
				</ul>
			</div>
			<div class="col-sm-4 col-md-3">
			  <br><br>
			  <div class="well">
			    <h3>learn by coding</h3>
			    <h3>learn through code</h3>
			  	建站时间：<s:property value="#request.buildSite.time"/><br>
			  	最后更新：<s:property value="#request.lastUpdate.time"/><br>
			  </div>
			</div>

		</div>
		<jsp:include page="bottom.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>
</body>
</html>