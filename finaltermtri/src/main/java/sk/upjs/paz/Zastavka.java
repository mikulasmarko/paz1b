package sk.upjs.paz;

public class Zastavka implements Comparable<Zastavka> {

    int kdeJeZastavka;
    int platnostDoprava;
    int platnostDolava;
    int platnost;

    public Zastavka(int kdeJeZastavka, int platnost) {
        this.kdeJeZastavka = kdeJeZastavka;
        this.platnostDoprava = kdeJeZastavka + platnost;
        this.platnostDolava = kdeJeZastavka - platnost;
        this.platnost = platnost;
    }

    @Override
    public String toString() {
        return "Zastavka: " + kdeJeZastavka;
    }

    @Override
    public int compareTo(Zastavka o) {
        return Integer.compare(this.kdeJeZastavka, o.kdeJeZastavka);
    }
}
