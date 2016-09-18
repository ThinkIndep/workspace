package com.issac;

public class BorrowInfo {
	private String borrowId;// 借阅编号
	private String readerId;// 读者编号
	private String borrowBookId;// 书籍编号
	private String borrowDate;// 借书时间
	private String returnDate = "未还";// 还书时间

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
