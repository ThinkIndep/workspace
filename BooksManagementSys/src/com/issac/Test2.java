package com.issac;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Test2 {

	public static void main(String[] args) throws AWTException {
		BooksManagementSys booksys = new BooksManagementSys();
		booksys.read();
		booksys.orderByBorrowSum();
	}

}
