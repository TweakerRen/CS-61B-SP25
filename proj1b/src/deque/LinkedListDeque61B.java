package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

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
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        int start;

        public LinkedListDequeIterator() {
            start = 0;
        }

        public boolean hasNext() {
            return start < size;
        }

        public T next() {
            return get(start++);
        }
    }

    private class Node {
        T item;
        Node next;
        Node prev;

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        if (size == 0) {
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next.next.prev = sentinel.next;
        }
        size++;
    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new Node(sentinel.prev, x, sentinel);
        if (size == 0) {
            sentinel.next = sentinel.prev;
        } else {
            sentinel.prev.prev.next = sentinel.prev;
        }
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            returnList.add(p.item);
            p = p.next;
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
            T value = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return value;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T value = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return value;
        }
    }

    @Override
    public T get(int index) {
        Node p = sentinel;
        if (index >= size || index < 0) {
            return null;
        } else {
            for (int i = 0; i < index + 1; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            Node p = sentinel.next;
            return getRecursiveHelper(p, index);
        }
    }

    private T getRecursiveHelper(Node p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
