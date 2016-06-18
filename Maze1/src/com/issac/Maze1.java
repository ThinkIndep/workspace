package com.issac;

import java.util.Random;

public class Maze1 {

	int maze[][];//�Թ�
	int rowsize;  //��������
	int colsize;

	public void initMaze() { // �Թ���ʼ��
		rowsize = 8;	//�趨��������	
		colsize = 8;
		maze = new int[10][10];	//�����Թ������С
		
		Random random = new Random();	

		for (int i = 0; i < 10; i++) { // Ϊ�Թ����һȦΧǽ

			maze[0][i] = 1;

			maze[9][i] = 1;

			maze[i][0] = 1;

			maze[i][9] = 1;
		}

		for (int i = 1; i < 9; i++) { // �Թ�ǽ����������ϰ���ͨ��

			for (int j = 1; j < 9; j++) {

				int t;

				t = random.nextInt(7);

				if (t < 3) { // �����Թ����ϰ���ͨ���ı������ϰ�:ͨ��=2:5
					t = 1;
				} else {
					t = 0;
				}
				maze[i][j] = t;
			}
		}
		maze[rowsize][colsize] = 0;	//�趨����λ��Ϊ0(��ͨ��)
	}
/**
 * Ѱ���Թ�·������
 * @param i �������
 * @param j
 * @return
 */
	public boolean solve(int i, int j)
	/*
	 * Post: attempt to find a path through the maze from coordinate (i,j)
	 */
	{
		boolean finish = false;
		maze[i][j] = 2;
		if (i == rowsize && j == colsize)//��ԭ�����ĸ��������ӵ��˸�����
			return true; // because we're done
		
		if (!finish && maze[i + 1][j] == 0)
			finish = solve(i + 1, j);
		
		if (!finish && maze[i + 1][j+1] == 0)
			finish = solve(i + 1, j+1);
		
		if (!finish && maze[i][j + 1] == 0)
			finish = solve(i, j + 1);

		if (!finish && maze[i - 1][j+1] == 0)
			finish = solve(i - 1, j+1);
		
		if (!finish && maze[i - 1][j] == 0)
			finish = solve(i - 1, j);
		
		if (!finish && maze[i - 1][j-1] == 0)
			finish = solve(i - 1, j-1);
		
		if (!finish && maze[i][j - 1] == 0)
			finish = solve(i, j - 1);

		if (!finish && maze[i + 1][j-1] == 0)
			finish = solve(i + 1, j-1);
		
		if (!finish) // �޷���ͨ����ô�޸�ԭ������Ϊ0
			maze[i][j] = 0;
		return finish;
	}
	
	public void judge() {//�ж��Ƿ��ҵ�·��
		if (solve(1, 1)) {
			printMaze();
			System.out.println("the path had find");
		} else {
			printMaze();
			System.out.println("not path of the maze");
		}
	}
	
	public void printMaze() {// ��ӡ�Թ�����

		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {

				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Maze1 maze = new Maze1();
		maze.initMaze();
		maze.judge();
	}
}
