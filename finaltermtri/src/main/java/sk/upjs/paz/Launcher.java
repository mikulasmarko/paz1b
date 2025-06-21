package sk.upjs.paz;

import sk.upjs.jpaz2.*;

import java.util.ArrayList;
import java.util.Collections;

public class Launcher {

    ArrayList<Dom> domiky;
    ArrayList<Zastavka> zastavocky;

    public Launcher(ArrayList<Dom> d, ArrayList<Zastavka> z) {
        domiky = d;
        zastavocky = z;
        Collections.sort(domiky);
        Collections.sort(zastavocky);

        pomocnaMetoda();
        for (Dom dom : domiky) {
            if (!dom.maZastavku) {
                zastavocky.add(new Zastavka(dom.kdeJePostaveny + zastavocky.get(0).platnost, zastavocky.get(0).platnost));
                pomocnaMetoda();
            }
        }

        System.out.println(zastavocky);

    }

    public void pomocnaMetoda() {

        for (Dom dom : domiky) {
            if (dom.maZastavku) break;
            for (Zastavka zastavka : zastavocky) {
                if (zastavka.platnostDolava < dom.kdeJePostaveny && zastavka.platnostDoprava > dom.kdeJePostaveny) {
                    dom.maZastavku = true;
                }
            }

        }


    }


    public static void main(String[] args) {
        Dom d1 = new Dom(60);
        Dom d2 = new Dom(120);
        Dom d3 = new Dom(150);
        Zastavka z1 = new Zastavka(160, 50);
        ArrayList<Dom> doms = new ArrayList<>();
        doms.add(d1);
        doms.add(d2);
        doms.add(d3);
        ArrayList<Zastavka> zs = new ArrayList<>();
        zs.add(z1);

        Launcher l = new Launcher(doms, zs);


    }
}