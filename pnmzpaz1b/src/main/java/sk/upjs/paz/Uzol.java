package sk.upjs.paz;

import java.util.*;

public class Uzol {

    private int hmotnost;
    private List<Uzol> deti = new ArrayList<>();

    public Uzol(int hmotnost) {
        this.hmotnost = hmotnost;
    }

    public void pridajDieta(Uzol dieta) {
        deti.add(dieta);
    }

    public void vypisRodostrom() {
        vypisRodostrom(0);
    }

    public void vypisRodostrom(int level) {
        for (int i = 0; i < level; i++)
            System.out.print(' ');

        System.out.println(hmotnost);
        for (Uzol dieta : deti)
            dieta.vypisRodostrom(level + 1);
    }

    @Override
    public String toString() {
        String result = Integer.toString(hmotnost);
        if (deti.size() != 0) {
            result += "(";
            boolean f = true;
            for (Uzol dieta : deti) {
                if (!f)
                    result += " ";
                result += dieta.toString();
                f = false;
            }
            result += ")";
        }
        return result;
    }

    public int jesen() {
        int pocetOdpadnutych = 0;
        List<Uzol> opadnuteListy = new ArrayList<>();

        for (Uzol oGeneraciuNizsie : this.deti) {
            if (oGeneraciuNizsie.deti.isEmpty()) {
                pocetOdpadnutych++;
                opadnuteListy.add(oGeneraciuNizsie);
            } else {
                pocetOdpadnutych += oGeneraciuNizsie.jesen();
            }
        }
        deti.removeAll(opadnuteListy);
        return pocetOdpadnutych;
    }

    public static void main(String[] args) {
        Uzol hlavny = new Uzol(1);
        Uzol deti1 = new Uzol(2);
        Uzol deti2 = new Uzol(3);
        Uzol vnucka1 = new Uzol(4);
        Uzol vnucka2 = new Uzol(5);
        Uzol vnucka3 = new Uzol(6);

        hlavny.pridajDieta(deti1);
        hlavny.pridajDieta(deti2);
        deti1.pridajDieta(vnucka1);
        deti2.pridajDieta(vnucka2);
        deti2.pridajDieta(vnucka3);

        System.out.println("Pred jeseňou: " + hlavny.toString());
        int suma = hlavny.jesen();
        System.out.println(suma);
        System.out.println("Po jeseni: " + hlavny.toString());
    }
}
