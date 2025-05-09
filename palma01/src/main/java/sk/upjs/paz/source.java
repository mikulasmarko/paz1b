package sk.upjs.paz;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class source {


    public void nacitajSubor() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int pocetVlakov = Integer.parseInt(br.readLine());

            for (int i = 0; i < pocetVlakov; i++) {
                System.out.println(vyriesVlak(br.readLine()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int vyriesVlak(String string) {
        String[] riadok = string.split(" ");

        int sprievodcovia = Integer.parseInt(riadok[0]);
        int vozne = Integer.parseInt(riadok[1]);

        int[] pasazieriVoVoznoch = new int[vozne];//velkost 9 v prvom pripade
        int priemer = 0;

        for (int i = 0; i < vozne; i++) {
            int aktualnyPocetVoVozni = Integer.parseInt(riadok[i + 2]);
            pasazieriVoVoznoch[i] = aktualnyPocetVoVozni;
            priemer += aktualnyPocetVoVozni;
        }

//        System.out.println(Arrays.toString(pasazieriVoVoznoch));
//        System.out.println(priemer);
        priemer = (int) priemer / sprievodcovia;
//        System.out.println(priemer);

        int pocitadlo = 0;
        int ktorySprievodca = 0;
        int[] sprievodcoviaPole = new int[sprievodcovia];

        for (int i = 0; i < pasazieriVoVoznoch.length - 1; i++) {
            pocitadlo += pasazieriVoVoznoch[i];
            if ((pocitadlo > priemer || pocitadlo + pasazieriVoVoznoch[i + 1] / 2 > priemer) && ktorySprievodca < sprievodcovia - 1) {
                sprievodcoviaPole[ktorySprievodca] = pocitadlo;
                ktorySprievodca++;
                pocitadlo = 0;
            }
        }
        pocitadlo += pasazieriVoVoznoch[vozne - 1];
        sprievodcoviaPole[sprievodcovia - 1] = pocitadlo;

        int max = 0;
        for (int j : sprievodcoviaPole) {
            if (j > max) {
                max = j;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        source s = new source();
        s.nacitajSubor();
    }
}
