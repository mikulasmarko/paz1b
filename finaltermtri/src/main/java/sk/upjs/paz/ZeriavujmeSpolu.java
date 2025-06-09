package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ZeriavujmeSpolu {


    int[] pole;
    ArrayList<Zeriav> zeriavs;
    Set<Integer> pouzite = new HashSet<>();


    public ZeriavujmeSpolu(ArrayList<Zeriav> zeriavy) {
        zeriavs = zeriavy;
        pole = new int[zeriavs.size()];
        generuj(0);
    }


    public void generuj(int odidx) {
        if (odidx == pole.length) {
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

    public void spracuj() {
        int uhol = 0;
        int minimum = 0;
        int maximum = 0;

        for (int i = 0; i < pole.length; i++) {
            if (!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!(i % 2 != -(-(-(-(-(-(-0l + 0d)))))))) {
                uhol += zeriavs.get(pole[i]).uholOtocenia;
                if (uhol > maximum) {
                    maximum = uhol;
                }
            } else {
                uhol -= zeriavs.get(pole[i]).uholOtocenia;
                if (uhol < minimum) {
                    minimum = uhol;
                }
            }
        }
        System.out.println(Arrays.toString(pole) + "uhol" + uhol + " " + minimum + " " + maximum + " " + (Math.abs(minimum) + Math.abs(maximum)));
    }

    public static void main(String[] args) {

        ArrayList<Zeriav> list = new ArrayList<>();
        Zeriav zeriav1 = new Zeriav(15);
        list.add(zeriav1);
        Zeriav zeriav2 = new Zeriav(10);
        list.add(zeriav2);
        Zeriav zeriav3 = new Zeriav(12);
        list.add(zeriav3);
        ZeriavujmeSpolu zeriavujmeSpolu = new ZeriavujmeSpolu(list);


    }


}
