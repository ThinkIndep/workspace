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
		input.close();
	}

	public void login() {
		read();
		mainMenu();
	}

	public void addBook() {

		System.out.print("请输入图书编号：");
		Scanner input = new Scanner(System.in);
		String bookid = input.nextLine();
		while (bookid.equals("")) {
			System.out.print("输入不能为空!!!\n请重新输入图书编号：");
			bookid = input.nextLine();
		}
		while (!isNumeric(bookid)) {
			System.out.print("输入有误！！图书编号只能由数字组成！！\n请重新输入图书编号：");
			bookid = input.nextLine();
		}
		if (library.get(bookid) != null) {
			System.out.print("此书图书馆已购买有，请输入新进册数：");
			String count = input.nextLine();
			while (!isNumeric(count)) {
				System.out.print("输入有误！！请不要输入数字以外的字符！！\n请重新输入新进册数：");
				count = input.nextLine();
			}
			book = library.get(bookid);
			int bookSum = Integer.valueOf(book.getBookSum()) + Integer.valueOf(count);
			int inLibrarySum = Integer.valueOf(book.getInLibrarySum()) + Integer.valueOf(count);
			book.setBookSum(String.valueOf(bookSum));
			book.setInLibrarySum(String.valueOf(inLibrarySum));
		} else {
			System.out.println("此书为新增图书,需要输入完整信息");
			book = new Book();
			book.setBookId(bookid);
			System.out.print("请输入图书名称：");
			String name = input.nextLine();
			book.setBookName(name);
			System.out.print("请输入图书作者：");
			String author = input.nextLine();
			book.setBookAuthor(author);
			System.out.print("请输入出版社：");
			String press = input.nextLine();
			book.setBookPress(press);
			System.out.print("请输入现存地址：");
			String address = input.nextLine();
			book.setBookAddress(address);
			System.out.print("请输入新进册数：");
			String count = input.nextLine();
			while (!isNumeric(count)) {
				System.out.print("输入有误！！请不要输入数字以外的字符！！\n请重新输入新进册数：");
				count = input.nextLine();
			}
			book.setBookSum(count);
			book.setInLibrarySum(count);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");// 录入时间系统自动生成
			book.setBuyDate(df.format(new Date()));
			library.put(book.getBookId(), book);// 将录入的新书暂存在TreeMap的实例library中
		}
		System.out.println("书籍添加成功！！");
		display();
		// input.close();
		mainMenu();

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
		Iterator<String> bookIds = library.keySet().iterator();
		StringBuilder bookAttribute = new StringBuilder();
		while (bookIds.hasNext()) {
			book = library.get(bookIds.next());
			bookAttribute.append(
					book.getBookId() + "," + book.getBookName() + "," + book.getBookAuthor() + "," + book.getBookPress()
							+ "," + book.getBookSum() + "," + book.getInLibrarySum() + "," + book.getBookAddress() + ","
							+ book.getBorrowSum() + "," + book.getBuyDate() + "," + book.getDeleteDate() + "\r\n");
		}

		File bookInfo = new File("H://BookManagementSys//bookInfo.txt");// 将TreeMap的实例library的所有数据都保存到这个文件里
		if (!bookInfo.exists()) {// 如果该文件不存在，则新建该文件
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
		System.out.println("保存成功！！\n");
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
		System.out.println("已退出系统，欢迎再次使用");
		System.exit(0);
	}

	/**
	 * 显示所有图书信息
	 */
	public void display() {
		Book b = new Book();
		Iterator<String> bookIds = library.keySet().iterator();
		System.out.println("所有图书信息：");
		System.out.println("图书编号" + "\t" + "图书名称" + "\t" + "图书作者" + "\t" + "图书出版社" + "\t" + "图书现存地址" + "\t" + "图书总数"
				+ "\t" + "图书在馆数" + "\t" + "借阅次数");
		while (bookIds.hasNext()) {
			b = library.get(bookIds.next());
			System.out.println(b.getBookId() + "\t" + b.getBookName() + "\t" + b.getBookAuthor() + "\t"
					+ b.getBookPress() + "\t" + b.getBookAddress() + "\t" + b.getBookSum() + "\t" + b.getInLibrarySum()
					+ "\t" + b.getBorrowSum() + "\t" + b.getBuyDate() + "\t" + b.getDeleteDate());

		}

	}

	/**
	 * 判断字符串是否为数字
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
