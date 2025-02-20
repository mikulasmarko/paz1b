package sk.upjs.paz;

import java.util.Arrays;

public class BinarySearch {

    public static boolean jeUsporiadane(int[] p) {
        for (int i = 0; i < p.length - 1; i++) {
            if (p[i] > p[i + 1])
                return false;
        }
        return true;
    }

    public static int binarneHladajIndex(int[] p, int hodnota) {
        int zaciatok = 0;
        int koniec = p.length - 1;
        while (zaciatok <= koniec) {
            int stredIdx = (zaciatok + koniec) / 2;
            if (p[stredIdx] == hodnota) {
                return stredIdx;
            }
            if (p[stredIdx] < hodnota) {
                // hladame cislo od stredIdx do konca
                zaciatok = stredIdx + 1;
            } else {
                // hladame od zaciatku po stredIdx
                koniec = stredIdx - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] p = new int[20];
        for (int i = 0; i < p.length; i++) {
            p[i] = (int) (Math.random() * p.length * 2);
        }
//        System.out.println(Arrays.toString(p));

        int[] p2 = {2, 4, 6, 7, 10};
//        System.out.println(jeUsporiadane(p2));
        System.out.println(binarneHladajIndex(p2, 7));


        BubbleSort bublinky = new BubbleSort();
        System.out.println(Arrays.toString(p));
        bublinky.utried(p);
        System.out.println(Arrays.toString(p));
        bublinky.vypisPole();
    }
}
