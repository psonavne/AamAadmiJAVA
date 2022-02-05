/**
 * 
 */
package com.pri.algorithms.pojos;

/**
 * @author prita
 *
 */
public class Tree {
	
	private TreeNode root;

	public void insert(Integer value) {
		if(root==null) {
			root=new TreeNode(value);
		}else {
			root.insert(value);
		}
	}
	
	public void printTree() {
		print(root,root.getLeftNode(),root.getRightNode());
	}
	/*					45
	 * 			32				65			
	 * 		12		35		55		68
	 * 
	 * */
	
	
	
	public void traversalInOrder() {
		root.traversalInOrder();
	}
	
	public void traversalPreOrder() {
		root.traversalPreOrder();
	}
	public void print(TreeNode node, TreeNode left, TreeNode right) {
		TreeNode current = node;
		
		  if (current == null) { return; }
		 
		if (current.getLeftNode() != null) {
				current = current.getLeftNode();
				print(current,current.getLeftNode(),current.getRightNode());
		} 
		if(current.getRightNode()!=null) {
				current = current.getRightNode();
				print(current,current.getLeftNode(),current.getRightNode());
		}
		if(current!=null && (current.getLeftNode()==null && current.getRightNode()==null)) {
			System.out.println(current.getValue());
			return;
			//System.out.println(node.getValue());
		}		
			
		
	}
	
	public void iterativePrint() {
		if(root==null) {
			System.out.println("Tree is empty");
		}
		TreeNode current=root;
		while(current!=null) {
			System.out.println(current.getValue());
			if(current.getLeftNode()!=null) {
				current=current.getLeftNode();
			}else if (current.getRightNode()!=null) {
				current=current.getRightNode();
			}
		}
		
		
	}

	public int getValue(int i) {
		int found= root.getValue(i);
		return found;
	}
	
	public int getMin() {
		return root.getMin();
	}
	
	
	  public int getMax() { return root.getMax(); }
	  
	  
	  public int delete(int value) {
		  root=delete(root,value);
		  return root!=null ? root.getValue() : null;
		  
		  
	  }
	  
		/*					45
		 * 			32				65			
		 * 		12		35		55		68
		 * 	17					  57
		 *12,32,55,65,68,
		 * */
	 public TreeNode delete(TreeNode subRoot, int value) {
		 
		 if(subRoot==null) {
			 return subRoot;
		 }
		 
		 else if(value<subRoot.getValue()) {
			 subRoot.setLeftNode(delete(subRoot.getLeftNode(),value));
		 }else if(value>subRoot.getValue()) {
			 subRoot.setRightNode(delete(subRoot.getRightNode(),value));
		 }else {
			 if(subRoot.getLeftNode()==null) {
				 return subRoot.getRightNode();
			 }else if(subRoot.getRightNode()==null) {
				 return subRoot.getLeftNode();
				 
			 }
			 if(subRoot.getLeftNode()!=null && subRoot.getRightNode()!=null) {
				 
				 subRoot.setValue(subRoot.getRightNode().getMin());
				 subRoot.setRightNode(delete(subRoot.getRightNode(),subRoot.getRightNode().getMin()));
				 //return subRoot;
			 }
			
		 }
		return subRoot;
	 
	 }
}
