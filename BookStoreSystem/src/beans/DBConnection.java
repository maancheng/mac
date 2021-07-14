package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//功能bean，实现连接数据库功能
public class DBConnection {

	public static Connection getConn() {

		Connection conn = null;

		try {

			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 拿连接对象
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bookstore",
					"root", "123456");

		} catch (ClassNotFoundException e) {

			System.out.println("驱动异常，" + e.getMessage());

		} catch (SQLException e2) {

			System.out.println("拿连接异常，" + e2.getMessage());
		}

		return conn;
	}

}
