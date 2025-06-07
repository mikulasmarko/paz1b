package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Collections;

public class Zapasik {
    int [] minutySledovania;

    public void maxPocetZapasik(ArrayList<Gol> goly) {
        Collections.sort(goly);
        int pocitadlo = 1;
        Gol aktualny = goly.get(0);
        System.out.println(aktualny);
        for (int i = 1; i <goly.size() ; i++) {
            if (aktualny.koniec<=goly.get(i).zaciatok) {
                aktualny = goly.get(i);
                pocitadlo++;
                System.out.println(goly.get(i));
            }
        }
        System.out.println(pocitadlo);
    }

    public static void main(String[] args) {
        Zapasik zapasik = new Zapasik();
        ArrayList<Gol> goly = new ArrayList<>();
        Gol jeden = new Gol(10,1,0);
        Gol dva = new Gol(11,1,0);
        Gol tri = new Gol(17,1,0);
        Gol styri = new Gol(47,2,0);
        Gol pat = new Gol(45,1,0);
        goly.add(jeden);
        goly.add(dva);
        goly.add(tri);
        goly.add(styri);
        goly.add(pat);
        zapasik.maxPocetZapasik(goly);


    }
}
