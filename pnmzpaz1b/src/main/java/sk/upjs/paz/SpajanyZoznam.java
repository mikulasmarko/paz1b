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
        if (prvy == null) {
            return;
        }       
        Uzol aktualny = prvy;

        if (prvy.hodnota == 0) {
            prvy = prvy.dalsi;
            return;
        }

        int posun = aktualny.hodnota;
        while (aktualny.dalsi != null) {
            for (int i = 0; i < posun - 1; i++) {
                aktualny = aktualny.dalsi;
            }
            if (aktualny.dalsi.hodnota == 0) {
                aktualny.dalsi = aktualny.dalsi.dalsi;
                break;
            } else {
                posun = aktualny.dalsi.hodnota;
                aktualny.dalsi = aktualny.dalsi.dalsi;
            }
        }
        prvy = prvy.dalsi;
    }


}