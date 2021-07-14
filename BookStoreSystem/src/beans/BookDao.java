package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDao {
	// ��ѯ����ͼ��
	public ArrayList<Book> getAllBooks(){
		ArrayList<Book> bookList = new ArrayList<Book>();
		Connection conn = DBConnection.getConn();
		String sql = "SELECT BookID,BookName, categories.CategoryName,Author,Press,Price,Isbn,ImgPath "
				+ "FROM books "
				+ "JOIN categories ON "
				+ "books.CategoryID = categories.CategoryID";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book onebook = new Book();
				onebook.setBookID(rs.getInt("BookID"));
				onebook.setBookName(rs.getString("BookName"));
				onebook.setCategoryName(rs.getString("CategoryName"));
				onebook.setAuthor(rs.getString("Author"));
				onebook.setPress(rs.getString("Press"));
				onebook.setPrice(rs.getDouble("Price"));
				onebook.setIsbn(rs.getString("Isbn"));
				onebook.setImgPath(rs.getString("ImgPath"));
				bookList.add(onebook);
			}
		}catch(SQLException e) {
			System.out.println("��ѯ�û�"+e.getMessage());
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bookList;
	}
	
	
	//��ҳ������ͼ�鹦��
	public ArrayList<Book> newbook(){
		ArrayList<Book> newbook = new ArrayList<Book>();
		Connection conn = DBConnection.getConn();
		String sql = "SELECT BookName FROM books  ORDER BY BookID DESC LIMIT 15";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book onebook = new Book();
				onebook.setBookName(rs.getString("BookName"));
				newbook.add(onebook);
			}
		}catch(SQLException e) {
			System.out.println("��ѯ�û�"+e.getMessage());
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return newbook;
	}
	
	//��ҳ�Ľ����Ƽ�����
	public ArrayList<Book> bookList(){
		ArrayList<Book> bookList = new ArrayList<Book>();
		Connection conn = DBConnection.getConn();
		String sql = "SELECT * FROM books WHERE BookID BETWEEN '10001' AND'10010' ORDER BY RAND() DESC LIMIT 8";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book onebook = new Book();
				onebook.setBookID(rs.getInt("BookID"));
				onebook.setBookName(rs.getString("BookName"));
				onebook.setAuthor(rs.getString("Author"));
				onebook.setPrice(rs.getDouble("Price"));
				onebook.setImgPath(rs.getString("ImgPath"));
				bookList.add(onebook);
			}
		}catch(SQLException e) {
			System.out.println("��ѯ�û�"+e.getMessage());
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bookList;
	}
	
	
	
	
	// ����ͼ��
		public ArrayList<Book> searchBook(String a,String b){
			ArrayList<Book> bookList = new ArrayList<Book>();
			Connection conn = DBConnection.getConn();
			String sql = "SELECT * FROM books "
					+ "JOIN categories ON "
					+ "books.CategoryID = categories.CategoryID "
					+"WHERE "+a+" LIKE ? ";
//			String sql = "SELECT BookID,BookName, categories.CategoryName,Author,Press,Price,Isbn "
//					+ "FROM books "
//					+ "JOIN categories ON "
//					+ "books.CategoryID = categories.CategoryID";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+b+"%");
				
				System.out.println(pstmt);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Book onebook = new Book();
					onebook.setBookID(rs.getInt("BookID"));
					onebook.setBookName(rs.getString("BookName"));
					onebook.setCategoryName(rs.getString("CategoryName"));
					onebook.setAuthor(rs.getString("Author"));
					onebook.setPress(rs.getString("Press"));
					onebook.setPrice(rs.getDouble("Price"));
					onebook.setIsbn(rs.getString("Isbn"));
					bookList.add(onebook);
				}
			}catch(SQLException e) {
				System.out.println("����ͼ��"+e.getMessage());
			}finally {
				if(conn!=null) {
					try {
						conn.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return bookList;
		}
	

	// ����BookID��ѯͼ��
	public Book getBookById(int BookID){
			Book book = new Book();
			Connection conn = DBConnection.getConn();
			String sql = "select * from books where BookID=?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, BookID);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					book.setBookID(rs.getInt("BookID"));
					book.setBookName(rs.getString("BookName"));
					book.setCategoryID(rs.getString("CategoryID"));
					book.setAuthor(rs.getString("Author"));
					book.setPress(rs.getString("Press"));
					book.setPrice(rs.getDouble("Price"));
					book.setIsbn(rs.getString("Isbn"));
				}
			}catch(SQLException e) {
				System.out.println("����BookID��ѯͼ��"+e.getMessage());
			}finally {
				if(conn!=null) {
					try {
						conn.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return book;
		}

	// �޸�ͼ�飬����id�޸�
	public void updBook(Book onebook){  //����book��id�Ǿ�ֵ���������޸ĵĲ���Ӧ������ֵ
						
						Connection conn = DBConnection.getConn();
						String sql="update books set BookName=?,CategoryID=?,Author=?,Press=?,Price=?,Isbn=? where BookID=?";
						try {
							PreparedStatement pstmt = conn.prepareStatement(sql);
							
							pstmt.setString(1, onebook.getBookName());
							pstmt.setString(2, onebook.getCategoryID());
							pstmt.setString(3, onebook.getAuthor());
							pstmt.setString(4, onebook.getPress());
							pstmt.setDouble(5, onebook.getPrice());
							pstmt.setString(6, onebook.getIsbn());
							pstmt.setInt(7, onebook.getBookID());
							
							pstmt.executeUpdate();    //ִ���޸�
						System.out.println(sql);
						} catch (SQLException e) {
							
							System.out.println("�޸�ͼ���쳣��" + e.getMessage());			
						}finally {  			
							if (conn != null) {
								try {
									conn.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						}		
					}

	// ɾ��ͼ��
	public void delBook(int BookID) {
			Connection conn = DBConnection.getConn();
			String sql = "DELETE FROM books where BookID=?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1,BookID);
				pstmt.executeUpdate();    //ִ��
			} catch (SQLException e) {

				System.out.println("ͼ��ɾ���쳣��" + e.getMessage());
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

	// ���ͼ��
	public void addBook(Book onebook){
			
			Connection conn = DBConnection.getConn();

			String sql = "insert into books(BookName,CategoryID,author,press,price,isbn,ImgPath) value(?,?,?,?,?,?,?)";

			try {

				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, onebook.getBookName());
				pstmt.setString(2, onebook.getCategoryID());
				pstmt.setString(3, onebook.getAuthor());
				pstmt.setString(4, onebook.getPress());
				pstmt.setDouble(5, onebook.getPrice());
				pstmt.setString(6, onebook.getIsbn());
				pstmt.setString(7, onebook.getImgPath());
				
				pstmt.executeUpdate();    //ִ�����	

			} catch (SQLException e) {

				System.out.println("ͼ������쳣��" + e.getMessage());
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
