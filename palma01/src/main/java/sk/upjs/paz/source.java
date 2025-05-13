package sk.upjs.paz;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class source {


    public void vyriesUlohu() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int pocetVlakov = Integer.parseInt(br.readLine());

            //prechadzam vlak po vlaku
            for (int i = 0; i < pocetVlakov; i++) {
                System.out.println(vyriesVlak(br.readLine()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int vyriesVlak(String string) {
        String[] riadok = string.split(" ");

        int pocetSprievodcov = Integer.parseInt(riadok[0]);
        int pocetVoznov = Integer.parseInt(riadok[1]);

        int[] poctyPasazierovVoVoznoch = new int[pocetVoznov];//velkost 9 v prvom pripade
        int priemerNaSprievodcu = 0;

        for (int i = 0; i < pocetVoznov; i++) {
            int aktualnyVozen = Integer.parseInt(riadok[i + 2]);
            poctyPasazierovVoVoznoch[i] = aktualnyVozen;
            //pripocitam pocet ludi vo vozni k priemeru - zatial iba pocet vsetkych ludi vo voznoch
            priemerNaSprievodcu += aktualnyVozen;
        }

//        System.out.println(Arrays.toString(poctyPasazierovVoVoznoch));
//        System.out.println(priemerNaSprievodcu);
        //vypocitam priemernu hodnotu
        priemerNaSprievodcu = (int) priemerNaSprievodcu / pocetSprievodcov;
//        System.out.println(priemerNaSprievodcu);


        //vytvorim si pocitadlo kde budem pripocitavat hodnotu pre
        int pocitadlo = 0;
        //urcujem ktoremu sprievodcovi dam hodnotu, vypocitam, priradim index na ktory budem davat o jeden vacsi
        int idxSprievodcu = 0;
        //vytvorim si pole sprievodcov do ktoreho si ulozim kolkych po sebe ludi mal jeden sprievodca
        int[] sprievodcoviaPole = new int[pocetSprievodcov];

        //prechadzam vozen po vozni - chcem prejst vsetky, musim zapocitat kazdeho zakaznika
        for (int i = 0; i < poctyPasazierovVoVoznoch.length - 1; i++) {
            pocitadlo += poctyPasazierovVoVoznoch[i];
            //ak sa priblizim k hranici alebo som ju prekrocil, zapisem sprievodcovi pocet, posuniem sprievodcu, vynulujem pocitadlo
            if ((pocitadlo > priemerNaSprievodcu || pocitadlo + poctyPasazierovVoVoznoch[i + 1] / 2 > priemerNaSprievodcu) && idxSprievodcu < pocetSprievodcov - 1) {
                sprievodcoviaPole[idxSprievodcu] = pocitadlo;
                idxSprievodcu++;
                pocitadlo = 0;
            }
        }
        //vyriesim aj posledny vozen
        pocitadlo += poctyPasazierovVoVoznoch[pocetVoznov - 1];
        sprievodcoviaPole[pocetSprievodcov - 1] = pocitadlo;
        //zistim kolko max ludi bude mat sprievodca na krku
        int max = 0;
        for (int j : sprievodcoviaPole) {
            if (j > max) {
                max = j;
            }
        }
        return max;
        //vratim pre kazdy riadok hodnotu co vypocitalo a som stastny a spokojny
    }

    public static void main(String[] args) {
        source s = new source();
        s.vyriesUlohu();
        //spustim a som stastny a spokojny :)
        //pisem kod v prehliadaci
        //este tu pisem nieco
    }
}
