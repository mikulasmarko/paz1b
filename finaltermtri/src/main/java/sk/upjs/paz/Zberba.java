package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;

public class Zberba {

    ArrayList<Mesto> mesta;
    int casik;
    int[][] dynamicke;
    int maxim = 0;

    public Zberba(ArrayList<Mesto> mestos, int zapadSlnka) {
        mesta = mestos;
        casik = zapadSlnka;
        dynamicke = new int[mesta.size() + 1][casik + 1];

        for (int i = 0; i < dynamicke.length; i++) {
            Arrays.fill(dynamicke[i], -1);
        }
//        System.out.println(Arrays.deepToString(dynamicke));
        vyries();
        System.out.println(maxim);
    }

    public void vyries() {
        dynamicke[0][0] = 0;

        //pre kazdy riadok pozeram vsetky stlpce
        for (int i = 0; i < dynamicke.length - 1; i++) {
            for (int j = 0; j < dynamicke[0].length; j++) {
                if (dynamicke[i][j] != -1) {
                    //ak mam na riadku hodnotu roznu od -1, viem vyplnit nasledujuce mesto
                    //zistim ci mi nevyleti index
                    if (j + mesta.get(i).bezZberu < dynamicke[0].length) {
                        int pamat = dynamicke[i][j];
                        dynamicke[i + 1][j + mesta.get(i).bezZberu] = pamat;
                        maxim = Math.max(maxim, pamat);
                    }

                    if (j + mesta.get(i).zber < dynamicke[0].length) {
                        int pamat = dynamicke[i][j] + mesta.get(i).zisk;
                        dynamicke[i + 1][j + mesta.get(i).zber] = pamat;
                        maxim = Math.max(maxim, pamat);
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        Mesto m1 = new Mesto(10, 30, 60);
        Mesto m2 = new Mesto(15, 40, 80);
        Mesto m3 = new Mesto(20, 50, 120);
        Mesto m4 = new Mesto(25, 60, 150);
//        Mesto m5 = new Mesto(30, 70, 200);
        ArrayList<Mesto> mestos = new ArrayList<>();
        mestos.add(m1);
        mestos.add(m2);
        mestos.add(m3);
        mestos.add(m4);
//        mestos.add(m5);
        Zberba zberba = new Zberba(mestos, 100);

    }

}
