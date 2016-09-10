package com.issac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class BooksManagementSys {

	private Book book;
	private TreeMap<String, Book> library = new TreeMap<String, Book>();
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
		input.close();
	}

	public void login() {
		read();
		mainMenu();
	}

	public void addBook() {

		System.out.print("������ͼ���ţ�");
		Scanner input = new Scanner(System.in);
		String bookid = input.nextLine();
		while (bookid.equals("")) {
			System.out.print("���벻��Ϊ��!!!\n����������ͼ���ţ�");
			bookid = input.nextLine();
		}
		while (!isNumeric(bookid)) {
			System.out.print("�������󣡣�ͼ����ֻ����������ɣ���\n����������ͼ���ţ�");
			bookid = input.nextLine();
		}
		if (library.get(bookid) != null) {
			System.out.print("����ͼ����ѹ����У��������½�������");
			String count = input.nextLine();
			while (!isNumeric(count)) {
				System.out.print("�������󣡣��벻Ҫ��������������ַ�����\n�����������½�������");
				count = input.nextLine();
			}
			book = library.get(bookid);
			int bookSum = Integer.valueOf(book.getBookSum()) + Integer.valueOf(count);
			int inLibrarySum = Integer.valueOf(book.getInLibrarySum()) + Integer.valueOf(count);
			book.setBookSum(String.valueOf(bookSum));
			book.setInLibrarySum(String.valueOf(inLibrarySum));
		} else {
			System.out.println("����Ϊ����ͼ��,��Ҫ����������Ϣ");
			book = new Book();
			book.setBookId(bookid);
			System.out.print("������ͼ�����ƣ�");
			String name = input.nextLine();
			book.setBookName(name);
			System.out.print("������ͼ�����ߣ�");
			String author = input.nextLine();
			book.setBookAuthor(author);
			System.out.print("����������磺");
			String press = input.nextLine();
			book.setBookPress(press);
			System.out.print("�������ִ��ַ��");
			String address = input.nextLine();
			book.setBookAddress(address);
			System.out.print("�������½�������");
			String count = input.nextLine();
			while (!isNumeric(count)) {
				System.out.print("�������󣡣��벻Ҫ��������������ַ�����\n�����������½�������");
				count = input.nextLine();
			}
			book.setBookSum(count);
			book.setInLibrarySum(count);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");// ¼��ʱ��ϵͳ�Զ�����
			book.setBuyDate(df.format(new Date()));
			library.put(book.getBookId(), book);// ��¼��������ݴ���TreeMap��ʵ��library��
		}
		System.out.println("�鼮��ӳɹ�����");
		display();
		// input.close();
		mainMenu();

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
		Iterator<String> bookIds = library.keySet().iterator();
		StringBuilder bookAttribute = new StringBuilder();
		while (bookIds.hasNext()) {
			book = library.get(bookIds.next());
			bookAttribute.append(
					book.getBookId() + "," + book.getBookName() + "," + book.getBookAuthor() + "," + book.getBookPress()
							+ "," + book.getBookSum() + "," + book.getInLibrarySum() + "," + book.getBookAddress() + ","
							+ book.getBorrowSum() + "," + book.getBuyDate() + "," + book.getDeleteDate() + "\r\n");
		}

		File bookInfo = new File("H://BookManagementSys//bookInfo.txt");// ��TreeMap��ʵ��library���������ݶ����浽����ļ���
		if (!bookInfo.exists()) {// ������ļ������ڣ����½����ļ�
			try {
				bookInfo.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(bookInfo));
			bw.write(bookAttribute.toString());
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("����ɹ�����\n");
		mainMenu();

	}

	public void read() {
		File bookInfo = new File("H://BookManagementSys//bookInfo.txt");
		BufferedReader reader = null;
		String tmpString = null;
		if (!bookInfo.exists()) {
			try {
				bookInfo.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			reader = new BufferedReader(new FileReader(bookInfo));
			while ((tmpString = reader.readLine()) != null) {
				String tmpArray[] = tmpString.split(",");
				book = new Book();
				book.setBookId(tmpArray[0]);
				book.setBookName(tmpArray[1]);
				book.setBookAuthor(tmpArray[2]);
				book.setBookPress(tmpArray[3]);
				book.setBookSum(tmpArray[4]);
				book.setInLibrarySum(tmpArray[5]);
				book.setBookAddress(tmpArray[6]);
				book.setBorrowSum(tmpArray[7]);
				book.setBuyDate(tmpArray[8]);
				book.setDeleteDate(tmpArray[9]);
				library.put(book.getBookId(), book);
			}
			display();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void exit() {
		System.out.println("���˳�ϵͳ����ӭ�ٴ�ʹ��");
		System.exit(0);
	}

	/**
	 * ��ʾ����ͼ����Ϣ
	 */
	public void display() {
		Book b = new Book();
		Iterator<String> bookIds = library.keySet().iterator();
		System.out.println("����ͼ����Ϣ��");
		System.out.println("ͼ����" + "\t" + "ͼ������" + "\t" + "ͼ������" + "\t" + "ͼ�������" + "\t" + "ͼ���ִ��ַ" + "\t" + "ͼ������"
				+ "\t" + "ͼ���ڹ���" + "\t" + "���Ĵ���");
		while (bookIds.hasNext()) {
			b = library.get(bookIds.next());
			System.out.println(b.getBookId() + "\t" + b.getBookName() + "\t" + b.getBookAuthor() + "\t"
					+ b.getBookPress() + "\t" + b.getBookAddress() + "\t" + b.getBookSum() + "\t" + b.getInLibrarySum()
					+ "\t" + b.getBorrowSum() + "\t" + b.getBuyDate() + "\t" + b.getDeleteDate());

		}

	}

	/**
	 * �ж��ַ����Ƿ�Ϊ����
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		new BooksManagementSys().login();

	}
}
