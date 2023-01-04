package com.JavaTest.myltiThreading;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

public class NonBlockedStack <T>
{
    private class StackNode {
        public T stackElement;
        public StackNode next;
        public StackNode(T stackElement){
            this.stackElement = stackElement;
        }
    }
    private volatile AtomicReference<StackNode> stackHead = new AtomicReference<>(null);

    public T pop(){

        try {
            StackNode oldHead;
            do {
                oldHead = stackHead.get();
                if (oldHead == null) {
                    return null;
                }
            } while (!stackHead.compareAndSet(oldHead, oldHead.next));
            return oldHead.stackElement;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean push(T newEl){
        try {
            final StackNode newNode = new StackNode(newEl);
            StackNode oldHead;
            do {
                oldHead = stackHead.get();
                newNode.next = oldHead;
            } while (!stackHead.compareAndSet(oldHead, newNode));
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        NonBlockedStack<Integer> stack = new NonBlockedStack<>();

        CyclicBarrier barrier4 = new CyclicBarrier(4);


        Thread thread1 = new Thread(() -> {
            for(int i=0; i<=99; i++){
                stack.push(100+i);
            }
            try {
                barrier4.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i=0; i<=99; i++){
                stack.push(200+i);
            }
            try {
                barrier4.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            for(int i=0; i<=99; i++){
                stack.push(300+i);
            }
            try {
                barrier4.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        barrier4.await();

        Integer stackVal = stack.pop();
        int count = 1;
        while(stackVal != null){
            System.out.println("LiningPop-ParallelPush: " + stackVal);
            stackVal = stack.pop();
            count++;
        }
        System.out.println("Count of stack elements: " + count);
        for(int i =1; i<400; i++){
            stack.push(i);
        }

        thread1 = new Thread(() -> {
            Integer stackVal1 = stack.pop();
            while(stackVal1 != null){
                System.out.println("Thread1-ParallelPop-LiningPush: " + stackVal1);
                stackVal1 = stack.pop();
            }
            try {
                barrier4.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread2 = new Thread(() -> {
            Integer stackVal2 = stack.pop();
            while(stackVal2 != null){
                System.out.println("Thread2-ParallelPop-LiningPush: " + stackVal2);
                stackVal2 = stack.pop();
            }
            try {
                barrier4.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread3 = new Thread(() -> {
            Integer stackVal3 = stack.pop();
            while(stackVal3 != null){
                System.out.println("Thread3-ParallelPop-LiningPush: " + stackVal3);
                stackVal3 = stack.pop();
            }
            try {
                barrier4.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        barrier4.await();
        System.out.println("It is all.");
    }
}
