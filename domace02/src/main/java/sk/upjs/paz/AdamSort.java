package sk.upjs.paz;

import java.util.Arrays;

public class AdamSort {

    /**
     * Vrati index v poli p, kde by sa mal nachadzat prvok so zadanou hodnotou v
     * zadanom usporiadanom podpoli pola p.
     *
     * @param p       pole
     * @param odIdx   index prveho prvku usporiadaneho podpola
     * @param poIdx   index posledneho prvku usporiadaneho podpola
     * @param hodnota hodnota, ktoru mame v plane vlozit
     * @return index pola, kde by sa mala nachadzat zadana hodnota
     */
    public static int binarneHladajIndex(int[] p, int odIdx, int poIdx, int hodnota) {

        // Skontrolujeme korektnost indexov
        if (odIdx > poIdx) throw new RuntimeException("Parameter odIdx musi byt mensi alebo rovny ako poIdx.");
        // Ak je hodnota vacsia, ako cislo na poslednom indexe uvazovaneho
        // podpola, tak vratime nasledujuci index (spravne miesto pre hodnotu je
        // az za poslednym prvkom podpola)
        if (p[poIdx] < hodnota) return poIdx + 1;
        // Vieme, ze spravny index je medzi odIdx..poIdx - aplikujeme binarne
        // vyhladavanie
        while (odIdx < poIdx) {
            // Vypocitame stredovy index
            int stred = (odIdx + poIdx) / 2;

            if (hodnota <= p[stred]) {
                // Ak je hodnota mensia alebo rovna ako hodnota v strede, tak
                // spravne miesto je niekde medzi odIdx..stred
                poIdx = stred;
            } else {
                // Ak je hodnota vacsia ako hodnota v strede, tak spravne miesto
                // bude az za stredovym indexom, t.j. niekde medzi stred+1 a
                // poIdx
                odIdx = stred + 1;
            }
        }
        return odIdx;
    }

    /**
     * Specialna verzia pre Adamov algoritmus, kedze on predpoklada, ze v poli
     * ma prvky na indexoch 0..poIdx uz usporiadane.
     */
    public static int binarneHladajIndex(int[] p, int poIdx, int hodnota) {

        return binarneHladajIndex(p, 0, poIdx, hodnota);
    }

    /**
     * Usporiada cisla v poli od najmensieho po najvacsie
     *
     * @param p pole, ktoreho prvky treba usporiadat
     */
    public static void utried(int[] p) {
        // Triediaci algoritmus na zaklade Adamovej myslienky
        if (p.length < 2) {
            return;
        }
        //utriedim prvych n-1 cisel klasicky
        for (int i = 0; i < p.length - 2; i++) {
            for (int j = i + 1; j < p.length - 1; j++) {
                if (p[i] > p[j]) {
                    vymenimeCiselka(p, i, j);
                }
            }
        }
//        System.out.println(Arrays.toString(p));
        //zistim kam ho chcem dat
        int miestoKdeChcemDat = binarneHladajIndex(p, p.length - 2, p[p.length - 1]);
        int cisloCoChcemDat = p[p.length - 1];

        //poposuvam ostatne nech mozem strcit to cislo tam kde chcem aby bolo
        for (int i = p.length - 1; i > miestoKdeChcemDat; i--) {
            p[i] = p[i - 1];
        }
        p[miestoKdeChcemDat] = cisloCoChcemDat;
    }

    //alg na vymenu cisel
    public static void vymenimeCiselka(int[] pole, int i, int j) {
        int pom = pole[i];
        pole[i] = pole[j];
        pole[j] = pom;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] p = new int[30];
        for (int i = 0; i < p.length; i++)
            p[i] = (int) (Math.random() * 1000);

        System.out.println(Arrays.toString(p));
        utried(p); // AdamSort.utried(p);
        System.out.println(Arrays.toString(p));

    }
    /*
    Adamov algoritmus bude fungovat
    Adamov algoritmus ma casovu zlozitost O((n-1)*logn*n)
    Adamov algoritmus je asymtoticky horsi
    bude trvat dlhsie lebo najrpv sice utriedi pole v O(n*(n-1)) a potom logaritmickou zlozitostou najde kde ma vlozit prvok
    ale potom treba poposuvat prvky v poli od indexu kde ho je potrebne vlozit co je teiz zlozitost n

     */

}