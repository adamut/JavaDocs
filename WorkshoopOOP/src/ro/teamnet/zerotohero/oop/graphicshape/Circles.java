package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Cosmin.Adamut on 7/4/2017.
 */
public class Circles {

   public double getAreaPub(){
        Circle circle = new Circle();
        return circle.area();
    }
    public void getAreaDef(){
       Circle circle = new Circle();
       circle.fillColour();
       circle.fillColour(2);
       circle.fillColour((float) 3.4);
    }
}
