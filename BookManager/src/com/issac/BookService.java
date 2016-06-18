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
	String filepath;//上传图片到数据库可以用路径或者bolb类，因为考虑到修改书籍信息时用户可能没有改变图片
	Blob blob;
	boolean isChangePicture;//判断用户是否更改了图片
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
	 * 为所有窗口的按钮添加监听，处理事件
	 */
	public void addEvent() {
		myBookSysManager.add_button.addActionListener(e -> {//主界面点击“添加按钮”，显示添加窗口，隐藏其他窗口
			addBookFrame.addFrame.setVisible(true);
			checkFrame.checkFrame.setVisible(false);
			changeFrame.addFrame.setVisible(false);
		});
		myBookSysManager.check_button.addActionListener(e -> {//主界面点击“查询按钮”，显示添加窗口，隐藏其他窗口
			addBookFrame.addFrame.setVisible(false);
			checkFrame.checkFrame.setVisible(true);
			changeFrame.addFrame.setVisible(false);
		});
		
		myBookSysManager.change_button.addActionListener(e ->{//主界面点击“修改按钮”，显示添加窗口，隐藏其他窗口
			
			if (changeBook()) {//如果没有选中列表中的书籍，则弹出警告窗口，不显示书籍窗口
				addBookFrame.addFrame.setVisible(false);
				checkFrame.checkFrame.setVisible(false);
				changeFrame.addFrame.setVisible(true);
			}
		});
		myBookSysManager.table.addMouseListener(new MouseAdapter() {//显示jtable表中被单击行的书籍图片
			public void  mouseClicked(MouseEvent e){
				if (e.getClickCount()==1) {
					int selectedRow[]=getSelectedRow();
					 int id = Integer.parseInt((String) myBookSysManager.table.getValueAt(selectedRow[0], 0));
					mainFrameShowImage(id);
				}
			}
		}
			
		);
		myBookSysManager.delete_button.addActionListener(e ->{// 主界面"删除按钮"添加监控与事件处理
			deleteBook();
		});
		addBookFrame.select_img.addActionListener(e -> {// 为添加书籍窗口的“选择图片”按钮添加事件处理
			select_img();
			ImageIcon icon = new ImageIcon(filepath);
			addBookFrame.img_label.setIcon(icon);
		});

		addBookFrame.addBookButton.addActionListener(e -> {// 为添加书籍窗口的“确认添加”按钮添加事件处理
			addBook(Integer.valueOf((addBookFrame.id_text.getText())), addBookFrame.name_text.getText(),
					Double.valueOf(addBookFrame.price_text.getText()), addBookFrame.author_text.getText(),
					addBookFrame.press_text.getText());
		});
		
		changeFrame.addBookButton.addActionListener(e ->{//为修改书籍信息窗口的"确认修改"按钮添加事件处理
			updateBook(Integer.valueOf((changeFrame.id_text.getText())), changeFrame.name_text.getText(),
					Double.valueOf(changeFrame.price_text.getText()), changeFrame.author_text.getText(),
					changeFrame.press_text.getText());
		});
		changeFrame.select_img.addActionListener(e ->{// 为修改书籍窗口的“选择图片”按钮添加事件处理
			select_img();
			ImageIcon icon = new ImageIcon(filepath);
			changeFrame.img_label.setIcon(icon);
		});
		checkFrame.check_button.addActionListener(e ->{//为查询窗口的"查询按钮添加监听与事件处理"
			try {
				checkBook();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
/**
 * 单击jtable表，主窗口显示对应书本图片
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
	 * 更新table表
	 * 
	 * @throws SQLException
	 */
	public void fillTable() throws SQLException {
		try (
				// 执行查询
				ResultSet rs = sqlServer.queryAll.executeQuery();

		) {
			// 先清除所有元素
			myBookSysManager.defaultTableModel.setRowCount(0);
			// 把查询的全部记录添加到table中

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
	 * 获取jtable中被选择的列,并且返回包含选中的所有列的索引号数组
	 */
	public int[] getSelectedRow(){
		int selectedRows []=myBookSysManager.table.getSelectedRows();
		if (selectedRows.length==0) {
			JOptionPane.showMessageDialog(myBookSysManager.mainFrame,"请先在表格中选中书籍");
		}
		 return selectedRows;
	}
	
	
	/**
	 * 过滤器选择图片事件处理方法
	 */
	public void select_img() {
		int result = sqlServer.chooser.showDialog(addBookFrame.addFrame, "浏览图片上传");
		if (result == JFileChooser.APPROVE_OPTION) {
			filepath = sqlServer.chooser.getSelectedFile().getPath();

			filepath = filepath.replace('\\', '/');// 路径结构改变
			isChangePicture = true;//用户改变了图片
		}
	}
	/**
	 * 更新书籍信息后上传
	 * @param id
	 * @param name
	 * @param price
	 * @param author
	 * @param press
	 */
	public void updateBook(int id, String name, Double price, String author, String press) {
		/**
		 * 上传数据到数据库
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
	 * 刷新主列表
	 */
	try {
		fillTable();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/**
	 * 弹出“修改成功”窗口
	 */
	if (true) {
		JOptionPane.showMessageDialog(addBookFrame.addFrame, "修改成功");
	}
	/**
	 * 清空文本框、图片内容、加载图片的路径
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
	 * 上传图片方法
	 * 
	 * @param id
	 * @param name
	 * @param price
	 * @param author
	 * @param press
	 */
	public void addBook(int id, String name, Double price, String author, String press) {
			/**
			 * 上传数据到数据库
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
		 * 刷新主列表
		 */
		try {
			fillTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 弹出“添加成功”窗口
		 */
		if (true) {
			JOptionPane.showMessageDialog(addBookFrame.addFrame, "上传成功");
		}
		/**
		 * 清空文本框、图片内容、加载图片的路径
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
			JOptionPane.showMessageDialog(myBookSysManager.mainFrame, "请不要同时选择多本书籍！！");
			return false;
		}
		 changeFrame.id_text.setEditable(false);//不允许修改id
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
	  * 删除书籍方法
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
	   * 查询书籍，并在jtable中将查询到的书籍行高亮显示
	   * @throws SQLException
	   */
	 public void checkBook() throws SQLException{
		String check_way = checkFrame.cbg.getSelectedCheckbox().getLabel();//从单选按钮中获得查询方式
		int table_column ;
		int id =-1;
		if (check_way.equals("书名")) {
			table_column = 1;//改成查询"书名"列
		}else if (check_way.equals("作者")) {
			table_column = 3;
		}else {
			table_column = 4;
		}
		for(int i =0;i<myBookSysManager.table.getRowCount();i++){//遍历jtable表，查找符合的书籍
			 if ((checkFrame.check_jtf.getText()).trim().equals(myBookSysManager.table.getValueAt(i, table_column).toString().trim())) {
				myBookSysManager.table.setRowSelectionInterval(i, i);
				id = Integer.parseInt((String) myBookSysManager.table.getValueAt(i, 0));
				if (id!=-1) {
					mainFrameShowImage(id);
					return;
				}
			}
		 }
		JOptionPane.showMessageDialog(myBookSysManager.mainFrame, "没有你要查询的书籍");
}
}
/**
 * 数据库处理类
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
		chooser.addChoosableFileFilter(filter);//为文件选择器添加过滤器
		chooser.setAcceptAllFileFilterUsed(false);

	}

	// 创建FileFilter的子类，用以实现文件过滤功能
	class ExtensionFileFilter extends FileFilter {
		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			// 如果该文件是路径，接受该文件
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
			return "图片文件(*.jpg)";
		}

	}

}