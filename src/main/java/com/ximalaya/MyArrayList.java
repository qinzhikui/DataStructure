package com.ximalaya;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Created by qinzhikui on 20180915
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private AnyType[] theItems;
    private int size;
    private static int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        doClear();
    }

    private void doClear() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public AnyType get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    public AnyType set(int index, AnyType value) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType old = theItems[index];
        theItems[index] = value;
        return old;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity <= size()) {
            return;
        }

        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            theItems[i] = old[i];
        }
    }

    public boolean add(AnyType value) {
        add(size(), value);
        return true;
    }

    public void add(int index, AnyType value) {
        if (theItems.length == size) {
            ensureCapacity(size * 2 + 1);
        }
        for (int i = size - 1; i >= index; i--) {
            theItems[i + 1] = theItems[i];
        }
        theItems[index] = value;
        size++;
    }

    public AnyType remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType removeItem = theItems[index];

        for (int i = index; i < size - 1; i++) {
            theItems[i] = theItems[i + 1];
        }

        size--;
        return removeItem;
    }

    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    public class ArrayListIterator implements Iterator<AnyType> {

        private int current = 0;

        public boolean hasNext() {
            return current < size;
        }

        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        list.add(10);
        list.add(11);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(list.size);
    }
}