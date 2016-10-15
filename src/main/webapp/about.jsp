<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关于</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/bootstrap.css">
<link rel="shortcut icon" href="icon.ico">
<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/jquery.js"></script>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container-fluid container">
		<h1>关于我</h1>
	
		<h2>基本信息</h2>
		<span style="font-size: 16px">温景毅（Paul），大三学生（2016-2017），现就读于<a href="http://baike.baidu.com/link?url=Da8RrwqgTkt2i6U9MEEu5G2ssszrtVyAvalwZuPNSbkr4mnkb24Jc80WN3bmK2LtC9kCD_I6uhEy2NmM-a2IGb9NSaJK0EDgq2GusK1OgASnsoiJSh65rFvLSaRx38UzobFULrWssqyE6gDImUCFjTZ1pHK0m7JvM7RPObE-d6HxIy1QAZTjT0VCIiXAxL2x" target="_blank">仲恺农业工程学院</a>，信息科学与技术学院，计算机科学与技术专业，程序猿一枚。</span>
	
		<h2>常用程序语言</h2>
		<span style="font-size: 16px">java，c/cpp</span>
	
		<h2>联系方式</h2>
		<span style="font-size: 16px">
		    邮箱	： wen229267643@gmail.com<br />
			微信 ： v229267643<br />
			企鹅 ： 229267643<br />
			github：https://github.com/gh-paulwen<br />
			欢迎骚扰
		</span>
	
		<h1>
			<br /> 关于本网站
		</h1>
		<h2>网站的名字</h2>
		<span style="font-size: 16px;">
		  WJY的笔记本，WJY是我名字的首字母。而笔记本则是这个网站的准确定位，她就是我的笔记本，同时显得老实，谦逊。<br>
		</span>
		<h2>域名的选择</h2>
		<span style="font-size: 16px;">
		  www.wengjingyi.tech ， wengjingyi是我名字的全拼，选择.tech后缀完全是因为便宜。<br>
		</span>
		<h2>本网站的github地址</h2>
        <a href="https://github.com/gh-paulwen/yiblog" target="_blank">https://github.com/gh-paulwen/yiblog</a>
		<br><br>
		<span style="font-size: 16px">这个网站是用ssh框架所搭建的一个记录学习笔记的东西，由于自己的前端水平的确是菜，所以页面看起来不是那么好看（怪自己不太会审美，总之简洁就好）。有需要的话，后期可能会找个bootstrap的模板套上算了。<br />
			搭这个网站是大二末期做数据库课程设计的时候产生的想法，目的主要有一下几个：
		</span>
		
		<ol>
			<li><span style="font-size: 16px">检验自己的学习成果。</span></li>
			<li><span style="font-size: 16px">用于记下自己的学习笔记，学了这么久发现自己并没有做学习笔记，这是一个bug。</span></li>
			<li><span style="font-size: 16px">回顾，整理以前所学知识。</span></li>
			<li><span style="font-size: 16px">希望认识到更多志同道合的人吧。</span></li>
			<li><span style="font-size: 16px">对自己会是一种激励。带着充实这个博客的心，我想我会更有动力去学习更多的东西。</span></li>
			<li><span style="font-size: 16px">练一下文笔和表达。这的确是一大短板。</span></li>
		</ol>
		<span style="font-size: 16px">
			博客内容仅仅是个人的见解和总结，如果有错误的话请指正。
		</span>
			
		<h1>关于捐赠</h1>
		<span style="font-size: 16px">众多类似的博客都有设置捐赠这东西，我也有考虑要不要。鉴于阿里云的服务器有学生优惠，良心，就没有了捐赠的需求。</span>
		<br /> 
		<br />
		
		<h1>大三老鸟的内心想法</h1>
		<span style="font-size: 16px">
		  大学（我想说我所在的学校），很坑的就是学校所设的课程实用性真的不强（如组成原理，电路等），对于参加工作帮助不大，尤其是大学毕业之后都忘光了。<br>
		  实用的要数，c/c++，数据库等。但是老师都是教点皮毛而已，真是"师傅带进门，修行靠个人"。<br>
		  所以，自学是唯一的出路。我之所以能搭建这个网站，全都是自学的，虽然并不是很完善很合理，但是能满足记笔记，分享这些知识的需求。未来，自己也一定会不断努力，学习更多的知识、技能，才可以在大四找份好实习，毕业后找份好工。<br>
		</span>
		<br>
		<h3>
			those who have fought for their own bloody future will <span style="font-style:italic;font-size:18px;color:red;">Not regret</span> . I am fighting for mine , what about you ?<br>
		</h3>
		
		<jsp:include page="bottom.jsp"></jsp:include>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/dist/js/bootstrap.js"></script>
</body>
</html>