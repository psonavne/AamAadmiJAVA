package com.pri.challenge;



import java.util.*;
import java.io.*;

class Node1 {
    Node1 left;
    Node1 right;
    int data;
    
    Node1(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinaryTreeTraversal {

	/* 
    
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static void levelOrder(Node1 root) {
		if(root==null) {
			return;
		}
		Queue<Node1> queue=new ArrayDeque<Node1>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node1 node= queue.peek();
			System.out.print(node.data + " ");
			if(node.left!=null) {
				queue.add(node.left);
			}
			if(node.right!=null) {
				queue.add(node.right);
			}
			queue.poll();
		}
		
    }

	public static Node1 insert(Node1 root, int data) {
        if(root == null) {
            return new Node1(data);
        } else {
            Node1 cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node1 root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        levelOrder(root);
    }	
}