package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
	// ��ѯ�����û�
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = DBConnection.getConn();
		String sql = "select * from users";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				User oneuser = new User();
				oneuser.setUserID(rs.getInt("UserID"));
				oneuser.setName(rs.getString("Name"));
				oneuser.setGender(rs.getString("Gender"));
				oneuser.setAge(rs.getString("Age"));
				oneuser.setUserName(rs.getString("UserName"));
				oneuser.setPassword(rs.getString("Password"));
				oneuser.setAdmin(rs.getInt("Admin"));
				oneuser.setPhone(rs.getString("Phone"));
				oneuser.setAdress(rs.getString("Adress"));

				userList.add(oneuser);
			}
		} catch (SQLException e) {
			System.out.println("��ѯ�����û��쳣��" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userList;
	}

	// ɾ���û�
	public void delUser(int UserID) {
		Connection conn = DBConnection.getConn();
		String sql = "DELETE FROM users where UserID=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, UserID);
			pstmt.executeUpdate(); // ִ��
		} catch (SQLException e) {

			System.out.println("�û�ɾ���쳣��" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// �û�ע��
	public void addUser(User oneuser) {

		Connection conn = DBConnection.getConn();

		String sql = "insert into users(Name,Gender,Age,UserName,Password,Admin,Phone,Adress) value(?,?,?,?,?,?,?,?)";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, oneuser.getName());
			pstmt.setString(2, oneuser.getGender());
			pstmt.setString(3, oneuser.getAge());
			pstmt.setString(4, oneuser.getUserName());
			pstmt.setString(5, oneuser.getPassword());
			pstmt.setInt(6, oneuser.getAdmin());
			pstmt.setString(7, oneuser.getPhone());
			pstmt.setString(8, oneuser.getAdress());

			pstmt.executeUpdate(); // ִ�����

		} catch (SQLException e) {

			System.out.println("ע���쳣��" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//����UserID��ѯͼ��
			public User getUserById(int UserID){
				User user = new User();
				Connection conn = DBConnection.getConn();
				String sql = "select * from users where UserID=?";
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, UserID);
					ResultSet rs = pstmt.executeQuery();
					
					if(rs.next()) {
						user.setUserID(rs.getInt("UserID"));
						user.setName(rs.getString("Name"));
						user.setGender(rs.getString("Gender"));
						user.setAge(rs.getString("Age"));
						user.setUserName(rs.getString("UserName"));
						user.setPassword(rs.getString("Password"));
						user.setAdmin(rs.getInt("Admin"));
						user.setPhone(rs.getString("Phone"));
						user.setAdress(rs.getString("Adress"));
					}
				}catch(SQLException e) {
					System.out.println("����UserID��ѯͼ��"+e.getMessage());
				}finally {
					if(conn!=null) {
						try {
							conn.close();
						}catch(SQLException e) {
							e.printStackTrace();
						}
					}
				}
				return user;
			}
	// �޸��û�������id�޸�
	public void updUser(User oneuser) { // ����book��id�Ǿ�ֵ���������޸ĵĲ���Ӧ������ֵ

		Connection conn = DBConnection.getConn();

		String sql = "update users set Name=?,Gender=?,Age=?,UserName=?,Password=?,Admin=?,Phone=?,Adress=? where UserID=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, oneuser.getName());
			pstmt.setString(2, oneuser.getGender());
			pstmt.setString(3, oneuser.getAge());
			pstmt.setString(4, oneuser.getUserName());
			pstmt.setString(5, oneuser.getPassword());
			pstmt.setInt(6, oneuser.getAdmin());
			pstmt.setString(7, oneuser.getPhone());
			pstmt.setString(8, oneuser.getAdress());
			pstmt.setInt(9, oneuser.getUserID());

			pstmt.executeUpdate(); // ִ���޸�

		} catch (SQLException e) {

			System.out.println("�޸��û���Ϣ�쳣��" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
