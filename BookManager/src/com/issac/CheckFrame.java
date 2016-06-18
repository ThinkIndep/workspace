package com.issac;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CheckFrame  {
	/**
	 * �����ѯ��ť����ѡ��ť���ı�����ʾ��
	 */
	JFrame checkFrame = new JFrame("��ѯ�鼮");
	JButton check_button = new JButton("��ѯ");
	JTextField check_jtf = new JTextField(15);
	CheckboxGroup cbg = new CheckboxGroup();
	Checkbox name_check = new Checkbox("����", cbg,true);
	Checkbox author_check = new Checkbox("����", cbg, false);
	Checkbox press_check = new Checkbox("������", cbg,false);

	JPanel top_jp = new JPanel();
	JPanel center_jp = new JPanel();
	
	public CheckFrame(){
		init();
	}
	public void init(){
		top_jp.add(name_check);
		top_jp.add(author_check);
		top_jp.add(press_check);
		
		center_jp.add(check_jtf);
		center_jp.add(check_button);
	
		checkFrame.add(top_jp);
		checkFrame.add(center_jp,BorderLayout.SOUTH);		
		checkFrame.pack();
	}

}