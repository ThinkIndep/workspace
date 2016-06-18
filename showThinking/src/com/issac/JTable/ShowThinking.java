package com.issac.JTable;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class ShowThinking  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea jTextArea = new JTextArea();
	JFrame showFrame = new JFrame();
	String MotosArra[];
	public void  init(){
		showFrame.setLayout(new GridBagLayout());
		showFrame.setUndecorated(true);
		showFrame.setBackground(new Color(0, 0, 0, 0));
//		setOpacity(0.7f);
		showFrame.addComponentListener(new ComponentAdapter(){
		public void componentResized(ComponentEvent e ){
			showFrame.setShape(new RoundRectangle2D.Double(0d, 0d, showFrame.getWidth(), showFrame.getHeight(), 20, 20));
		}
		});
//		showFrame.setSize(300,200);
		showFrame.setLocation(350, 880);
		JPanel panel = new JPanel()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent (Graphics g){
				if (g instanceof Graphics2D) {
					final int R = 240;
					final int G = 240;
					final int B = 240;
					
					Paint p = new GradientPaint(0.0f, 0.0f, new Color(R,G,B,0),0.0f,getHeight(),new Color(R, G, B,255),true);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setPaint(p);
					g2d.fillRect(0, 0, getWidth(), getHeight());
				}
			}
		};
		jTextArea.setFont(new Font("隶书", Font.BOLD, 40));
		jTextArea.setEditable(false);
		jTextArea.setOpaque(false);
		jTextArea.setText("Stay hungry. Stay foolish.\n——史蒂夫.乔布斯引自《全球概览》");
		show();
		panel.add(jTextArea);
		showFrame.add(panel);
		showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showFrame.pack();
		showFrame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		JFrame.setDefaultLookAndFeelDecorated(true);
		new ShowThinking().init();;
	}
	public  void show(){
		// TODO Auto-generated method stub
				try (
					FileReader fr = new FileReader("H:/e_book/格言.txt"); 
					BufferedReader br = new BufferedReader(fr);) 
				{
					String motto = "";
					String tmp = "";
					while ((tmp = br.readLine()) != null) {
						motto += tmp;
					}
					MotosArra = motto.split("@");
//					System.out.println(random);
//					System.out.println(MotosArra[1].replace('%', '\n'));
					Timer timer = new Timer(1000*60*60, new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							int random = (int) (Math.random() * MotosArra.length);//随机选取格言
							jTextArea.setText(MotosArra[random].replace('%', '\n'));
							showFrame.pack();
						}
					});
					timer.start();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}