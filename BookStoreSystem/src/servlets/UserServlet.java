
package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import beans.User;
import beans.UserDao;

public class UserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String dowhat = request.getParameter("do");
		// 查询用户信息
		UserDao user_dao = new UserDao();
		ArrayList<User> users = new ArrayList<User>();
		users = user_dao.getAllUsers();
		request.setAttribute("users", users);

		// 添加用户
		if ("addUser".equals(dowhat)) {
			// 取表单参数
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String age = request.getParameter("age");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			int admin = Integer.parseInt(request.getParameter("admin"));
			String phone = request.getParameter("phone");
			String adress = request.getParameter("adress");

			// 封装好待写入数据库的User对象
			User oneuser = new User();
			oneuser.setName(name);
			oneuser.setGender(gender);
			oneuser.setAge(age);
			oneuser.setUserName(userName);
			oneuser.setPassword(password);
			oneuser.setAdmin(admin);
			oneuser.setPhone(phone);
			oneuser.setAdress(adress);

			user_dao.addUser(oneuser);
			users = user_dao.getAllUsers();

			request.setAttribute("users", users);
		}

		// 删除用户
		if ("delUser".equals(dowhat)) {
			// 取表单参数
			int UserID = Integer.parseInt(request.getParameter("UserID"));
			user_dao.delUser(UserID);
			users = user_dao.getAllUsers();

			request.setAttribute("users", users);
		}
		// 修改用户
		if ("updUser".equals(dowhat)) {
			// 取表单参数
			int userID = Integer.parseInt(request.getParameter("UserID"));
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String age = request.getParameter("age");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			int admin = Integer.parseInt(request.getParameter("admin"));
			String phone = request.getParameter("phone");
			String adress = request.getParameter("adress");

			// 封装好待写入数据库的User对象
			User oneuser = new User();
			oneuser.setUserID(userID);
			oneuser.setName(name);
			oneuser.setGender(gender);
			oneuser.setAge(age);
			oneuser.setUserName(userName);
			oneuser.setPassword(password);
			oneuser.setAdmin(admin);
			oneuser.setPhone(phone);
			oneuser.setAdress(adress);

			user_dao.updUser(oneuser);
			users = user_dao.getAllUsers();

			request.setAttribute("users", users);
		}
		// 转到修改图书页 editbook.jsp
		if ("toEditUser".equals(dowhat)) {
			// 取URL地址传的参数id
			int UserId = Integer.parseInt(request.getParameter("UserID"));
			User user = user_dao.getUserById(UserId); // 调用模型层查询指定图书方法

			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/admin/edituser.jsp");
			// 重定向到图书修改页editcategory.jsp

			return;
			/*
			 * response.sendRedirect() 之后，若再 forward() 会出错！
			 * 
			 * 这里加 return; 令方法立刻结束，不会执行后面的forward()了
			 */
		}

		RequestDispatcher rd = request.getRequestDispatcher("admin/show/showuser.jsp");
		rd.forward(request, response);
	}

}
