package com.JavaTest;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class PowerOfTwo {
    private final AtomicReference<BigInteger> current = new AtomicReference<>(null);

    BigInteger next() {
        BigInteger recent, next;
        do {
            recent = current.get();
            next = (recent == null) ? BigInteger.valueOf(1) : recent.shiftLeft(1);
        } while (!current.compareAndSet(recent, next));
        return next;
    }



    public static void main(String[] args) {

        PowerOfTwo powTwo = new PowerOfTwo();


        for (int i=0; i< 15; i++) {
            System.out.println(powTwo.next());
        }
    }


}
