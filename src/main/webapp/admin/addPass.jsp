<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加文章</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/ckfinder/ckfinder.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/subCategory_getXmlByCategory?category.id=" + $("#categorySelect").val(),
			type:"GET",
			dataType:"xml",
			success:function(xml){
				$("#subCategorySelect").empty();
				var root = xml.documentElement;
				var arr = root.getElementsByTagName("subcategory");
				for(var i = 0;i < arr.length ;i++){
					var subCate = arr[i];
					var attrs = subCate.attributes;
					var nodeOption = document.createElement("option");
					var nodeText = document.createTextNode(attrs[1].value);
					nodeOption.appendChild(nodeText);
					nodeOption.setAttribute("value",attrs[0].value);
					$("#subCategorySelect").append(nodeOption);
				}					
			},
			error: function(){}
		});
		$("#categorySelect").change(function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/subCategory_getXmlByCategory?category.id=" + $("#categorySelect").val(),
				type:"GET",
				dataType:"xml",
				success:function(xml){
					$("#subCategorySelect").empty();
					var root = xml.documentElement;
					var arr = root.getElementsByTagName("subcategory");
					for(var i = 0;i < arr.length ;i++){
						var subCate = arr[i];
						var attrs = subCate.attributes;
						var nodeOption = document.createElement("option");
						var nodeText = document.createTextNode(attrs[1].value);
						nodeOption.appendChild(nodeText);
						nodeOption.setAttribute("value",attrs[0].value);
						$("#subCategorySelect").append(nodeOption);
					}					
				},
				error: function(){}
			});
		});
	});
</script>
</head>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>
	<div class="container-fluid container">
	
	<h1>添加文章</h1>
	<s:form action="pass_save" method="POST" theme="simple">
		<s:hidden name="author.id" value="%{#session.currentUser.id}"></s:hidden>
		<table >
			<tr>
				<td>主标题 : </td>
				<td><s:select id="categorySelect" list="%{#request.listCategory}"
			listKey="id" listValue="name" name="category.id"></s:select></td>
				<td align="right">子标题 : </td>
				<td align="left"><select name="subCategory.id" id="subCategorySelect"></select></td>
			</tr>
			<tr>
				<td colspan="2">标题 : </td>
				<td colspan="2"><s:textarea name="passage.title" cols="50" rows="2"></s:textarea></td>
			</tr>
		</table>
		<textarea name="passage.content"></textarea>
	</s:form>
	
	</div>
	
	
	<script type="text/javascript">
			var editor = CKEDITOR.replace('passage.content',{
				height : '400px',
				filebrowserBrowseUrl : '${pageContext.request.contextPath}/ckfinder/ckfinder.html',
				filebrowserImageBrowseUrl : '${pageContext.request.contextPath}/ckfinder/ckfinder.html?Type=Images',
				filebrowserFlashBrowseUrl : '${pageContext.request.contextPath}/ckfinder/ckfinder.html?Type=Flash',
				filebrowserUploadUrl : '${pageContext.request.contextPath}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
				filebrowserImageUploadUrl : '${pageContext.request.contextPath}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
				filebrowserFlashUploadUrl : '${pageContext.request.contextPath}/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
				filebrowserWindowHeight : '50%',
				filebrowserWindowWidth : '70%'
			});	
			
			CKFinder.setupCKEditor(editor);
			
		</script>
</body>
</html>