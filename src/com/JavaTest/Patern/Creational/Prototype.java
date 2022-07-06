package com.JavaTest.Patern.Creational;


import java.awt.*;




 abstract class Shape implements Cloneable{
    private int x, y;
    private Color color;


    public Shape (int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public String render(){
        return "from "+ x + " to " + y +" "+ color+" Shape";
    }

     @Override
     public Shape clone() {
         try {
             Shape clone = (Shape) super.clone();
             clone.x = x;
             clone.y = y;
             clone.color = color;
             return clone;
         } catch (CloneNotSupportedException e) {
             throw new AssertionError();
         }
     }
 }

class Rectangle extends Shape implements Cloneable {
     private int width, height;

    public Rectangle(int x, int y, Color color, int width, int height) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public String render() {

        return super.render() + " with width-"+width+" and height-"+ height+ " it is Rectangle";
    }

    @Override
    public Rectangle clone() {
        Rectangle clone = (Rectangle) super.clone();
        clone.width = width;
        clone.height = height;
        return clone;
    }
}

class Circle extends Shape implements Cloneable {
     private double radius;

    public Circle(int x, int y, Color color,  double radius) {
        super(x, y, color);
        this.radius = radius;
    }


    public String render() {
        return super.render() + " with radius-"+radius+ " it is a Circle";
    }

    @Override
    public Circle clone() {
        Circle clone = (Circle) super.clone();
        clone.radius = radius;
        return clone;
    }
}


public class Prototype {


    public static void main(String[] args) {
        Shape shape = new Circle(0, 0, new Color(192,111,131), 7.2 );
        Shape shapeClone = shape.clone();
        System.out.println("Shape\n" + shape.render());
        System.out.println("Clone shape\n" + shapeClone.render());

        shape = new Rectangle(1, 1, new Color(121,30,120), 7, 12);
        shapeClone = shape.clone();
        System.out.println("Shape\n" + shape.render());
        System.out.println("Clone shape\n" + shapeClone.render());
    }


}
