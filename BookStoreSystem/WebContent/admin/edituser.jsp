<%@ page language="java" import="java.util.*,beans.*" 
	pageEncoding="UTF-8"%>
<%
	User user = (User) session.getAttribute("user");

	//取BookServlet传过来的book对象
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>图书管理系统—后台</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src="../js/jquery-2.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
<style type="text/css">
      	.navbar-default .navbar-nav>li>a,.navbar-brand,.navbar-default{
      		color: #fff !important;
      	}

      </style>
	</head>

	<body>
		<!--导航栏开始-->
		<jsp:include page="navANDlist.jsp" />
			<!-- 主体部分开始 -->
			<div class="col-md-9">

				<form class="form-horizontal" role="form"
	action="../UserServlet?do=updUser&UserID=<%=user.getUserID()%>"
	method="post">
	<div class="form-group">
		<label class="control-label col-md-2">姓名：</label>
		<div class="col-md-3">
			<input type="text" class="form-control" value="<%=user.getName()%>"
				name="name" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">性别：</label>
		<div class="col-md-3 radio">
			<label> <input type="radio" name="gender" value="男"
				<%if ("男".equals(user.getGender())) {
				out.print("checked");
			}%> />男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="gender" value="女"
				<%if ("女".equals(user.getGender())) {
				out.print("checked");
			}%> />女
			</label>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">年龄：</label>
		<div class="col-md-3">
			<input type="text" class="form-control" value="<%=user.getAge()%>"
				name="age" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">用户名：</label>
		<div class="col-md-3">
			<input type="text" class="form-control"
				value="<%=user.getUserName()%>" name="username" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">密码：</label>
		<div class="col-md-3">
			<input type="text" class="form-control"
				value="<%=user.getPassword()%>" name="password" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">管理员：</label>
		<div class="col-md-3 radio">
			<label> <input type="radio" name="admin" value="1"
				<%if (1==user.getAdmin()) {
				out.print("checked");
			}%> />是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="admin" value="0"
				<%if (0==user.getAdmin()) {
				out.print("checked");
			}%> />否
			</label>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">电话：</label>
		<div class="col-md-3">
			<input type="text" class="form-control"
				value="<%=user.getPhone()%>" name="phone" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">住址：</label>
		<div class="col-md-3">
			<input type="text" class="form-control"
				value="<%=user.getAdress()%>" name="adress" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-md-2"></div>
		<div class="col-md-3">
			<button type="submit" class="btn btn-primary pull-right"
				style="margin-right: 10px;">修改</button>
			<button type="reset" class="btn btn-default pull-right"
				style="margin-right: 10px;">复原</button>
		</div>
	</div>

</form>
			</div>
			<!-- 主体部分结束 -->

		</div>
	</div>



</body>
</html>