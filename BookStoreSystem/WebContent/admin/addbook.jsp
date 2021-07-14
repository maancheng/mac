<%@ page language="java" import="java.util.*,beans.*" pageEncoding="UTF-8"%> 
<form class="form-horizontal" role="form" action="BookServlet?do=addBook" method="post" enctype="multipart/form-data">
<%
	
//图书类别
	  @SuppressWarnings("unchecked")
	  ArrayList<Category> categories = (ArrayList<Category>)request.getAttribute("categories");
	  Iterator<Category> iter_category = categories.iterator();
	  Category onecategory = new Category();
%>
	<div class="form-group">
		<label class="control-label col-md-2">书名：</label>
		<div class="col-md-3">
			<input type="text" class="form-control" placeholder="请输入书名" name="bookname" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">图片：</label>
		<div class="col-md-3">
			<input type="file" class="form-control" name="image" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">类别：</label>
		<div class="col-md-3">
			<select name="categoryID" class="btn btn-default">
	
<%
	  //图书类别下拉框
		while(iter_category.hasNext()){
		onecategory = iter_category.next();
		out.println("<option value=\"" + onecategory.getCategoryID() + "\">"+onecategory.getCategoryName()+"</option>");
										}
	%>
			</select>
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-2">作者：</label>
		<div class="col-md-3">
			<input type="text" class="form-control" placeholder="请输入作者" name="author" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">出版社：</label>
		<div class="col-md-3">
			<input type="text" class="form-control" placeholder="请输入出版社" name="press" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">价格：</label>
		<div class="col-md-3">
			<input type="text" class="form-control" placeholder="请输入价格" name="price" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">ISBN：</label>
		<div class="col-md-3">
			<input type="text" class="form-control" placeholder="请输入ISBN" name="isbn" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-md-2"></div>
		<div class="col-md-3">
			<button type="submit" class="btn btn-primary pull-right" style="margin-right: 10px;">添加</button>
			<button type="reset" class="btn btn-default pull-right" style="margin-right: 10px;">重置</button>
		</div>
	</div>

</form>