package com.issac;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * add git 
 * @author 苏伟锋
 *
 */
public class BinaryTree {

	public BinaryNode buildTree() { // 建立二叉树方法

		BinaryNode FrontBinayNode; // 二叉树头结点

		Scanner scan = new Scanner(System.in);// 输入二叉树的数据

		String s = scan.next();

		scan.close();

		char[] datas = s.toCharArray();

		Queue<BinaryNode> A = new LinkedList<>(); // 存放输入数据的队列

		Queue<BinaryNode> B = new LinkedList<>(); // 存放父节点的队列

		for (Character data : datas) { // 为所有二叉树节点添加数据
			
			BinaryNode binaryNode = new BinaryNode();
			
			binaryNode.data = data;
			
			binaryNode.right = null;
			
			binaryNode.left = null;
			
			A.add(binaryNode);      //将加了数据的节点入队
		}

		FrontBinayNode = A.poll(); // 弹出二叉树头结点

		B.add(FrontBinayNode); // 将头节点放入B队列

		while (true) { // 构建二叉树核心代码
			
			boolean exit = false; // 判断是否已经完成二叉树的构建

			BinaryNode pfather = B.poll();
			
			if (!A.isEmpty()) { // 如果A队列所有元素都已经弹出，则完成了二叉树的构建
				
				BinaryNode pLchild = A.poll(); // 先弹出的是左儿子
				
				if (pLchild.data != '.') { // 判断左儿子是否为空
					
					pfather.left = pLchild;
					
					B.add(pLchild);			//儿子不为空的才进入队列B
					
				} else {
					
					pfather.left = null;
				}

			} else { // 如果A队列为空，则二叉树已经完成构建，准备退出循环
				
				exit = true;
			}

			if (!A.isEmpty()) {// 构造右子树和上述构造左子树一样，这里不再注释

				BinaryNode pRchild = A.poll();
				
				if (pRchild.data != '.') {
					
					pfather.right = pRchild;
					
					B.add(pRchild);
					
				} else {
					
					pfather.right = null;
					
				}
				
			} else {
				
				exit = true;
			}

			if (exit) {
				
				break;
			}

		}

		return FrontBinayNode;

	}
	public void sequence(BinaryNode FrontBinaryNode){//层次遍历
		
		Queue<BinaryNode> queue= new LinkedList<>();
		
		queue.add(FrontBinaryNode);
		
		while(!queue.isEmpty()){
			
			BinaryNode FatherBinaryNode = queue.poll();
			
			System.out.print(FatherBinaryNode.data);
			
			if (FatherBinaryNode.left!=null) {
				
				queue.add(FatherBinaryNode.left);
				
			}
			if (FatherBinaryNode.right!=null) {
				
				queue.add(FatherBinaryNode.right);
			}
		}
		System.out.println();
	}
	public void preorder(BinaryNode FrontBinaryNode) {// 先序遍历
		
		if (FrontBinaryNode == null) {
			
			return;
		}
		
		System.out.print(FrontBinaryNode.data);
		
		preorder(FrontBinaryNode.left);
		
		preorder(FrontBinaryNode.right);
	}

	public void inorder(BinaryNode FrontBinaryNode) {// 中序遍历
		
		if (FrontBinaryNode == null) {
			
			return;
		}
		inorder(FrontBinaryNode.left);
		
		System.out.print(FrontBinaryNode.data);
		
		inorder(FrontBinaryNode.right);
	}

	public void postorder(BinaryNode FrontBinaryNode) {// 后序遍历
		
		if (FrontBinaryNode == null) {
			
			return;
		}
		postorder(FrontBinaryNode.left);
		
		postorder(FrontBinaryNode.right);
		
		System.out.print(FrontBinaryNode.data);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree binaryTree = new BinaryTree();
		BinaryNode FrontBinaryNode = binaryTree.buildTree();
		System.out.print("层次遍历:");
		binaryTree.sequence(FrontBinaryNode);
		System.out.print("先序遍历:");
		binaryTree.preorder(FrontBinaryNode);
		System.out.println();
		System.out.print("中序遍历:");
		binaryTree.inorder(FrontBinaryNode);
		System.out.println();
		System.out.print("后序遍历:");
		binaryTree.postorder(FrontBinaryNode);
		//测试数据：ebfad.g..c

	}

	class BinaryNode { // 定义子节点类
		
		char data;
		
		BinaryNode left;
		
		BinaryNode right;

		@Override
		public String toString() {// 代码调试用的临时方法
			// TODO Auto-generated method stub
			return String.valueOf(data);
		}

	}

}
