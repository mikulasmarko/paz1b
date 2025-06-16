package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Zivot {

    ArrayList<Snicek> sny;
    int[] dynamicke;

    public Zivot(ArrayList<Snicek> sniceks) {
        sny = sniceks;
        //vytvorim si dynamicke policko o velkosti od 0rokov do max rokov
        dynamicke = new int[sny.get(sny.size() - 1).koniec + 1];
        dynamicke[0] = 0;
        spravujZivot();
        System.out.println(dynamicke[dynamicke.length - 1]);
    }


    public void spravujZivot() {
        for (int i = 1; i < dynamicke.length; i++) {
            dynamicke[i] = dynamicke[i - 1];
            for (Snicek sen : sny) {
                if (sen.koniec == i) {
                    dynamicke[i] = Math.max(dynamicke[sen.zaciatok] + sen.cena, dynamicke[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Snicek s1 = new Snicek(19, 5, 10_000);
        Snicek s2 = new Snicek(24, 1, 4_000);
        Snicek s3 = new Snicek(22, 2, 2_000);
        Snicek s4 = new Snicek(20, 2, 6_000);
        ArrayList<Snicek> snicky = new ArrayList<>();
        snicky.add(s1);
        snicky.add(s2);
        snicky.add(s3);
        snicky.add(s4);
        Collections.sort(snicky);
        System.out.println(snicky);
        Zivot z = new Zivot(snicky);
    }
}
