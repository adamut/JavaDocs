package ro.teamnet.zerotohero.oop.oop;
import ro.teamnet.zerotohero.oop.canvas.Canvas;
import ro.teamnet.zerotohero.oop.exception.ExampleException;
import ro.teamnet.zerotohero.oop.graphicshape.*;

/**
 * Created by Cosmin.Adamut on 7/4/2017.
 */
public class RunApp {
    public static void main(String[] args) {
        Circles circles = new Circles();
        Canvas canvas = new Canvas();
        System.out.println("The default circle area " + circles.getAreaPub());
        circles.getAreaDef();
        //System.out.println(canvas.paint); nu o sa mearga pentru ca avem metoda default
        Shape shape = new Circle(10);
        ShapeBehaviour shapeBehaviour= new Square(10);
        System.out.println("Shape area of circle is: "+shape.area());
        System.out.println("ShapeBehaviour of square is: "+ shapeBehaviour.area());

        try {
            Circle circ = new Circle(12,12,-1);
        } catch (ExampleException e) {
            System.out.println("Exceptia este: "+ e);
        }


        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);
        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

    }
}
