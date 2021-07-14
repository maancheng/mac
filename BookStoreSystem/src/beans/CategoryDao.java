package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao {
	// ��ѯ����ͼ�����
	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> categoryList = new ArrayList<Category>();
		Connection conn = DBConnection.getConn();
		String sql = "SELECT * FROM categories";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Category onecategory = new Category();
				onecategory.setCategoryID(rs.getString("CategoryID"));
				onecategory.setCategoryName(rs.getString("CategoryName"));

				categoryList.add(onecategory);
			}
		} catch (SQLException e) {
			System.out.println("��ѯͼ�����" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return categoryList;
	}

	// ɾ��ͼ�����(ͬʱɾ��������ͼ��)
	public void delCategory(String categoryID) {
		Connection conn = DBConnection.getConn();
		String sql1 = "DELETE FROM books where CategoryID=?";
		String sql2 = "DELETE FROM categories where CategoryID=?";
		try {
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, categoryID);
			pstmt1.executeUpdate(); // ִ��

			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, categoryID);
			pstmt2.executeUpdate(); // ִ��

			System.out.println(pstmt1);
			System.out.println(pstmt2);
		} catch (SQLException e) {
			// switch(){
			// case
			// }

			System.out.println("ͼ�����ɾ���쳣��" + e.getMessage());
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

	// ���ͼ�����
	public void addCategory(Category onecategory) {

		Connection conn = DBConnection.getConn();

		String sql = "insert into categories(CategoryID,categoryName) value(?,?)";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, onecategory.getCategoryID());
			pstmt.setString(2, onecategory.getCategoryName());

			pstmt.executeUpdate(); // ִ�����

		} catch (SQLException e) {//�쳣���ģ��
			System.out.print("���ͼ������쳣��");
			
			switch (e.getErrorCode()){
			case 1062: System.out.println("���ID�Ѵ���");break;
			default:System.out.println("������룺"+e.getErrorCode()+" ��Ϣ��"+e.getMessage());
			}
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

	// ����CategoryID��ѯͼ���б�
	public Category getCategoryById(String bookID) {
		Category category = new Category();
		Connection conn = DBConnection.getConn();
		String sql = "select * from categories where CategoryID=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookID);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				category.setCategoryID(rs.getString("CategoryID"));
				category.setCategoryName(rs.getString("CategoryName"));
			}
		} catch (SQLException e) {
			System.out.println("����CategoryID��ѯͼ���б�" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return category;
	}

	// �޸�ͼ����𣬸���id�޸�
	public void updCategory(Category onecategory) { // ����book��id�Ǿ�ֵ���������޸ĵĲ���Ӧ������ֵ

		Connection conn = DBConnection.getConn();

		String sql = "update categories set CategoryID=?,CategoryName=? where CategoryID=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, onecategory.getCategoryID());
			pstmt.setString(2, onecategory.getCategoryName());
			pstmt.setString(3, onecategory.getCategoryID());

			pstmt.executeUpdate(); // ִ���޸�
		} catch (SQLException e) {

			System.out.println("�޸�ͼ������쳣��" + e.getMessage());
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
