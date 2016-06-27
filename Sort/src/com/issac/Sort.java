package com.issac;

import java.util.Random;

public class Sort {

	/**
	 * �����������
	 * 
	 * @return
	 */
	public int[] createRandomArr() {
		Random random = new Random();
		int datas[] = new int[10];
		for (int i = 0; i < 10; i++) {
			datas[i] = random.nextInt(100);
		}
		return datas;
	}

	/**
	 * ��ӡ����
	 * 
	 * @param Arr
	 */
	public void PrintArr(int[] Arr) {
		for (int tmp : Arr) {
			System.out.print(tmp + " ");
		}
		System.out.println();
	}

	public void quickSort(int arr[], int start, int end, Boolean DESC) {// ��������
		int i, j, pivot;
		if (start > end) {
			return;
		}
		i = start;
		j = end;
		pivot = arr[start];
		if (DESC) {// ����������
			while (i != j) { // �ҵ���Ԫ������λ��
				while (i < j && arr[j] <= pivot) {// �����ɨ���ҵ�С����Ԫ����
					j--;
				}
				if (i < j) {
					arr[i++] = arr[j];
				}
				while (i < j && arr[i] >= pivot) {// ���ұ�ɨ���ҵ�������Ԫ����
					i++;
				}
				if (i < j) {
					arr[j--] = arr[i];
				}
				arr[i] = pivot;// ��Ԫ������λ��
			}
			quickSort(arr, start, i - 1, DESC);// �ݹ鴦��������
			quickSort(arr, i + 1, end, DESC);// �ݹ鴦��������
		} else {// ����������
			while (i != j) {
				while (i < j && arr[j] >= pivot) {
					j--;
				}
				if (i < j) {
					arr[i++] = arr[j];
				}
				while (i < j && arr[i] <= pivot) {
					i++;
				}
				if (i < j) {
					arr[j--] = arr[i];
				}
				arr[i] = pivot;
			}
			quickSort(arr, start, i - 1, DESC);
			quickSort(arr, i + 1, end, DESC);
		}

	}

	public void SelectSort(int[] arr, boolean DESC) {// ѡ������
		int index = 0, tmp;
		for (int i = 0; i < arr.length - 1; i++) {
			int data = arr[i];
			index = i;
			for (int j = i; j < arr.length - 1; j++) {// Ѱ����С��
				if (DESC) {// �������������

					if (data < arr[j + 1]) {
						data = arr[j + 1];
						index = j + 1;
					}
				} else {
					if (data > arr[j + 1]) {
						data = arr[j + 1];
						index = j + 1;
					}
				}
			}
			tmp = arr[i];// ����С������λ��
			arr[i] = arr[index];
			arr[index] = tmp;
		}
	}

	public void InsertSort(int[] arr, boolean DESC) {// ��������

		for (int i = 1; i < arr.length; i++) {

			int tmp = arr[i];// ȡ��Ҫ�Ƚϵ�����

			if (DESC) {

				for (int j = i; j > 0 && arr[j - 1] < tmp; j--) {

					arr[j] = arr[j - 1];// �Ƴ���λ
					arr[j - 1] = tmp;// ��ȡ������λ��
				}
			} else {

				for (int j = i; j > 0 && arr[j - 1] > tmp; j--) {

					arr[j] = arr[j - 1];
					arr[j - 1] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) {
		Sort sort = new Sort();
		int RandomArr[] = sort.createRandomArr();
		System.out.print("������ɵĴ���������:");
		sort.PrintArr(RandomArr);
		System.out.println("��������");
		sort.quickSort(RandomArr, 0, RandomArr.length - 1, false);
		System.out.print("����");
		sort.PrintArr(RandomArr);
		sort.quickSort(RandomArr, 0, RandomArr.length - 1, true);
		System.out.print("����");
		sort.PrintArr(RandomArr);
		System.out.println("��������");
		System.out.print("����");
		sort.InsertSort(RandomArr, false);
		sort.PrintArr(RandomArr);
		System.out.print("����");
		sort.InsertSort(RandomArr, true);
		sort.PrintArr(RandomArr);
		System.out.println("ѡ������");
		sort.SelectSort(RandomArr, false);
		System.out.print("����");
		sort.PrintArr(RandomArr);
		sort.SelectSort(RandomArr, true);
		System.out.print("����");
		sort.PrintArr(RandomArr);
	}
}
