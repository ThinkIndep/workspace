package com.issac;

public class Book {
	private String bookId;// ͼ����
	private String bookAuthor;// ͼ������
	private String bookName;// ͼ������
	private String bookPress;// ������
	private String bookAddress;// �ִ��ַ
	private String bookSum;// �ܹ�����
	private String inLibrarySum;// �ڹ���
	private String borrowSum;// ���Ĵ���
	private String deleteDate;// ɾ��ʱ��
	private String buyDate;// ¼��ʱ��

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
