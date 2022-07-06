package com.JavaTest.Patern.Creational;




final class Singleton {
    // Поле обязательно должно быть объявлено volatile, чтобы двойная проверка
    // блокировки сработала как надо.
    private static volatile Singleton instance;

    public String value;

    private Singleton(String value) {
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        // Техника, которую мы здесь применяем называется «блокировка с двойной
        // проверкой» (Double-Checked Locking). Она применяется, чтобы
        // предотвратить создание нескольких объектов-одиночек, если метод будет
        // вызван из нескольких потоков одновременно.
        //
        // Хотя переменная `result` вполне оправданно кажется здесь лишней, она
        // помогает избежать подводных камней реализации DCL в Java.
        Singleton result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Singleton.class) {
            if (instance == null) {
                instance = new Singleton(value);
            }
            return instance;
        }
    }
}




public class MultiThreadSingleton {

    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("FOO");
            System.out.println(singleton.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("BAR");
            System.out.println(singleton.value);
        }
    }


}
