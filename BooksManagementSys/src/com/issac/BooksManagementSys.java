package com.issac;

import java.util.Scanner;
import java.util.TreeMap;

public class BooksManagementSys {

	private Book book;
	private TreeMap<String, Book> library;
	private Reader reader;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public TreeMap<String, Book> getLibrary() {
		return library;
	}

	public void setLibrary(TreeMap<String, Book> library) {
		this.library = library;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public void mainMenu() {
		System.out.println("#####欢迎使用GUET图书管理系统#####\n");
		System.out.print(
				"主菜单:\n1.新增图书\n2.借/还图书\n3.修改图书信息\n4.删除图书\n5.查找图书\n6.新增管理员\n7.读者管理\n8.信息统计\n9.保存修改\n10.退出系统\n请选择要执行的操作：");
		Scanner input = new Scanner(System.in);
		int option = input.nextInt();
		switch (option) {
		case 1:
			addBook();
			break;
		case 2:
			borrowOrReturnBook();
			break;
		case 3:
			modifyBookInfo();
			break;
		case 4:
			deleteBook();
			break;
		case 5:
			queryBook();
			break;
		case 6:
			addAdmin();
			break;
		case 7:
			readerManagement();
			break;
		case 8:
			infoStatistics();
			break;
		case 9:
			save();
			break;
		case 10:
			exit();
			break;
		default:
			System.out.println("输入有误！！请从新输入");
			mainMenu();
			break;
		}

	}
	public void login(){}

	public void addBook() {
		System.out.println("新增图书");
	}

	public void borrowOrReturnBook() {
		System.out.println("借/还图书");
	}

	public void modifyBookInfo() {
		System.out.println("修改图书信息");
	}

	public void deleteBook() {
		System.out.println("删除图书");
	}

	public void queryBook() {
		System.out.println("查找图书");
	}

	public void addAdmin() {
		System.out.println("新增管理员");
	}

	public void readerManagement() {
		System.out.println("读者管理");
	}

	public void infoStatistics() {
		System.out.println("信息统计");
	}

	public void save() {
		System.out.println("保存修改");
	}

	public void exit() {
		System.out.println("退出系统");
	}
}
