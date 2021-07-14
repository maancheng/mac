
package servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Book;
import beans.BookDao;
import beans.Category;
import beans.CategoryDao;
import beans.DBConnection;
import beans.User;
import beans.UserDao;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String dowhat = request.getParameter("do");
		HttpSession session = request.getSession();
		session.setAttribute("msg", null);
		// 查询图书
		BookDao book_dao = new BookDao();
		ArrayList<Book> books = book_dao.newbook();
		ArrayList<Book> booklist = book_dao.bookList();
		request.setAttribute("books", books);
		request.setAttribute("booklist", booklist);
		// 查询类别图书

		CategoryDao category_dao = new CategoryDao();
		ArrayList<Category> categories = new ArrayList<Category>();
		if ("toAdmin".equals(dowhat)) {
			RequestDispatcher rd = request.getRequestDispatcher("admin/show/admin.jsp");
			rd.forward(request, response);
			return;
		}
		if ("toIndex".equals(dowhat)) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}
		
		// 搜索图书
		if ("searchBook".equals(dowhat)) {
			String condition = request.getParameter("condition");
			String key = request.getParameter("key");
			System.out.println(condition);
			System.out.println(key);
			books = book_dao.searchBook(condition, key);
			request.setAttribute("books", books);

			RequestDispatcher rd = request.getRequestDispatcher("allbooks.jsp");
			rd.forward(request, response);
			return;
		}

		// 登陆验证
		if ("login".equals(dowhat)) {
			String realcode = session.getAttribute("realcode").toString();
			String inputcode = request.getParameter("checkcode");
			if (realcode.equalsIgnoreCase(inputcode)) {
				if (0 == Integer.parseInt(request.getParameter("admin"))) {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					Connection conn = DBConnection.getConn();
					String sql = "SELECT * FROM users WHERE UserName=? AND Password=? AND Admin=0";
					try {
						PreparedStatement pastm = conn.prepareStatement(sql);
						pastm.setString(1, username);
						pastm.setString(2, password);
						ResultSet rs = pastm.executeQuery();
						boolean lock = rs.next();
						System.out.println(rs.next());
						session.setAttribute("login", true);
						session.setAttribute("user", username);
						
					} catch (SQLException e) {
						System.out.println("判断用户合法性" + e.getMessage());
					}
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
					return;
				}
				if (1 == Integer.parseInt(request.getParameter("admin"))) {

					String username = request.getParameter("username");
					String password = request.getParameter("password");
					Connection conn = DBConnection.getConn();
					String sql = "SELECT * FROM users WHERE UserName=? AND Password=? AND Admin=1";
					try {
						PreparedStatement pastm = conn.prepareStatement(sql);
						pastm.setString(1, username);
						pastm.setString(2, password);
						ResultSet rs = pastm.executeQuery();
						boolean lock = rs.next();
						System.out.println(rs.next());
						session.setAttribute("login", true);
					} catch (SQLException e) {
						System.out.println("判断用户合法性：" + e.getMessage());
					}
					
					session.setAttribute("user", username);
					response.sendRedirect(request.getContextPath() + "?do=toAdmin");
					return;
				}
			}else {
				session.setAttribute("msg", "alert(\"验证码错误！\")");
			}
		}
		// 添加用户
		if ("addUser".equals(dowhat)) {
			//
			UserDao user_dao = new UserDao();
			ArrayList<User> users = new ArrayList<User>();
			// 取表单参数
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String age = request.getParameter("age");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String adress = request.getParameter("adress");

			// 封装好待写入数据库的User对象
			User oneuser = new User();
			oneuser.setName(name);
			oneuser.setGender(gender);
			oneuser.setAge(age);
			oneuser.setUserName(userName);
			oneuser.setPassword(password);
			oneuser.setPhone(phone);
			oneuser.setAdress(adress);
			user_dao.addUser(oneuser);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		// 用户查询所有图书
		if ("Q_A_B".equals(dowhat)) {
			books = book_dao.getAllBooks();
			request.setAttribute("books", books);
			RequestDispatcher rd = request.getRequestDispatcher("allbooks.jsp");
			rd.forward(request, response);
			return;

		}
		// 查询类别
		if ("Q_C_B".equals(dowhat)) {
			categories = category_dao.getAllCategories();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("index.jsp");
			return;
		}
		// 清空购物车
		if ("destoryBookOrder".equals(dowhat)) {
			ArrayList<Book> orderbooks = null;
			session.setAttribute("orderbooks", orderbooks);
			RequestDispatcher rd = request.getRequestDispatcher("/myorder.jsp");
			rd.forward(request, response);
			return;
		}
		// 添加订单
		if ("addorder".equals(dowhat)) {

			ArrayList<Book> orderbooks = (ArrayList<Book>) session.getAttribute("orderbooks");

			if (orderbooks == null) {
				orderbooks = new ArrayList<Book>();
			}
			request.setCharacterEncoding("utf-8");

			String bookname = request.getParameter("BookName");
			String categoryname = request.getParameter("CategoryName");
			String author = request.getParameter("Author");
			String press = request.getParameter("Press");
			double price = Double.parseDouble(request.getParameter("Price"));
			String isbn = request.getParameter("Isbn");
//			String imgPath = request.getParameter("imgPath");
			Book OrderBooks = new Book(bookname, categoryname, author, press, price, isbn);
			orderbooks.add(OrderBooks);
			session.setAttribute("orderbooks", orderbooks);
			RequestDispatcher rd = request.getRequestDispatcher("/myorder.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

}
