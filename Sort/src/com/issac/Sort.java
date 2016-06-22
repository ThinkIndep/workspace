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

	public int[] swap(int arr[], int a, int b) {// 交换两个变量的值
		int tmp;
		tmp = arr[b];
		arr[b] = arr[a];
		arr[a] = tmp;
		return arr;
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

	public static void main(String[] args) {
		Sort sort = new Sort();
		int RandomArr[] = sort.createRandomArr();
		System.out.print("随机生成的数组:");
		sort.PrintArr(RandomArr);
		System.out.println("快速排序");
		sort.quickSort(RandomArr, 0, RandomArr.length - 1, false);
		System.out.print("升序：");
		sort.PrintArr(RandomArr);
		sort.quickSort(RandomArr, 0, RandomArr.length - 1, true);
		System.out.print("降序：");
		sort.PrintArr(RandomArr);
	}
}
