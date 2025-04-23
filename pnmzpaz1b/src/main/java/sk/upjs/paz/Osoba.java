package sk.upjs.paz;

import java.util.ArrayList;
import java.util.List;

public class Osoba {
    private String meno;
    private List<Osoba> deti = new ArrayList<>();

    public Osoba(String meno) {
        this.meno = meno;
    }

    public void pridajDieta(Osoba dieta) {
        deti.add(dieta);
    }

    public void vypisRodostrom() {
        vypisRodostrom(0);
    }

    public void vypisRodostrom(int level) {
        for (int i = 0; i < level; i++)
            System.out.print(' ');
        System.out.println(meno);
        for (Osoba dieta : deti)
            dieta.vypisRodostrom(level + 1);
    }

    @Override
    public String toString() {
        String result = meno;
        if (!deti.isEmpty()) {
            result += "(";
            boolean f = true;
            for (Osoba dieta : deti) {
                if (!f)
                    result += " ";
                result += dieta.toString();
                f = false;
            }
            result += ")";
        }
        return result;
    }


    private int pocetMax;

    public int najstastnejsiDedo() {
        int pocet = 0;
        for (Osoba osoba : deti) {
            if (!osoba.deti.isEmpty()) {
                pocet++;
            }
            int vnucata = osoba.najstastnejsiDedo();
            if(Math.max(vnucata, pocet)>pocetMax){
                pocetMax = Math.max(vnucata, pocet);
            }


        }
        return pocetMax;
    }


    public static void main(String[] args) {

//        Osoba dedo=new Osoba("dedo");
//        Osoba oco1=new Osoba("oco1");
//        Osoba oco2=new Osoba("oco2");
//        Osoba oco3=new Osoba("oco3");
//        Osoba medzioco=new Osoba("medzioco");
//
//        Osoba dieta1=new Osoba("dieta1");
//        Osoba dieta2=new Osoba("dieta2");
//        Osoba dieta3=new Osoba("dieta3");
//        Osoba dieta4=new Osoba("dieta4");
//
//        dedo.pridajDieta(oco1);
//        dedo.pridajDieta(oco2);
//        dedo.pridajDieta(oco3);
//        oco1.pridajDieta(dieta1);


    }
}