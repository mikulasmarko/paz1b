package sk.upjs.paz;

public class Program {
    private static class Vystupenie {
        int dlzka;
        Vystupenie dalsie;
    }

    private Vystupenie prve = null;

    public void pridajNaZaciatok(int hodnota) {
        Vystupenie pridavane = new Vystupenie();
        pridavane.dlzka = hodnota;
        pridavane.dalsie = prve;
        prve = pridavane;
    }

    @Override
    public String toString() {
        String vysledok = "[";
        Vystupenie aktualne = prve;
        while (aktualne != null) {
            if (aktualne != prve)
                vysledok += ", ";
            vysledok += aktualne.dlzka;
            aktualne = aktualne.dalsie;
        }
        return vysledok + "]";
    }

    public void pridajUvedenie(int uvod, int hranica) {
        Vystupenie predosle = null;
        if (prve == null) {
            return;
        }
        Vystupenie aktualne = prve;

        if (prve.dlzka >= hranica) {
            Vystupenie uvodka = new Vystupenie();
            uvodka.dlzka = uvod;
            uvodka.dalsie = prve;
            prve = uvodka;
        }

        while (aktualne.dalsie != null) {
            predosle = aktualne;
            aktualne = aktualne.dalsie;
            if (aktualne.dlzka >= hranica) {
                Vystupenie uvodka = new Vystupenie();
                uvodka.dlzka = uvod;
                uvodka.dalsie = aktualne;
                predosle.dalsie = uvodka;
            }
        }
    }

    public static void main(String[] args) {
        Program p = new Program();
        p.pridajNaZaciatok(30);
        p.pridajNaZaciatok(40);
        p.pridajNaZaciatok(40);
        p.pridajNaZaciatok(20);
        p.pridajNaZaciatok(20);
        p.pridajUvedenie(1, 30);
        System.out.println(p.toString());
    }
}
