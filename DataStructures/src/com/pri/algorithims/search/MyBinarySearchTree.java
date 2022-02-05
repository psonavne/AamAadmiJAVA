/**
 * 
 */
package com.pri.algorithims.search;

import com.pri.algorithms.pojos.MyTree;

/**
 * @author prita
 *
 */
public class MyBinarySearchTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyTree tree=new MyTree();
		
		tree.insert(45);
		tree.insert(25);
		tree.insert(32);
		tree.insert(55);
		tree.insert(63);
		tree.insert(15);
		tree.insert(17);
		tree.traversalInorder();
		
		/*					45
		 * 			25				55
		 * 		15		32				63
		 * 			17
		 * */
		System.out.println("");
		System.out.println("Found" + tree.getValue(11));
		System.out.println("Min: " + tree.getMin());
		System.out.println("Max:" + tree.getMax());
		System.out.println("Delete" + tree.delete(45));
		tree.traversalInorder();
	}

}
