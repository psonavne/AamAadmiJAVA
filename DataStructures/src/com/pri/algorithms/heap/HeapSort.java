/**
 * 
 */
package com.pri.algorithms.heap;

/**
 * @author prita
 *
 */
public class HeapSort {

	private int [] heap;
	
	private int size;
	
	HeapSort(int capacity){
		heap=new int[capacity];
	}
	
	public void insert(int value) {
		if(size==heap.length-1) {
			throw new IndexOutOfBoundsException();
		}
		heap[size]=value;
		heapifyAbove(size);
		size++;
	}
	
	public void heapifyAbove(int index) {
		
		int childToSwap=heap[index];
		while(index>0 && childToSwap>heap[getParent(index)]) {
			heap[index]=heap[getParent(index)];
			index=getParent(index);
		}
		heap[index]=childToSwap;
	}
	
	public int getParent(int index) {
		return (index-1)/2;
	}
	
	public void heapSort() {
		
		heapSort(size);
	}
	//80, 75, 60, 68, 55, 40, 52, 67
	/*
	 * 	67, 75, 60, 68, 55, 40, 52, 80
	 * 
	 * 75, 67, 60, 68, 55, 40, 52, 80
	 * 
	 * 75, 68, 60, 67, 55, 40, 52, 80
	 * 
	 * 75, 68, 60, 67, 55, 40, 52, 80
	 * 
	 * */
	public void heapSort(int largestHeapIndex) {
		
		if(largestHeapIndex==0) {
			return;
		}
		largestHeapIndex--;
		while(largestHeapIndex>0) {
			int index=0;
			if(heap[index]>heap[largestHeapIndex]) {
			int tmp=heap[largestHeapIndex];
			heap[largestHeapIndex]=heap[index];
			heap[index]=tmp;
			int childToSwap;
			largestHeapIndex--;
			
			while(index<largestHeapIndex-1) {
				int leftChild=getChild(index,true);
				int rightChild=getChild(index, false);
				if(leftChild<=largestHeapIndex) {
					if(rightChild>largestHeapIndex) {
						childToSwap=leftChild;
					}
					childToSwap=heap[leftChild] > heap[rightChild] ? leftChild: rightChild;
					if(heap[index]<heap[childToSwap]) {
						int tmp2=heap[index];
						heap[index]=heap[childToSwap];
						heap[childToSwap]=tmp2;
					}else {
						break;
					}
					index=childToSwap;
				}else {
					break;
				}
	
				}
			//largestHeapIndex--;
			} else {
				largestHeapIndex--;
			}
		}
	}
	
	public int getChild(int index,boolean left) {
		return 2*index + (left?1: 2);
	}
	
	public void print() {
		for(int i=0;i<size;i++) {
			System.out.print(heap[i] + ", ");
		}
	}
}
