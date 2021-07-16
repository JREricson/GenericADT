package linkedlist;



public class Main {
    public static void main(String[] args) {
        doublyLinkedList<String> testList = new doublyLinkedList<>();
        System.out.println();
        System.out.println("size is " + testList.size());
        System.out.println("now adding an element");
        Node<String> newNode = testList.append("hat");
        System.out.println("size is " + testList.size() + " and value is " + newNode.toString()) ;

    }
}


//note - make sure removing an item that does not exist does not lead to an error or create proper error for it






