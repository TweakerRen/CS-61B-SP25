package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    public void resizeHelper(int s) {
        T[] newArray = (T[]) new Object[s];
        int first = Math.floorMod(nextFirst + 1, items.length);
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(first + i) % items.length];
        }
        nextFirst = s - 1;
        nextLast = size;
        items = newArray;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) resizeHelper(size * 2);
        size++;
        items[nextFirst] = x;
        nextFirst--;
        nextFirst = Math.floorMod(nextFirst, items.length);
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) resizeHelper(size * 2);
        size++;
        items[nextLast] = x;
        nextLast++;
        nextLast = Math.floorMod(nextLast, items.length);
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            nextFirst++;
            size--;
            nextFirst = Math.floorMod(nextFirst, items.length);
            T returnValue = items[nextFirst];
            items[nextFirst] = null;
            if (items.length >= 16 && size <= items.length / 4)
                resizeHelper(items.length / 2);
            return returnValue;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            nextLast--;
            size--;
            nextLast = Math.floorMod(nextLast, items.length);
            T returnValue = items[nextLast];
            items[nextLast] = null;
            if (items.length >= 16 && size <= items.length / 4)
                resizeHelper(items.length / 2);
            return returnValue;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        int real = Math.floorMod(nextFirst + 1 + index, items.length);
        return items[real];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public int capacity() {
        return items.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int start;

        public ArrayDequeIterator() {
            start = 0;
        }

        public boolean hasNext() {
            return start < size;
        }

        public T next() {
            return get(start++);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque61B<?> oas) {
            if (oas.size() != this.size) {
                return false;
            }
            Iterator<T> it1 = this.iterator();
            Iterator<?> it2 = oas.iterator();
            while (it1.hasNext() && it2.hasNext()) {
                T x = it1.next();
                Object y = it2.next();
                if (!x.equals(y)) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
