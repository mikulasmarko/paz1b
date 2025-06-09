package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class heroesOnTheBoard {
    int[] p;
    int sila = Integer.MIN_VALUE;
    ArrayList<Vojacicek> vstup;
    int vahaLode;

    public void urobLodicku(ArrayList<Vojacicek> vojacicekovci, int vahicicka) {
        vstup = vojacicekovci;
        p = new int[vojacicekovci.size()];
        vahaLode = vahicicka;
        generuj(0);
        System.out.println(sila);
    }

    public void generuj(int odIdx) {
        if (odIdx == p.length) {
//            spracuj();
            System.out.println(Arrays.toString(p));
            int aktualnaSila = 0;
            for (int i = 0; i < p.length; i++) {
                if (p[i] == 1) {
                    aktualnaSila += vstup.get(i).sila;
                }
            }
            sila = Math.max(aktualnaSila, sila);
            return;

        }
        int aktualnaVaha = 0;
        for (int i = 0; i <= 1; i++) {
            p[odIdx] = i;
            if (p[odIdx] == 1) {
                aktualnaVaha += vstup.get(odIdx).vaha;
                if (aktualnaVaha > vahaLode) {
                    aktualnaVaha -= vstup.get(odIdx).vaha;
                    return;
                }
            }
            generuj(odIdx + 1);
            if (p[odIdx] == 1) {
                aktualnaVaha -= vstup.get(odIdx).vaha;
            }
        }
    }

    public void spracuj() {
        System.out.println(Arrays.toString(p));
        int aktualnaVaha = 0;
        int aktualnaSila = 0;

        for (int i = 0; i < p.length; i++) {
            if (p[i] == 1) {
                aktualnaVaha += vstup.get(i).vaha;
                aktualnaSila += vstup.get(i).sila;
                if (aktualnaVaha > vahaLode) {
                    return;
                }
            }
        }
        sila = Math.max(sila, aktualnaSila);
    }

    public static void main(String[] args) {
        Vojacicek vojacicek1 = new Vojacicek(6, 200);
        Vojacicek vojacicek2 = new Vojacicek(10, 300);
        Vojacicek vojacicek3 = new Vojacicek(1, 100);

        ArrayList<Vojacicek> vojaci = new ArrayList<>();
        vojaci.add(vojacicek1);
        vojaci.add(vojacicek2);
        vojaci.add(vojacicek3);

        heroesOnTheBoard hotb = new heroesOnTheBoard();
        hotb.urobLodicku(vojaci, 9);
    }
}