package sk.upjs.paz;

import java.util.Arrays;

public class LogickeVyrazy {


    private boolean[] poleBooleanov;
    //    private int[] poleCisel;
    private boolean vysledok;

    public boolean jeTautologia(int n) {
        poleBooleanov = new boolean[n];
//        poleCisel = new int[n];
        vysledok = true;
        generuj();
        return vysledok;
    }

    public void generuj() {
        generuj(0);
    }

    public void generuj(int idx) {
        if (idx == poleBooleanov.length) {
            spracuj();
            return;
        }

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                poleBooleanov[idx] = true;
            } else {
                poleBooleanov[idx] = false;
            }
            generuj(idx + 1);
        }
    }

    public void spracuj() {
//        System.out.println(Arrays.toString(poleCisel));
//        System.out.println(Arrays.toString(poleBooleanov));
        if (!Eval.overVyraz(poleBooleanov)) {
            vysledok = false;
        }
    }

    public static void main(String[] args) {
        LogickeVyrazy logickeVyrazy = new LogickeVyrazy();
        System.out.println(logickeVyrazy.jeTautologia(5));

    }
}