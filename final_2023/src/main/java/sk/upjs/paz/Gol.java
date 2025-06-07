package sk.upjs.paz;

public class Gol implements Comparable<Gol> {
    int zaciatok;
    int koniec;
    int zapas;

    public Gol(int kedyPadolGol, int typ,int ktoryZapas) {
       this.zapas = ktoryZapas;

        if (typ == 1) {
            // 1 =  cez zapas
            zaciatok = kedyPadolGol - 3;
            koniec = kedyPadolGol + 3;
        } else if (typ == 2) {
            //standardna situacia
            zaciatok = kedyPadolGol - 1;
            koniec = kedyPadolGol + 3;
        }

        if (zaciatok < 0) {
            zaciatok = 0;
        }

        if(zaciatok>=46){
            zaciatok=zaciatok+5;
            koniec = koniec+5;
        }

    }

    @Override
    public String toString() {
        return "Gol{" +
                "zaciatok=" + zaciatok +
                ", koniec=" + koniec +
                ", zapas=" + zapas +
                '}';
    }

    @Override
    public int compareTo(Gol o) {
        return Integer.compare(this.koniec,o.koniec);
    }
}