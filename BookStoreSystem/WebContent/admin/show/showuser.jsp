<%@ page language="java" import="java.util.*,beans.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>图书管理系统—后台</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script>
${msg}
</script>
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->

<%
	@SuppressWarnings("unchecked")
	ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
	Iterator<User> iter_user = users.iterator();
	User oneuser = new User();
%>
<style type="text/css">
.navbar-default .navbar-nav>li>a, .navbar-brand, .navbar-default {
	color: #fff !important;
}
</style>
</head>

<body>
	<!--导航栏开始-->
	<jsp:include page="navANDlist.jsp" />

	<!-- 主体部分开始 -->
	<div class="col-md-9">
		<div class="tab-content">
			<div class="tab-pane active" id="User">
				<div class="panel panel-primary">
					<div class="panel-heading">用户管理</div>

					<table class="table table-striped">
						<tr>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
							<th>用户名</th>
							<th>密码</th>
							<th>管理员</th>
							<th>电话</th>
							<th>住址</th>
							<th colspan="2">操作</th>
						</tr>

						<%
							while (iter_user.hasNext()) {
								oneuser = iter_user.next();
								out.println("<tr>");
								out.println("<td>" + oneuser.getName() + "</td>");
								out.println("<td>" + oneuser.getGender() + "</td>");
								out.println("<td>" + oneuser.getAge() + "</td>");
								out.println("<td>" + oneuser.getUserName() + "</td>");
								out.println("<td>" + oneuser.getPassword() + "</td>");
								out.print("<td>");
								if (1 == oneuser.getAdmin()) {
									out.print("是");
								} else
									out.print("否");
								out.println("</td>");
								out.println("<td>" + oneuser.getPhone() + "</td>");
								out.println("<td>" + oneuser.getAdress() + "</td>");
								out.println("<td>" + "<a href=" + request.getContextPath() + "/UserServlet?do=toEditUser&UserID="
										+ oneuser.getUserID() + ">修改</a>" + "</td>");
								out.println("<td>" + "<a href=" + request.getContextPath() + "/UserServlet?do=delUser&UserID="
										+ oneuser.getUserID() + " onclick=\"return confirm('确认删除？')\">删除</a>" + "</td>");
								out.println("</tr>");
							}
						%>
					</table>

				</div>
				<!-- <a class="btn btn-primary" href="#AddUser" data-toggle="tab">添加用戶</a> -->
			</div>
			<div class="tab-pane" id="AddUser">
				<jsp:include page="../adduser.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<!-- 主体部分结束 -->
	</div>
	</div>
</body>