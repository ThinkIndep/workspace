package com.issac;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Maze {

	public int[][] CreateRadomArray() {		//��������Թ����鷽��

		int maze[][] = new int[10][10];

		Random random = new Random();		

		for (int i = 0; i < 10; i++) {	//Ϊ�Թ����һȦΧǽ

			maze[0][i] = 1;

			maze[9][i] = 1;

			maze[i][0] = 1;

			maze[i][9] = 1;
		}

		for (int i = 1; i < 9; i++) {	//�Թ�ǽ����������ϰ���ͨ��

			for (int j = 1; j < 9; j++) {

				int t;

				t = random.nextInt(7);

				if (t < 3) { //�����Թ����ϰ���ͨ���ı������ϰ�:ͨ��=2:5    
					t = 1;
				} else {
					t = 0;
				}

				maze[i][j] = t;

			}
		}
		return maze;

	}

	
	/**
	 * �Թ�Ѱ��·������
	 * @param maze:�Թ�����
	 * @param x1:���x����
	 * @param y1:���y����
	 * @param x2:����x����
	 * @param y2:����y����
	 */
	public void mazePath(int maze[][], int x1, int y1, int x2, int y2) {
																		
		int i, j, k;

		int g, h;
		
		int direction [][] = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};

		Stack<Position> stack = new Stack<>();//ʵ������ջ��

		Position position = new Position();	//ʵ����λ����

		maze[x1][y1] = 2;	//�߹���·����Ϊ2��
		maze[x2][y2] = 0;	//����λ�ø�Ϊ0��

		position.x = x1;	//Ϊ���λ�ø�ֵ
		position.y = y1;
		position.d = -1;

		stack.push(position);		//�����λ����ջ

		while (!stack.isEmpty()) {	//�߲�ͨʶ��һ��������

			position = stack.pop();

			i = position.x;

			j = position.y;

			k = position.d + 1;

			while (k < 8) {		//���������̽

				g = i + direction[k][0];

				h = j + direction[k][1];

				if (g == x2 && h == y2) {	//�ߵ����ڵ�
					
					maze[g][h] = 2;
					
					printMaze(maze);
					
					System.out.println("find finish!!");
					
					return;
				}

				if (maze[g][h] == 0) {	//�ߵ�û�߹��ĵ�

					maze[g][h] = 2;		//�����
					
					printMaze(maze);
					
					waitNextStep();		//�����̽�����һ��

					position.x = i;

					position.y = j;

					position.d = k;

					stack.push(position);	//��ջ

					i = g;					//��һ��ת���ɵ�ǰ��

					j = h;

					k = -1;
				}

				k = k + 1;
			}

		}

		printMaze(maze);
		System.out.println("The path has not been found!!!");//ջ���꣬δ�ҵ�·��
	}
	
	public void waitNextStep(){//�ȴ����������ٽ�����һ��
		System.out.println("print any key to continue........");
		
		new Scanner(System.in).nextLine();
	}

	public void printMaze(int maze[][]) {//��ӡ�Թ�����
		
		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {
				
				System.out.print(maze[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maze maze = new Maze();
		
		int mazeArr[][] = maze.CreateRadomArray();
		
		maze.mazePath(mazeArr,  1, 1, 8, 8);
	}

	class Position {//�Թ��߹�λ����
		int x, y, d;//x,y,d �ֱ��ʾ�Թ�̽��λ�õ��С��С�̽������
	}

}
