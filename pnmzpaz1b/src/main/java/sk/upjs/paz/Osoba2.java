package sk.upjs.paz;

import java.util.ArrayList;
import java.util.List;

public class Osoba2 {
    private String meno;
    private List<Osoba> deti = new ArrayList<>();

    public Osoba2(String meno) {
        this.meno = meno;
    }

    public void pridajDieta(Osoba dieta) {
        deti.add(dieta);
    }

    public void vypisRodostrom() {
        vypisRodostrom(0);
    }

    public void vypisRodostrom(int level) {
        for (int i = 0; i < level; i++)
            System.out.print(' ');
        System.out.println(meno);
        for (Osoba dieta : deti)
            dieta.vypisRodostrom(level + 1);
    }

    public int pocetSekretarok() {
        return 0;
    }
}