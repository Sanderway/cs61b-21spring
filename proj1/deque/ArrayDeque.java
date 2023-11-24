package deque;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (size < items.length) {
            items[nextFirst] = item;
            size += 1;
            nextFirst -= 1;
            if (nextFirst == -1) {
                nextFirst = items.length - 1;
            }
        }
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (size < items.length) {
            items[nextLast] = item;
            size += 1;
            nextLast += 1;
            if (nextLast == items.length) {
                nextLast = 0;
            }
        }
    }

    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        int realFirst = nextFirst + 1;
        int realLast = nextLast - 1;

        if (isEmpty()) {
            return;
        }

        if (realFirst == items.length) {
            realFirst = 0;
        }
        if (realLast == 0) {
            realLast = items.length - 1;
        }

        if (realFirst < realLast) {
            for (int i = realFirst; i < realLast; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println(items[realLast]);
        } else {
            for (int i = realFirst; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < realLast; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println(items[realLast]);
        }
    }

    // Removes and returns the item at the front of the deque.
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int realFirst = nextFirst + 1;
        if (realFirst == items.length) {
            realFirst = 0;
        }
        T removeItem = items[realFirst];
        items[realFirst] = null;
        nextFirst = realFirst;
        size -= 1;
        return removeItem;
    }

    // Removes and returns the item at the back of the deque.
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int realLast = nextLast - 1;
        if (realLast == -1) {
            realLast = items.length - 1;
        }
        T removeItem = items[realLast];
        items[realLast] = null;
        nextLast = realLast;
        size -= 1;
        return removeItem;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    public T get(int index){
        if (isEmpty()){
            return null;
        }
        if (index >= size || index < 0){
            return null;
        }
        int realFirst = nextFirst + 1;
        if (realFirst == items.length) {
            realFirst = 0;
        }
        int itemIndex = realFirst + index;
        if (itemIndex >= items.length){
            itemIndex -= items.length;
        }
        return items[itemIndex];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    // Define the ArrayDequeIterator.
    private class ArrayDequeIterator implements Iterator<T> {
        private int iterateIndex;
        private int realFirst = nextFirst + 1;
        private int realLast = nextLast - 1;
        private void change(){
            if (realFirst == items.length) {
                realFirst = 0;
            }
            if (realLast == -1) {
                realLast = items.length - 1;
            }
        }
        public ArrayDequeIterator() {
            change();
            iterateIndex = realFirst;
        }

        public boolean hasNext() {
            return iterateIndex != nextLast;
        }

        public T next() {
            T returnItem = items[iterateIndex];
            iterateIndex += 1;
            if (iterateIndex == items.length){
                iterateIndex = 0;
            }
            return returnItem;
        }
    }

    // Returns whether or not the parameter o is equal to the Deque.
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque otherDeque) {
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
}
