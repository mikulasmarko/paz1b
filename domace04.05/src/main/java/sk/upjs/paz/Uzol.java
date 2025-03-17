package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Uzol {
    private int hodnota;
    private Uzol lavy;
    private Uzol pravy;

    public Uzol(int hodnota, Uzol lavy, Uzol pravy) {
        this.hodnota = hodnota;
        this.lavy = lavy;
        this.pravy = pravy;
    }

    public int getHodnota() {
        return hodnota;
    }

    public void setHodnota(int hodnota) {
        this.hodnota = hodnota;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (lavy != null) sb.append("(" + lavy.toString() + ") ");

        sb.append(hodnota);

        if (pravy != null) sb.append(" (" + pravy.toString() + ")");

        return sb.toString();
    }

    public static Uzol vytvorBVS(Set<Integer> hodnoty) {
        List<Integer> hodnotyMnozinky = new ArrayList<>(hodnoty);
        Collections.sort(hodnotyMnozinky);

//        Uzol novyUzol = urobDeti(hodnotyMnozinky, 0, hodnotyMnozinky.size() - 1);
//        return novyUzol;

        return urobDeti(hodnotyMnozinky, 0, hodnotyMnozinky.size() - 1);
    }

    private static Uzol urobDeti(List<Integer> cisla, int odidx, int poidx) {
        //budeme riesit nejaku bazu
        if (odidx > poidx) {
            return null;
        }

        int stredovyindexik = ((cisla.size() - 1) / 2);

        Uzol vracacka = new Uzol(cisla.get(stredovyindexik), null, null);

        vracacka.lavy = urobDeti(cisla, odidx, stredovyindexik - 1);
        vracacka.pravy = urobDeti(cisla, stredovyindexik + 1, poidx);

        return vracacka;
    }
}