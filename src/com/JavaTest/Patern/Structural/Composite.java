package com.JavaTest.Patern.Structural;



import java.util.ArrayList;
import java.util.List;

interface Graphic  {

    void move(int x, int y);
    void draw();
}

class Dot implements Graphic {

    int x, y;

    public Dot(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void draw() {
        System.out.println("Dot in coord: "+ x +", " + y+ ".");

    }
}

class Circle extends Dot{
    double radius;


    public Circle(int x, int y, double r){
        super(x, y);
        radius = r;
    }

    @Override
    public void draw() {
        System.out.println("Circe with centre in coord: "+ x +", " + y+ ". And with radius "+ radius+".");
    }
}


class CompoundGraphic implements Graphic{
    private final List<Graphic> childrens;

    public CompoundGraphic() {
        this.childrens = new ArrayList<>();
    }

    public void add(Graphic children) {
        childrens.add(children);
    }

    public void remove(Graphic children) {
        childrens.remove(children);
    }
    @Override
    public void move(int x, int y) {
        for (Graphic children : childrens) {
            children.move(x,y);
        }
    }

    @Override
    public void draw() {
        System.out.println("Group draw start");
        for (Graphic children : childrens) {
            children.draw();
        }
        System.out.println("Group draw end");
    }
}

public class Composite {


    public static void main(String[] args) {
        CompoundGraphic all = new CompoundGraphic();
        all.add(new Dot(1,2));
        all.add(new Circle(2,1, 8.34));

        CompoundGraphic group = new CompoundGraphic();
        group.add(new Dot(3,8));
        CompoundGraphic group2 = new CompoundGraphic();
        group2.add(group);
        group2.add(new Circle(7,7, 12));
        group.add(new Dot(3,4));
        group.move(1,-1);

        group = new CompoundGraphic();
        group.add(new Dot(8,8));
        group2.move(100,100);


        group.add(group2);
        all.add(group);
        all.add(group2);
        group.remove(group2);


        System.out.println("draw group1");
        group.draw();
        System.out.println("draw group2");
        group2.draw();
        System.out.println("draw all");
        all.draw();
    }

}
