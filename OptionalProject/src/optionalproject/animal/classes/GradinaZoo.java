package optionalproject.animal.classes;

import optionalproject.animal.interfaces.AngajatZoo;

import java.util.Date;

/**
 * Created by Cosmin.Adamut on 7/4/2017.
 */
public final class GradinaZoo {
    private final String denumireGradinaZoo;
    private final Date dataDeschideriiGradinii;
    private final AnimalZooRar animalRar;
    private final IngrijitorZoo angajatulLunii;

    public String getDenumireGradinaZoo() {
        return denumireGradinaZoo;
    }

    public Date getDataDeschideriiGradinii() {
        return dataDeschideriiGradinii;
    }

    public AnimalZooRar getAnimalRar() {
        return animalRar;
    }

    public IngrijitorZoo getAngajatulLunii() {
        return angajatulLunii;
    }

    public GradinaZoo(String denumireGradinaZoo, Date dataDeschideriiGradinii, AnimalZooRar animalRar, IngrijitorZoo angajatulLunii) {
        this.denumireGradinaZoo = denumireGradinaZoo;
        this.dataDeschideriiGradinii = new Date(dataDeschideriiGradinii.getTime());
        AnimalZooRar azr = new AnimalZooRar(animalRar.getNume(),animalRar.getNumeTaraDeOrigine());
        this.animalRar = azr;
        IngrijitorZoo ingr = new IngrijitorZoo();
        this.angajatulLunii = ingr;
    }
}
