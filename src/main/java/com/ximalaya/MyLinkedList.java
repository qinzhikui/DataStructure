package com.ximalaya;

/*
 * Created by qinzhikui on 20180915
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarket;
    private Node<AnyType> endMarket;

    private static class Node<AnyType> {

        public Node(AnyType value, Node<AnyType> prev, Node<AnyType> next) {
            this.data = value;
            this.prev = prev;
            this.next = next;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    public void doClear() {
        beginMarket = new Node(null, null, null);
        endMarket = new Node(null, beginMarket, null);

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public boolean add(AnyType x) {
        add(theSize, x);
        return true;
    }

    public void add(int index, AnyType x) {
        addBefore(getNode(index, 0, theSize), x);
    }

    public void addBefore(Node<AnyType> node, AnyType x) {
        Node<AnyType> newNode = new Node(x, node, node.next);
        node.next.prev = newNode;
        node.next = newNode;

        theSize++;
        modCount++;
    }

    public Node<AnyType> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    public Node<AnyType> getNode(int index, int lower, int upper) {
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }

        Node<AnyType> p;
        if (index < size() / 2) {
            p = beginMarket.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = endMarket;
            for (int i = size(); i > index; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    public AnyType get(int index) {
        return getNode(index).data;
    }

    public AnyType set(int index, AnyType newValue) {
        Node<AnyType> node = getNode(index);
        AnyType old = node.data;
        node.data = newValue;

        return old;
    }

    public AnyType remove(int index) {
        return remove(getNode(index));
    }

    private AnyType remove(Node<AnyType> node) {
        AnyType old = node.data;

        node.prev.next = node.next;
        node.next.prev = node.prev;
        theSize--;
        modCount++;
        return old;
    }

    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<AnyType> {

        private Node<AnyType> current = beginMarket.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarket;
        }

        public AnyType next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();

        for (int i = 0; i < 10; i++) {
            myLinkedList.add(i);
        }
    }
}