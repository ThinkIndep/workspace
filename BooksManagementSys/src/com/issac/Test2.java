package com.issac;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Test2 {
	public void clear() throws AWTException {
		Robot r = new Robot();
		r.mousePress(InputEvent.BUTTON3_MASK); // ��������Ҽ�
		r.mouseRelease(InputEvent.BUTTON3_MASK); // �ͷ�����Ҽ�
		r.keyPress(KeyEvent.VK_CONTROL); // ����Ctrl��
		r.keyPress(KeyEvent.VK_R); // ����R��
		r.keyRelease(KeyEvent.VK_R); // �ͷ�R��
		r.keyRelease(KeyEvent.VK_CONTROL); // �ͷ�Ctrl��
		r.delay(100);

	}

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100000; i++) {
			System.out.println(i);
		}
		new Test2().clear();
	}

}
