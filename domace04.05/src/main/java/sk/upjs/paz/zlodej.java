package org.example;

import java.util.Arrays;

public class zlodej {

    private int[] predmety;
    private int[] p;
    private int najmensiRozdiel = Integer.MAX_VALUE;
    private int[] najRozdelenie;


    public zlodej(int[] pole) {
        predmety = pole.clone();
        p = new int[predmety.length];
    }

    public void spracuj() {

        int zlodej1 = 0;
        int zlodej2 = 0;

        for (int i = 0; i < p.length; i++) {
            if (p[i] == 0) {
                zlodej1 += predmety[i];
            } else {
                zlodej2 += predmety[i];
            }
        }
        if (Math.abs(zlodej1 - zlodej2) < najmensiRozdiel) {
            najmensiRozdiel = Math.abs(zlodej1 - zlodej2);
            najRozdelenie = p.clone();
        }
        System.out.println(Arrays.toString(p)+zlodej1 + " " + zlodej2);
        System.out.println();

    }

    public void generuj() {
        generuj(0);
        System.out.println("naj bolo toto: "+ Arrays.toString(najRozdelenie));
    }

    private void generuj(int odidx) {
        if (odidx == predmety.length) {
            spracuj();
            return;
        }

        for (int i = 0; i < 2; i++) {
            p[odidx] = i;
            generuj(odidx + 1);
        }
    }


    public static void main(String[] args) {
        int[] pole = new int[5];
        for (int i = 0; i < pole.length; i++) {
            pole[i] = (int) (Math.random() * 8) * 2;
        }
        zlodej zlodej = new zlodej(pole);
        zlodej.generuj();
    }
}
