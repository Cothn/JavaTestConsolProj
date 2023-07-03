package com.JavaTest.myltiThreading;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


public class NonBlockingArrayList<T>{
    private volatile Object[] content = new Object[0];
    private final AtomicInteger size = new AtomicInteger(0);

    public NonBlockingArrayList<T> add (final T item){
        return add(size.get(), item);
    }

    public NonBlockingArrayList<T> add (final int index, final T item){
        if ((index < 0) || (index > size.get())) {
            throw new IllegalArgumentException();
        }
        if((index < size.get()) && item.equals(content[index])){
            return this;
        }
        final Object[] renewed;
        if (size.get() >= content.length){
            renewed = Arrays.copyOf(content,(int)(Math.floor(size.get()*1.5)+1));
            content = renewed;
        }
        if(index < size.get()){
            if (size.get() + 1 - (index + 1) >= 0)
                System.arraycopy(content, index, content, index + 1, size.get() - index);
        }
        content[index] = item;
        size.getAndIncrement();
        return this;
    }

    NonBlockingArrayList<T> remove(int index) {
        if ((index < 0) || (index >= size.get())) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(content, index+1, content, index , size.get() - (index+1));
        size.getAndDecrement();
        return this;
    }

    T get(int index) {
        if ((index < 0) || (index >= size.get())) {
            throw new IllegalArgumentException();
        }
        return (T) content[index];
    }

    int size() {
        return size.get();
    }


    public static void main(String[] args) {

        NonBlockingArrayList<String> array = new NonBlockingArrayList<>();

        array.add("Один");
        array.add("Два");
        System.out.println("Size: " + array.size());
        System.out.println("First: " + array.get(0));
        System.out.println("Two: " + array.get(1));

        array.add(1,"Три");
        array.add("Четыре");
        System.out.println("Size: " + array.size());
        System.out.println("First: " + array.get(0));
        System.out.println("Two: " + array.get(1));
        System.out.println("Three: " + array.get(2));
        System.out.println("Four: " + array.get(3));

        array.remove(2);
        System.out.println("Size: " + array.size());
        System.out.println("First: " + array.get(0));
        System.out.println("Two: " + array.get(1));
        System.out.println("Three: " + array.get(2));

    }


}
