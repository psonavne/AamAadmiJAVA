/**
 * 
 */
package com.pri.algorithims.search;

import com.pri.algorithms.pojos.Tree;

/**
 * @author prita
 *
 */
public class BinarySearchTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*					45
		 * 			32				65			
		 * 		12		35		55		68
		 * 
		 * */
		Tree tree=new Tree();
		tree.insert(45);
		tree.insert(32);
		tree.insert(12);
		tree.insert(65);
		tree.insert(55);
		tree.insert(68);
		tree.insert(35);
		tree.insert(17);
		tree.insert(57);
		tree.traversalInOrder();
		System.out.println("");
		//System.out.println("Found: " + tree.getValue(68));
		
		/*
		 * System.out.println("Min" + tree.getMin()); System.out.println("MIn" +
		 * tree.getMax());
		 * 
		 * System.out.println("Deleted" + tree.delete(45)); tree.traversalInOrder();
		 */
		tree.traversalPreOrder();
	}

}
