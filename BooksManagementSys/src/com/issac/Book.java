package com.issac;

import java.sql.Date;

public class Book {
	private String bookId;
	private String bookAuthor;
	private String bookName;
	private String bookPress;
	private String bookAddress;
	private String bookSum;
	private String inLibrarySum;
	private String borrowSum;
	private Date deleteDate;
	private Date buyDate;

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

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

}
