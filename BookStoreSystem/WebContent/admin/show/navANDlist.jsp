<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<nav class="navbar navbar-default" style="background-color: #337AB7;">
	<!-- navbar-fixed-top -->
	<div class="container">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">图书管理系统</a>
			</div>

			<ul class="nav navbar-nav">
				<li><a href="Servlet?do=toAdmin">后台首页</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">

				<li><a>欢迎您，管理员</a></li>

				<li><a href="Servlet">退出登陆</a></li>
			</ul>

		</div>
	</div>
</nav>






<!--导航栏结束-->
<div class="container">
	<div class="row">
		<!-- 左侧边栏开始 -->

		<div class="col-md-2">
			<div class="list-group">
				<li class="list-group-item active">用户管理</li> <a href="UserServlet"
					class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户列表</a>
				<a href="UserServlet?#AddUser" data-toggle="tab"
					class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加用户</a>
				<li class="list-group-item active">图书管理</li> <a href="BookServlet"
					class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图书列表</a>
				<a href="#AddBook" data-toggle="tab" class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加图书</a>
				<li class="list-group-item active">图书类别管理</li> <a
					href="CategoryServlet" class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图书类别列表</a>
				<a href="#AddBookCategory" data-toggle="tab" class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加图书类别</a>
			</div>
		</div>

		<div class="col-md-1"></div>