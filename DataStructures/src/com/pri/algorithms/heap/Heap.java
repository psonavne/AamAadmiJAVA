package com.pri.algorithms.heap;

import java.util.Arrays;

public class Heap {
	
	private int [] heap;
	
	private int size;
	
	Heap(int capacity){
		heap=new int [capacity];
	}
	
	public boolean isFull() {
		if(size==heap.length-1) {
			return true;
		}
		else return false;
	}

	public int getParent(int index) {
		return (index-1)/2;
	}
	
	public void insert(int value) {
		if(isFull()) {
			throw new IndexOutOfBoundsException();
		}
		else {
			heap[size]=value;
			int parentIndex=getParent(size);
			heapify(size,parentIndex);
			size++;
		
		}
		
	}
	
	public void print() {
		System.out.println(Arrays.toString(heap));
	}
	public int getChild(int value, int index) {
		return value>heap[index] ? 2*index+2 : 2*index+1;
		
		
		
		/*
		 * if(value> heap[index]) { return (2*index+2); } else return (2*index+1);
		 */
	}
	/*					45
	 * 			25				55
	 * 		15		32		54		63
	 * 	52
	 * 
	 * 				45
	 * 			25				55
	 * 		25		32		54		63
	 * 	15
	 * 
	 * 45 25 55 25 32 54 63 15 
	 * 0  1  2  3  4   5 6   7
	 * */
	public void heapify(int childIndex, int parentIndex) {
		
		int childToSwap=heap[childIndex]; // 52
		while(childIndex>0 && childToSwap>heap[parentIndex]) { // 3>0 && 52>15 // 1>0 && 52> 25
			heap[childIndex]=heap[parentIndex]; //heap[7]=heap[3]  // heap[3]=heap[1] 
			childIndex=parentIndex;// childindex=3 childindex=1
			parentIndex=getParent(parentIndex); //parentindex=1 parentIndex=0
		}
		//heap[childIndex]=heap[parentIndex];
		heap[childIndex]=childToSwap;
	}
	
	
	public void delete(int deleteIndex) {
		
		heap[deleteIndex]=heap[size-1];
		heap[size-1]=0;
		
		heapBelow(deleteIndex, size);
		size--;
		
	}
	
	public void heapBelow(int index, int largestHeap) {
		
		int childToSwap;
		if(heap[index]>heap[getParent(index)]) {
			heapify(index, getParent(index));
		}else {
			while(index<=largestHeap) {
				/*
				 * if(heap[index]<heap[getlargestChild(index)] && getlargestChild(index)<size) {
				 * heap[index]=heap[getlargestChild(index)];
				 * heap[getlargestChild(index)]=childToSwap; }
				 */
				int leftChild=2*index+1;
				int rightChild=2*index+1;
				if(leftChild<=largestHeap) {
					if(rightChild>largestHeap) {
						childToSwap=leftChild;
					}else {
						childToSwap= heap[leftChild]> heap[rightChild]? leftChild: rightChild;
					}
					if(heap[index]<heap[childToSwap]) {
						int tmp=heap[childToSwap];
						heap[childToSwap]=heap[index];
						heap[index]=tmp;
					}else {
						break;
					}
				}
				else {
					break;
				}
				index=childToSwap;
				
			}
		}
		
	}
	/*				63
	 * 		32				55
	 * 	15		25		45		54
	 * 
	 * 
	 * 
	 * */
	public int getlargestChild(int index) {
		
		return heap[2*index+1] >heap[2*index+2] ? 2*index+1 : 2*index+2;
		
	}
}
