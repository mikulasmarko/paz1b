package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;

public class ZaprahovyProblem {

    ArrayList<Pesik> psiky;
    int[][] dynamicke;
    int minimum = Integer.MAX_VALUE;

    public ZaprahovyProblem(ArrayList<Pesik> psiky) {
        this.psiky = psiky;
        dynamicke = new int[psiky.size()][psiky.size() * psiky.size()];
        for (int i = 0; i < dynamicke.length; i++) {
            Arrays.fill(dynamicke[i], Integer.MAX_VALUE);
        }
        dynamicke[0][0] = psiky.get(0).vaha;
        vyries();
        vyriesPoslednyRiadocek();
        System.out.println(minimum);
    }

    public void vyries() {
        int riadok = dynamicke.length;
        int stlpec = dynamicke[0].length;
        for (int i = 0; i < riadok - 1; i++) {
            int couterIndexov = 0;
            //tento counter zabezpecuje ze sa nam hodnoty zapisuju postupne zaradom v dalsom riadku, posuva miesta v stlpci
            for (int j = 0; j < stlpec; j++) {
                if (dynamicke[i][j] != Integer.MAX_VALUE) {
                    dynamicke[i + 1][couterIndexov] = Math.abs(dynamicke[i][j] - psiky.get(i+1).vaha);
                    couterIndexov++;
                    dynamicke[i + 1][couterIndexov] = Math.abs(dynamicke[i][j] + psiky.get(i+1).vaha);
                    couterIndexov++;
                }
            }
        }
    }

    public void vyriesPoslednyRiadocek() {

        for (int i = 0; i < dynamicke[0].length; i++) {
//            System.out.println(dynamicke[dynamicke.length - 1][i]);
            minimum = Math.min(dynamicke[dynamicke.length - 1][i], minimum);
        }
    }

    public static void main(String[] args) {
        Pesik p1 = new Pesik(7);
        Pesik p2 = new Pesik(4);
        Pesik p3 = new Pesik(2);
//        Pesik p4 = new Pesik(5);
        ArrayList<Pesik> psiky = new ArrayList<>();
        psiky.add(p1);
        psiky.add(p2);
        psiky.add(p3);
//        psiky.add(p4);
        ZaprahovyProblem zp = new ZaprahovyProblem(psiky);
    }
}
