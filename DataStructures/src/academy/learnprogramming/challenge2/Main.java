package academy.learnprogramming.challenge2;

public class Main {

    public static void main(String[] args) {

        Integer one = 1;
        Integer two = 2;
        Integer three = 3;
        Integer four = 4;

        IntegerLinkedList list = new IntegerLinkedList();
        list.insertSorted(three);
        list.printList();
        list.insertSorted(two);
        list.printList();
        list.insertSorted(one);
        list.printList();
        list.insertSorted(four);
        list.printList();
        list.insertSorted(34);
        list.printList();
        list.insertSorted(5);
        list.printList();

        list.insertSorted(54);
        list.printList();
        list.insertSorted(13);
        list.printList();
    }
}
