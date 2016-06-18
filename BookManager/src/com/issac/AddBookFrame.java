package com.issac;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AddBookFrame {

	JFrame addFrame = new JFrame("添加书籍");
	JPanel top_jp = new JPanel();
	JPanel bottom_jp = new JPanel();
	JPanel right_jp = new JPanel();
	JPanel left_jp = new JPanel();
	ImageIcon icon = new ImageIcon("book.jpg");
	JLabel img_label = new JLabel();
	JTextField id_text = new JTextField(15);
	JTextField name_text = new JTextField(15);
	JTextField price_text = new JTextField(15);
	JTextField author_text = new JTextField(15);
	JTextField press_text = new JTextField(15);
	JButton select_img = new JButton("选择图片");
	JButton addBookButton = new JButton("确认添加书籍");
	

	public AddBookFrame() {
		init();
	}

	public void init() {
		left_jp.add(new JLabel("ID"));
		left_jp.add(id_text);
		left_jp.add(new JLabel("书名"));
		left_jp.add(name_text);
		left_jp.add(new JLabel("价格"));
		left_jp.add(price_text);
		left_jp.add(new JLabel("作者"));
		left_jp.add(author_text);
		left_jp.add(new JLabel("出版社"));
		left_jp.add(press_text);

		img_label.setIcon(icon);
		right_jp.setLayout(new BorderLayout());
		right_jp.add(new JScrollPane(img_label));
		right_jp.add(select_img, BorderLayout.SOUTH);
		

		top_jp.setLayout(new BorderLayout());
		top_jp.add(left_jp);
		top_jp.add(right_jp, BorderLayout.EAST);

		bottom_jp.add(addBookButton);
	

		addFrame.add(top_jp);
		addFrame.add(bottom_jp, BorderLayout.SOUTH);

		addFrame.pack();
		addFrame.setSize(400, 330);

	}

}
