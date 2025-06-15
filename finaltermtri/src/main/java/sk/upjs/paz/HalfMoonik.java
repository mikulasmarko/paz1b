package sk.upjs.paz;

import java.util.Arrays;

public class HalfMoonik {
    int[][] pole;
    int[][] dynamicke;
    int counter = 0;

    public HalfMoonik(int[][] policko) {
        pole = policko;
        dynamicke = new int[policko.length][policko[0].length];

        for (int i = 0; i < policko.length; i++) {
            dynamicke[0][i] = pole[0][i];
            counter = Math.max(counter, policko[0][i]);
        }
        System.out.println(Arrays.deepToString(dynamicke));
        zistiAkeNajvacsie();
        System.out.println(counter);
        System.out.println(Arrays.deepToString(dynamicke));
//        System.out.println(Arrays.deepToString(policko));
    }
    /*
//    Trivialny pripad - v celom hornom riadku, celom lavom stlpci a celom
//pravom stlpci sa skopiruju jednotky a nuly zo vstupu. Tam nic vacsie byt
//nemoze.

Potom prechadzas pole z hora dole. Teda po riadkoch zhora dole, kazdy
riadok postupne zlava doprava.
Ak je pre nejake policko vstupna hodnota 0, tak aj to pole bude mat 0.

Ak je tam hodnota 1, tak to pole bude mat hodnotu D[riadok, stlpec] =
MIN (D[r-1, s-1], D[r-1, s], D[r-1, s+1]) + 1
Co to znamena - tri jednotky urobia dole dvojku. Alebo ak mas tri
dvojky, tak to urobi velkost 3. To minimum tam je preto, ze niekde mozes
mat dve dvojky a potom nulu alebo nieco mensie - toto najlepsie vidiet
na priklade.
     */

    public void zistiAkeNajvacsie() {

        int riadky = dynamicke.length;
        int stlpce = dynamicke[0].length;


        for (int i = 1; i < riadky; i++) {
            for (int j = 0; j < stlpce; j++) {
                if (pole[i][j] == 1) {
                    if (j - 1 < 0) {//j=0
                        //predosly riadok v mojom stlpci a predosly riadok v stlpci napravo
                        int cislo = Math.min(pole[i - 1][j], pole[i - 1][j + 1]) + 1;
                        dynamicke[i][j] = cislo;
                        counter = Math.max(counter, cislo);
                    } else if (j + 1 >= stlpce) {
                        //predosly riadok v mojom stlpci a predosly riadok v stlpci nalavo
                        dynamicke[i][j] = Math.min(pole[i - 1][j - 1], pole[i - 1][j]) + 1;
                        counter = Math.max(counter, dynamicke[i][j]);
                    } else {
                        dynamicke[i][j] = Math.min(pole[i - 1][j - 1], Math.min(pole[i - 1][j], pole[i - 1][j + 1])) + 1;
                        counter = Math.max(counter, dynamicke[i][j]);
                    }


                } else {
                    dynamicke[i][j] = 0;
                }

            }

        }


    }


    public static void main(String[] args) {
        int[][] pole = {{1, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}};
        HalfMoonik hm = new HalfMoonik(pole);
    }
}
