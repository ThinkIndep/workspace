package com.issac;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReaderMana extends JFrame {
	String[] columnNames = { "Id", "姓名", "性别", "电话" };
	String[][] tableValues = { { "A", "B", "C", "D" }, { "A", "B", "C", "D" }, { "A", "B", "C", "D" },
			{ "A", "B", "C", "D" }, { "A", "B", "C", "D" } };
	JTable table = new JTable(tableValues, columnNames);
	JScrollPane scrollPane = new JScrollPane();
	JPanel bottomPanel = new JPanel();
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("菜单");
	JMenu exit_menu = new JMenu("退出");
	JMenuItem bookManager = new JMenuItem("图书管理系统");
	JMenuItem borrowMana = new JMenuItem("借阅管理系统");

	public ReaderMana() {
		menu.add(bookManager);
		menu.add(borrowMana);
		menuBar.add(menu);
		menuBar.add(exit_menu);

		scrollPane.setViewportView(table);
		bottomPanel.add(new JButton("增加"));
		bottomPanel.add(new JButton("删除"));
		bottomPanel.add(new JButton("修改"));
		bottomPanel.add(new JButton("查找"));

		this.setJMenuBar(menuBar);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(scrollPane);
		this.setTitle("读者管理系统");
		// this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

}
