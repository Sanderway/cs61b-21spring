package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int realFirst;
    private int realLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
        realFirst = 0;
        realLast = 0;
    }

    // get the real index of nextFirst and nextLast.
    private void realIndex() {
        realFirst = nextFirst + 1;
        realLast = nextLast - 1;
        if (realFirst == items.length) {
            realFirst = 0;
        }
        if (realLast == 0) {
            realLast = items.length - 1;
        }
    }

    // Circulate the nextFirst and nextLast.
    private void circular() {
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        if (nextLast == items.length) {
            nextLast = 0;
        }
    }

    // Resizes the underlying array to the target capacity.
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        circular();
        realIndex();
        int oldSize = size;
        if (realFirst < realLast) {
            System.arraycopy(items, realFirst, newItems, 0, realLast - realFirst + 1);
        } else {
            System.arraycopy(items, realFirst, newItems, 0, items.length - realFirst);
            System.arraycopy(items, 0, newItems, items.length - realFirst, realLast + 1);
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = oldSize;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
        circular();
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast += 1;
        circular();
    }

    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        circular();
        realIndex();

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
        if (size < items.length * 0.25 && items.length > 16) {
            resize(items.length / 2);
        }
        circular();
        realIndex();
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
        if (size < items.length * 0.25 && items.length > 16) {
            resize(items.length / 2);
        }
        circular();
        realIndex();
        T removeItem = items[realLast];
        items[realLast] = null;
        nextLast = realLast;
        size -= 1;
        return removeItem;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index >= size || index < 0) {
            return null;
        }
        circular();
        realIndex();
        int itemIndex = realFirst + index;
        if (itemIndex >= items.length) {
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

        private void change() {
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
            if (iterateIndex == items.length) {
                iterateIndex = 0;
            }
            return returnItem;
        }
    }

    @Override
    // Returns whether the parameter o is equal to the Deque.
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        } else {
            if (this.size != ((ArrayDeque<?>) o).size) {
                return false;
            }
            Iterator otherIterator = ((ArrayDeque<?>) o).iterator();
            for (T t : this) {
                if (!t.equals(otherIterator.next())) {
                    return false;
                }
            }
        }
        return true;
    }
}
