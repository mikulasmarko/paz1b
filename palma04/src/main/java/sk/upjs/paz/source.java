package sk.upjs.paz;

import sk.upjs.jpaz2.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class source {

    int pocetKariet;
    int cisloCoChcem;
    int[][] poleKarticky;
    int[] generovane;
    String s;

    public void vyriesUlohu() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = br.readLine().split(" ");
            pocetKariet = Integer.parseInt(strings[0]);
            cisloCoChcem = Integer.parseInt(strings[1]);
            poleKarticky = new int[pocetKariet][2];
            for (int i = 0; i < pocetKariet; i++) {
                String[] string = br.readLine().split(" ");
                poleKarticky[i][0] = Integer.parseInt(string[0]);
                poleKarticky[i][1] = Integer.parseInt(string[1]);
            }
            generovane = new int[pocetKariet];
            s = "nie";
            generuj(0);

            System.out.println(s);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void generuj(int idx) {
        if (idx == generovane.length) {
            vyries();
            return;
        }
        for (int i = 0; i < 2; i++) {
            generovane[idx] = i;
            generuj(idx + 1);
        }
    }

    private void vyries() {
//        System.out.println(Arrays.toString(generovane));
        int hodnota = 0;
        for (int i = 0; i < generovane.length; i++) {
            if (generovane[i] == 0) {
                hodnota += poleKarticky[i][0];
            }
            if (generovane[i] == 1) {
                hodnota += poleKarticky[i][1];
            }
        }
        if (hodnota == cisloCoChcem) {
            s = "ano";
        }

    }


    public static void main(String[] args) {
        source obj = new source();
        obj.vyriesUlohu();


    }
}