package com.JavaTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Main3 {
    //позиция, с которой удаляем
    private static int m = 0;
    //количество удаляемых элементов
    private static int n = 0;
    //количество элементов в списке
    private static final int size = 1000000;
    //основной список (для удаления вызовом remove() и его копия для удаления путём перезаписи)
    private static ArrayList<Integer> initList, copyList, copyList2;

    public static void main(String[] args) {
        unsupExp();
    }

    public static void concurMod (String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer integer : list) {
            list.remove(1);
        }
    }
    public static void unsupExp() {
        List<Integer> list = Collections.emptyList();
        list.add(0);
    }




}
