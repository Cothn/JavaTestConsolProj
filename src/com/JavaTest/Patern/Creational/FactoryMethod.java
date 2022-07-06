package com.JavaTest.Patern.Creational;


import java.awt.*;

interface Button {

    String render();
    String onClick();
}

class WindowsButton implements Button {

    @Override
    public String render() {
        return "WinButt";
    }

    @Override
    public String onClick() {
        return "Win event";
    }
}

class HTMLButton implements Button {

    @Override
    public String render() {
        return "HTMLButt";
    }

    @Override
    public String onClick() {
        return "HTML event";
    }
}


 abstract class Window {
    public int x1, y1, x2, y2;
    public Color color;
    public Button button;

    public Window (int x1, int y1, int x2, int y2, Color color){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
    abstract public String render();
    abstract public Button createButton();
}

class WindowsWindow extends Window {

    public WindowsWindow(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    @Override
    public String render() {
        return "WindowsWindow "+ button.render() + "\n";
    }
    @Override
    public Button createButton() {
        this.button = new WindowsButton();
        return this.button;
    }
}

class HTMLWindow extends Window{

    public HTMLWindow(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    @Override
    public String render() {
        return "HTMLWindow "+ button.render() + "\n";
    }
    @Override
    public Button createButton() {
        this.button = new HTMLButton();
        return this.button;
    }

}


public class FactoryMethod {


    public static void main(String[] args) {
        Window window = new WindowsWindow(0, 0, 1, 1, new Color(1,2,3) );
        window.createButton();
        System.out.println("Render\n" + window.render());
        System.out.println("Click\n" + window.button.onClick());

        window = new HTMLWindow(0, 0, 1, 1, new Color(10,232, 50) );
        window.createButton();
        System.out.println("Render\n" + window.render());
        System.out.println("Click\n" + window.button.onClick());
    }


}
