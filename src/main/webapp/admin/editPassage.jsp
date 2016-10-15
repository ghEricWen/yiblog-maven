<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑文章</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/ckfinder/ckfinder.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript">
	function loadSubCategory(categoryId,flag){
		$.ajax({
			url:"${pageContext.request.contextPath}/subCategory_getXmlByCategory?category.id="+categoryId,
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
				if(flag){
					$("#hiddenCategoryId").val($("#categorySelect").val());
					$("#hiddenSubCategoryId").val($("#subCategorySelect").val());	
				}				
			},
			error:function(){}
		});
	}

	$(document).ready(function(){
		loadSubCategory($("#categorySelect").val(),false);
		//$("#categorySelect").val($("#hiddenCategoryId").val());
		//$("#subCategorySelect").val($("#hiddenSubCategoryId").val());
		
		$("#categorySelect").change(function(){
			loadSubCategory($("#categorySelect").val(),true);
		});
		
		$("#subCategorySelect").change(function(){
			$("#hiddenCategoryId").val($("#categorySelect").val());
			$("#hiddenSubCategoryId").val($("#subCategorySelect").val());
		});
	});
</script>
</head>
<body>
	<jsp:include page="../nav.jsp"></jsp:include>
	<div class="container-fluid container">
	<h1>编辑文章</h1>
	<s:select id="categorySelect" list="%{#request.listCategory}" listKey="id" listValue="name"></s:select>
	<select id="subCategorySelect"></select>
	<s:form action="pass_submitUpdate" method="POST" theme="simple">
		<s:hidden name="passage.id" value="%{#request.passageGet.id}"/>
		<s:hidden name="author.id" value="%{#session.currentUser.id}"/>
		<input type="hidden" id="hiddenCategoryId" name="category.id" value="${requestScope.passageGet.category.id }">
		<input type="hidden" id="hiddenSubCategoryId" name="subCategory.id" value="${requestScope.passageGet.subCategory.id} ">
		
		<table >
			<tr>
				<td colspan="2">标题 : </td>
				<td>
					<textarea rows="2" cols="40" name="passage.title" id="passageTitle"><s:property value="#passageGet.title"/></textarea>
				</td>
			</tr>
		</table>
		<textarea name="passage.content" id="passageContent">${requestScope.passageGet.content }</textarea>
	</s:form>
	
	</div>
	
	
	<script type="text/javascript">
			var editor = CKEDITOR.replace('passage.content',{
				height : '400px',
				filebrowserBrowseUrl : 'ckfinder/ckfinder.html',
				filebrowserImageBrowseUrl : 'ckfinder/ckfinder.html?Type=Images',
				filebrowserFlashBrowseUrl : 'ckfinder/ckfinder.html?Type=Flash',
				filebrowserUploadUrl : 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
				filebrowserImageUploadUrl : 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
				filebrowserFlashUploadUrl : 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
				filebrowserWindowHeight : '50%',
				filebrowserWindowWidth : '70%'
			});	
			
			CKFinder.setupCKEditor(editor);
			
		</script>
</body>
</html>