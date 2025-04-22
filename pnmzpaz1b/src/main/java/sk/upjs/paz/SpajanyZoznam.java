package sk.upjs.paz;

import java.util.Set;

public class SpajanyZoznam {

    private static class Uzol {
        int hodnota;
        Uzol dalsi;
    }

    private Uzol prvy = null;

    public void pridajNaZaciatok(int hodnota) {
        Uzol pridavany = new Uzol();
        pridavany.hodnota = hodnota;
        pridavany.dalsi = prvy;
        prvy = pridavany;
    }

    @Override
    public String toString() {
        String vysledok = "[";
        Uzol aktualny = prvy;
        while (aktualny != null) {
            if (aktualny != prvy) vysledok += ", ";

            vysledok += aktualny.hodnota;
            aktualny = aktualny.dalsi;
        }
        return vysledok + "]";
    }


    public void priemer() {
        Uzol aktualny = prvy;
        int counter = 0;
        int hodnota = 0;

        Uzol pomocny = new Uzol();
        Uzol pomocnyAktualny = pomocny;

        if (aktualny == null)
            return;

        while (aktualny != null) {

            counter++;
            hodnota += aktualny.hodnota;
            aktualny = aktualny.dalsi;

            if (counter == 3) {
                pomocnyAktualny.hodnota = hodnota / counter;
                if (aktualny == null) {
                    break;
                }
                pomocnyAktualny.dalsi = new Uzol();
                pomocnyAktualny = pomocnyAktualny.dalsi;
                counter = 0;
                hodnota = 0;
            }
        }
        if (counter != 0) {
            pomocnyAktualny.hodnota = hodnota / counter;
        }
        prvy = pomocny;
    }


    public void odstranFake() {
        Uzol aktualny = prvy;
        Uzol predosly = aktualny;

        if (aktualny.hodnota == 0) {
            prvy = prvy.dalsi;
            return;
        }

        int hodnotka = prvy.hodnota;
        while (hodnotka == 1) {
            aktualny = aktualny.dalsi;
            hodnotka = aktualny.hodnota;
        }
        prvy = aktualny.dalsi;

        while (aktualny.dalsi != null) {
            int skoky = (int) aktualny.hodnota;
            if (skoky == 0) {
                predosly.dalsi = aktualny.dalsi;
                return;
            }
            for (int i = 0; i < skoky; i++) {
                predosly = aktualny;
                aktualny = aktualny.dalsi;
            }
            if (aktualny != null) {
                predosly.dalsi = aktualny.dalsi;
            }
        }
    }


}