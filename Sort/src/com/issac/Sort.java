package com.issac;

import java.util.Random;

public class Sort {

	public int[] createRandomArr() {
		Random random = new Random();
		int datas[] = new int[10];
		for (int i = 0; i < 10; i++) {
			datas[i] = random.nextInt(100);
		}
		return datas;
	}

	public void PrintArr(int[] Arr) {
		for (int tmp : Arr) {
			System.out.print(tmp + " ");
		}
	}

	public void quickSort() {

	}

	public static void main(String[] args) {
		Sort sort = new Sort();
		sort.PrintArr(sort.createRandomArr());
	}
}
