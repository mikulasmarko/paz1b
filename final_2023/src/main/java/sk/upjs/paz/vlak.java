package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;

public class vlak {
    int[] vlak;
    int pocetCestujucich;
    int[] mnozinaCisel;
    int[] p;
    boolean[] pouzite;

    public vlak(int pocetMiestVoVlaku) {
        vlak = new int[pocetMiestVoVlaku];
        mnozinaCisel = new int[pocetMiestVoVlaku];
        pouzite = new boolean[pocetMiestVoVlaku];

    }

    public void usadenie(int pocetLudi) {
        p = new int[pocetLudi];
        pocetCestujucich = pocetLudi;
        generuj(0);
    }

    private void generuj(int odIdx) {
        if (p.length == odIdx) {
            System.out.println(Arrays.toString(p));
            spracuj();
            return;
        }

        for (int i = 0; i < pocetCestujucich; i++) {
            if (!pouzite[i]) {
                p[odIdx] = i;
                pouzite[i] = true;
                generuj(odIdx + 1);
                pouzite[i] = false;
            }
        }
    }

    public void generujVozen(int odIdx){

    }

    public void spracuj() {

    }

    public static void main(String[] args) {
        sk.upjs.paz.vlak v = new vlak(36);
        v.usadenie(6);
    }

}
