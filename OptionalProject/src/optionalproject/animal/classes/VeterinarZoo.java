package optionalproject.animal.classes;

import optionalproject.animal.interfaces.AngajatZoo;

/**
 * Created by Cosmin.Adamut on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo {
    int worksDone=0;
    @Override
    public void calculeazaBonusSalarial() {
        System.out.println("Bonusul salarial este: "+ valoareBonusPerAnimal * 2 * this.worksDone);
    }

    @Override
    public void lucreaza(Animal animal) {
        if (animal instanceof AnimalZooFeroce) {
            animal.faceBaie();
        } else {
            System.out.println("Veterinarul are grija de animal");
            this.worksDone++;
        }
    }
}
