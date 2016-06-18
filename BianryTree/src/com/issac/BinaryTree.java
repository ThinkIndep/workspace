package com.issac;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * add git 
 * @author ��ΰ��
 *
 */
public class BinaryTree {

	public BinaryNode buildTree() { // ��������������

		BinaryNode FrontBinayNode; // ������ͷ���

		Scanner scan = new Scanner(System.in);// ���������������

		String s = scan.next();

		scan.close();

		char[] datas = s.toCharArray();

		Queue<BinaryNode> A = new LinkedList<>(); // ����������ݵĶ���

		Queue<BinaryNode> B = new LinkedList<>(); // ��Ÿ��ڵ�Ķ���

		for (Character data : datas) { // Ϊ���ж������ڵ��������
			
			BinaryNode binaryNode = new BinaryNode();
			
			binaryNode.data = data;
			
			binaryNode.right = null;
			
			binaryNode.left = null;
			
			A.add(binaryNode);      //���������ݵĽڵ����
		}

		FrontBinayNode = A.poll(); // ����������ͷ���

		B.add(FrontBinayNode); // ��ͷ�ڵ����B����

		while (true) { // �������������Ĵ���
			
			boolean exit = false; // �ж��Ƿ��Ѿ���ɶ������Ĺ���

			BinaryNode pfather = B.poll();
			
			if (!A.isEmpty()) { // ���A��������Ԫ�ض��Ѿ�������������˶������Ĺ���
				
				BinaryNode pLchild = A.poll(); // �ȵ������������
				
				if (pLchild.data != '.') { // �ж�������Ƿ�Ϊ��
					
					pfather.left = pLchild;
					
					B.add(pLchild);			//���Ӳ�Ϊ�յĲŽ������B
					
				} else {
					
					pfather.left = null;
				}

			} else { // ���A����Ϊ�գ���������Ѿ���ɹ�����׼���˳�ѭ��
				
				exit = true;
			}

			if (!A.isEmpty()) {// ��������������������������һ�������ﲻ��ע��

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
	public void sequence(BinaryNode FrontBinaryNode){//��α���
		
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
	public void preorder(BinaryNode FrontBinaryNode) {// �������
		
		if (FrontBinaryNode == null) {
			
			return;
		}
		
		System.out.print(FrontBinaryNode.data);
		
		preorder(FrontBinaryNode.left);
		
		preorder(FrontBinaryNode.right);
	}

	public void inorder(BinaryNode FrontBinaryNode) {// �������
		
		if (FrontBinaryNode == null) {
			
			return;
		}
		inorder(FrontBinaryNode.left);
		
		System.out.print(FrontBinaryNode.data);
		
		inorder(FrontBinaryNode.right);
	}

	public void postorder(BinaryNode FrontBinaryNode) {// �������
		
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
		System.out.print("��α���:");
		binaryTree.sequence(FrontBinaryNode);
		System.out.print("�������:");
		binaryTree.preorder(FrontBinaryNode);
		System.out.println();
		System.out.print("�������:");
		binaryTree.inorder(FrontBinaryNode);
		System.out.println();
		System.out.print("�������:");
		binaryTree.postorder(FrontBinaryNode);
		//�������ݣ�ebfad.g..c

	}

	class BinaryNode { // �����ӽڵ���
		
		char data;
		
		BinaryNode left;
		
		BinaryNode right;

		@Override
		public String toString() {// ��������õ���ʱ����
			// TODO Auto-generated method stub
			return String.valueOf(data);
		}

	}

}
