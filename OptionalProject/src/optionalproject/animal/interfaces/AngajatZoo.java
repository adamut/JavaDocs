package optionalproject.animal.interfaces;

import optionalproject.animal.classes.Animal;

/**
 * Created by Cosmin.Adamut on 7/4/2017.
 */
public interface AngajatZoo {
    Integer valoareBonusPerAnimal = 50;
    void lucreaza(Animal animal);
    void calculeazaBonusSalarial();
}
