package com.issac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class BooksManagementSys {

	private Book book;
	private Administrator admin;
	private TreeMap<String, Administrator> AdminMap = new TreeMap<>();
	private TreeMap<String, Book> library = new TreeMap<String, Book>();
	private TreeMap<String, Book> deleteBook = new TreeMap<>();
	private TreeMap<String, Reader> readers = new TreeMap<>();
	private BorrowInfo borrowInfo;
	private TreeMap<String, BorrowInfo> borrowInfoMap = new TreeMap<>();
	private Reader reader;
	private Scanner input = new Scanner(System.in);

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
		System.out.print(
				"主菜单:\n1.新增图书\n2.借/还图书\n3.修改图书信息\n4.删除图书\n5.查找图书\n6.新增管理员\n7.读者管理\n8.信息统计\n9.保存修改\n10.退出系统\n请选择要执行的操作：");
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

	/**
	 * 系统登录
	 */
	public void login() {
		read();
		Scanner input = new Scanner(System.in);
		System.out.print("账号:");
		String AdminId = input.nextLine();
		System.out.print("密码:");
		String pass = input.nextLine();
		if (AdminMap.get(AdminId).getAdminPass().equals(pass)) {
			System.out.println("#####欢迎使用GUET图书管理系统#####\n");
			System.out.println("所有图书信息:");
			display(library);
			mainMenu();
		} else {
			System.out.println("账号或密码有误！！");
			login();
		}
	}

	public void addBook() {

		System.out.print("请输入图书编号：");
		input.nextLine();
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
		display(library);
		// input.close();
		mainMenu();

	}

	public void borrowOrReturnBook() {
		Scanner input = new Scanner(System.in);
		System.out.println("1.借书\n2.还书");
		System.out.print("请选择要执行的操作：");
		String option = input.nextLine();
		if (option.equals("1")) {
			borrowBook();
		} else if (option.equals("2")) {
			returnBook();
		} else {
			System.out.println("输入有误！！请从新输入");
			borrowOrReturnBook();
		}
		mainMenu();
	}

	/**
	 * 借书
	 */
	public void borrowBook() {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入要借阅书籍编号:");
		String bookId = input.nextLine();
		book = library.get(bookId);
		while (book == null) {// 如果图书馆中找不到这本书
			System.out.print("输入有误！！请重新输入:");
			bookId = input.nextLine();
			book = library.get(bookId);
		}
		if (book.getInLibrarySum().equals("0")) {// 如果此书在图书馆中已经被借完
			System.out.println("此书已借完!!");
			return;
		}
		System.out.println("书名：" + book.getBookName() + "\t" + "作者:" + book.getBookAuthor());
		System.out.print("请输入读者编号：");
		String readerId = input.nextLine();
		while ((reader = readers.get(readerId)) == null) {
			System.out.print("输入有误！！！请重新输入:");
			readerId = input.nextLine();
		}
		String inLibrarySum = String.valueOf(Integer.valueOf(book.getInLibrarySum()) - 1);// 借书成功则将此书在馆数-1
		book.setInLibrarySum(inLibrarySum);
		String borrowSum = String.valueOf(Integer.parseInt(book.getBorrowSum().trim()) + 1);// 借阅次数+1
		book.setBorrowSum(borrowSum);
		borrowInfo = new BorrowInfo();// 将借阅信息记录下来
		if (borrowInfoMap.keySet().isEmpty()) {// 如果是本系统第一次借阅，则借阅编号从11111开始
			borrowInfo.setBorrowId("11111");
		} else {
			borrowInfo.setBorrowId(newId(borrowInfoMap.lastKey()));
		}
		borrowInfo.setReaderId(reader.getReaderId());
		borrowInfo.setBorrowBookId(book.getBookId());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");// 借书时间从系统获取
		borrowInfo.setBorrowDate(df.format(new Date()));
		borrowInfoMap.put(borrowInfo.getBorrowId(), borrowInfo);
		System.out.println("借书成功！！");
	}

	/**
	 * 还书
	 */
	public void returnBook() {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入借阅编号:");
		String borrowId = input.nextLine();
		while ((borrowInfo = borrowInfoMap.get(borrowId)) == null) {
			System.out.print("输入有误！！！请重新输入:");
			borrowId = input.nextLine();
		}
		book = library.get(borrowInfo.getBorrowBookId());
		String inLibrarySum = String.valueOf(Integer.valueOf(book.getInLibrarySum()) + 1);// 还书则将在馆数+1
		book.setInLibrarySum(inLibrarySum);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");// 还书时间从系统获取
		borrowInfo.setReturnDate(df.format(new Date()));
		System.out.println("还书成功！！");

	}

	/**
	 * 修改图书信息
	 */
	public void modifyBookInfo() {
		System.out.print("请输入要修改图书的编号：");
		String bookId = input.nextLine();
		while (bookId.equals("")) {
			System.out.print("输入不能为空!!!\n请重新输入图书编号：");
			bookId = input.nextLine();
		}
		while (!isNumeric(bookId)) {
			System.out.print("输入有误！！图书编号只能由数字组成！！\n请重新输入图书编号：");
			bookId = input.nextLine();
		}
		if (library.get(bookId) == null) {
			System.out.print("此图书不存在，请重新输入正确的图书编号：");
			bookId = input.next();
		}
		book = library.get(bookId);
		System.out.println("图书编号：" + book.getBookId() + "\n" + "图书名称：" + book.getBookName() + "\n" + "图书作者："
				+ book.getBookAuthor() + "\n" + "出版社：" + book.getBookPress() + "\n" + "现存地址：" + book.getBookAddress()
				+ "\n" + "图书总数：" + book.getBookSum() + "\n" + "在馆数：" + book.getInLibrarySum());
		System.out.println("请输入修改后的信息，如不需要修改该项请直接按回车！");
		System.out.print("图书名称：");
		String bookName = input.nextLine();
		if (!bookName.equals("")) {
			book.setBookName(bookName);
		}
		System.out.print("图书作者：");
		String bookAuthor = input.nextLine();
		if (!bookAuthor.equals("")) {
			book.setBookAuthor(bookAuthor);
		}
		System.out.print("出版社：");
		String bookPress = input.nextLine();
		if (!bookPress.equals("")) {
			book.setBookPress(bookPress);
		}
		System.out.print("现存地址：");
		String bookAddress = input.nextLine();
		if (!bookAddress.equals("")) {
			book.setBookAddress(bookAddress);
		}
		System.out.print("图书总数：");
		String bookSum = input.nextLine();
		if (!bookSum.equals("")) {
			book.setBookSum(bookSum);
		}
		System.out.print("在馆数：");
		String inLibrarySum = input.nextLine();
		if (!inLibrarySum.equals("")) {
			book.setInLibrarySum(inLibrarySum);
		}
		System.out.println("修改成功！！");
		System.out.println("图书编号：" + book.getBookId() + "\n" + "图书名称：" + book.getBookName() + "\n" + "图书作者："
				+ book.getBookAuthor() + "\n" + "出版社：" + book.getBookPress() + "\n" + "现存地址：" + book.getBookAddress()
				+ "\n" + "图书总数：" + book.getBookSum() + "\n" + "在馆数：" + book.getInLibrarySum());
		// library.put(book.getBookId(), book);
		mainMenu();
	}

	public void deleteBook() {
		System.out.print("请输入要删除图书的编号：");
		String bookId = input.nextLine();
		while (bookId.equals("")) {
			System.out.print("输入不能为空!!!\n请重新输入图书编号：");
			bookId = input.nextLine();
		}
		while (!isNumeric(bookId)) {
			System.out.print("输入有误！！图书编号只能由数字组成！！\n请重新输入图书编号：");
			bookId = input.nextLine();
		}
		if (library.get(bookId) == null) {
			System.out.print("此图书不存在，请重新输入正确的图书编号：");
			bookId = input.nextLine();
		}
		book = library.get(bookId);
		System.out.println("图书名称\t图书作者\n" + book.getBookName() + "\t" + book.getBookAuthor());
		System.out.println("删除前：在馆数/图书总数：" + book.getInLibrarySum() + "/" + book.getBookSum());
		System.out.print("请输入删除原因：");
		String deleteReason = input.nextLine();
		book.setDeleteReason(deleteReason);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");// 删除时间从系统获取
		book.setDeleteDate(df.format(new Date()));
		System.out.println("请输入删除后的图书数量：");
		System.out.print("1.图书总数：");
		String bookSum = input.nextLine();
		book.setBookSum(bookSum);
		System.out.print("2.在馆数：");
		String inLibrarySum = input.nextLine();
		book.setInLibrarySum(inLibrarySum);
		if (book.getBookSum().equals("0")) {
			library.remove(bookId);
		}
		String deleteId = "";
		if (deleteBook.keySet().isEmpty()) {
			deleteId = "11111";
		} else {
			deleteId = newId(deleteBook.lastKey());
		}
		deleteBook.put(deleteId, book);
		System.out.println("删除成功！！");
		mainMenu();
	}

	/**
	 * 查找图书
	 */
	public void queryBook() {
		List<String> removeBookId = new ArrayList<>();
		TreeMap<String, Book> queryBook = new TreeMap<String, Book>();
		// TreeMap<String, Book> removeBook = new TreeMap<>();//
		// 多条件查询时用来筛除不符合条件的Book
		Iterator<String> iterator;
		Scanner input = new Scanner(System.in);
		System.out.print("1.按图书编号\n2.按书名\n3.按作者\n4.按出版社\n请选择要查找的方式(若要多条件联合查询，请用逗号将查找方式隔开，例如：1,2):");
		String queryMethod = input.nextLine();
		if (queryMethod.equals("")) {// 如果直接回车不输入查找方式，则显示所有图书信息
			display(library);
			mainMenu();
			return;
		}
		String[] queryMethodArray = queryMethod.split(",");
		for (String method : queryMethodArray) {
			if (method.equals("1")) {
				iterator = library.keySet().iterator();
				System.out.print("请输入书籍编号:");
				String bookId = input.nextLine();
				if (queryBook.isEmpty()) {
					while (iterator.hasNext()) {
						if (iterator.next().indexOf(bookId) > -1) {
							queryBook.put(bookId, library.get(bookId));
						}
					}
				} else {
					iterator = queryBook.keySet().iterator();
					while (iterator.hasNext()) {
						book = queryBook.get(iterator.next());
						if (!book.getBookId().equals(bookId)) {
							queryBook.remove(book.getBookId());
						}
					}
				}

			} else if (method.equals("2")) {
				iterator = library.keySet().iterator();
				System.out.print("请输入书名:");
				String bookName = input.nextLine();
				if (queryBook.isEmpty()) {
					while (iterator.hasNext()) {
						String bookId = iterator.next();
						if (library.get(bookId).getBookName().indexOf(bookName) > -1) {
							queryBook.put(bookId, library.get(bookId));
						}
					}
				} else {
					iterator = queryBook.keySet().iterator();
					while (iterator.hasNext()) {
						book = queryBook.get(iterator.next());
						if (!book.getBookName().equals(bookName)) {
							queryBook.remove(book.getBookId());
						}
					}
				}
			} else if (method.equals("3")) {
				iterator = library.keySet().iterator();
				System.out.print("请输入作者:");
				String author = input.nextLine();
				if (queryBook.isEmpty()) {
					while (iterator.hasNext()) {
						String bookId = iterator.next();
						if (library.get(bookId).getBookAuthor().indexOf(author) > -1) {
							queryBook.put(bookId, library.get(bookId));
						}
					}
				} else {
					iterator = queryBook.keySet().iterator();
					while (iterator.hasNext()) {
						book = queryBook.get(iterator.next());
						if (!book.getBookAuthor().equals(author)) {
							queryBook.remove(book.getBookId());
						}
					}
				}
			} else if (method.equals("4")) {
				iterator = library.keySet().iterator();
				System.out.print("请输入出版社名称:");
				String press = input.nextLine();
				if (queryBook.isEmpty()) {
					while (iterator.hasNext()) {
						String bookId = iterator.next();
						if (library.get(bookId).getBookPress().indexOf(press) > -1) {
							queryBook.put(bookId, library.get(bookId));
						}
					}
				} else {
					iterator = queryBook.keySet().iterator();
					while (iterator.hasNext()) {
						book = queryBook.get(iterator.next());
						if (!book.getBookPress().equals(press)) {
							queryBook.remove(book.getBookId());
						}
					}
				}
			} else {
				System.out.println("输入有误！！");
				queryBook();
			}
		}
		System.out.println("查询结果:");
		display(queryBook);
		queryBook.clear();
		mainMenu();
	}

	/**
	 * 添加管理员
	 */
	public void addAdmin() {
		Scanner input = new Scanner(System.in);
		admin = new Administrator();
		if (AdminMap.isEmpty()) {// 如果是第一次添加，则管理员id号从11111开始
			admin.setAdminId("11111");
		} else {
			admin.setAdminId(newId(AdminMap.lastKey()));// 否则以自增的方式自动生成管理员id号
		}
		System.out.print("请输入管理员姓名:");
		String adminName = input.nextLine();
		admin.setAdminName(adminName);
		System.out.print("请输入密码:");
		String pass = input.nextLine();
		admin.setAdminPass(pass);
		System.out.print("请输入电话号码:");
		String phone = input.nextLine();
		admin.setAdminPhone(phone);
		AdminMap.put(admin.getAdminId(), admin);// 将新增加的管理员放到管理员集合
		System.out.println("添加成功！！");
		mainMenu();
	}

	public void readerManagement() {
		Scanner input = new Scanner(System.in);
		System.out.println("1.新增读者\n2.查询读者");
		System.out.print("请选择要执行的操作:");
		String option = input.nextLine();
		if (option.equals("1")) {
			addReader();
		} else if (option.equals("2")) {
			queryReader();
		} else {
			System.out.println("输入有误,请重新输入！！");
			readerManagement();
		}
	}

	public void addReader() {
		reader = new Reader();
		System.out.print("请输入读者编号：");
		String readerId = input.nextLine();
		reader.setReaderId(readerId);
		System.out.print("请输入读者姓名：");
		String readerName = input.nextLine();
		reader.setReaderName(readerName);
		System.out.print("请输入读者电话号码：");
		String readerPhone = input.nextLine();
		reader.setReaderPhone(readerPhone);
		System.out.print("请输入读者性别：");
		String readerGender = input.nextLine();
		reader.setReaderGender(readerGender);
		readers.put(reader.getReaderId(), reader);
		System.out.println("添加成功！！");
		mainMenu();
	}

	public void deleteReader() {
		System.out.print("请输入读者编号：");
		String readerId = input.nextLine();
		if (readers.get(readerId) == null) {
			System.out.print("读者不存在，请输入正确的读者编号:");
			readerId = input.nextLine();
		}
		System.out.println("读者姓名:" + readers.get(readerId).getReaderName());
		readers.remove(readerId);
		System.out.println("删除成功！！");
		mainMenu();
	}

	public void queryReader() {
		System.out.print("请输入读者编号：");
		String readerId = input.nextLine();
		while (readers.get(readerId) == null) {
			System.out.print("读者不存在，请输入正确的读者编号:");
			readerId = input.nextLine();
		}
		reader = readers.get(readerId);
		System.out.println("读者信息:\n" + "编号：" + reader.getReaderId() + "\n" + "姓名:" + reader.getReaderName() + "\n"
				+ "电话号码:" + reader.getReaderPhone() + "\n" + "性别:" + reader.getReaderGender());

		// 将读者的借阅图书的所有信息列出
		Iterator<String> borrowIds = borrowInfoMap.keySet().iterator();
		System.out.println("#####读者借书记录#####");
		System.out.println("借阅编号" + "\t" + "书籍编号" + "\t" + "书籍名称" + "\t" + "借书时间" + "\t" + "还书时间");
		while (borrowIds.hasNext()) {
			borrowInfo = borrowInfoMap.get(borrowIds.next());
			if (borrowInfo.getReaderId().equals(readerId)) {
				book = library.get(borrowInfo.getBorrowBookId());
				System.out.println(borrowInfo.getBorrowId() + "\t" + book.getBookId() + "\t" + book.getBookName() + "\t"
						+ borrowInfo.getBorrowDate() + "\t" + borrowInfo.getReturnDate());
			}
		}
		mainMenu();
	}

	public void infoStatistics() {
		List<Map.Entry<String, Book>> list = new ArrayList<Map.Entry<String, Book>>(library.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Book>>() {
			public int compare(Entry<String, Book> o1, Entry<String, Book> o2) {
				int a = Integer.valueOf(o1.getValue().getBorrowSum());
				int b = Integer.valueOf(o2.getValue().getBorrowSum());
				if (a < b) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		System.out.println("图书借阅数量排序:");
		System.out.println("图书编号" + "\t" + "图书名称" + "\t" + "图书作者" + "\t" + "图书出版社" + "\t" + "图书现存地址" + "\t" + "图书总数"
				+ "\t" + "图书在馆数" + "\t" + "借阅次数" + "\t" + "购入时间");
		for (Map.Entry<String, Book> mapping : list) {
			Book b = library.get(mapping.getKey());
			System.out.println(b.getBookId() + "\t" + b.getBookName() + "\t" + b.getBookAuthor() + "\t"
					+ b.getBookPress() + "\t" + b.getBookAddress() + "\t" + b.getBookSum() + "\t" + b.getInLibrarySum()
					+ "\t" + b.getBorrowSum() + "\t" + b.getBuyDate());
		}
		System.out.println("\n\n\n");

		TreeMap<String, Book> bookMap = new TreeMap<>();
		Set<String> bookRoom = new HashSet<>();
		Iterator<String> iterator = library.keySet().iterator();
		while (iterator.hasNext()) {
			bookRoom.add(library.get(iterator.next()).getBookAddress());
		}
		Iterator<String> bookRoomIterator = bookRoom.iterator();
		while (bookRoomIterator.hasNext()) {
			String bookRoomName = bookRoomIterator.next();
			iterator = library.keySet().iterator();
			while (iterator.hasNext()) {
				book = library.get(iterator.next());
				if (book.getBookAddress().equals(bookRoomName)) {
					bookMap.put(book.getBookId(), book);
				}
			}
			System.out.println("######书库" + bookRoomName + "的藏书情况#######");
			display(bookMap);
			System.out.println("\n");
			bookMap.clear();
		}
		mainMenu();
	}

	/**
	 * 将修改后的数据写入文件里
	 */
	public void save() {

		Iterator<String> iterator;
		StringBuilder stringBuilder = new StringBuilder();
		File file;
		/**
		 * 将新增图书写入文件
		 */
		iterator = library.keySet().iterator();
		while (iterator.hasNext()) {
			book = library.get(iterator.next());
			stringBuilder.append(
					book.getBookId() + "," + book.getBookName() + "," + book.getBookAuthor() + "," + book.getBookPress()
							+ "," + book.getBookSum() + "," + book.getInLibrarySum() + "," + book.getBookAddress() + ","
							+ book.getBorrowSum() + "," + book.getBuyDate() + "," + book.getDeleteDate() + "\r\n");
		}

		file = new File("H://BookManagementSys//bookInfo.txt");// 将TreeMap的实例library的所有数据都保存到这个文件里
		try {
			if (!file.exists()) {// 如果该文件不存在，则新建该文件
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			// System.out.println(stringBuilder.toString());
			bw.write(stringBuilder.toString());
			stringBuilder.delete(0, stringBuilder.length());
			// System.out.println(stringBuilder.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 将删除图书的信息写入文件
		 */
		iterator = deleteBook.keySet().iterator();
		while (iterator.hasNext()) {
			book = deleteBook.get(iterator.next());
			stringBuilder.append(book.getBookId() + "," + book.getBookName() + "," + book.getBookAuthor() + ","
					+ book.getBookPress() + "," + book.getBookSum() + "," + book.getInLibrarySum() + ","
					+ book.getBookAddress() + "," + book.getBorrowSum() + "," + book.getBuyDate() + ","
					+ book.getDeleteDate() + "," + book.getDeleteReason() + "\r\n");
		}

		file = new File("H://BookManagementSys//deleteBookInfo.txt");// 将TreeMap的实例deleteBooks的所有数据都保存到这个文件里
		try {
			if (!file.exists()) {// 如果该文件不存在，则新建该文件
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(stringBuilder.toString());
			stringBuilder.delete(0, stringBuilder.length());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 将读者信息写入文件里
		 */
		iterator = readers.keySet().iterator();
		while (iterator.hasNext()) {
			reader = readers.get(iterator.next());
			stringBuilder.append(reader.getReaderId() + "," + reader.getReaderName() + "," + reader.getReaderPhone()
					+ "," + reader.getReaderGender() + "\r\n");
		}
		file = new File("H://BookManagementSys//readerInfo.txt");

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			// System.out.println(stringBuilder.toString());
			bw.write(stringBuilder.toString());
			stringBuilder.delete(0, stringBuilder.length());
			// System.out.println(stringBuilder.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 将借阅记录写入文件
		 */
		iterator = borrowInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			borrowInfo = borrowInfoMap.get(iterator.next());
			stringBuilder.append(
					borrowInfo.getBorrowId() + "," + borrowInfo.getReaderId() + "," + borrowInfo.getBorrowBookId() + ","
							+ borrowInfo.getBorrowDate() + "," + borrowInfo.getReturnDate() + "\r\n");
		}
		file = new File("H://BookManagementSys//borrowInfo.txt");

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(stringBuilder.toString());
			stringBuilder.delete(0, stringBuilder.length());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * 将管理员信息写入文件
		 */
		iterator = AdminMap.keySet().iterator();
		while (iterator.hasNext()) {
			admin = AdminMap.get(iterator.next());
			stringBuilder.append(admin.getAdminId() + "," + admin.getAdminName() + "," + admin.getAdminPass() + ","
					+ admin.getAdminPhone());
		}
		file = new File("H://BookManagementSys//adminInfo.txt");

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(stringBuilder.toString());
			stringBuilder.delete(0, stringBuilder.length());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("保存成功！！\n");
		mainMenu();

	}

	/**
	 * 读取磁盘中文件的信息
	 */
	public void read() {
		File file;
		BufferedReader bufferedReader = null;
		String tmpString = null;
		String tmpArray[];
		/**
		 * 读取购买的图书信息
		 */
		file = new File("H://BookManagementSys//bookInfo.txt");

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((tmpString = bufferedReader.readLine()) != null) {
				tmpArray = tmpString.split(",");
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 读取被删除图书的信息
		 */
		file = new File("H://BookManagementSys//deleteBookInfo.txt");

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			bufferedReader = new BufferedReader(new FileReader(file));
			String delelteId = "11110";
			while ((tmpString = bufferedReader.readLine()) != null) {
				tmpArray = tmpString.split(",");
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
				book.setDeleteReason(tmpArray[10]);
				delelteId = newId(delelteId);
				deleteBook.put(delelteId, book);// 这里有问题
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 读取读者信息
		 */
		file = new File("H://BookManagementSys//readerInfo.txt");
		try {
			if (file.exists()) {
				bufferedReader = new BufferedReader(new FileReader(file));
				while ((tmpString = bufferedReader.readLine()) != null) {
					tmpArray = tmpString.split(",");
					reader = new Reader();
					reader.setReaderId(tmpArray[0]);
					reader.setReaderName(tmpArray[1]);
					reader.setReaderPhone(tmpArray[2]);
					reader.setReaderGender(tmpArray[3]);
					readers.put(reader.getReaderId(), reader);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 读取借阅记录
		 */
		file = new File("H://BookManagementSys//borrowInfo.txt");
		try {
			if (file.exists()) {
				bufferedReader = new BufferedReader(new FileReader(file));
				while ((tmpString = bufferedReader.readLine()) != null) {
					tmpArray = tmpString.split(",");
					borrowInfo = new BorrowInfo();
					borrowInfo.setBorrowId(tmpArray[0]);
					borrowInfo.setReaderId(tmpArray[1]);
					borrowInfo.setBorrowBookId(tmpArray[2]);
					borrowInfo.setBorrowDate(tmpArray[3]);
					borrowInfo.setReturnDate(tmpArray[4]);
					borrowInfoMap.put(borrowInfo.getBorrowId(), borrowInfo);
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
		}

		/**
		 * 读取管理员信息
		 */
		file = new File("H://BookManagementSys//adminInfo.txt");
		try {
			if (file.exists()) {
				bufferedReader = new BufferedReader(new FileReader(file));
				while ((tmpString = bufferedReader.readLine()) != null) {
					tmpArray = tmpString.split(",");
					admin = new Administrator();
					admin.setAdminId(tmpArray[0]);
					admin.setAdminName(tmpArray[1]);
					admin.setAdminPass(tmpArray[2]);
					admin.setAdminPhone(tmpArray[3]);
					AdminMap.put(admin.getAdminId(), admin);
				}
			}
		} catch (IOException e) {
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
	public void display(TreeMap<String, Book> treeMap) {
		Book b = new Book();
		Iterator<String> bookIds = treeMap.keySet().iterator();
		System.out.println("图书编号" + "\t" + "图书名称" + "\t" + "图书作者" + "\t" + "图书出版社" + "\t" + "图书现存地址" + "\t" + "图书总数"
				+ "\t" + "图书在馆数" + "\t" + "借阅次数" + "\t" + "购入时间");
		while (bookIds.hasNext()) {
			b = library.get(bookIds.next());
			System.out.println(b.getBookId() + "\t" + b.getBookName() + "\t" + b.getBookAuthor() + "\t"
					+ b.getBookPress() + "\t" + b.getBookAddress() + "\t" + b.getBookSum() + "\t" + b.getInLibrarySum()
					+ "\t" + b.getBorrowSum() + "\t" + b.getBuyDate());

		}

	}

	/**
	 * 以自增的方式生成新的Id号
	 * 
	 * @param key
	 * @return
	 */
	public String newId(String key) {
		key = String.valueOf(Integer.valueOf(key) + 1);
		return key;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 *            待判断的字符串
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
