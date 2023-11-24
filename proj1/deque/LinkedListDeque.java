package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {
    private int size;
    private IntNode sentinel;

    private class IntNode {
        public T item;
        public IntNode prev;
        public IntNode next;

        public IntNode(T element, IntNode prevNode, IntNode nextNode) {
            item = element;
            prev = prevNode;
            next = nextNode;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode(null, null, null);
    }

    // Return true if the deque is empty.
    public boolean isEmpty() {
        return size == 0;
    }

    // Add an item to the front of the deque.
    public void addFirst(T item) {
        if (isEmpty()) {
            IntNode first = new IntNode(item, sentinel, sentinel);
            sentinel.prev = first;
            sentinel.next = first;
        } else {
            IntNode first = new IntNode(item, sentinel, sentinel.next);
            sentinel.next.prev = first;
            sentinel.next = first;
        }
        size += 1;
    }

    // Add an item to the back of the deque.
    public void addLast(T item) {
        if (isEmpty()) {
            IntNode last = new IntNode(item, sentinel, sentinel);
            sentinel.prev = last;
            sentinel.next = last;
        } else {
            IntNode last = new IntNode(item, sentinel.prev, sentinel);
            sentinel.prev.next = last;
            sentinel.prev = last;
        }
        size += 1;
    }

    // Return the number of items in the deque.
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        IntNode printNode = sentinel.next;
        while (printNode.next != sentinel) {
            System.out.print(printNode.item + " ");
            printNode = printNode.next;
        }
        System.out.println(printNode.item);
    }

    // Removes and returns the item at the front of the deque.
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removeItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removeItem;
    }

    // Removes and returns the item at the back of the deque.
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removeItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removeItem;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    public T get(int index) {
        if (isEmpty()){
            return null;
        }
        if (index > size || index < 0) {
            return null;
        }
        int i = 0;
        for (T item : this) {
            if (i == index) {
                return item;
            }
            i++;
        }
        return null;
    }

    @Override
    // Returns whether or not the parameter o is equal to the Deque.
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque otherDeque) {
            if (this.size != otherDeque.size) {
                return false;
            }
            Iterator otherIterator = otherDeque.iterator();
            for (T t : this) {
                if (!t.equals(otherIterator.next())) {
                    return false;
                }
            }
        }
        return true;
    }

    // Same as get, but uses recursion.
    public T getRecursive(int index) {
        if (index > size && index < 0){
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    // Help getRecursive.
    public T getRecursiveHelper(int i, IntNode nextDeque) {
        if (i == 0) {
            return nextDeque.item;
        } else {
            return getRecursiveHelper(i - 1, nextDeque.next);
        }
    }

    // Return an iterator.
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    // Define the LinkedListDequeIterator.
    private class LinkedListDequeIterator implements Iterator<T> {
        private IntNode wizNode;

        public LinkedListDequeIterator() {
            wizNode = sentinel.next;
        }

        public boolean hasNext() {
            return wizNode.next != sentinel;
        }

        public T next() {
                T returnItem = wizNode.item;
                wizNode = wizNode.next;
                return returnItem;
        }
    }

}
