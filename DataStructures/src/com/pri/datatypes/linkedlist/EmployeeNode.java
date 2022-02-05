package com.pri.datatypes.linkedlist;

public class EmployeeNode {

	
	private Employee node;
	private EmployeeNode next;
	private EmployeeNode previous;
	
	
	public EmployeeNode(Employee head) {
		super();
		this.node = head;
		
	}
	public EmployeeNode getNext() {
		return next;
	}
	public Employee getNode() {
		return node;
	}
	public void setNode(Employee node) {
		this.node = node;
	}
	public void setNext(EmployeeNode next) {
		this.next = next;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeNode other = (EmployeeNode) obj;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return node.toString();
	}
	public EmployeeNode getPrevious() {
		return previous;
	}
	public void setPrevious(EmployeeNode previous) {
		this.previous = previous;
	}
	
	
}
