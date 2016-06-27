package com.issac;

import java.util.Random;

public class Sort {

	/**
	 * 创建随机数组
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
	 * 打印数组
	 * 
	 * @param Arr
	 */
	public void PrintArr(int[] Arr) {
		for (int tmp : Arr) {
			System.out.print(tmp + " ");
		}
		System.out.println();
	}

	public void quickSort(int arr[], int start, int end, Boolean DESC) {// 快速排序
		int i, j, pivot;
		if (start > end) {
			return;
		}
		i = start;
		j = end;
		pivot = arr[start];
		if (DESC) {// 按降序排序
			while (i != j) { // 找到主元的最终位置
				while (i < j && arr[j] <= pivot) {// 向左边扫描找到小于主元的数
					j--;
				}
				if (i < j) {
					arr[i++] = arr[j];
				}
				while (i < j && arr[i] >= pivot) {// 向右边扫描找到大于主元的数
					i++;
				}
				if (i < j) {
					arr[j--] = arr[i];
				}
				arr[i] = pivot;// 主元的最终位置
			}
			quickSort(arr, start, i - 1, DESC);// 递归处理左区间
			quickSort(arr, i + 1, end, DESC);// 递归处理右区间
		} else {// 按升序排序
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

	public void SelectSort(int[] arr, boolean DESC) {// 选择排序
		int index = 0, tmp;
		for (int i = 0; i < arr.length - 1; i++) {
			int data = arr[i];
			index = i;
			for (int j = i; j < arr.length - 1; j++) {// 寻找最小数
				if (DESC) {// 如果按降序排序

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
			tmp = arr[i];// 与最小数交换位置
			arr[i] = arr[index];
			arr[index] = tmp;
		}
	}

	public void InsertSort(int[] arr, boolean DESC) {// 插入排序

		for (int i = 1; i < arr.length; i++) {

			int tmp = arr[i];// 取出要比较的数，

			if (DESC) {

				for (int j = i; j > 0 && arr[j - 1] < tmp; j--) {

					arr[j] = arr[j - 1];// 移出空位
					arr[j - 1] = tmp;// 新取出数的位置
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
		System.out.print("随机生成的待排序数组:");
		sort.PrintArr(RandomArr);
		System.out.println("快速排序");
		sort.quickSort(RandomArr, 0, RandomArr.length - 1, false);
		System.out.print("升序：");
		sort.PrintArr(RandomArr);
		sort.quickSort(RandomArr, 0, RandomArr.length - 1, true);
		System.out.print("降序：");
		sort.PrintArr(RandomArr);
		System.out.println("插入排序");
		System.out.print("升序：");
		sort.InsertSort(RandomArr, false);
		sort.PrintArr(RandomArr);
		System.out.print("降序：");
		sort.InsertSort(RandomArr, true);
		sort.PrintArr(RandomArr);
		System.out.println("选择排序");
		sort.SelectSort(RandomArr, false);
		System.out.print("升序：");
		sort.PrintArr(RandomArr);
		sort.SelectSort(RandomArr, true);
		System.out.print("降序：");
		sort.PrintArr(RandomArr);
	}
}
