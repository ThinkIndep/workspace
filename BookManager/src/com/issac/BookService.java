package com.issac;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.mysql.jdbc.Blob;

public class BookService {
	String filepath;//�ϴ�ͼƬ�����ݿ������·������bolb�࣬��Ϊ���ǵ��޸��鼮��Ϣʱ�û�����û�иı�ͼƬ
	Blob blob;
	boolean isChangePicture;//�ж��û��Ƿ������ͼƬ
	sqlServer sqlServer = new sqlServer();
	MyBookSysManager myBookSysManager = new MyBookSysManager();
	AddBookFrame addBookFrame = new AddBookFrame();
	CheckFrame checkFrame = new CheckFrame();
	ChangeFrame changeFrame = new ChangeFrame();

	public BookService() throws SQLException {
		addEvent();
		fillTable();
	}

	/**
	 * Ϊ���д��ڵİ�ť��Ӽ����������¼�
	 */
	public void addEvent() {
		myBookSysManager.add_button.addActionListener(e -> {//������������Ӱ�ť������ʾ��Ӵ��ڣ�������������
			addBookFrame.addFrame.setVisible(true);
			checkFrame.checkFrame.setVisible(false);
			changeFrame.addFrame.setVisible(false);
		});
		myBookSysManager.check_button.addActionListener(e -> {//������������ѯ��ť������ʾ��Ӵ��ڣ�������������
			addBookFrame.addFrame.setVisible(false);
			checkFrame.checkFrame.setVisible(true);
			changeFrame.addFrame.setVisible(false);
		});
		
		myBookSysManager.change_button.addActionListener(e ->{//�����������޸İ�ť������ʾ��Ӵ��ڣ�������������
			
			if (changeBook()) {//���û��ѡ���б��е��鼮���򵯳����洰�ڣ�����ʾ�鼮����
				addBookFrame.addFrame.setVisible(false);
				checkFrame.checkFrame.setVisible(false);
				changeFrame.addFrame.setVisible(true);
			}
		});
		myBookSysManager.table.addMouseListener(new MouseAdapter() {//��ʾjtable���б������е��鼮ͼƬ
			public void  mouseClicked(MouseEvent e){
				if (e.getClickCount()==1) {
					int selectedRow[]=getSelectedRow();
					 int id = Integer.parseInt((String) myBookSysManager.table.getValueAt(selectedRow[0], 0));
					mainFrameShowImage(id);
				}
			}
		}
			
		);
		myBookSysManager.delete_button.addActionListener(e ->{// ������"ɾ����ť"��Ӽ�����¼�����
			deleteBook();
		});
		addBookFrame.select_img.addActionListener(e -> {// Ϊ����鼮���ڵġ�ѡ��ͼƬ����ť����¼�����
			select_img();
			ImageIcon icon = new ImageIcon(filepath);
			addBookFrame.img_label.setIcon(icon);
		});

		addBookFrame.addBookButton.addActionListener(e -> {// Ϊ����鼮���ڵġ�ȷ����ӡ���ť����¼�����
			addBook(Integer.valueOf((addBookFrame.id_text.getText())), addBookFrame.name_text.getText(),
					Double.valueOf(addBookFrame.price_text.getText()), addBookFrame.author_text.getText(),
					addBookFrame.press_text.getText());
		});
		
		changeFrame.addBookButton.addActionListener(e ->{//Ϊ�޸��鼮��Ϣ���ڵ�"ȷ���޸�"��ť����¼�����
			updateBook(Integer.valueOf((changeFrame.id_text.getText())), changeFrame.name_text.getText(),
					Double.valueOf(changeFrame.price_text.getText()), changeFrame.author_text.getText(),
					changeFrame.press_text.getText());
		});
		changeFrame.select_img.addActionListener(e ->{// Ϊ�޸��鼮���ڵġ�ѡ��ͼƬ����ť����¼�����
			select_img();
			ImageIcon icon = new ImageIcon(filepath);
			changeFrame.img_label.setIcon(icon);
		});
		checkFrame.check_button.addActionListener(e ->{//Ϊ��ѯ���ڵ�"��ѯ��ť��Ӽ������¼�����"
			try {
				checkBook();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
/**
 * ����jtable����������ʾ��Ӧ�鱾ͼƬ
 */
	public void mainFrameShowImage(int id){
		try {
			sqlServer.query_img.setInt(1, id);
			ResultSet rs = sqlServer.query_img.executeQuery();
			if (rs.next()) {
				Blob imgBlob = (Blob) rs.getBlob(1);
				ImageIcon icon = new ImageIcon(imgBlob.getBytes(1L,(int)imgBlob.length()));
				myBookSysManager.img_lable.setIcon(icon);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * ����table��
	 * 
	 * @throws SQLException
	 */
	public void fillTable() throws SQLException {
		try (
				// ִ�в�ѯ
				ResultSet rs = sqlServer.queryAll.executeQuery();

		) {
			// ���������Ԫ��
			myBookSysManager.defaultTableModel.setRowCount(0);
			// �Ѳ�ѯ��ȫ����¼��ӵ�table��

			while (rs.next()) {
				Vector<String> data = new Vector<String>();
				data.addElement(String.valueOf(rs.getInt(1)));
				data.addElement(rs.getString(2));
				data.addElement(String.valueOf(rs.getDouble(3)));
				data.addElement(String.valueOf(rs.getString(4)));
				data.addElement(String.valueOf(rs.getString(5)));
				myBookSysManager.rows.addElement(data);
			}

			myBookSysManager.defaultTableModel.setDataVector(myBookSysManager.rows, myBookSysManager.columnHeads);

		}
	}
	/**
	 * ��ȡjtable�б�ѡ�����,���ҷ��ذ���ѡ�е������е�����������
	 */
	public int[] getSelectedRow(){
		int selectedRows []=myBookSysManager.table.getSelectedRows();
		if (selectedRows.length==0) {
			JOptionPane.showMessageDialog(myBookSysManager.mainFrame,"�����ڱ����ѡ���鼮");
		}
		 return selectedRows;
	}
	
	
	/**
	 * ������ѡ��ͼƬ�¼�������
	 */
	public void select_img() {
		int result = sqlServer.chooser.showDialog(addBookFrame.addFrame, "���ͼƬ�ϴ�");
		if (result == JFileChooser.APPROVE_OPTION) {
			filepath = sqlServer.chooser.getSelectedFile().getPath();

			filepath = filepath.replace('\\', '/');// ·���ṹ�ı�
			isChangePicture = true;//�û��ı���ͼƬ
		}
	}
	/**
	 * �����鼮��Ϣ���ϴ�
	 * @param id
	 * @param name
	 * @param price
	 * @param author
	 * @param press
	 */
	public void updateBook(int id, String name, Double price, String author, String press) {
		/**
		 * �ϴ����ݵ����ݿ�
		 */
	try {
		sqlServer.update.setInt(1, id);
		sqlServer.update.setString(2, name);
		sqlServer.update.setDouble(3, price);
		sqlServer.update.setString(4, author);
		sqlServer.update.setString(5, press);
		sqlServer.update.setInt(6, id);
		sqlServer.update_img.setInt(2, id);
		if (isChangePicture) {
			File f = new File(filepath);
			InputStream is = new FileInputStream(f);
			sqlServer.update_img.setBinaryStream(1, is, (int) f.length());
		}
		else{
			sqlServer.update_img.setBlob(1, blob);
		}

		sqlServer.update.execute();
		sqlServer.update_img.execute();
		

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/**
	 * ˢ�����б�
	 */
	try {
		fillTable();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/**
	 * �������޸ĳɹ�������
	 */
	if (true) {
		JOptionPane.showMessageDialog(addBookFrame.addFrame, "�޸ĳɹ�");
	}
	/**
	 * ����ı���ͼƬ���ݡ�����ͼƬ��·��
	 */
	addBookFrame.id_text.setText("");
	addBookFrame.name_text.setText("");
	addBookFrame.price_text.setText("");
	addBookFrame.author_text.setText("");
	addBookFrame.press_text.setText("");
	filepath="";
	addBookFrame.img_label.setIcon(new ImageIcon("book.jpg"));
}
	/**
	 * �ϴ�ͼƬ����
	 * 
	 * @param id
	 * @param name
	 * @param price
	 * @param author
	 * @param press
	 */
	public void addBook(int id, String name, Double price, String author, String press) {
			/**
			 * �ϴ����ݵ����ݿ�
			 */
		try {
			sqlServer.insert.setInt(1, id);
			sqlServer.insert.setString(2, name);
			sqlServer.insert.setDouble(3, price);
			sqlServer.insert.setString(4, author);
			sqlServer.insert.setString(5, press);

			
		
			File f = new File(filepath);
			InputStream is = new FileInputStream(f);
			sqlServer.insert_img.setInt(1, id);
			sqlServer.insert_img.setBinaryStream(2, is, (int) f.length());

			sqlServer.insert.execute();
			sqlServer.insert_img.execute();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * ˢ�����б�
		 */
		try {
			fillTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * ��������ӳɹ�������
		 */
		if (true) {
			JOptionPane.showMessageDialog(addBookFrame.addFrame, "�ϴ��ɹ�");
		}
		/**
		 * ����ı���ͼƬ���ݡ�����ͼƬ��·��
		 */
		addBookFrame.id_text.setText("");
		addBookFrame.name_text.setText("");
		addBookFrame.price_text.setText("");
		addBookFrame.author_text.setText("");
		addBookFrame.press_text.setText("");
		filepath="";
		addBookFrame.img_label.setIcon(new ImageIcon("book.jpg"));
	}
	 public  boolean changeBook(){
		 int selectedRow[] = getSelectedRow();
		 if (selectedRow.length==0) {
			return false;
		}
		 if (selectedRow.length>1) {
			JOptionPane.showMessageDialog(myBookSysManager.mainFrame, "�벻Ҫͬʱѡ��౾�鼮����");
			return false;
		}
		 changeFrame.id_text.setEditable(false);//�������޸�id
		 int id = Integer.parseInt((String) myBookSysManager.table.getValueAt(selectedRow[0], 0));
		 try {
			sqlServer.query_id.setInt(1,id);
			sqlServer.query_img.setInt(1, id);
			ResultSet rs_img = sqlServer.query_img.executeQuery();
 			ResultSet rs =  sqlServer.query_id.executeQuery();
 			if (rs.next()&&rs_img.next()) {
 				changeFrame.id_text.setText((String.valueOf(rs.getInt(1))));
 				changeFrame.name_text.setText(rs.getString(2));
 				changeFrame.price_text.setText(String.valueOf(rs.getString(3)));
 				changeFrame.author_text.setText(rs.getString(4));
 				changeFrame.press_text.setText(rs.getString(5));
 				blob = (Blob) rs_img.getBlob(1);
 				changeFrame.img_label.setIcon(new ImageIcon(blob.getBytes(1L, (int)blob.length())));
			}
 			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return true;
	 }
	 /**
	  * ɾ���鼮����
	  */
	  public void deleteBook(){
		  int selectedRow [] = getSelectedRow();
		  if (selectedRow.length==0) {
			return;
		}
		for(int i =0 ;i<selectedRow.length;i++){
			int id = Integer.parseInt((String) myBookSysManager.table.getValueAt(selectedRow[i], 0));
			try {
				sqlServer.delete.setInt(1, id);
				sqlServer.delete.setInt(1, id);
				sqlServer.delete_img.setInt(1, id);
				sqlServer.delete.execute();
				sqlServer.delete_img.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fillTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  /**
	   * ��ѯ�鼮������jtable�н���ѯ�����鼮�и�����ʾ
	   * @throws SQLException
	   */
	 public void checkBook() throws SQLException{
		String check_way = checkFrame.cbg.getSelectedCheckbox().getLabel();//�ӵ�ѡ��ť�л�ò�ѯ��ʽ
		int table_column ;
		int id =-1;
		if (check_way.equals("����")) {
			table_column = 1;//�ĳɲ�ѯ"����"��
		}else if (check_way.equals("����")) {
			table_column = 3;
		}else {
			table_column = 4;
		}
		for(int i =0;i<myBookSysManager.table.getRowCount();i++){//����jtable�����ҷ��ϵ��鼮
			 if ((checkFrame.check_jtf.getText()).trim().equals(myBookSysManager.table.getValueAt(i, table_column).toString().trim())) {
				myBookSysManager.table.setRowSelectionInterval(i, i);
				id = Integer.parseInt((String) myBookSysManager.table.getValueAt(i, 0));
				if (id!=-1) {
					mainFrameShowImage(id);
					return;
				}
			}
		 }
		JOptionPane.showMessageDialog(myBookSysManager.mainFrame, "û����Ҫ��ѯ���鼮");
}
}
/**
 * ���ݿ⴦����
 * 
 * @author mmmm
 *
 */
class sqlServer {
	private static Connection conn;
	static PreparedStatement insert;
	static PreparedStatement insert_img;
	static PreparedStatement query_img;
	static PreparedStatement query_id;
	static PreparedStatement queryAll;
	static PreparedStatement update;
	static PreparedStatement update_img;
	static PreparedStatement delete;
	static PreparedStatement delete_img;
	JFileChooser chooser = new JFileChooser(".");
	ExtensionFileFilter filter = new ExtensionFileFilter();

	static {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/db1";
		String user = "root";
		String pass = "root";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			insert = conn.prepareStatement("insert into book_table values (?,?,?,?,?)");
			insert_img = conn.prepareStatement("insert into img_table values (?,?)");
			delete = conn.prepareStatement("delete from book_table where book_id = ?");
			delete_img = conn.prepareStatement("delete from img_table where img_id = ?");
			queryAll = conn.prepareStatement("select* from book_table ");
			query_id = conn.prepareStatement("select * from book_table where book_id =?");
			query_img = conn.prepareStatement("select img from img_table where img_id = ?");
			update = conn.prepareStatement("update book_table set book_id =?,"
					+ "book_name = ?,"
					+"book_price = ?,"
					+ "book_author = ?,"
					+ "book_press=? "
					+ "where book_id =?");
			update_img = conn.prepareStatement("update img_table set img =? where img_id = ?");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public sqlServer() {
		init();
	}

	public void init() {
		chooser.addChoosableFileFilter(filter);//Ϊ�ļ�ѡ������ӹ�����
		chooser.setAcceptAllFileFilterUsed(false);

	}

	// ����FileFilter�����࣬����ʵ���ļ����˹���
	class ExtensionFileFilter extends FileFilter {
		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			// ������ļ���·�������ܸ��ļ�
			if (f.isDirectory()) {
				return true;
			}
			String name = f.getName().toLowerCase();
			if (name.endsWith(".jpg")) {
				return true;
			}
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "ͼƬ�ļ�(*.jpg)";
		}

	}

}