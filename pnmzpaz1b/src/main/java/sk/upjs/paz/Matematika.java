package sk.upjs.paz;

import java.util.Arrays;

public class Matematika {

    private int[] znamienka;
    private int[] cisla;
    private int vysledok;
    private int vysledokFinal;
    private boolean nasielSom;

    public boolean findOperators(int[] numbers, int result) {
        znamienka = new int[numbers.length - 1];
        cisla = numbers;
        vysledokFinal = result;
        nasielSom = false;
        generuj();
        return nasielSom;
    }

    public void generuj() {
        generuj(0);
    }

    public void generuj(int idx) {
        if (idx == znamienka.length) {
            spracuj();
            return;
        }
        for (int i = 0; i < 4; i++) {
            znamienka[idx] = i;
            generuj(idx + 1);
        }
    }

    public void spracuj() {
        System.out.println(Arrays.toString(znamienka));
        vysledok = cisla[0];
        for (int i = 0; i < znamienka.length; i++) {
            if (znamienka[i] == 0) {
                vysledok = vysledok + cisla[i + 1];
            }
            if (znamienka[i] == 1) {
                vysledok = vysledok - cisla[i + 1];
            }
            if (znamienka[i] == 2) {
                vysledok = vysledok * cisla[i + 1];
            }
            if (znamienka[i] == 3) {
                vysledok = vysledok / cisla[i + 1];
            }
        }
        System.out.println(vysledok);
        if (vysledok == vysledokFinal) {
            nasielSom = true;
        }
    }

    public static void main(String[] args) {
        Matematika matematika = new Matematika();
        int[] pole = {10, 5, 4, 2};
        System.out.println(matematika.findOperators(pole, 58));
    }
}