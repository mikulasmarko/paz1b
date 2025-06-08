package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MiskovaFazulovaPolievocka {

    boolean[] mojePismenka;

    public MiskovaFazulovaPolievocka(ArrayList<Integer> nabratia, int vsetkyPismenky, int pocetCoHladamPismenkyM) {
        mojePismenka = new boolean[vsetkyPismenky + 1];
        Arrays.fill(mojePismenka, false);
        mojePismenka[vsetkyPismenky] = true;

        for (Integer nabratie : nabratia) {
            for (int i = 0; i <= vsetkyPismenky; i++) {
                if (mojePismenka[i] && (i - nabratie) >= 0) {
                    mojePismenka[i - nabratie] = true;
                }
            }
        }
        double counter = 0d;
        for (int i = 0; i < mojePismenka.length; i++) {
            if (mojePismenka[i] == true) {
                counter++;
            }
        }
        System.out.println(1d / counter);
        System.out.println(mojePismenka[pocetCoHladamPismenkyM]);


        List<Integer> mojeLyzice = new ArrayList<>();
        int indexicek = pocetCoHladamPismenkyM;

        for (Integer nabraticko : nabratia) {

            if ((indexicek + nabraticko <= vsetkyPismenky) && mojePismenka[indexicek + nabraticko] == true) {
                mojeLyzice.add(nabraticko);
                indexicek += nabraticko;
            }
        }
        System.out.println(mojeLyzice);

    }


    public static void main(String[] args) {
        ArrayList<Integer> nabratia = new ArrayList<>();
        nabratia.add(10);
        nabratia.add(12);
        nabratia.add(7);

        MiskovaFazulovaPolievocka miskovaFazulovaPolievocka = new MiskovaFazulovaPolievocka(nabratia, 90, 68);
    }

}
