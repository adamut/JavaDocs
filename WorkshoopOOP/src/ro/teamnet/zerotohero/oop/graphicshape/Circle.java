package ro.teamnet.zerotohero.oop.graphicshape;

import ro.teamnet.zerotohero.oop.exception.ExampleException;

/**
 * Created by Cosmin.Adamut on 7/4/2017.
 */
public class Circle extends Shape {
    int xPos;
    int yPos;
    int radius;

    public Circle() {
        this.xPos = 0;
        this.yPos = 0;
        this.radius = 0;
    }

    public Circle(int xPos) {
        this.xPos = xPos;
    }

    public Circle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius) throws ExampleException {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        if(radius < 0)
            throw new ExampleException("Aria este negativa");
    }

    @Override
    public double area() {
        return radius * radius * Math.PI;
    }

    @Override
    public String toString() {
        return "center = (" + xPos + "," + yPos + ")" + "and radius =" + radius ;
    }

    public void fillColour() {
        System.out.println(super.color);
    }

    public void fillColour(int col) {
        super.color = col;
        System.out.println("The circle color is now " + col);
    }

    public void fillColour(float sat) {
        super.saturation = sat;
        System.out.println("The circle color is now " + sat);
    }

}
