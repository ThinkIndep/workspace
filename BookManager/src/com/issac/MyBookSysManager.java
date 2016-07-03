package com.issac;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyBookSysManager {
	JFrame mainFrame = new JFrame("图书管理系统");
	JButton add_button = new JButton("添加");
	JButton check_button = new JButton("查询");
	JButton change_button = new JButton("修改");
	JButton delete_button = new JButton("删除");
	JLabel img_lable = new JLabel();
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("菜单");
	JMenuItem rederManager = new JMenuItem("读者管理系统");
	JMenuItem borrowManager = new JMenuItem("借阅管理系统");
	ImageIcon icon = new ImageIcon("book.jpg");
	JPanel top_jp, bottom_jp;
	DefaultTableModel defaultTableModel;
	JTable table;
	Vector<String> columnHeads = new Vector<String>();
	Vector<Vector<String>> rows = new Vector<Vector<String>>();

	public MyBookSysManager() {
		initParam();
	}

	public void initParam() {
		img_lable.setIcon(icon);// 设置默认图片，将容器撑大

		columnHeads.addElement("id");
		columnHeads.addElement("书名");
		columnHeads.addElement("价格");
		columnHeads.addElement("作者");
		columnHeads.addElement("出版社");

		defaultTableModel = new DefaultTableModel(rows, columnHeads);
		table = new JTable(defaultTableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		top_jp = new JPanel();
		top_jp.setLayout(new BorderLayout());
		top_jp.add(img_lable, BorderLayout.WEST);
		top_jp.add(new JScrollPane(table));

		bottom_jp = new JPanel();
		bottom_jp.add(add_button);
		bottom_jp.add(delete_button);
		bottom_jp.add(change_button);
		bottom_jp.add(check_button);

		menu.add(rederManager);
		menu.add(borrowManager);
		menuBar.add(menu);

		mainFrame.setJMenuBar(menuBar);
		mainFrame.add(top_jp);
		mainFrame.add(bottom_jp, BorderLayout.SOUTH);
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mainFrame.setVisible(true);

	}

}
