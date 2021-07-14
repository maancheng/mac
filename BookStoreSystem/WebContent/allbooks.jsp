<%@ page language="java" import="java.util.*,beans.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>图书管理系统—所有图书</title>
	
  <% 
  @SuppressWarnings("unchecked")
  ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("books");
  Iterator<Book> iter_book = books.iterator();
  Book onebook = new Book();
  %>
<jsp:include page="modules/indexnav.jsp"/>
		
		<!--导航栏结束-->
		<div class="container" >
	<div class="panel panel-primary" style="margin-top: 50px;">
						<div class="panel-heading">查询结果 </div>
	<table class="table table-striped">
							<tr>
								<th>书名</th>
								<th>类别</th>
								<th>作者</th>
								<th>出版社</th>
								<th>价格</th>
								<th>ISBN</th>
								<th>操作</th>
							</tr>
							<%
							while(iter_book.hasNext()){
							onebook = iter_book.next();
							
							out.println("<tr>");
							out.println("<td>" + onebook.getBookName() + "</td>");
							out.println("<td>" + onebook.getCategoryName() + "</td>");
							out.println("<td>" + onebook.getAuthor() + "</td>");
							out.println("<td>" + onebook.getPress() + "</td>");
							out.println("<td>" + onebook.getPrice() + "</td>");
							out.println("<td>" + onebook.getIsbn() + "</td>");
							out.print("<td><a href=\"Servlet?do=addorder"
									+"&BookName="+onebook.getBookName()
									+"&CategoryName="+onebook.getCategoryName()
									+"&Author="+onebook.getAuthor()
									+"&Press="+onebook.getPress()
									+"&Price="+onebook.getPrice()
									+"&Isbn="+onebook.getIsbn()
									+"\">添加到购物车</a></td>");
							out.println("</tr>");
							request.getContextPath();
						}
						%>
						
					</table>
					</div>
					</div>
</body>
</html>