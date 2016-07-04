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

public class BorrowMana extends JFrame {
	String[] columnNames = { "Id", "����", "����", "��������" };
	String[][] tableValues = { { "A", "B", "C", "D" }, { "A", "B", "C", "D" }, { "A", "B", "C", "D" },
			{ "A", "B", "C", "D" }, { "A", "B", "C", "D" } };
	JTable table = new JTable(tableValues, columnNames);
	JScrollPane scrollPane = new JScrollPane();
	JPanel bottomPanel = new JPanel();
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("�˵�");
	JMenu exit_menu = new JMenu("�˳�");
	JMenuItem bookManager = new JMenuItem("ͼ�����ϵͳ");
	JMenuItem readerMana = new JMenuItem("���߹���ϵͳ");

	public BorrowMana() {
		menu.add(bookManager);
		menu.add(readerMana);
		menuBar.add(menu);
		menuBar.add(exit_menu);

		scrollPane.setViewportView(table);
		bottomPanel.add(new JButton("����"));
		bottomPanel.add(new JButton("ɾ��"));
		bottomPanel.add(new JButton("�޸�"));
		bottomPanel.add(new JButton("����"));

		this.setJMenuBar(menuBar);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(scrollPane);
		this.setTitle("���Ĺ���ϵͳ");
		// this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

}
