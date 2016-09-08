package com.issac;

import java.util.TreeMap;

public class Test2 {
	private Book book;
	private TreeMap<String, Book> library = new TreeMap<>();

	public void add2() {
		add();
	}

	public void add() {
		book = new Book();
		book.setBookId("534534");
		library.put(book.getBookId(), book);
		book = new Book();
		book.setBookId("66789");
		library.put(book.getBookId(), book);
		System.out.println(library);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test2().add();

	}

}
