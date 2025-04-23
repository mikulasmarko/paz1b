package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Poklad {
    private int[] poleCisel;
    private List<String> list;
    private int x;
    private int y;

    public List<String> cesty(int k, int x, int y) {
        list = new ArrayList<>();
        poleCisel = new int[k];
        this.y = y;
        this.x = x;
        generuj();
        return list;
    }

    public void generuj() {
        generuj(0);
    }

    public void generuj(int idx) {

        if (idx == poleCisel.length) {
            spracuj();
            return;
        }

        for (int i = 0; i < 4; i++) {
            poleCisel[idx] = i;
            generuj(idx + 1);
        }
    }

    public void spracuj() {
        String out = "";
        int xKamSaPosunie = 0;
        int yKamSaPosunie = 0;
        System.out.println(Arrays.toString(poleCisel));
        for (int i = 0; i < poleCisel.length; i++) {
            if (poleCisel[i] == 0) {
                out += "N";
                yKamSaPosunie++;
            }
            if (poleCisel[i] == 1) {
                out += "E";
                xKamSaPosunie++;
            }
            if (poleCisel[i] == 2) {
                out += "S";
                yKamSaPosunie--;
            }
            if (poleCisel[i] == 3) {
                out += "W";
                xKamSaPosunie--;
            }
        }
        if (x == xKamSaPosunie && y == yKamSaPosunie) {
            list.add(out);
        }

    }

    public static void main(String[] args) {
        Poklad p = new Poklad();
        System.out.println(p.cesty(10, 1, 3).toString());
    }
}

