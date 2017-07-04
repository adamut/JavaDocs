package optionalproject.animal.classes;


import optionalproject.animal.exceptions.AnimalManancaOmException;
import optionalproject.animal.interfaces.AngajatZoo;

/**
 * Created by Cosmin.Adamut on 7/4/2017.
 */
public class AnimalZooRar extends Animal {
    private String nume;
    private String numeTaraDeOrigine;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumeTaraDeOrigine() {
        return numeTaraDeOrigine;
    }

    public void setNumeTaraDeOrigine(String numeTaraDeOrigine) {
        this.numeTaraDeOrigine = numeTaraDeOrigine;
    }

    public AnimalZooRar() {
    }

    public AnimalZooRar(String nume) {
        this.nume = nume;
    }

    public AnimalZooRar(String nume, String numeTaraDeOrigine) {
        this.nume = nume;
        this.numeTaraDeOrigine = numeTaraDeOrigine;
    }

    @Override
    void faceBaie() {
        System.out.println("AnimalulZooRar face baie");
    }

    @Override
    void mananca(Object o) throws AnimalManancaOmException{
        if (o instanceof AngajatZoo)
            throw new AnimalManancaOmException("AnimalManancaOmException a fost aruncata");
        else
            System.out.println("AnimalulZooRar mananca");
    }

    @Override
    void seJoaca() {
        System.out.println("AnimalulZooRar se joaca");
        super.doarme();
    }

}
