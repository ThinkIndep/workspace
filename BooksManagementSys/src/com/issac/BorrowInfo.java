package com.issac;

import java.sql.Date;

public class BorrowInfo {
	private String borrowId;
	private String borrowBookId;
	private Date borrowDate;
	private Date returnDae;

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getBorrowBookId() {
		return borrowBookId;
	}

	public void setBorrowBookId(String borrowBookId) {
		this.borrowBookId = borrowBookId;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDae() {
		return returnDae;
	}

	public void setReturnDae(Date returnDae) {
		this.returnDae = returnDae;
	}
}
