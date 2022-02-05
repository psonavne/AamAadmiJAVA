/**
 * 
 */
package com.pri.datatypes.linkedlist;

/**
 * @author prita
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Employee andy=new Employee("Andy", 123);
		Employee steve=new Employee("Steve", 456);
		Employee ben=new Employee("ben",789);
		EmployeeLinkedList employeeLinkedList=new EmployeeLinkedList();
		employeeLinkedList.addToEnd(ben);
		//System.out.println("After adding to end");
		//employeeLinkedList.print();
		employeeLinkedList.addToFront(andy);
		employeeLinkedList.addToFront(steve);
		//System.out.println("After adding from front");
		employeeLinkedList.print();
		
		
		//employeeLinkedList.removeFromFront();
		//System.out.println(employeeLinkedList.getSize());
		
		//employeeLinkedList.print();
		
		//employeeLinkedList.removeFromEnd();
		System.out.println("-----------------------------------");
		Employee newEmployee=new Employee("Andrew", 897);
		employeeLinkedList.addBefore(newEmployee,ben);
		employeeLinkedList.print();
	}

}
