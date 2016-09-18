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
				"���˵�:\n1.����ͼ��\n2.��/��ͼ��\n3.�޸�ͼ����Ϣ\n4.ɾ��ͼ��\n5.����ͼ��\n6.��������Ա\n7.���߹���\n8.��Ϣͳ��\n9.�����޸�\n10.�˳�ϵͳ\n��ѡ��Ҫִ�еĲ�����");
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

	/**
	 * ϵͳ��¼
	 */
	public void login() {
		read();
		Scanner input = new Scanner(System.in);
		System.out.print("�˺�:");
		String AdminId = input.nextLine();
		System.out.print("����:");
		String pass = input.nextLine();
		if (AdminMap.get(AdminId).getAdminPass().equals(pass)) {
			System.out.println("#####��ӭʹ��GUETͼ�����ϵͳ#####\n");
			System.out.println("����ͼ����Ϣ:");
			display(library);
			mainMenu();
		} else {
			System.out.println("�˺Ż��������󣡣�");
			login();
		}
	}

	public void addBook() {

		System.out.print("������ͼ���ţ�");
		input.nextLine();
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
		display(library);
		// input.close();
		mainMenu();

	}

	public void borrowOrReturnBook() {
		Scanner input = new Scanner(System.in);
		System.out.println("1.����\n2.����");
		System.out.print("��ѡ��Ҫִ�еĲ�����");
		String option = input.nextLine();
		if (option.equals("1")) {
			borrowBook();
		} else if (option.equals("2")) {
			returnBook();
		} else {
			System.out.println("�������󣡣����������");
			borrowOrReturnBook();
		}
		mainMenu();
	}

	/**
	 * ����
	 */
	public void borrowBook() {
		Scanner input = new Scanner(System.in);
		System.out.print("������Ҫ�����鼮���:");
		String bookId = input.nextLine();
		book = library.get(bookId);
		while (book == null) {// ���ͼ������Ҳ����Ȿ��
			System.out.print("�������󣡣�����������:");
			bookId = input.nextLine();
			book = library.get(bookId);
		}
		if (book.getInLibrarySum().equals("0")) {// ���������ͼ������Ѿ�������
			System.out.println("�����ѽ���!!");
			return;
		}
		System.out.println("������" + book.getBookName() + "\t" + "����:" + book.getBookAuthor());
		System.out.print("��������߱�ţ�");
		String readerId = input.nextLine();
		while ((reader = readers.get(readerId)) == null) {
			System.out.print("�������󣡣�������������:");
			readerId = input.nextLine();
		}
		String inLibrarySum = String.valueOf(Integer.valueOf(book.getInLibrarySum()) - 1);// ����ɹ��򽫴����ڹ���-1
		book.setInLibrarySum(inLibrarySum);
		String borrowSum = String.valueOf(Integer.parseInt(book.getBorrowSum().trim()) + 1);// ���Ĵ���+1
		book.setBorrowSum(borrowSum);
		borrowInfo = new BorrowInfo();// ��������Ϣ��¼����
		if (borrowInfoMap.keySet().isEmpty()) {// ����Ǳ�ϵͳ��һ�ν��ģ�����ı�Ŵ�11111��ʼ
			borrowInfo.setBorrowId("11111");
		} else {
			borrowInfo.setBorrowId(newId(borrowInfoMap.lastKey()));
		}
		borrowInfo.setReaderId(reader.getReaderId());
		borrowInfo.setBorrowBookId(book.getBookId());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");// ����ʱ���ϵͳ��ȡ
		borrowInfo.setBorrowDate(df.format(new Date()));
		borrowInfoMap.put(borrowInfo.getBorrowId(), borrowInfo);
		System.out.println("����ɹ�����");
	}

	/**
	 * ����
	 */
	public void returnBook() {
		Scanner input = new Scanner(System.in);
		System.out.print("��������ı��:");
		String borrowId = input.nextLine();
		while ((borrowInfo = borrowInfoMap.get(borrowId)) == null) {
			System.out.print("�������󣡣�������������:");
			borrowId = input.nextLine();
		}
		book = library.get(borrowInfo.getBorrowBookId());
		String inLibrarySum = String.valueOf(Integer.valueOf(book.getInLibrarySum()) + 1);// �������ڹ���+1
		book.setInLibrarySum(inLibrarySum);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");// ����ʱ���ϵͳ��ȡ
		borrowInfo.setReturnDate(df.format(new Date()));
		System.out.println("����ɹ�����");

	}

	/**
	 * �޸�ͼ����Ϣ
	 */
	public void modifyBookInfo() {
		System.out.print("������Ҫ�޸�ͼ��ı�ţ�");
		String bookId = input.nextLine();
		while (bookId.equals("")) {
			System.out.print("���벻��Ϊ��!!!\n����������ͼ���ţ�");
			bookId = input.nextLine();
		}
		while (!isNumeric(bookId)) {
			System.out.print("�������󣡣�ͼ����ֻ����������ɣ���\n����������ͼ���ţ�");
			bookId = input.nextLine();
		}
		if (library.get(bookId) == null) {
			System.out.print("��ͼ�鲻���ڣ�������������ȷ��ͼ���ţ�");
			bookId = input.next();
		}
		book = library.get(bookId);
		System.out.println("ͼ���ţ�" + book.getBookId() + "\n" + "ͼ�����ƣ�" + book.getBookName() + "\n" + "ͼ�����ߣ�"
				+ book.getBookAuthor() + "\n" + "�����磺" + book.getBookPress() + "\n" + "�ִ��ַ��" + book.getBookAddress()
				+ "\n" + "ͼ��������" + book.getBookSum() + "\n" + "�ڹ�����" + book.getInLibrarySum());
		System.out.println("�������޸ĺ����Ϣ���粻��Ҫ�޸ĸ�����ֱ�Ӱ��س���");
		System.out.print("ͼ�����ƣ�");
		String bookName = input.nextLine();
		if (!bookName.equals("")) {
			book.setBookName(bookName);
		}
		System.out.print("ͼ�����ߣ�");
		String bookAuthor = input.nextLine();
		if (!bookAuthor.equals("")) {
			book.setBookAuthor(bookAuthor);
		}
		System.out.print("�����磺");
		String bookPress = input.nextLine();
		if (!bookPress.equals("")) {
			book.setBookPress(bookPress);
		}
		System.out.print("�ִ��ַ��");
		String bookAddress = input.nextLine();
		if (!bookAddress.equals("")) {
			book.setBookAddress(bookAddress);
		}
		System.out.print("ͼ��������");
		String bookSum = input.nextLine();
		if (!bookSum.equals("")) {
			book.setBookSum(bookSum);
		}
		System.out.print("�ڹ�����");
		String inLibrarySum = input.nextLine();
		if (!inLibrarySum.equals("")) {
			book.setInLibrarySum(inLibrarySum);
		}
		System.out.println("�޸ĳɹ�����");
		System.out.println("ͼ���ţ�" + book.getBookId() + "\n" + "ͼ�����ƣ�" + book.getBookName() + "\n" + "ͼ�����ߣ�"
				+ book.getBookAuthor() + "\n" + "�����磺" + book.getBookPress() + "\n" + "�ִ��ַ��" + book.getBookAddress()
				+ "\n" + "ͼ��������" + book.getBookSum() + "\n" + "�ڹ�����" + book.getInLibrarySum());
		// library.put(book.getBookId(), book);
		mainMenu();
	}

	public void deleteBook() {
		System.out.print("������Ҫɾ��ͼ��ı�ţ�");
		String bookId = input.nextLine();
		while (bookId.equals("")) {
			System.out.print("���벻��Ϊ��!!!\n����������ͼ���ţ�");
			bookId = input.nextLine();
		}
		while (!isNumeric(bookId)) {
			System.out.print("�������󣡣�ͼ����ֻ����������ɣ���\n����������ͼ���ţ�");
			bookId = input.nextLine();
		}
		if (library.get(bookId) == null) {
			System.out.print("��ͼ�鲻���ڣ�������������ȷ��ͼ���ţ�");
			bookId = input.nextLine();
		}
		book = library.get(bookId);
		System.out.println("ͼ������\tͼ������\n" + book.getBookName() + "\t" + book.getBookAuthor());
		System.out.println("ɾ��ǰ���ڹ���/ͼ��������" + book.getInLibrarySum() + "/" + book.getBookSum());
		System.out.print("������ɾ��ԭ��");
		String deleteReason = input.nextLine();
		book.setDeleteReason(deleteReason);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");// ɾ��ʱ���ϵͳ��ȡ
		book.setDeleteDate(df.format(new Date()));
		System.out.println("������ɾ�����ͼ��������");
		System.out.print("1.ͼ��������");
		String bookSum = input.nextLine();
		book.setBookSum(bookSum);
		System.out.print("2.�ڹ�����");
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
		System.out.println("ɾ���ɹ�����");
		mainMenu();
	}

	/**
	 * ����ͼ��
	 */
	public void queryBook() {
		List<String> removeBookId = new ArrayList<>();
		TreeMap<String, Book> queryBook = new TreeMap<String, Book>();
		// TreeMap<String, Book> removeBook = new TreeMap<>();//
		// ��������ѯʱ����ɸ��������������Book
		Iterator<String> iterator;
		Scanner input = new Scanner(System.in);
		System.out.print("1.��ͼ����\n2.������\n3.������\n4.��������\n��ѡ��Ҫ���ҵķ�ʽ(��Ҫ���������ϲ�ѯ�����ö��Ž����ҷ�ʽ���������磺1,2):");
		String queryMethod = input.nextLine();
		if (queryMethod.equals("")) {// ���ֱ�ӻس���������ҷ�ʽ������ʾ����ͼ����Ϣ
			display(library);
			mainMenu();
			return;
		}
		String[] queryMethodArray = queryMethod.split(",");
		for (String method : queryMethodArray) {
			if (method.equals("1")) {
				iterator = library.keySet().iterator();
				System.out.print("�������鼮���:");
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
				System.out.print("����������:");
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
				System.out.print("����������:");
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
				System.out.print("���������������:");
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
				System.out.println("�������󣡣�");
				queryBook();
			}
		}
		System.out.println("��ѯ���:");
		display(queryBook);
		queryBook.clear();
		mainMenu();
	}

	/**
	 * ��ӹ���Ա
	 */
	public void addAdmin() {
		Scanner input = new Scanner(System.in);
		admin = new Administrator();
		if (AdminMap.isEmpty()) {// ����ǵ�һ����ӣ������Աid�Ŵ�11111��ʼ
			admin.setAdminId("11111");
		} else {
			admin.setAdminId(newId(AdminMap.lastKey()));// �����������ķ�ʽ�Զ����ɹ���Աid��
		}
		System.out.print("���������Ա����:");
		String adminName = input.nextLine();
		admin.setAdminName(adminName);
		System.out.print("����������:");
		String pass = input.nextLine();
		admin.setAdminPass(pass);
		System.out.print("������绰����:");
		String phone = input.nextLine();
		admin.setAdminPhone(phone);
		AdminMap.put(admin.getAdminId(), admin);// �������ӵĹ���Ա�ŵ�����Ա����
		System.out.println("��ӳɹ�����");
		mainMenu();
	}

	public void readerManagement() {
		Scanner input = new Scanner(System.in);
		System.out.println("1.��������\n2.��ѯ����");
		System.out.print("��ѡ��Ҫִ�еĲ���:");
		String option = input.nextLine();
		if (option.equals("1")) {
			addReader();
		} else if (option.equals("2")) {
			queryReader();
		} else {
			System.out.println("��������,���������룡��");
			readerManagement();
		}
	}

	public void addReader() {
		reader = new Reader();
		System.out.print("��������߱�ţ�");
		String readerId = input.nextLine();
		reader.setReaderId(readerId);
		System.out.print("���������������");
		String readerName = input.nextLine();
		reader.setReaderName(readerName);
		System.out.print("��������ߵ绰���룺");
		String readerPhone = input.nextLine();
		reader.setReaderPhone(readerPhone);
		System.out.print("����������Ա�");
		String readerGender = input.nextLine();
		reader.setReaderGender(readerGender);
		readers.put(reader.getReaderId(), reader);
		System.out.println("��ӳɹ�����");
		mainMenu();
	}

	public void deleteReader() {
		System.out.print("��������߱�ţ�");
		String readerId = input.nextLine();
		if (readers.get(readerId) == null) {
			System.out.print("���߲����ڣ���������ȷ�Ķ��߱��:");
			readerId = input.nextLine();
		}
		System.out.println("��������:" + readers.get(readerId).getReaderName());
		readers.remove(readerId);
		System.out.println("ɾ���ɹ�����");
		mainMenu();
	}

	public void queryReader() {
		System.out.print("��������߱�ţ�");
		String readerId = input.nextLine();
		while (readers.get(readerId) == null) {
			System.out.print("���߲����ڣ���������ȷ�Ķ��߱��:");
			readerId = input.nextLine();
		}
		reader = readers.get(readerId);
		System.out.println("������Ϣ:\n" + "��ţ�" + reader.getReaderId() + "\n" + "����:" + reader.getReaderName() + "\n"
				+ "�绰����:" + reader.getReaderPhone() + "\n" + "�Ա�:" + reader.getReaderGender());

		// �����ߵĽ���ͼ���������Ϣ�г�
		Iterator<String> borrowIds = borrowInfoMap.keySet().iterator();
		System.out.println("#####���߽����¼#####");
		System.out.println("���ı��" + "\t" + "�鼮���" + "\t" + "�鼮����" + "\t" + "����ʱ��" + "\t" + "����ʱ��");
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
		System.out.println("ͼ�������������:");
		System.out.println("ͼ����" + "\t" + "ͼ������" + "\t" + "ͼ������" + "\t" + "ͼ�������" + "\t" + "ͼ���ִ��ַ" + "\t" + "ͼ������"
				+ "\t" + "ͼ���ڹ���" + "\t" + "���Ĵ���" + "\t" + "����ʱ��");
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
			System.out.println("######���" + bookRoomName + "�Ĳ������#######");
			display(bookMap);
			System.out.println("\n");
			bookMap.clear();
		}
		mainMenu();
	}

	/**
	 * ���޸ĺ������д���ļ���
	 */
	public void save() {

		Iterator<String> iterator;
		StringBuilder stringBuilder = new StringBuilder();
		File file;
		/**
		 * ������ͼ��д���ļ�
		 */
		iterator = library.keySet().iterator();
		while (iterator.hasNext()) {
			book = library.get(iterator.next());
			stringBuilder.append(
					book.getBookId() + "," + book.getBookName() + "," + book.getBookAuthor() + "," + book.getBookPress()
							+ "," + book.getBookSum() + "," + book.getInLibrarySum() + "," + book.getBookAddress() + ","
							+ book.getBorrowSum() + "," + book.getBuyDate() + "," + book.getDeleteDate() + "\r\n");
		}

		file = new File("H://BookManagementSys//bookInfo.txt");// ��TreeMap��ʵ��library���������ݶ����浽����ļ���
		try {
			if (!file.exists()) {// ������ļ������ڣ����½����ļ�
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
		 * ��ɾ��ͼ�����Ϣд���ļ�
		 */
		iterator = deleteBook.keySet().iterator();
		while (iterator.hasNext()) {
			book = deleteBook.get(iterator.next());
			stringBuilder.append(book.getBookId() + "," + book.getBookName() + "," + book.getBookAuthor() + ","
					+ book.getBookPress() + "," + book.getBookSum() + "," + book.getInLibrarySum() + ","
					+ book.getBookAddress() + "," + book.getBorrowSum() + "," + book.getBuyDate() + ","
					+ book.getDeleteDate() + "," + book.getDeleteReason() + "\r\n");
		}

		file = new File("H://BookManagementSys//deleteBookInfo.txt");// ��TreeMap��ʵ��deleteBooks���������ݶ����浽����ļ���
		try {
			if (!file.exists()) {// ������ļ������ڣ����½����ļ�
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
		 * ��������Ϣд���ļ���
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
		 * �����ļ�¼д���ļ�
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
		 * ������Ա��Ϣд���ļ�
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
		System.out.println("����ɹ�����\n");
		mainMenu();

	}

	/**
	 * ��ȡ�������ļ�����Ϣ
	 */
	public void read() {
		File file;
		BufferedReader bufferedReader = null;
		String tmpString = null;
		String tmpArray[];
		/**
		 * ��ȡ�����ͼ����Ϣ
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
		 * ��ȡ��ɾ��ͼ�����Ϣ
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
				deleteBook.put(delelteId, book);// ����������
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * ��ȡ������Ϣ
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
		 * ��ȡ���ļ�¼
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
		 * ��ȡ����Ա��Ϣ
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
		System.out.println("���˳�ϵͳ����ӭ�ٴ�ʹ��");
		System.exit(0);
	}

	/**
	 * ��ʾ����ͼ����Ϣ
	 */
	public void display(TreeMap<String, Book> treeMap) {
		Book b = new Book();
		Iterator<String> bookIds = treeMap.keySet().iterator();
		System.out.println("ͼ����" + "\t" + "ͼ������" + "\t" + "ͼ������" + "\t" + "ͼ�������" + "\t" + "ͼ���ִ��ַ" + "\t" + "ͼ������"
				+ "\t" + "ͼ���ڹ���" + "\t" + "���Ĵ���" + "\t" + "����ʱ��");
		while (bookIds.hasNext()) {
			b = library.get(bookIds.next());
			System.out.println(b.getBookId() + "\t" + b.getBookName() + "\t" + b.getBookAuthor() + "\t"
					+ b.getBookPress() + "\t" + b.getBookAddress() + "\t" + b.getBookSum() + "\t" + b.getInLibrarySum()
					+ "\t" + b.getBorrowSum() + "\t" + b.getBuyDate());

		}

	}

	/**
	 * �������ķ�ʽ�����µ�Id��
	 * 
	 * @param key
	 * @return
	 */
	public String newId(String key) {
		key = String.valueOf(Integer.valueOf(key) + 1);
		return key;
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ����
	 * 
	 * @param str
	 *            ���жϵ��ַ���
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
