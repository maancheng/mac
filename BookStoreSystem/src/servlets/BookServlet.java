
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

		// 查询图书
		BookDao book_dao = new BookDao();
		ArrayList<Book> books = new ArrayList<Book>();
		books = book_dao.getAllBooks();
		request.setAttribute("books", books);
		// 查询图书类别
		CategoryDao category_dao = new CategoryDao();
		ArrayList<Category> categories = new ArrayList<Category>();
		categories = category_dao.getAllCategories();
		request.setAttribute("categories", categories);

		// 添加图书
		if ("addBook".equals(dowhat)) {
			// 取表单参数
			String bookname = request.getParameter("bookname");
			String categoryID = request.getParameter("categoryID");
			String author = request.getParameter("author");
			String press = request.getParameter("press");
			double price = Double.parseDouble(request.getParameter("price"));
			String isbn = request.getParameter("isbn");

			// 封装好待写入数据库的User对象
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
		// 删除图书
		if ("delBook".equals(dowhat)) {
			// 取表单参数
			int BookID = Integer.parseInt(request.getParameter("BookID"));
			book_dao.delBook(BookID);
			books = book_dao.getAllBooks();

			request.setAttribute("books", books);
		}
		// 修改图书
		if ("updBook".equals(dowhat)) {
			// 取表单参数，封装成待修改的Book对象
			Book book = new Book();

			// id需要从修改页传过来，id是作为修改的依据的
			book.setBookID(Integer.parseInt(request.getParameter("BookID")));
			book.setBookName(request.getParameter("bookname"));
			book.setCategoryID(request.getParameter("categoryID"));
			book.setAuthor(request.getParameter("author"));
			book.setPress(request.getParameter("press"));
			book.setPrice(Double.parseDouble(request.getParameter("price")));
			book.setIsbn(request.getParameter("isbn"));

			book_dao.updBook(book); // 调用模型层修改图书方法

			books = book_dao.getAllBooks(); // 重新查询所有
			request.setAttribute("books", books);
		}

		// 转到修改图书页 editbook.jsp
		if ("toEditbook".equals(dowhat)) {
			// 取URL地址传的参数id
			int bookID = Integer.parseInt(request.getParameter("BookID"));

			Book book = book_dao.getBookById(bookID); // 调用模型层查询指定图书方法

			request.getSession().setAttribute("book", book); // 绑定待修改图书到session对象
			request.getSession().setAttribute("categories", categories);
			response.sendRedirect(request.getContextPath() + "/admin/editbook.jsp");
			// 重定向到图书修改页editbook.jsp

			return;
			/*
			 * response.sendRedirect() 之后，若再 forward() 会出错！
			 * 
			 * 这里加 return; 令方法立刻结束，不会执行后面的forward()了
			 */
		}
		RequestDispatcher rd = request.getRequestDispatcher("admin/show/showbook.jsp");
		rd.forward(request, response);

	}

}
