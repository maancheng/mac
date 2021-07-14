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
	ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
	Iterator<Category> iter_category = categories.iterator();
	Category onecategory = new Category();
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
			<div class="tab-pane active" id="BookCategory">

				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">用户管理</div>

							<table class="table table-striped">
								<tr>
									<th>类别编号</th>
									<th>类别名称</th>

									<th colspan="2">操作</th>
								</tr>

								<%
									while (iter_category.hasNext()) {
										onecategory = iter_category.next();

										out.println("<tr>");
										out.println("<td>" + onecategory.getCategoryID() + "</td>");
										out.println("<td>" + onecategory.getCategoryName() + "</td>");
										out.println("<td>" + "<a href=" + request.getContextPath()
												+ "/CategoryServlet?do=toEditCategory&CategoryID=" + onecategory.getCategoryID() + ">修改</a>"
												+ "</td>");
										out.println(
												"<td>" + "<a href=" + request.getContextPath() + "/CategoryServlet?do=delCategory&CategoryID="
														+ onecategory.getCategoryID() + " onclick=\"return confirm('确认删除？')\">删除</a>" + "</td>");
										out.println("</tr>");
									}
								%>

							</table>

						</div>
					</div>
					<div class="col-md-6"></div>
				</div>
			<!--	<a class="btn btn-primary" href="#AddBookCategory" data-toggle="tab">添加图书类别</a> -->
			</div>
			<div class="tab-pane" id="AddBookCategory">
				<jsp:include page="../addcategory.jsp"></jsp:include>
			</div>
		</div>
		<!-- 主体部分结束 -->
	</div>
	</div>
	</div>
</body>