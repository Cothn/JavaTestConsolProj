package com.JavaTest.myltiThreading;

import java.util.concurrent.Semaphore;


public class SemaphoreStack<T>{
    private class Element {
        private T value;
        private Element previous = null;
        Element(){}
        Element(T value, Element head){
            this.value = value;
            this.previous = head;
        }
    }
    private final Semaphore semaphore = new Semaphore(1);
    private  Element head = new Element();

    public SemaphoreStack<T> push (final T value){
        semaphore.acquireUninterruptibly();
        try {
            head = new Element(value, head);
        }
        finally {
            semaphore.release();
        }
        return this;
    }

    public T pop (){
        T result = null;
        semaphore.acquireUninterruptibly();
        try {
            if (head != null) {
                result = head.value;
                head = head.previous;
            }
        }
        finally {
            semaphore.release();
        }
        return result;
    }


    public static void main(String[] args) {

        SemaphoreStack<Integer> stack = new SemaphoreStack<Integer>();

        stack.push(1);
        stack.push(2);
        System.out.println("First: " + stack.pop());
        System.out.println("Two: " + stack.pop());
        System.out.println("Three: " + stack.pop());

        stack.push(3);
        System.out.println("Four: " + stack.pop());
    }


}
