package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//����bean��ʵ���������ݿ⹦��
public class DBConnection {

	public static Connection getConn() {

		Connection conn = null;

		try {

			// ��������
			Class.forName("com.mysql.jdbc.Driver");

			// �����Ӷ���
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bookstore",
					"root", "123456");

		} catch (ClassNotFoundException e) {

			System.out.println("�����쳣��" + e.getMessage());

		} catch (SQLException e2) {

			System.out.println("�������쳣��" + e2.getMessage());
		}

		return conn;
	}

}
