package com.issac;

import java.util.TreeMap;

public class Reader {
	private String readerId;
	private String readerName;
	private String readerPhone;
	private String readerGender;
	private TreeMap<String, BorrowInfo> borrowBooks;

	public String getReaderId() {
		return readerId;
	}

	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getReaderPhone() {
		return readerPhone;
	}

	public void setReaderPhone(String readerPhone) {
		this.readerPhone = readerPhone;
	}

	public String getReaderGender() {
		return readerGender;
	}

	public void setReaderGender(String readerGender) {
		this.readerGender = readerGender;
	}

	public TreeMap<String, BorrowInfo> getBorrowBooks() {
		return borrowBooks;
	}

	public void setBorrowBooks(TreeMap<String, BorrowInfo> borrowBooks) {
		this.borrowBooks = borrowBooks;
	}
}
