package sk.upjs.paz;

import java.util.ArrayList;
import java.util.List;

public class Oddelenie {
    private List<Oddelenie> podOddelenia = new ArrayList<>();
    private int hodnota;

    public Oddelenie(int hodnota) {
        this.hodnota = hodnota;
    }

    public void pridajPodOddelenie(Oddelenie oddelenie) {
        podOddelenia.add(oddelenie);
    }

    public void vypisFirmu() {
        vypisFirmu(0);
    }

    public void vypisFirmu(int level) {
        for (int i = 0; i < level; i++)
            System.out.print(' ');
        System.out.println(hodnota);
        for (Oddelenie dieta : podOddelenia)
            dieta.vypisFirmu(level + 1);
    }

    @Override
    public String toString() {
        String result = Integer.toString(hodnota);
        if (!podOddelenia.isEmpty()) {
            result += "(";
            boolean f = true;
            for (Oddelenie odd : podOddelenia) {
                if (!f)
                    result += " ";
                result += odd.toString();
                f = false;
            }
            result += ")";
        }
        return result;
    }

    private int maximumCoMam = Integer.MIN_VALUE;

    public int richestDepartment() {
        spracuj();
        return maximumCoMam;
    }

    public int spracuj() {
        int pocitadlo = this.hodnota;
        for (Oddelenie oddelenie : podOddelenia) {
            pocitadlo += oddelenie.spracuj();
        }

        maximumCoMam = Math.max(pocitadlo, maximumCoMam);
        return pocitadlo;
    }

    
}