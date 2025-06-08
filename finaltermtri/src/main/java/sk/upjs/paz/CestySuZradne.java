package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CestySuZradne {


    int[] pole;
    Set<Integer> pouzite = new HashSet<>();
    ArrayList<Tabor> tabors;


    public CestySuZradne(ArrayList<Tabor> tabory) {
        pole = new int[tabory.size()];
        tabors = tabory;
        generuj(0);
    }

    public void generuj(int odidx) {
        if (odidx == (pole.length)) {
            spracuj();
            return;
        }

        for (int i = 0; i < pole.length; i++) {
            if (!pouzite.contains(i)) {
                pole[odidx] = i;
                pouzite.add(i);
                generuj(odidx + 1);
                pouzite.remove(i);
            }

        }

    }

    private void spracuj() {

        int energia = tabors.get(pole[0]).pocetBizonov;
        for (int i = 1; i < pole.length; i++) {
            energia -= tabors.get(pole[i]).pocetDniNaPresun;
            if (energia < 0) {
                return;
            }
            energia += tabors.get(pole[i]).pocetBizonov;
        }
        System.out.println(Arrays.toString(pole) + " zvysna energia: " + energia);


    }


    public static void main(String[] args) {
        ArrayList<Tabor> tabory = new ArrayList<>();
        Tabor tabor1 = new Tabor(0, 2);
        tabory.add(tabor1);
        Tabor tabor2 = new Tabor(1, 3);
        tabory.add(tabor2);
        Tabor tabor3 = new Tabor(2, 3);
        tabory.add(tabor3);
        Tabor tabor4 = new Tabor(2, 3);
        tabory.add(tabor4);
        Tabor tabor5 = new Tabor(6, 2);
        tabory.add(tabor5);
        CestySuZradne cestySuZradne = new CestySuZradne(tabory);
    }
}
