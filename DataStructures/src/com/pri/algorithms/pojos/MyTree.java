package com.pri.algorithms.pojos;

public class MyTree {

	private MyTreeNode root;

	public void insert(int data) {
		if(root==null) {
			root=new MyTreeNode(data);
		}
		else
		root.insert(data);
		
	}
	
	public void traversalInorder() {
		root.traversalInOrder();
	}

	public int getValue(int value) {
	
		return root.getValue(value);
		
	}

	public int getMin() {
		// TODO Auto-generated method stub
		return root.getMin();
	}

	public int getMax() {
		// TODO Auto-generated method stub
		return root.getMax();
	}
	
	public int delete(int value) {
		return delete(root,value).getData();
	}
	/*					45
	 * 			25				55
	 * 	15			32				63
	 * 		17
	 * */
	public MyTreeNode delete(MyTreeNode subRootNode, int value) {
	
		if(subRootNode==null) {
			return subRootNode;
		}
		
		if(value<subRootNode.getData()) {
			subRootNode.setLeftChild(delete(subRootNode.getLeftChild(), value));
		}else if(value>subRootNode.getData()) {
			subRootNode.setRightChild(delete(subRootNode.getRightChild(), value));
		}else {
			if(subRootNode.getLeftChild()==null) {
				return subRootNode.getRightChild();
			}else if(subRootNode.getRightChild()==null){
				return subRootNode.getLeftChild();
			}
			subRootNode.setData(subRootNode.getRightChild().getMin());
			subRootNode.setRightChild(delete(subRootNode.getRightChild(),subRootNode.getRightChild().getMin()));
		}
		
		
		return subRootNode;
	}
}
