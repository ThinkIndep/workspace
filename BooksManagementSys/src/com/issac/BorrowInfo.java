package com.issac;

public class BorrowInfo {
	private String borrowId;
	private String readerId;
	private String borrowBookId;
	private String borrowDate;
	private String returnDate="Î´»¹";

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getReaderId() {
		return readerId;
	}

	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	public String getBorrowBookId() {
		return borrowBookId;
	}

	public void setBorrowBookId(String borrowBookId) {
		this.borrowBookId = borrowBookId;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDae) {
		this.returnDate = returnDae;
	}
}
