package com.JavaTest.Patern.Creational;


interface Chair {

    boolean hasLegs();
    boolean SitOn();
}

class ModernChair implements Chair {

    public boolean hasLegs(){
        return false;
    }
    public boolean SitOn(){
        return true;
    }
}

class VictorianChair implements Chair {

    public boolean hasLegs(){
        return true;
    }
    public boolean SitOn(){
        return true;
    }
}


interface FurnitureFactory {

   Chair CreateChair();

}

class VictorianFurnitureFactory implements  FurnitureFactory {

    public Chair CreateChair(){
        return new VictorianChair();
    }

}

class ModernFurnitureFactory implements  FurnitureFactory {

    public Chair CreateChair(){
        return new ModernChair();
    }

}


public class AbstractFactory {


    public static void main(String[] args) {
        FurnitureFactory factory = new ModernFurnitureFactory();
        Chair chair = factory.CreateChair();
        System.out.println("ModernChair has legs?\n" + chair.hasLegs());
        factory = new VictorianFurnitureFactory();
        chair = factory.CreateChair();
        System.out.println("VictorianChair has legs?\n" + chair.hasLegs());
    }


}
