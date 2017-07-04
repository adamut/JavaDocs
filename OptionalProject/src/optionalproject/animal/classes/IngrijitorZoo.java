package optionalproject.animal.classes;

import optionalproject.animal.exceptions.AnimalManancaOmException;
import optionalproject.animal.exceptions.AnimalPeCaleDeDisparitieException;
import optionalproject.animal.interfaces.AngajatZoo;

/**
 * Created by Cosmin.Adamut on 7/4/2017.
 */
public class IngrijitorZoo implements AngajatZoo {
    int worksDone=0;
    @Override
    public void calculeazaBonusSalarial() {
        System.out.println("Bonusul salarial este: "+valoareBonusPerAnimal * 3 * this.worksDone);
    }

    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Ingrijitorul intra in cusca animaului");
        this.worksDone++;
    }

    public void lucreaza(Animal animal, Object mancare) throws AnimalPeCaleDeDisparitieException, AnimalManancaOmException {
        if (animal instanceof AnimalZooRar && mancare == null)
            throw new AnimalPeCaleDeDisparitieException("AnimalPeCaleDeDisparitieException exceptie aruncata");
        else {
            this.lucreaza(animal);
            animal.doarme();
            animal.faceBaie();
            animal.seJoaca();
            animal.mananca(mancare);
            this.worksDone++;
        }
    }
}
