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
		System.out.println("#####��ӭʹ��GUETͼ�����ϵͳ#####\n");
		System.out.print(
				"���˵�:\n1.����ͼ��\n2.��/��ͼ��\n3.�޸�ͼ����Ϣ\n4.ɾ��ͼ��\n5.����ͼ��\n6.��������Ա\n7.���߹���\n8.��Ϣͳ��\n9.�����޸�\n10.�˳�ϵͳ\n��ѡ��Ҫִ�еĲ�����");
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
			System.out.println("�������󣡣����������");
			mainMenu();
			break;
		}

	}
	public void login(){}

	public void addBook() {
		System.out.println("����ͼ��");
	}

	public void borrowOrReturnBook() {
		System.out.println("��/��ͼ��");
	}

	public void modifyBookInfo() {
		System.out.println("�޸�ͼ����Ϣ");
	}

	public void deleteBook() {
		System.out.println("ɾ��ͼ��");
	}

	public void queryBook() {
		System.out.println("����ͼ��");
	}

	public void addAdmin() {
		System.out.println("��������Ա");
	}

	public void readerManagement() {
		System.out.println("���߹���");
	}

	public void infoStatistics() {
		System.out.println("��Ϣͳ��");
	}

	public void save() {
		System.out.println("�����޸�");
	}

	public void exit() {
		System.out.println("�˳�ϵͳ");
	}
}
