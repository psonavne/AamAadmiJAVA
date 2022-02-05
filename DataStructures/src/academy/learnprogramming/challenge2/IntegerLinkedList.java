package academy.learnprogramming.challenge2;

public class IntegerLinkedList {

    private IntegerNode head;
    private int size;

    public void addToFront(Integer value) {
        IntegerNode node = new IntegerNode(value);
        node.setNext(head);
        head = node;
        size++;
    }

    public IntegerNode removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        IntegerNode removedNode = head;
        head = head.getNext();
        size--;
        removedNode.setNext(null);
        return removedNode;
    }
// 3 2 1 4
    
    //1 2 3 
    public void insertSorted(Integer value) {
    	IntegerNode integerNode=new IntegerNode(value);
    	int listcount=size;
    	boolean flag=false;
    	if(head==null) {
    		head=integerNode;
    		size++;
    	}else {
    		IntegerNode current=head;
    		while(current!=null && flag==false) {
    			if(current.getValue()>value) {
    				integerNode.setNext(current);
    				head=integerNode;
    				size++;
    				flag=true;
    			} else if (current.getValue()<value && value< current.getNext().getValue()) {
    				integerNode.setNext(current.getNext());
    				current.setNext(integerNode);
    				size++;
    				flag=true;
    			}
    			else {
    				current=current.getNext();
    				if(current.getNext()==null) {
    					current.setNext(integerNode);
    					size++;
    					flag=true;
    				}
    			}
    			listcount--;
    		}
			/*
			 * if(current.getNext()==null) { current.setNext(integerNode); }
			 */
    	}
        
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        IntegerNode current = head;
        System.out.print("HEAD -> ");
        while (current != null) {
            System.out.print(current);
            System.out.print(" -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

}
