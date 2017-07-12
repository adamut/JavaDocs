package ro.teamnet.zerotohero.reflection.demoobjects;

/**
 * Created by Cosmin.Adamut on 7/12/2017.
 */
public class Constructore {
    int ana;
    int are;
    int mere;
    int pere;
    int alune;

    public Constructore(int ana, int are, int mere, int pere) {
        this.ana = ana;
        this.are = are;
        this.mere = mere;
        this.pere = pere;
    }

    public Constructore(int ana, int are, int mere) {

        this.ana = ana;
        this.are = are;
        this.mere = mere;
    }

    public Constructore(int ana, int are) {

        this.ana = ana;
        this.are = are;
        System.out.println("Success");
    }

    public Constructore(int ana) {

        this.ana = ana;
    }
}
