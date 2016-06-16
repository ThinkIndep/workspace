package com.issac;

import java.util.Random;

public class Maze1 {

	int maze[][];//迷宫
	int rowsize;  //出口坐标
	int colsize;

	public void initMaze() { // 迷宫初始化
		rowsize = 8;	//设定出口坐标	
		colsize = 8;
		maze = new int[10][10];	//定义迷宫数组大小
		
		Random random = new Random();	

		for (int i = 0; i < 10; i++) { // 为迷宫外加一圈围墙

			maze[0][i] = 1;

			maze[9][i] = 1;

			maze[i][0] = 1;

			maze[i][9] = 1;
		}

		for (int i = 1; i < 9; i++) { // 迷宫墙内随机生成障碍和通道

			for (int j = 1; j < 9; j++) {

				int t;

				t = random.nextInt(7);

				if (t < 3) { // 分配迷宫内障碍和通道的比例，障碍:通道=2:5
					t = 1;
				} else {
					t = 0;
				}
				maze[i][j] = t;
			}
		}
		maze[rowsize][colsize] = 0;	//设定出口位置为0(即通道)
	}
/**
 * 寻找迷宫路径方法
 * @param i 入口坐标
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
		if (i == rowsize && j == colsize)//从原来的四个方向增加到八个方向
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
		
		if (!finish) // 无法走通，那么修改原来设置为0
			maze[i][j] = 0;
		return finish;
	}
	
	public void judge() {//判断是否找到路径
		if (solve(1, 1)) {
			printMaze();
			System.out.println("the path had find");
		} else {
			printMaze();
			System.out.println("not path of the maze");
		}
	}
	
	public void printMaze() {// 打印迷宫方法

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
