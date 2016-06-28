package com.issac;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{
	JLabel name_label, pass_label, image_label;
	JTextField name_text;
	JPasswordField pass_text;
	JButton login_button, clear_button;
	JPanel jPanel, jPanel2, jPanel3, imagePanel;
	ImageIcon background;

	public LoginFrame() {
		background = new ImageIcon("library.jpg");
		image_label = new JLabel(background);
		image_label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		name_label = new JLabel("ÓÃ»§Ãû");
		pass_label = new JLabel("ÃÜÂë   ");
		name_text = new JTextField(8);
		pass_text = new JPasswordField(8);
		login_button = new JButton("µÇÂ¼");
		clear_button = new JButton("Çå¿Õ");
		jPanel = new JPanel();
		jPanel2 = new JPanel();
		jPanel3 = new JPanel();
		jPanel2.setLayout(new GridLayout(2, 2));
		jPanel3.setLayout(new FlowLayout());
		jPanel2.add(name_label);
		jPanel2.add(name_text);
		jPanel2.add(pass_label);
		jPanel2.add(pass_text);
		jPanel3.add(login_button);
		jPanel3.add(clear_button);
		jPanel.add(jPanel2);
		jPanel.add(jPanel3, new BorderLayout().SOUTH);
//		this.setLayout(null);
		jPanel.setBounds(200, 300, 250, 200);
		imagePanel.add(image_label);
		imagePanel.add(jPanel);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(image_label,new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(background.getIconWidth(), background.getIconHeight());
		this.setTitle("µÇÂ¼");
		this.setVisible(true);

	}
}
