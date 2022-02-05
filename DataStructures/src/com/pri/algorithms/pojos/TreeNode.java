/**
 * 
 */
package com.pri.algorithms.pojos;

/**
 * @author prita
 *
 */
public class TreeNode {

	private Integer value;
	
	private TreeNode leftNode;
	
	private TreeNode rightNode;
	
	public TreeNode() {
		
	}
	public TreeNode(Integer value) {
		this.value=value;
	}
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public TreeNode getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}

	public TreeNode getRightNode() {
		return rightNode;
	}

	public void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}
	// 45 32 23 14 67 35 43
	
	//		 45 
	//	32		67
	// 23 35
	//14	43
	public void insert(Integer val) {
		if(value==val) {
			return;
		}
		else if(value!=null){
			
			if(val> value) {
				if(this.rightNode==null) {
					rightNode=new TreeNode(val);
				}else
				{
					rightNode.insert(val);
				}
			}else {
				if(leftNode==null) {
					leftNode=new TreeNode(val);
				}else {
					leftNode.insert(val);
				}
			}
			
			
		}
	}
	/*					45
	 * 			32				65			
	 * 		12		35		55		68
	 * 
	 * */
	
	
	public void traversalInOrder() {
		if (leftNode!=null) {
			leftNode.traversalInOrder();
		}
		System.out.print(value + ",");
		if(rightNode!=null) {
			rightNode.traversalInOrder();
		}
	}
	public int getValue(int i) {
		
		if(value==i) {
			return value;
		}
		if(i<value) {
			if(leftNode.value!=null) {
				return leftNode.getValue(i);
			} /*
				 * else { return leftNode.getValue(); }
				 */
		} else {
			if(rightNode.value!=null) {
				return rightNode.getValue(i);
			} /*
				 * else { return rightNode.getValue(); }
				 */
		}
		return -1;
	}
	
	public int getMin() {
		
		if (leftNode!=null) {
			return leftNode.getMin();
		} else if(leftNode==null) {
			return value;
		}
		return -1;
	}
	
	public int getMax() {
		if (rightNode != null) {
			return rightNode.getMax();
		} else if (rightNode == null)
			return value;
		
		return -1;
	}
	public void traversalPreOrder() {
		
		System.out.print(value + ",");
		if(leftNode!=null) {
			leftNode.traversalPreOrder();
		}
		if(rightNode!=null) {
			rightNode.traversalPreOrder();
		}
	}

}
