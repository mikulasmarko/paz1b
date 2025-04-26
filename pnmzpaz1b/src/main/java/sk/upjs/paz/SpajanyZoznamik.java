package sk.upjs.paz;

public class SpajanyZoznamik {

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
            if (aktualny != prvy)
                vysledok += ", ";

            vysledok += aktualny.hodnota;
            aktualny = aktualny.dalsi;
        }
        return vysledok + "]";
    }


    public void seasonPlan(int hardThreshold) {

        Uzol aktualny = prvy;
        if (prvy == null) {
            return;
        }
        if (prvy.hodnota >= hardThreshold) {
            Uzol pomocny = new Uzol();
            pomocny.hodnota = prvy.hodnota * 2;
            pomocny.dalsi = prvy;
            prvy = pomocny;
        }

        while (aktualny.dalsi != null) {
            Uzol predosly = new Uzol();
            predosly = aktualny;
            aktualny = aktualny.dalsi;
            if (aktualny.hodnota >= hardThreshold) {
                Uzol rozcvicka = new Uzol();
                rozcvicka.hodnota = aktualny.hodnota * 2;
                predosly.dalsi = rozcvicka;
                rozcvicka.dalsi = aktualny;
            }
        }
    }


    public static void main(String[] args) {
        SpajanyZoznamik spajanyZoznamik = new SpajanyZoznamik();
        spajanyZoznamik.pridajNaZaciatok(20);
        spajanyZoznamik.pridajNaZaciatok(10);
        spajanyZoznamik.pridajNaZaciatok(15);
        spajanyZoznamik.pridajNaZaciatok(10);
        spajanyZoznamik.pridajNaZaciatok(5);
        spajanyZoznamik.pridajNaZaciatok(15);

        spajanyZoznamik.seasonPlan(14);
        System.out.println(spajanyZoznamik.toString());
    }

}
