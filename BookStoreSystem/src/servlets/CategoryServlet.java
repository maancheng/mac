
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

		// ��ѯͼ�����
		CategoryDao category_dao = new CategoryDao();
		ArrayList<Category> categories = new ArrayList<Category>();
		categories = category_dao.getAllCategories();
		request.setAttribute("categories", categories);

		// ���ͼ�����
		if ("addCategory".equals(dowhat)) {
			// ȡ������
			String categoryID = request.getParameter("categoryID");
			String categoryname = request.getParameter("categoryname");

			// ��װ�ô�д�����ݿ��User����
			Category onecategory = new Category();
			onecategory.setCategoryID(categoryID);
			onecategory.setCategoryName(categoryname);

			category_dao.addCategory(onecategory);
			categories = category_dao.getAllCategories();

			request.setAttribute("categories", categories);

		}
		// ɾ��ͼ�����
		if ("delCategory".equals(dowhat)) {
			// ȡ������
			String CategoryID = request.getParameter("CategoryID");
			category_dao.delCategory(CategoryID);
			categories = category_dao.getAllCategories();

			request.setAttribute("categories", categories);
		}

		

		// �޸�ͼ�����
		if ("updCategory".equals(dowhat)) {
			// ȡ������
			String categoryID = request.getParameter("categoryID");
			String categoryname = request.getParameter("categoryname");

			// ��װ�ô�д�����ݿ��User����
			Category onecategory = new Category();
			onecategory.setCategoryID(categoryID);
			onecategory.setCategoryName(categoryname);

			category_dao.updCategory(onecategory);
			categories = category_dao.getAllCategories();

			request.setAttribute("categories", categories);

		}
		// ת���޸�ͼ��ҳ editbook.jsp
				if ("toEditCategory".equals(dowhat)) {
					// ȡURL��ַ���Ĳ���id
					String CategoryId = request.getParameter("CategoryID");

					Category category = category_dao.getCategoryById(CategoryId); // ����ģ�Ͳ��ѯָ��ͼ�鷽��
					request.getSession().setAttribute("category", category);
					response.sendRedirect(request.getContextPath() + "/admin/editcategory.jsp");
					// �ض���ͼ���޸�ҳeditcategory.jsp
					return;
					/*
					 * response.sendRedirect() ֮������ forward() �����
					 * 
					 * ����� return; ������̽���������ִ�к����forward()��
					 */
				}
				RequestDispatcher rd = request.getRequestDispatcher("admin/show/showcategory.jsp");
				rd.forward(request, response);
	}

}
