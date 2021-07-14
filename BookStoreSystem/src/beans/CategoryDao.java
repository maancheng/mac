package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao {
	// 查询所有图书类别
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
			System.out.println("查询图书类别" + e.getMessage());
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

	// 删除图书类别(同时删除此类别的图书)
	public void delCategory(String categoryID) {
		Connection conn = DBConnection.getConn();
		String sql1 = "DELETE FROM books where CategoryID=?";
		String sql2 = "DELETE FROM categories where CategoryID=?";
		try {
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, categoryID);
			pstmt1.executeUpdate(); // 执行

			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, categoryID);
			pstmt2.executeUpdate(); // 执行

			System.out.println(pstmt1);
			System.out.println(pstmt2);
		} catch (SQLException e) {
			// switch(){
			// case
			// }

			System.out.println("图书类别删除异常：" + e.getMessage());
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

	// 添加图书类别
	public void addCategory(Category onecategory) {

		Connection conn = DBConnection.getConn();

		String sql = "insert into categories(CategoryID,categoryName) value(?,?)";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, onecategory.getCategoryID());
			pstmt.setString(2, onecategory.getCategoryName());

			pstmt.executeUpdate(); // 执行添加

		} catch (SQLException e) {//异常输出模块
			System.out.print("添加图书类别异常：");
			
			switch (e.getErrorCode()){
			case 1062: System.out.println("类别ID已存在");break;
			default:System.out.println("错误代码："+e.getErrorCode()+" 信息："+e.getMessage());
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

	// 根据CategoryID查询图书列表
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
			System.out.println("根据CategoryID查询图书列表" + e.getMessage());
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

	// 修改图书类别，根据id修改
	public void updCategory(Category onecategory) { // 参数book：id是旧值，其它可修改的部分应该是新值

		Connection conn = DBConnection.getConn();

		String sql = "update categories set CategoryID=?,CategoryName=? where CategoryID=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, onecategory.getCategoryID());
			pstmt.setString(2, onecategory.getCategoryName());
			pstmt.setString(3, onecategory.getCategoryID());

			pstmt.executeUpdate(); // 执行修改
		} catch (SQLException e) {

			System.out.println("修改图书类别异常：" + e.getMessage());
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
