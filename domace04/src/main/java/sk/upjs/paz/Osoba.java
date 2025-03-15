package sk.upjs.paz;

import java.util.*;

public class Osoba {
    private String meno;
    private List<Osoba> deti = new ArrayList<Osoba>();

    public Osoba(String meno) {
        this.meno = meno;
    }

    public void pridajDieta(Osoba dieta) {
        deti.add(dieta);
    }

    public int pocetJedinacikovVGeneracii(int generacia) {
        //ak sa mi znizi generacia na 1, pozeram sa kolko potomkov ma dana osoba, ak ma 1 vratim 1
        //ak ma 0 alebo viac ako 1 vratim 0

        if (generacia == 1) {
            if (deti.size() == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        //vytvorim si pocitadlo ktore bdue pocitat deti
        int pocetDeti = 0;

        // prechadzam deti osoby a pocitam kolko ma dana generacia potomkov
        //lustrujem potomkov kym nedojdem na taku generaciu ktora ma zaujima
        // tj ked dostanem na vstupe 7 tak sa poposuvam na take miesto kym nebude gen 1
        for (Osoba osoba : deti) {
            //spocitam deti
            pocetDeti += osoba.pocetJedinacikovVGeneracii(generacia - 1);
        }
        //vratim deti
        return pocetDeti;
    }
}