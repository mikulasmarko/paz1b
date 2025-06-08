package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Suroviny {

    int[] maximalnePocty;
    int[] p;
    List<Integer> surovinyKopia;
    List<Integer> suroviny;
    List<Recepty> receptiky;


    public Suroviny(ArrayList<Recepty> recepty, List<Integer> suroviny) {
        maximalnePocty = new int[recepty.size()];
        pomocnaMetoda(recepty, suroviny);
        this.suroviny = suroviny;
        receptiky = recepty;
        p = new int[recepty.size()];
//        System.out.println(Arrays.toString(maximalnePocty));
        generuj(0);


    }


    public void generuj(int odidx) {
        if (odidx == p.length) {
            spracuj();
            return;
        }

        for (int i = 0; i <= maximalnePocty[odidx]; i++) {
            p[odidx] = i;
            generuj(odidx + 1);

        }


    }

    public void spracuj() {
//        System.out.println(Arrays.toString(p));
        surovinyKopia = new ArrayList<>();
        surovinyKopia.addAll(suroviny);
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < surovinyKopia.size(); j++) {
                surovinyKopia.set(j, surovinyKopia.get(j) - ((receptiky.get(i).recept.get(j) * p[i])));
            }
        }

        for (Integer integer : surovinyKopia) {
            if (integer != 0) {
//                System.out.println("nejde to spravit touto kombinaciou");
                return;
            }
        }
        System.out.println(Arrays.toString(p));

    }


    public void pomocnaMetoda(ArrayList<Recepty> r, List<Integer> surovina) {

        int counter = 0;
        for (Recepty recepty : r) {
            int minimum = Integer.MAX_VALUE;
            for (int i = 0; i < surovina.size(); i++) {
                if (recepty.recept.get(i) != 0)
                    minimum = Math.min(minimum, surovina.get(i) / recepty.recept.get(i));
            }
            maximalnePocty[counter] = minimum;
            counter++;
        }
    }

    public static void main(String[] args) {
        Recepty recept1 = new Recepty(List.of(1, 0, 0));
        Recepty recept2 = new Recepty(List.of(0, 1, 0));
        Recepty recept3 = new Recepty(List.of(0, 0, 1));
        Recepty recept4 = new Recepty(List.of(3, 3, 3));
        ArrayList<Recepty> recepty = new ArrayList<>();
        recepty.add(recept1);
        recepty.add(recept2);
        recepty.add(recept3);
        recepty.add(recept4);


        Suroviny s = new Suroviny(recepty, List.of(7, 8, 4));
    }


}
