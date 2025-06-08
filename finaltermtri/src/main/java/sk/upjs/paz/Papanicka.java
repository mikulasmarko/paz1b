package sk.upjs.paz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Papanicka {


    Set<Integer> tieCoMajuRovnakePapania;
    List<Integer> navareno = new ArrayList<>();


    public void rozpisObedov(ArrayList<Vnucikovia> vnucikovia, int pocetDni) {
        //kazdemu dnu hladam jedno jedlo ktore navarime
        for (int i = 0; i < pocetDni; i++) {
            //nahadzem vsetky jedla ake mozem navarit
            tieCoMajuRovnakePapania = new HashSet<>();
            for (int j = 0; j < vnucikovia.size(); j++) {
                tieCoMajuRovnakePapania.addAll(vnucikovia.get(j).papanicka);
            }


            for (int j = 0; j < vnucikovia.size(); j++) {
                //zistujem ktory vnucikovia pridu
                if (vnucikovia.get(j).kedyChceDojst.get(i)) {
                    //robim prieniky
                    tieCoMajuRovnakePapania.retainAll(vnucikovia.get(j).papanicka);
                }
            }

            for (int j = 0; j < navareno.size(); j++) {

                if (tieCoMajuRovnakePapania.contains(navareno.get(j))) {
                    tieCoMajuRovnakePapania.remove(navareno.get(j));
                }
            }
            if (tieCoMajuRovnakePapania.isEmpty()) {
                System.out.println("Papanicka nem rovnaki");
                return;
            }
            int max = Integer.MIN_VALUE;
            for (Integer integer : tieCoMajuRovnakePapania) {
                max = Math.max(max, integer);
            }
            navareno.add(max);
        }
        System.out.println(navareno);
    }


    public static void main(String[] args) {


        Vnucikovia v1 = new Vnucikovia(Set.of(0, 1), (List.of(true, true, false, false)));
        Vnucikovia v2 = new Vnucikovia(Set.of(0, 1), (List<Boolean>) List.of(true, true, false, true));
        Vnucikovia v3 = new Vnucikovia(Set.of(0, 1, 2, 3), (List<Boolean>) List.of(true, false, true, true));
        Papanicka papanicka = new Papanicka();
        ArrayList<Vnucikovia> vnucikovia = new ArrayList<>();
        vnucikovia.add(v1);
        vnucikovia.add(v2);
        vnucikovia.add(v3);
        papanicka.rozpisObedov(vnucikovia, 4);

    }
}
