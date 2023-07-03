package com.JavaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



public class ListExceptions {

    private static ArrayList<Integer> initList, copyList, copyList2;

    public static void main(String[] args) {
        concurMod();
        unsupExp();
    }

    public static void concurMod () {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);


        Iterator<Integer> iter = list.iterator();

        list.add(3);
        iter.next();


        for (Integer el : list) {
            list.remove(1);
        }
    }
    public static void unsupExp() {
        List<Integer> list = Collections.emptyList();
        list.add(0);
    }




}
