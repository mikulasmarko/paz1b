package sk.upjs.paz;

import java.util.Arrays;

public class Vzpieranie {


    private int[] strany;
    private int[] vahy;
    private int maxVaha;

    public int maximalnaCinka(int[] zavazia) {
        vahy = zavazia;
        strany = new int[zavazia.length];
        maxVaha = 0;
        generuj();
        return maxVaha;
    }


    public void generuj() {
        generuj(0);
    }

    public void generuj(int idx) {


        if (idx == strany.length) {
            System.out.println(Arrays.toString(strany));
            spracuj();
            return;
        }
        for (int i = 0; i < 3; i++) {
            strany[idx] = i;
            generuj(idx + 1);
        }

    }

    public void spracuj() {
        int pravaStrana = 0;
        int lavaStrana = 0;
        for (int i = 0; i < strany.length; i++) {
            if (strany[i] == 0) {
                pravaStrana += vahy[i];
            } else if (strany[i] == 1) {
                lavaStrana += vahy[i];
            }
        }
        if (pravaStrana == lavaStrana) {
            maxVaha = Math.max(maxVaha, pravaStrana + lavaStrana + 20);
        }

    }

    public static void main(String[] args) {
        Vzpieranie v = new Vzpieranie();
        int[] cinky = {50, 20, 30, 10, 7, 12, 15, 15};
        System.out.println(v.maximalnaCinka(cinky));
    }
}
