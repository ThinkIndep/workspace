package com.issac;

public class Book {
	private String bookId;// 图书编号
	private String bookAuthor;// 图书作者
	private String bookName;// 图书名称
	private String bookPress;// 出版社
	private String bookAddress;// 现存地址
	private String bookSum;// 总共册数
	private String inLibrarySum;// 在馆数
	private String borrowSum;// 借阅次数
	private String deleteDate;// 删除时间
	private String buyDate;// 录入时间

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookPress() {
		return bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}

	public String getBookAddress() {
		return bookAddress;
	}

	public void setBookAddress(String bookAddress) {
		this.bookAddress = bookAddress;
	}

	public String getBookSum() {
		return bookSum;
	}

	public void setBookSum(String bookSum) {
		this.bookSum = bookSum;
	}

	public String getInLibrarySum() {
		return inLibrarySum;
	}

	public void setInLibrarySum(String inLibrarySum) {
		this.inLibrarySum = inLibrarySum;
	}

	public String getBorrowSum() {
		return borrowSum;
	}

	public void setBorrowSum(String borrowSum) {
		this.borrowSum = borrowSum;
	}

	public String getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

}
