/**
 * 
 */
package com.pri.datatypes.linkedlist;

/**
 * @author prita
 *
 */
public class EmployeeLinkedList {
	
	private EmployeeNode head;
	private int size=0;
	private EmployeeNode tail;
	
	public void addToFront(Employee current) {
		EmployeeNode employeeNode=new EmployeeNode(current);
		if(head==null) {
			head=employeeNode;
			if(tail==null) {
				tail=employeeNode;
			}
		}else {
			employeeNode.setNext(head);
			head.setPrevious(employeeNode);
			head=employeeNode;
		}
		
		
		//employeeNode.setNext(head);
		//head=employeeNode;
		size++;
	}
	
	public void addToEnd(Employee current) {
		
		EmployeeNode node =new EmployeeNode(current);
		if(tail==null) {
			tail=node;
			if(head==null) {
				head=node;
			}
		}else {
			tail.setNext(node);
			node.setPrevious(tail);
			tail=node;
			
		}
		size++;
		
	}
	
	public EmployeeNode removeFromFront() {
		
		if(head==null) {
			return null;
		} 
		EmployeeNode removedNode=head;
		head=head.getNext();
		head.setPrevious(null);
		size--;
		return removedNode;
	}
	
	public EmployeeNode removeFromEnd() {
		if(tail==null) {
			return null;
		} 
		EmployeeNode node=tail;
		tail=tail.getPrevious();
		tail.setNext(null);
		size--;
		return node;
	}
	
	public int getSize() {
		return size;
	}
	public void print() {
		EmployeeNode current=head;;
		while(current!=null) {
			System.out.println(current.toString());
			current=current.getNext();
		}
	}

	public void addBefore(Employee newEmployee, Employee currentEmployee) {
		EmployeeNode current=head;
		EmployeeNode found = null;
		
		while(current!=null && found==null) {
			if(current.getNode().equals(currentEmployee)) {
				found=current;
			}
			current=current.getNext();
		}
		if(found==null) {
			System.out.println("Employee not found");
		}else {
			EmployeeNode newNode=new EmployeeNode(newEmployee);
			if(found.getPrevious()!=null) {
				found.getPrevious().setNext(newNode);
			}
			newNode.setNext(found);
			newNode.setPrevious(found.getPrevious());
			found.setPrevious(newNode);
			size++;
		}
		
		
	}
}
