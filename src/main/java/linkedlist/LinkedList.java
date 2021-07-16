package linkedlist;

import java.util.ArrayList;

public interface LinkedList<T> {

    public Node<T> getFirst();
    public Node<T> getLast();

    /**
     *
     * @return num of elements in list
     */
    public int size();

    /**
     *
     * @return true if list is empty, else false
     */
    public boolean isEmpty();

    /**
     * removes all elements from list
     */
    public void clear();

    /**
     * Inserts a new node with the given value after n.
     *
     * @param cursor The node to insert after. Set this to null to insert value as the new first Node.
     * @param value  The value to insert
     * @return a reference to the newly inserted Node
     */
    public Node<T> insert(Node<T> cursor, T value);

    public Node<T> append(T value);
    public Node<T> prepend(T value);
    public void remove(Node<T> cursor);

    /**
     *
     * @param startNode -  the Node<T> in list to start looking from
     * @param key
     * @return
     */
    public Node<T> find(Node<T> startNode, T key);




    public Node<T> find(Node<T> start, T key, boolean reverseDirection);

    /**
     *
     * @return the arrayList form of the linked list
     */
    public ArrayList<T> toList();
}
