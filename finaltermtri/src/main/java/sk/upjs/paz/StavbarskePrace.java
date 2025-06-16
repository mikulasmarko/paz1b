package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;

public class StavbarskePrace {

    ArrayList<Cesta> cesticky;
    int[][] dynamicke;
    int kilometre;
    int minimum = Integer.MAX_VALUE;

    public StavbarskePrace(ArrayList<Cesta> cesticky, int pocetKM) {
        this.cesticky = cesticky;
        dynamicke = new int[cesticky.size()][pocetKM + 1];
        kilometre = pocetKM;
        for (int i = 0; i < dynamicke.length; i++) {
            Arrays.fill(dynamicke[i], Integer.MAX_VALUE);
        }
        dynamicke[0][0] = 0;
        vyries();
        vyriesDanyKilometer();
        if (minimum == Integer.MAX_VALUE) {
            System.out.println("neda sa vyjadrit");
        } else {
            System.out.println(minimum);
        }

    }

    private void vyriesDanyKilometer() {

        for (int i = 0; i < dynamicke.length; i++) {
            minimum = Math.min(minimum, dynamicke[i][kilometre]);
        }
    }

    private void vyries() {

        for (int i = 0; i < dynamicke.length - 1; i++) {
            //ale v tomto kode moze byt aj <= kilometre lebo som kokot z toho mam pici pazko skurvene
            for (int j = 0; j < kilometre; j++) {
                if (dynamicke[i][j] != Integer.MAX_VALUE) {
                    dynamicke[i + 1][j] = dynamicke[i][j];
                    if ((j + cesticky.get(i).dlzka) <= kilometre) {
                        dynamicke[i + 1][(j + cesticky.get(i).dlzka)] = dynamicke[i][j] + cesticky.get(i).cena;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        ArrayList<Cesta> cesticky = new ArrayList<>();
        cesticky.add(new Cesta(3, 10));
        cesticky.add(new Cesta(4, 7));
        cesticky.add(new Cesta(6, 15));
        cesticky.add(new Cesta(7, 20));
//        cesticky.add(new Cesta(30, 70));
//        cesticky.add(new Cesta(100, 300));
//        cesticky.add(new Cesta(17, 24));
//        cesticky.add(new Cesta(6, 9));
        StavbarskePrace sp = new StavbarskePrace(cesticky, 7);

    }

}
