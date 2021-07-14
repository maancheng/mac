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
		<h2 style="margin: 50px 0 0 80px;">欢迎进入后台！</h2>

		<div class="tab-content">

			<div class="tab-pane" id="Order"></div>
		</div>
	</div>
	<!-- 主体部分结束 -->

	</div>
	</div>

</body>

</html>