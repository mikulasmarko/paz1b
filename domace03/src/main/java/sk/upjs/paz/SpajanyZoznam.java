package sk.upjs.paz;

public class SpajanyZoznam {

    /**
     * Sukromna trieda reprezentujuca jeden uzol spajaneho zoznamu
     */
    private static class Uzol {
        double hodnota;
        Uzol dalsi;
    }

    /**
     * Referencia na prvy uzol spajaneho zoznamu
     */
    private Uzol prvy = null;

    /**
     * Prida novu hodnotu na zaciatok spajaneho zoznamu
     *
     * @param hodnota pridavana hodnota
     */
    public void pridajNaZaciatok(double hodnota) {
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

    public void vlozUsporiadane(double hodnota) {
        // TODO implementovat

        //riesim ci nie je spajany zoznam prazdny alebo ci nie je dana hodnota mensia ako hodnota prveho
        //v takomto pripade pridavam na zaciatok
        //nebudem sa vyjadrovat k tomu ze tu je uz vytvorena metoda a ja som ju napisal znovu
        //na truc to tu necham xdd
        if (prvy == null || prvy.hodnota >= hodnota) {
            Uzol novy = new Uzol();
            novy.hodnota = hodnota;
            novy.dalsi = prvy;
            prvy = novy;
            return;
        }

        //vytvorim si pomocne uzly+ novy uzol ktory potrebujem vlozit
        Uzol aktualny = prvy;
        Uzol predtym = null;
        Uzol novy = new Uzol();

        // potrebujem prechadzat cez linkedlist az kym nieje dalsi v poradi null, O(n)
        //alebo ak mi hdonota dalsieho neprevysi vstup
        while (aktualny != null && aktualny.hodnota < hodnota) {
            predtym = aktualny;
            aktualny = aktualny.dalsi;
        }
        //ak prestane vyhovovat podmienka, viem ze som nasiel miesto kde ho chcem vlozit, tak ho vlozim
        predtym.dalsi = novy;
        novy.hodnota = hodnota;
        novy.dalsi = aktualny;
    }

    public void odstranZaporne() {
        // TODO implementovat

        //vytvorim si dva pomocne uzly
        Uzol aktualny = prvy;
        Uzol predtym = null;
        //potrebujem prejst celym linkedlistom O(n)
        while (aktualny != null) {
            //riesim ci hodnota nie je mensia ako nula(tj zaporna)
            if (aktualny.hodnota < 0) {
                //riesim ci nie je na prvom mieste zaporna hodnota
                if (predtym == null) {
                    //ak ano prvy nastavim na dalsi
                    prvy = aktualny.dalsi;
                    //ak nie tak posuniem predtym na dalsi
                } else {
                    predtym.dalsi = aktualny.dalsi;
                }
                //a tu sa posuniem dalej v linked liste
            } else {
                predtym = aktualny;
            }
            //samozrejme aj s tymto
            aktualny = aktualny.dalsi;
        }
    }


    public static void vlozNahodneHodnoty(SpajanyZoznam zoznam, int pocet) {
        for (int i = 0; i < pocet; i++) {
            zoznam.pridajNaZaciatok((int) (500 - Math.random() * 1000) / 10.0);
        }
    }

    public static void vlozNahodneUsporiadaneHodnoty(SpajanyZoznam zoznam, int pocet) {
        int hodnota = (int) (500 + Math.random() * 1000);
        for (int i = 0; i < pocet; i++) {
            zoznam.pridajNaZaciatok(hodnota / 10.0);
            hodnota -= (int) (Math.random() * 100);
        }
    }

    public static void main(String[] args) {
        // Demo
        SpajanyZoznam zoznam = new SpajanyZoznam();
        vlozNahodneHodnoty(zoznam, 20);
        System.out.println("Pred: " + zoznam);
        System.out.println("odstranZaporne()");
        zoznam.odstranZaporne();
        System.out.println("Po: " + zoznam);

        System.out.println();

        zoznam = new SpajanyZoznam();
        vlozNahodneUsporiadaneHodnoty(zoznam, 1);
        System.out.println("Pred: " + zoznam);
        System.out.println("vlozUsporiadane(...)");
        zoznam.vlozUsporiadane(10);
        System.out.println("Po: " + zoznam);
    }
}