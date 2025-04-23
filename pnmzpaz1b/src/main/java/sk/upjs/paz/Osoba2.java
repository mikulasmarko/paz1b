package sk.upjs.paz;

import java.util.ArrayList;
import java.util.List;

public class Osoba2 {
    private String meno;
    private List<Osoba2> deti = new ArrayList<>();

    public Osoba2(String meno) {
        this.meno = meno;
    }

    public void pridajDieta(Osoba2 dieta) {
        deti.add(dieta);
    }

    public void vypisRodostrom() {
        vypisRodostrom(0);
    }

    public void vypisRodostrom(int level) {
        for (int i = 0; i < level; i++)
            System.out.print(' ');
        System.out.println(meno);
        for (Osoba2 dieta : deti)
            dieta.vypisRodostrom(level + 1);
    }

    public int pocetSekretarok() {
        int pocet = 0;
        boolean mamTamNiekohoSPOdriadenymi = false;
        for (Osoba2 osoba : deti) {

            for (Osoba2 osoba2 : deti) {
                if (!osoba2.deti.isEmpty()) {
                    mamTamNiekohoSPOdriadenymi = true;
                }
            }
            if (deti.size() >= 2 && mamTamNiekohoSPOdriadenymi)
                if (osoba.deti.isEmpty()) {
                    pocet++;
                }
            pocet = pocet + osoba.pocetSekretarok();
        }
        return pocet;
    }

    public static void main(String[] args) {
        Osoba2 osoba = new Osoba2("sefinko");
        Osoba2 osoba2 = new Osoba2("sekret1");
        Osoba2 osoba3 = new Osoba2("podsefinko");
        Osoba2 osoba4 = new Osoba2("veducko1");
        Osoba2 osoba5 = new Osoba2("veducko2");
        Osoba2 osoba6 = new Osoba2("veducko3");
//        Osoba2 osoba7 = new Osoba2("veducko4");


        osoba.pridajDieta(osoba2);
        osoba.pridajDieta(osoba3);
        osoba3.pridajDieta(osoba4);
        osoba3.pridajDieta(osoba5);
        osoba3.pridajDieta(osoba6);
//        osoba6.pridajDieta(osoba7);
        osoba.vypisRodostrom();

        System.out.println(osoba.pocetSekretarok());
    }
}