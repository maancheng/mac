
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import beans.Book;
import beans.BookDao;
import beans.Category;
import beans.CategoryDao;

@MultipartConfig
public class BookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String dowhat = request.getParameter("do");

		// ��ѯͼ��
		BookDao book_dao = new BookDao();
		ArrayList<Book> books = new ArrayList<Book>();
		books = book_dao.getAllBooks();
		request.setAttribute("books", books);
		// ��ѯͼ�����
		CategoryDao category_dao = new CategoryDao();
		ArrayList<Category> categories = new ArrayList<Category>();
		categories = category_dao.getAllCategories();
		request.setAttribute("categories", categories);

		// ���ͼ��
		if ("addBook".equals(dowhat)) {
			// ȡ������
			String bookname = request.getParameter("bookname");
			String categoryID = request.getParameter("categoryID");
			String author = request.getParameter("author");
			String press = request.getParameter("press");
			double price = Double.parseDouble(request.getParameter("price"));
			String isbn = request.getParameter("isbn");

			// ��װ�ô�д�����ݿ��User����
			Book onebook = new Book();
			onebook.setBookName(bookname);
			onebook.setCategoryID(categoryID);
			onebook.setAuthor(author);
			onebook.setPress(press);
			onebook.setPrice(price);
			onebook.setIsbn(isbn);

			Part part = request.getPart("image");
			String cd = part.getHeader("content-disposition");
			String filext = cd.substring(cd.indexOf("filename") + 10, cd.length() - 1);
			filext = filext.substring(filext.lastIndexOf("."));
			String path = this.getServletContext().getRealPath("/img");
			String uuidFileName = UUID.randomUUID().toString().replace("-", "") + filext;
			String filePath = path + "/" + uuidFileName;
			part.write(filePath);
			onebook.setImgPath("img/" + uuidFileName);

			book_dao.addBook(onebook);
			books = book_dao.getAllBooks();

			request.setAttribute("books", books);
		}
		// ɾ��ͼ��
		if ("delBook".equals(dowhat)) {
			// ȡ������
			int BookID = Integer.parseInt(request.getParameter("BookID"));
			book_dao.delBook(BookID);
			books = book_dao.getAllBooks();

			request.setAttribute("books", books);
		}
		// �޸�ͼ��
		if ("updBook".equals(dowhat)) {
			// ȡ����������װ�ɴ��޸ĵ�Book����
			Book book = new Book();

			// id��Ҫ���޸�ҳ��������id����Ϊ�޸ĵ����ݵ�
			book.setBookID(Integer.parseInt(request.getParameter("BookID")));
			book.setBookName(request.getParameter("bookname"));
			book.setCategoryID(request.getParameter("categoryID"));
			book.setAuthor(request.getParameter("author"));
			book.setPress(request.getParameter("press"));
			book.setPrice(Double.parseDouble(request.getParameter("price")));
			book.setIsbn(request.getParameter("isbn"));

			book_dao.updBook(book); // ����ģ�Ͳ��޸�ͼ�鷽��

			books = book_dao.getAllBooks(); // ���²�ѯ����
			request.setAttribute("books", books);
		}

		// ת���޸�ͼ��ҳ editbook.jsp
		if ("toEditbook".equals(dowhat)) {
			// ȡURL��ַ���Ĳ���id
			int bookID = Integer.parseInt(request.getParameter("BookID"));

			Book book = book_dao.getBookById(bookID); // ����ģ�Ͳ��ѯָ��ͼ�鷽��

			request.getSession().setAttribute("book", book); // �󶨴��޸�ͼ�鵽session����
			request.getSession().setAttribute("categories", categories);
			response.sendRedirect(request.getContextPath() + "/admin/editbook.jsp");
			// �ض���ͼ���޸�ҳeditbook.jsp

			return;
			/*
			 * response.sendRedirect() ֮������ forward() �����
			 * 
			 * ����� return; ������̽���������ִ�к����forward()��
			 */
		}
		RequestDispatcher rd = request.getRequestDispatcher("admin/show/showbook.jsp");
		rd.forward(request, response);

	}

}
