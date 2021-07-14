
package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Category;
import beans.CategoryDao;

public class CategoryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String dowhat = request.getParameter("do");

		// 查询图书类别
		CategoryDao category_dao = new CategoryDao();
		ArrayList<Category> categories = new ArrayList<Category>();
		categories = category_dao.getAllCategories();
		request.setAttribute("categories", categories);

		// 添加图书类别
		if ("addCategory".equals(dowhat)) {
			// 取表单参数
			String categoryID = request.getParameter("categoryID");
			String categoryname = request.getParameter("categoryname");

			// 封装好待写入数据库的User对象
			Category onecategory = new Category();
			onecategory.setCategoryID(categoryID);
			onecategory.setCategoryName(categoryname);

			category_dao.addCategory(onecategory);
			categories = category_dao.getAllCategories();

			request.setAttribute("categories", categories);

		}
		// 删除图书类别
		if ("delCategory".equals(dowhat)) {
			// 取表单参数
			String CategoryID = request.getParameter("CategoryID");
			category_dao.delCategory(CategoryID);
			categories = category_dao.getAllCategories();

			request.setAttribute("categories", categories);
		}

		

		// 修改图书类别
		if ("updCategory".equals(dowhat)) {
			// 取表单参数
			String categoryID = request.getParameter("categoryID");
			String categoryname = request.getParameter("categoryname");

			// 封装好待写入数据库的User对象
			Category onecategory = new Category();
			onecategory.setCategoryID(categoryID);
			onecategory.setCategoryName(categoryname);

			category_dao.updCategory(onecategory);
			categories = category_dao.getAllCategories();

			request.setAttribute("categories", categories);

		}
		// 转到修改图书页 editbook.jsp
				if ("toEditCategory".equals(dowhat)) {
					// 取URL地址传的参数id
					String CategoryId = request.getParameter("CategoryID");

					Category category = category_dao.getCategoryById(CategoryId); // 调用模型层查询指定图书方法
					request.getSession().setAttribute("category", category);
					response.sendRedirect(request.getContextPath() + "/admin/editcategory.jsp");
					// 重定向到图书修改页editcategory.jsp
					return;
					/*
					 * response.sendRedirect() 之后，若再 forward() 会出错！
					 * 
					 * 这里加 return; 令方法立刻结束，不会执行后面的forward()了
					 */
				}
				RequestDispatcher rd = request.getRequestDispatcher("admin/show/showcategory.jsp");
				rd.forward(request, response);
	}

}
