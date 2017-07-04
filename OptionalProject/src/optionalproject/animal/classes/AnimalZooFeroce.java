package optionalproject.animal.classes;

import optionalproject.animal.exceptions.AnimalManancaOmException;
import optionalproject.animal.interfaces.AngajatZoo;

/**
 * Created by Cosmin.Adamut on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {

    @Override
    void faceBaie() {
        System.out.println("AnimalulZooFeroce face baie");
    }

    @Override
    void doarme() {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }

    @Override
    void mananca(Object o) throws AnimalManancaOmException {
        if (o instanceof AngajatZoo)
            throw new AnimalManancaOmException("AnimalManancaOmException a fost aruncata");
        else
            System.out.println("AnimalulZooRar mananca");
    }

    @Override
    void seJoaca() {
        System.out.println("AnimalulZooFeroce se joaca");
        super.doarme();
    }
}
