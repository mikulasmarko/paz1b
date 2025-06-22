package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BrigadoveLeto {

    ArrayList<Brigada> brigady;
    int[] dynamicke;
    ArrayList<Brigada> coSomZobralZaBrigadu = new ArrayList<>();

    public BrigadoveLeto(ArrayList<Brigada> b) {
        this.brigady = b;
        Collections.sort(brigady);
        dynamicke = new int[brigady.get(brigady.size() - 1).stop + 1];
        dynamicke[0] = 0;
        vyplnBrigadoveLeto();
        spatnyPrechod();
        System.out.println(dynamicke[dynamicke.length - 1]);
        System.out.println(Arrays.toString(dynamicke));
        System.out.println("hotovo");
        System.out.println(coSomZobralZaBrigadu);
    }

    public void vyplnBrigadoveLeto() {

        for (int i = 1; i < dynamicke.length; i++) {
            dynamicke[i] = dynamicke[i - 1];
            for (Brigada b : brigady) {
                if (b.stop == i) {
                    dynamicke[i] = Math.max(dynamicke[i], dynamicke[b.start] + b.penaze);
                }
            }
        }
    }


//    public void spatnyPrechod() {
//        int aktualne = dynamicke[dynamicke.length - 1];
//        for (int i = dynamicke.length - 1; i > 0; i--) {
//            int porovnavany = dynamicke[i];
//            if (aktualne != porovnavany) {
////                int rozdiel = Math.abs(aktualne - porovnavany);
//                aktualne = porovnavany;
//                for (Brigada brigada : brigady) {
//                    if (brigada.stop == i + 1) {
//                        coSomZobralZaBrigadu.add(brigada);
//                        aktualne = brigada.start;
//                    }
//                }
//            }
//        }
//    }

public void spatnyPrechod() {
    int aktualnaHodnota = dynamicke[dynamicke.length - 1];
    int aktualnyDen = dynamicke.length - 1;

    // Ideme od konca po začiatok
    for (int den = aktualnyDen; den > 0; den--) {
        // Ak sa hodnota zmenila oproti predchádzajúcemu dňu
        if (den == 0 || dynamicke[den] != dynamicke[den-1]) {
            // Hľadáme brigádu, ktorá končí v tento deň
            for (Brigada brigada : brigady) {
                if (brigada.stop == den &&
                        dynamicke[brigada.start] + brigada.penaze == dynamicke[den]) {
                    // Našli sme brigádu, ktorá prispela k maximu
                    coSomZobralZaBrigadu.add(brigada);
                    // Preskočíme na začiatok tejto brigády
                    den = brigada.start + 1;
                    break;
                }
            }
        }
    }

    // Otočíme poradie, lebo sme išli odzadu
    Collections.reverse(coSomZobralZaBrigadu);
}

    public static void main(String[] args) {
        Brigada b1 = new Brigada(2, 2, 30);
        Brigada b2 = new Brigada(4, 6, 90);
        Brigada b3 = new Brigada(8, 10, 90);
        Brigada b4 = new Brigada(12, 12, 30);
        Brigada b5 = new Brigada(5, 9, 250);
        ArrayList<Brigada> brigady = new ArrayList<Brigada>();
        brigady.add(b1);
        brigady.add(b2);
        brigady.add(b3);
        brigady.add(b4);
        brigady.add(b5);
        BrigadoveLeto b = new BrigadoveLeto(brigady);
    }
}
