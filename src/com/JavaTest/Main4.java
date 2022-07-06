package com.JavaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Main4 {
    //позиция, с которой удаляем
    private static int m = 0;
    //количество удаляемых элементов
    private static int n = 0;
    //количество элементов в списке
    private static final int size = 1000000;
    //основной список (для удаления вызовом remove() и его копия для удаления путём перезаписи)
    private static ArrayList<Integer> initList, copyList, copyList2;

    public static void main(String[] args) {

        ArrayList<Integer> first = new ArrayList<Integer>();
        ArrayList<Integer> two = new ArrayList<Integer>();
        first.add(1);
        first.add(2);
        first.add(3);
        first.add(4);
        two.add(5);
        two.add(7);
        two.add(2);
        two.add(3);

        Collection result = symmetricDifference(first, two);
        System.out.println("Result: " + result);

    }

    static <T> Collection<T> symmetricDifference(Collection<T> a, Collection<T> b){
        // Объединяем коллекции.
        Collection<T> result= new ArrayList<T>(a);
        result.addAll(b);

        // Получаем пересечение коллекций.
        Collection<T> intersection= new ArrayList<T>(a);
        intersection.retainAll(b);

        // Удаляем элементы, расположенные в обоих коллекциях.
        result.removeAll(intersection);

        return result;
    }


}
