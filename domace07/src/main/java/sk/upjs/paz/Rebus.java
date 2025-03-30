package sk.upjs.paz;

import java.util.Arrays;

public class Rebus {

    private int cislo;
    private int[] kamDamMedzeru;
    private int[] poleZCisla;


    /*
    ak tam reznem 1 tak to bude plus
    ak tam reznem 2 tak to bude minus
    ak tam reznem 3 tak to necham tak
     */


    //vygenerovat vsetky take 1,2,3 o dlzke pocet cisel minus 2
    //overit ze ak ma dlzku 1,2 tak mam ine podmienky
    public Rebus(){
        
    }



    public void generuj(int odidx) {

        if (odidx == kamDamMedzeru.length) {
            spracuj();
            return;

        }

        for (int i = 0; i < 3; i++) {
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

    }

    public static void main(String[] args) {
        Rebus rebus = new Rebus();
        rebus.generuj();
    }

}
