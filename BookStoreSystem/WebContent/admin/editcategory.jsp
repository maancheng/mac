<%@ page language="java" import="java.util.*,beans.*" 
	pageEncoding="UTF-8"%>
<%
	Category category = (Category) session.getAttribute("category");

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

				<form class="form-horizontal" role="form" action="../CategoryServlet?do=updCategory&CategoryID=<%= category.getCategoryID() %>" method="post">
				
				<div class="form-group">
					<label class="control-label col-md-2">类别ID：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" value="<%= category.getCategoryID() %>" name="categoryID" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">类别名称：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" value="<%= category.getCategoryName() %>" name="categoryname" />
					</div>
				</div>
				
				

				<div class="form-group">
					<div class="col-md-2"></div>
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary pull-right" style="margin-right: 10px;">修改</button>
						<button type="reset" class="btn btn-default pull-right" style="margin-right: 10px;">复原</button>
					</div>
				</div>

			</form>
			</div>
			<!-- 主体部分结束 -->

		</div>
	</div>



</body>
</html>