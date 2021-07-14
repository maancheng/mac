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
	ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
	Iterator<Book> iter_book = books.iterator();
	Book onebook = new Book();
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
			<div class="tab-pane active" id="Book">
				<div class="panel panel-primary">
					<div class="panel-heading">图书管理</div>

					<table class="table table-striped">
						<tr>
							<th>书名</th>
							<th>类别</th>
							<th>作者</th>
							<th>出版社</th>
							<th>价格</th>
							<th>ISBN</th>
							<th colspan="2">操作</th>
						</tr>
						<%
							while (iter_book.hasNext()) {
								onebook = iter_book.next();

								out.println("<tr>");
								out.println("<td>" + onebook.getBookName() + "</td>");
								out.println("<td>" + onebook.getCategoryName() + "</td>");
								out.println("<td>" + onebook.getAuthor() + "</td>");
								out.println("<td>" + onebook.getPress() + "</td>");
								out.println("<td>" + onebook.getPrice() + "</td>");
								out.println("<td>" + onebook.getIsbn() + "</td>");
								out.println("<td>" + "<a href=" + request.getContextPath() + "/BookServlet?do=toEditbook&BookID="
										+ onebook.getBookID() + ">修改</a>" + "</td>");
								out.println("<td>" + "<a href=" + request.getContextPath() + "/BookServlet?do=delBook&BookID="
										+ onebook.getBookID() + " onclick=\"return confirm('确认删除？')\">删除</a>" + "</td>");
								out.println("</tr>");
							}
						%>

					</table>

				</div>

			<!--	<a class="btn btn-primary" href="#AddBook" data-toggle="tab">添加图书</a> -->
			</div>

			<div class="tab-pane" id="AddBook">
				<jsp:include page="../addbook.jsp"></jsp:include>
			</div>
		</div>
		<!-- 主体部分结束 -->
	</div>
	</div>
	</div>
</body>