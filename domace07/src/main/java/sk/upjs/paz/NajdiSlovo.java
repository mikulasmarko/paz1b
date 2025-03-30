package sk.upjs.paz;

import java.util.Arrays;

public class NajdiSlovo {

    private int[] kamDamMedzeru;
    private String slovicko;
    private String pamatSlovicko;
    private int hashcode;
    private String vratka;


    public NajdiSlovo(String slovo, int hashCode) {
        kamDamMedzeru = new int[slovo.length()];
        slovicko = slovo;
        hashcode = hashCode;
        vratka = null;
        pamatSlovicko = slovo;
    }


    public void generuj(int odidx) {

        if (odidx == kamDamMedzeru.length) {
            spracuj();
            return;

        }

        for (int i = 0; i < 2; i++) {
//            if (kamDamMedzeru[odidx - 1] == 1 && i == 1) {
//                return;
//            } else {
            kamDamMedzeru[odidx] = i;
            generuj(odidx + 1);
//            }

        }


    }

    public void generuj() {
        kamDamMedzeru[0] = 0;
        generuj(1);

    }


    public void spracuj() {
        System.out.println(Arrays.toString(kamDamMedzeru));
        slovicko = pamatSlovicko;

        for (int i = kamDamMedzeru.length - 1; i > 0; i--) {
            if (kamDamMedzeru[i] == 1) {
                slovicko = slovicko.substring(0, i) + " " + slovicko.substring(i);
            }
        }
        System.out.println(slovicko);
        if (slovicko.hashCode() == hashcode) {
            vratka = slovicko;
        }


    }


    public String najdiRiesenie() {
        generuj();
        return vratka;
    }


    public static void main(String[] args) {
        NajdiSlovo najdiSlovo = new NajdiSlovo("abcde", 1198195529);
        System.out.println(najdiSlovo.najdiRiesenie());
    }
}