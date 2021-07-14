
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
		// ��ѯ�û���Ϣ
		UserDao user_dao = new UserDao();
		ArrayList<User> users = new ArrayList<User>();
		users = user_dao.getAllUsers();
		request.setAttribute("users", users);

		// ����û�
		if ("addUser".equals(dowhat)) {
			// ȡ������
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String age = request.getParameter("age");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			int admin = Integer.parseInt(request.getParameter("admin"));
			String phone = request.getParameter("phone");
			String adress = request.getParameter("adress");

			// ��װ�ô�д�����ݿ��User����
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

		// ɾ���û�
		if ("delUser".equals(dowhat)) {
			// ȡ������
			int UserID = Integer.parseInt(request.getParameter("UserID"));
			user_dao.delUser(UserID);
			users = user_dao.getAllUsers();

			request.setAttribute("users", users);
		}
		// �޸��û�
		if ("updUser".equals(dowhat)) {
			// ȡ������
			int userID = Integer.parseInt(request.getParameter("UserID"));
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String age = request.getParameter("age");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			int admin = Integer.parseInt(request.getParameter("admin"));
			String phone = request.getParameter("phone");
			String adress = request.getParameter("adress");

			// ��װ�ô�д�����ݿ��User����
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
		// ת���޸�ͼ��ҳ editbook.jsp
		if ("toEditUser".equals(dowhat)) {
			// ȡURL��ַ���Ĳ���id
			int UserId = Integer.parseInt(request.getParameter("UserID"));
			User user = user_dao.getUserById(UserId); // ����ģ�Ͳ��ѯָ��ͼ�鷽��

			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/admin/edituser.jsp");
			// �ض���ͼ���޸�ҳeditcategory.jsp

			return;
			/*
			 * response.sendRedirect() ֮������ forward() �����
			 * 
			 * ����� return; ������̽���������ִ�к����forward()��
			 */
		}

		RequestDispatcher rd = request.getRequestDispatcher("admin/show/showuser.jsp");
		rd.forward(request, response);
	}

}
