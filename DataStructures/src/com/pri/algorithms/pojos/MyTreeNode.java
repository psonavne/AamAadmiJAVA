/**
 * 
 */
package com.pri.algorithms.pojos;

/**
 * @author prita
 *
 */
public class MyTreeNode {

	
	private int data;
	
	private MyTreeNode leftChild;
	
	private MyTreeNode rightChild;

	
	
	
	public MyTreeNode(int data2) {
		data=data2;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public MyTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(MyTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public MyTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(MyTreeNode rightChild) {
		this.rightChild = rightChild;
	}

	public void insert(int value) {
		
		if(data==value) {
			return;
		}
		if(value<data) {
			if(leftChild==null) {
				leftChild=new MyTreeNode(value);
			}else {
				leftChild.insert(value);
			}
		}else if(value>data) {
			if(rightChild==null) {
				rightChild=new MyTreeNode(value);
			}
			else{
				rightChild.insert(value);
			}
		}
		//data=value;
	}
	
	
	public void traversalInOrder() {
	
		if(leftChild!=null) {
			leftChild.traversalInOrder();
		}
		System.out.print(data + ", ");
		if(rightChild!=null) {
			rightChild.traversalInOrder();
		}
	}

	public int getValue(int value) {
		
		if(data==value) {
			return data;
		}
		if(value<data) {
			if(leftChild!=null) {
				return leftChild.getValue(value);
			}
		}else {
			if(rightChild!=null) {
				return rightChild.getValue(value);
			}
		}
		
		return -1;
	}
	
	public int getMin() {
		if(leftChild==null) {
			return data;
		}else if(leftChild!=null) {
			return leftChild.getMin();
		}
			
		return -1;
	}
	
	public int getMax() {
		if(rightChild==null) {
			return data;
		}else if(rightChild!=null) {
			return rightChild.getMax();
		}
		return -1;
	}
	
	
}
