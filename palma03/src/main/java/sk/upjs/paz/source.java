package sk.upjs.paz;

import sk.upjs.jpaz2.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class source {

    int pocetKartiet;
    int[] generovane;
    int[] karticky;
    int finalCislo;

    public void vyriesUlohu() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int pocetOpakovani = Integer.parseInt(br.readLine());

            for (int i = 0; i < pocetOpakovani; i++) {
                finalCislo = 0;
                pocetKartiet = Integer.parseInt(br.readLine());
                System.out.println(vyriesSadu(br.readLine()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int vyriesSadu(String s) {
        generovane = new int[pocetKartiet];
        karticky = new int[pocetKartiet];
        String[] sa = s.split(" ");
        for (int i = 0; i < sa.length; i++) {
            karticky[i] = Integer.parseInt(sa[i]);
        }
        generuj(0);
//        System.out.println(Arrays.toString(karticky));
        return finalCislo;
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
        int indexZpredu = 0;
        int indexVzadu = karticky.length - 1;
        int hodnota = 0;
        for (int i = 0; i < generovane.length; i++) {
            if (generovane[i] == 0) {
                hodnota += karticky[indexZpredu] * (i + 1);
                indexZpredu++;
            }
            if (generovane[i] == 1) {
                hodnota += karticky[indexVzadu] * (i + 1);
                indexVzadu--;
            }
        }
//        System.out.println(hodnota);
        if (hodnota > finalCislo) {
            finalCislo = hodnota;
        }
    }


    public static void main(String[] args) {
        source source = new source();
        source.vyriesUlohu();

    }
}