package linkedlist;

import java.util.ArrayList;

public class doublyLinkedList<T> implements LinkedList<T>{

    private int count;
    private Node<T> start;
    private Node<T> end;


    @Override
    public Node<T> getFirst() {
        return start;
    }

    @Override
    public Node<T> getLast() {
        return end;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return start == null;
    }

    @Override
    public void clear() {
        start = null;
        end = null;
        count = 0;

    }

    @Override
    public Node<T> insert(Node<T> cursor, T value) {

        //create node
        Node<T> newNode = new Node<>( value);
        count++;

        //if at start
        if (cursor == null ){
            if (start ==null) {
                start = newNode;
                end=start;
            } else {

                newNode.next = start;
                start.prev = newNode;
                start = newNode;

            }

            }







        //if at end
        else if (cursor == end){
         newNode.prev = end;
         end.next = newNode; //Write test for this line
         end = newNode;

        }

        //if in middle
        else {
            newNode.next = cursor.next;
            newNode.prev = cursor;
            cursor.next.prev = newNode;
            cursor.next = newNode;

        }

        return newNode;

    }

    @Override
    public Node<T> append(T value) {
        Node<T> newNode = new Node<>(value);
        if (count == 0) {
            start = newNode;
            end = newNode;


        } else{

        end.next = newNode;
        newNode.prev = end;
        end = newNode;



    }
        count++;
        return newNode;
    }

    @Override
    public Node<T> prepend(T value) {
        Node<T>  newNode = new Node<>(value, start, null);
        if (count == 0) {
            start = newNode;
            end = newNode;


        } else{
        newNode.next = start;
        start.prev = newNode;

        start = newNode;
        }
        count++;
        return newNode;
    }

    @Override
    public void remove(Node<T> cursor) {
        count--;
        if (cursor == start){

            //check if new list size is 0
            if(count==0){
                start = null;
                end = null;
            } else {
                start = start.next;
                start.prev = null;
            }

        }

        //if at end
        else if (cursor == end){
            end.prev.next = null;
            end = end.prev;
        }


        //if in middle
        else {
            cursor.next.prev = cursor.prev;
            cursor.prev.next = cursor.next;

        }



    }

    @Override
    public Node<T> find(Node<T> startNode, T key) {

        if (startNode==null){
            startNode = start;

        }

        while(startNode!=null){
            if( startNode.value.equals(key)){
                return startNode;
            }
            startNode = startNode.next;
        }
        // if not in remainder of list
        return null;
    }

    @Override
    public Node<T> find(Node<T> start, T key, boolean reverseDirection) {


        if (reverseDirection) {
            return find(start, key);

        } else {
            while (start != null) {
                if (start.value.equals(key)) {
                    return start;
                }
                start = start.prev;
            }
            // if not in remainder of list
            return null;
        }
    }

    @Override
    public ArrayList<T> toList() {
        ArrayList<T> Arr = new ArrayList<>();
        Node<T> curNode = start;
        while (curNode!=null) {
            Arr.add(curNode.value);
            curNode= curNode.next;
        }
        return Arr;
    }
}
