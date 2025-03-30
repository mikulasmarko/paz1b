package sk.upjs.paz;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Vyskrtavacka {

    private int[] rozdelenie;
    private Set<Integer> pole;
    private String cisloString;


    public Vyskrtavacka(int cislo) {
        rozdelenie = new int[Integer.toString(cislo).length()];
        pole = new HashSet<>();
        cisloString = Integer.toString(cislo);

    }


    private void generuj(int odidx) {
        if (odidx == rozdelenie.length) {
            spracuj();
            return;
        }

        for (int i = 0; i < 2; i++) {
            rozdelenie[odidx] = i;
            generuj(odidx + 1);
        }


    }


    private void spracuj() {
        System.out.println(Arrays.toString(rozdelenie));

        int counter = 0;
        String von = "";
        for (int i = 0; i < rozdelenie.length; i++) {
            if (rozdelenie[i] == 0) {
                counter++;
            }
            if (rozdelenie[i] == 1) {
                von = von + cisloString.charAt(i);
                counter--;
            }
        }
        if (Math.abs(counter) != rozdelenie.length) {
            pole.add(Integer.parseInt(von));
        }


    }

    public Set<Integer> generuj() {
        generuj(0);
        return pole;
    }


    public static void main(String[] args) {
        Vyskrtavacka v = new Vyskrtavacka(313);
        System.out.println(v.generuj());

    }
}