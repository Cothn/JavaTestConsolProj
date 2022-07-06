package com.JavaTest;

public class ManyThreadMain implements Runnable{
    PowerOfTwo powTwo ;
    ManyThreadMain(PowerOfTwo powTwo){
        this.powTwo = powTwo;
    }
    @Override
    public void run() {
        System.out.println( Thread.currentThread().getName()+ " started... ");
        for (int i=1; i<= 10; i++) {
            System.out.println( Thread.currentThread().getName() + " : " + powTwo.next() + "/"+ i);
        }
        System.out.println( Thread.currentThread().getName()+ " finished... ");
    }

    public static void main(String[] args){
        System.out.println("Main thread started...");
        PowerOfTwo powTwo = new PowerOfTwo() ;
        for (int i=1; i<= 10; i++) {
            Thread myThread = new Thread(new ManyThreadMain(powTwo),"Thread number "+i);
            myThread.start();
        }
        System.out.println("Main thread finished...");

    }
}
