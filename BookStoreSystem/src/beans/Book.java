package beans;

public class Book {
	
	private int bookID;
	private String bookName;
	private String imgPath;
	private String categoryID;
	private String categoryName;
	private String author;
	private String press;
	private double price;
	private String isbn;
	
	public Book(String bookName,String categoryName,String author,String press,double price,String isbn) {
		this.bookName=bookName;
//		this.imgPath=imgPath;
		this.categoryName=categoryName;
		this.author=author;
		this.press=press;
		this.price=price;
		this.isbn=isbn;
	}
	public Book() {
		
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	}

	
	

