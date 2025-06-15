package sk.upjs.paz;

public class Snicek implements Comparable<Snicek> {

    int zaciatok;
    int koniec;
    int cena;


    public Snicek(int zaciatok, int trvanie, int cena) {
        this.zaciatok = zaciatok;
        this.koniec = zaciatok + trvanie;
        this.cena = cena;
    }

    @Override
    public int compareTo(Snicek o) {
        return Integer.compare(koniec, o.koniec);
    }
}
