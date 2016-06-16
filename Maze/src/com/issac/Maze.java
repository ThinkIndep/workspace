package com.issac;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Maze {

	public int[][] CreateRadomArray() {		//创建随机迷宫数组方法

		int maze[][] = new int[10][10];

		Random random = new Random();		

		for (int i = 0; i < 10; i++) {	//为迷宫外加一圈围墙

			maze[0][i] = 1;

			maze[9][i] = 1;

			maze[i][0] = 1;

			maze[i][9] = 1;
		}

		for (int i = 1; i < 9; i++) {	//迷宫墙内随机生成障碍和通道

			for (int j = 1; j < 9; j++) {

				int t;

				t = random.nextInt(7);

				if (t < 3) { //分配迷宫内障碍和通道的比例，障碍:通道=2:5    
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
	 * 迷宫寻找路径方法
	 * @param maze:迷宫数组
	 * @param x1:入口x坐标
	 * @param y1:入口y坐标
	 * @param x2:出口x坐标
	 * @param y2:出口y坐标
	 */
	public void mazePath(int maze[][], int x1, int y1, int x2, int y2) {
																		
		int i, j, k;

		int g, h;
		
		int direction [][] = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};

		Stack<Position> stack = new Stack<>();//实例化堆栈类

		Position position = new Position();	//实例化位置类

		maze[x1][y1] = 2;	//走过的路径改为2；
		maze[x2][y2] = 0;	//出口位置改为0；

		position.x = x1;	//为入口位置赋值
		position.y = y1;
		position.d = -1;

		stack.push(position);		//将入口位置入栈

		while (!stack.isEmpty()) {	//走不通识，一步步回退

			position = stack.pop();

			i = position.x;

			j = position.y;

			k = position.d + 1;

			while (k < 8) {		//逐个方向试探

				g = i + direction[k][0];

				h = j + direction[k][1];

				if (g == x2 && h == y2) {	//走到出口点
					
					maze[g][h] = 2;
					
					printMaze(maze);
					
					System.out.println("find finish!!");
					
					return;
				}

				if (maze[g][h] == 0) {	//走到没走过的点

					maze[g][h] = 2;		//做标记
					
					printMaze(maze);
					
					waitNextStep();		//按键盘进行下一步

					position.x = i;

					position.y = j;

					position.d = k;

					stack.push(position);	//进栈

					i = g;					//下一点转换成当前点

					j = h;

					k = -1;
				}

				k = k + 1;
			}

		}

		printMaze(maze);
		System.out.println("The path has not been found!!!");//栈退完，未找到路径
	}
	
	public void waitNextStep(){//等待键盘输入再进行下一步
		System.out.println("print any key to continue........");
		
		new Scanner(System.in).nextLine();
	}

	public void printMaze(int maze[][]) {//打印迷宫方法
		
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

	class Position {//迷宫走过位置类
		int x, y, d;//x,y,d 分别表示迷宫探索位置的行、列、探索方向
	}

}
