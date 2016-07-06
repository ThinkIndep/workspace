package com.issac;

public class PostmanQuestion {
	static int MAX = 65535;
	int i,j;
	char[] c = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L'};
//	public static void PostmanQuestion(int [][]graph,int n){
//	}
	
	
	public void count(int [][]graph){		
		int sum;
		for(i = 0;i<graph.length;i++){
			sum = 0;
			for(j = 0; j<graph.length;j++){
				if (graph[i][j]>0&&graph[i][j]<MAX) {
				sum++;	
				}
			}
			System.out.println("顶点"+c[i]+"的度数:"+sum);
		}
	}
	
	public static void main(String[] args) {
		int [][] map = new int[][]{
			{0,3,MAX,MAX,MAX,MAX,1,MAX,MAX,MAX,MAX,2},
			{3,0,1,MAX,MAX,MAX,MAX,MAX,MAX,MAX,MAX,MAX},
			{MAX,1,0,4,MAX,3,MAX,MAX,MAX,MAX,MAX,MAX},
			{MAX,MAX,4,0,3,MAX,MAX,MAX,MAX,MAX,MAX,MAX},
			{MAX,MAX,MAX,3,0,2,4,MAX,MAX,MAX,MAX,MAX},
			{MAX,MAX,MAX,MAX,2,0,MAX,2,3,MAX,MAX,MAX},
			{1,MAX,3,MAX,4,MAX,0,MAX,MAX,MAX,MAX,MAX},
			{MAX,MAX,MAX,MAX,MAX,2,MAX,0,MAX,3,MAX,3},
			{MAX,MAX,MAX,MAX,MAX,3,MAX,MAX,0,2,MAX,MAX},
			{MAX,MAX,MAX,MAX,MAX,MAX,MAX,3,2,0,3,MAX},
			{MAX,MAX,MAX,MAX,MAX,MAX,MAX,MAX,MAX,3,0,3},
			{2,MAX,MAX,MAX,MAX,MAX,MAX,3,MAX,MAX,3,0},
		};
		PostmanQuestion pQuestion = new PostmanQuestion();
		pQuestion.count(map);

	}
	

}
