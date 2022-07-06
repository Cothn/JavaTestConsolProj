package com.JavaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;



public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxEntries;

    public LRUCache(int initialCapacity, int maxEntries) {
        super(initialCapacity, 0.85f, true);
        this.maxEntries = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxEntries;
    }

    //позиция, с которой удаляем
    private static int m = 0;
    //количество удаляемых элементов
    private static int n = 0;
    //количество элементов в списке
    private static final int size = 1000000;
    //основной список (для удаления вызовом remove() и его копия для удаления путём перезаписи)
    private static ArrayList<Integer> initList, copyList, copyList2;

    public static void main(String[] args) {

        LRUCache<Integer, String> cache = new LRUCache<Integer, String> (2, 2);

        cache.put(1, "один");
        cache.put(2, "два");
        System.out.println("First: " + cache);

        cache.put(3, "три");
        System.out.println("Two: " + cache);

        cache.get(2);
        cache.put(4, "четыре");
        System.out.println("Three: " + cache);

    }



}
