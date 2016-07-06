package com.issac;

public class PostmanQuestion {
	static int MAX = 65535;
	int i, j;
	char[] c = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L' };
	static int[] odd = new int[12];
	// public static void PostmanQuestion(int [][]graph,int n){
	// }

	public void count(int[][] graph) {
		int sum;
		boolean isOdd;
		System.out.println("�������Ķ�����");
		for (i = 0; i < graph.length; i++) {
			sum = 0;
			for (j = 0; j < graph.length; j++) {
				if (graph[i][j] > 0 && graph[i][j] < MAX) {
					sum++;
				}
			}
			if (sum % 2 != 0) {
				odd[i] = 1;
			}
			System.out.println("����" + c[i] + "�Ķ���:" + sum);
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("���������ȵ㣺");
		for (i = 0; i < graph.length; i++) {
			if (odd[i] == 1) {
				System.out.println("����" + c[i] + "����ȵ�");
			}
		}
	}

	public void oddMin(int [][] graph){
		int min ;
		for(i=0;i<graph.length;i++){
			min = MAX;
			if (odd[i] == 1) {
				for (int j = 0; j < graph.length; j++) {
					if (odd[j] == 1 &&j!=i) {
						if (min>graph[i][j]) {
							min=graph[i][j];
						}
					}
				}
			}else {
				continue;
			}
			System.out.println("��ȵ�"+c[i]+"��������ȵ�����·��Ϊ��"+min);
		}
	}

	public static void main(String[] args) {
		int[][] map = new int[][] { { 0, 3, MAX, MAX, MAX, MAX, 1, MAX, MAX, MAX, MAX, 2 },
				{ 3, 0, 1, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX },
				{ MAX, 1, 0, 4, MAX, 3, MAX, MAX, MAX, MAX, MAX, MAX },
				{ MAX, MAX, 4, 0, 3, MAX, MAX, MAX, MAX, MAX, MAX, MAX },
				{ MAX, MAX, MAX, 3, 0, 2, 4, MAX, MAX, MAX, MAX, MAX },
				{ MAX, MAX, MAX, MAX, 2, 0, MAX, 2, 3, MAX, MAX, MAX },
				{ 1, MAX, 3, MAX, 4, MAX, 0, MAX, MAX, MAX, MAX, MAX },
				{ MAX, MAX, MAX, MAX, MAX, 2, MAX, 0, MAX, 3, MAX, 3 },
				{ MAX, MAX, MAX, MAX, MAX, 3, MAX, MAX, 0, 2, MAX, MAX },
				{ MAX, MAX, MAX, MAX, MAX, MAX, MAX, 3, 2, 0, 3, MAX },
				{ MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, 3, 0, 3 },
				{ 2, MAX, MAX, MAX, MAX, MAX, MAX, 3, MAX, MAX, 3, 0 }, };
		PostmanQuestion pQuestion = new PostmanQuestion();
		pQuestion.count(map);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("���ÿһ����ȵ㵽������ȵ�����·����");
		pQuestion.oddMin(map);

	}

}
