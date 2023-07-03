package com.JavaTest.myltiThreading;

import java.util.concurrent.atomic.AtomicReference;


public class NonBlockedStack_old<T>{
    private class Element {
        private T value;
        private Element previous;
        Element(T value){
            this.value = value;
        }
    }
    private final AtomicReference<Element> head = new AtomicReference<>(null);

    public NonBlockedStack_old<T> push (final T value){
        final Element current = new Element(value);

        Element oldHead;
        do {
            oldHead = head.get();
            current.previous = oldHead;
        } while (!head.compareAndSet(oldHead, current));

        return this;
    }

    public T pop (){
        Element result;
        Element previous;
        do {
            result = head.get();
            if (result == null) {
                return null;
            }
            previous = result.previous;
        } while (!head.compareAndSet(result, previous));

        return result.value;
    }


    public static void main(String[] args) {

        NonBlockedStack_old<Integer> stack = new NonBlockedStack_old<Integer>();

        stack.push(1);
        stack.push(2);
        System.out.println("First: " + stack.pop());
        System.out.println("Two: " + stack.pop());
        System.out.println("Three: " + stack.pop());

        stack.push(3);
        System.out.println("Four: " + stack.pop());
    }


}
