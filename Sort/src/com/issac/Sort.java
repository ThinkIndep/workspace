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

	public int[] swap(int arr[], int a, int b) {// ��������������ֵ
		int tmp;
		tmp = arr[b];
		arr[b] = arr[a];
		arr[a] = tmp;
		return arr;
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

	public static void main(String[] args) {
		Sort sort = new Sort();
		int RandomArr[] = sort.createRandomArr();
		System.out.print("������ɵ�����:");
		sort.PrintArr(RandomArr);
		System.out.println("��������");
		sort.quickSort(RandomArr, 0, RandomArr.length - 1, false);
		System.out.print("����");
		sort.PrintArr(RandomArr);
		sort.quickSort(RandomArr, 0, RandomArr.length - 1, true);
		System.out.print("����");
		sort.PrintArr(RandomArr);
	}
}
