package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Uzol {
    private int hodnota;
    private Uzol lavy;
    private Uzol pravy;

    public Uzol(int hodnota, Uzol lavy, Uzol pravy) {
        this.hodnota = hodnota;
        this.lavy = lavy;
        this.pravy = pravy;
    }

    public int getHodnota() {
        return hodnota;
    }

    public void setHodnota(int hodnota) {
        this.hodnota = hodnota;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (lavy != null) sb.append("(" + lavy.toString() + ") ");

        sb.append(hodnota);

        if (pravy != null) sb.append(" (" + pravy.toString() + ")");

        return sb.toString();
    }

    public int spocitajDeti(Uzol uzol) {
        int pocet = 0;
        //ak ma uzol co dostanem na vstupe prave dieta pripocitam pocet, a pytam sa na pocet deti daneho uzla
        if (uzol.pravy != null) {
            pocet++;
            pocet += spocitajDeti(uzol.pravy);
        }
        //ak ma uzol na vstupe lave dieta pytam sa na pcoet deti
        if (uzol.lavy != null) {
            pocet++;
            pocet += spocitajDeti(uzol.lavy);
        }
        return pocet;
    }


    public boolean jeVyvazeny() {
        //spocitam kolko uzlov mam na pravo a kolko nalavo
        int pocetNaPravo = spocitajDeti(pravy);
        int pocetNaLavo = spocitajDeti(lavy);
        //ak sa lisia iba o 1 vratim true, ak o viac vratim false
        return Math.abs(pocetNaLavo - pocetNaPravo) < 2;

        //neviem ci ja alebo evalulu je delulu ale podla mna by to takto malo fungovat nevidim dovod aby nie

        /*
                10
            5        6
         7         4
            3
            -strom je podla mna vyvazeny

                10
            5        6
         7    3    4

            -strom je podla mna vyvazeny

                10
            5        6
         7    3    4    9

            -strom je podla mna vyvazeny

                10
            5           6
         7          4
            3
         2
            -strom je podla mna nie vyvazeny

            v takychto casoch mi to hadze true/false ako ma, nevidim iny dovod ako zle pochopene zadanie
            ale mne logicky zo zadania vyplyva ze sa to ma spravat takto

            sucet vsetkych uzlov od hlavneho korena na lavo sa ma od suctu uzlov na pravo od hlavneho korena lisit
            nanajvys o 1, co je splnene v mojom kode podla mna

            ak sa mylim ospravedlnujem sa ale chcel by som k tomu nejake vysvetlenie od niekoho
            kto to vytvaral/ opravuje to nech to pochopim lebo toto je madness

            good luck pri citani xdd

         */

    }


    public static Uzol vytvorBVS(Set<Integer> hodnoty) {
        List<Integer> zoradeneHodnoty = new ArrayList<>(hodnoty);
        //utriedim si hodnoty aby som mohol pekne vytvorit strom
        //akoze neviem ci to mozem spravit ale takto mi to aspon kus dava zmysel jak by sa to asi cca dalo spravit
        //inak netusim jak by som to akoze mal spravit
        Collections.sort(zoradeneHodnoty);

        //vratim bvs ktory tvorim uz z usporiadaneho zoznamu
        return vytvorBVSZoZoznamu(zoradeneHodnoty, 0, zoradeneHodnoty.size() - 1);
    }

    private static Uzol vytvorBVSZoZoznamu(List<Integer> hodnoty, int start, int end) {

        //zarucuje aby som nevybehol z pola von, nastavuje dieta na null ak uz nemam co pridat
        if (start > end) {
            return null;
        }
        //chcem aby hodnoty napravo boli vacsie ako stredna hodnota
        // a nalavo mensie ako dana hodnota
        //to znamena ze toto v strede ma byt toto v strede
        int stred = (start + end) / 2;
        //zinicializujem si koren a nastavim zatial jeho deti na null aby to bolo aspon kus prehladne
        Uzol koren = new Uzol(hodnoty.get(stred), null, null);
        //a vytvorim mu prave a lave dieta z podzoznamov, ak mi vyleti podmienka nastavi sa decko na null
        koren.lavy = vytvorBVSZoZoznamu(hodnoty, start, stred - 1); // Ľavá polovica
        koren.pravy = vytvorBVSZoZoznamu(hodnoty, stred + 1, end);   // Pravá polovica

        //vrati odkaz na hlavny koren
        //alebo teda rodica dalsich deti/ uzlov
        //kt sa potom moze nastavit do pravy/lavy alebo na konci ked vsetko zbehne vrati referenciu na hlavny koren
        return koren;
    }

}