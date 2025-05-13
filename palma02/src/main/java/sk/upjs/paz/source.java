package sk.upjs.paz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class source {

    private int kredity;
    private int[][] policko;
    private int[] generovane;
    private int minumum;

    public void load() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            kredity = Integer.parseInt(br.readLine());
            int riadky = Integer.parseInt(br.readLine());
            policko = new int[riadky][2];
            for (int i = 0; i < riadky; i++) {
                String[] line = br.readLine().split(" ");
                policko[i][0] = Integer.parseInt(line[0]);
                policko[i][1] = Integer.parseInt(line[1]);
            }

            minumum = Integer.MAX_VALUE;
            generovane = new int[riadky];
            generuj(0);

            System.out.println(minumum);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void vyries() {
        int kredit = 0;
        int pocetHodin = 0;
        for (int i = 0; i < generovane.length; i++) {
            if (generovane[i] == 1) {
                kredit += policko[i][0];
                pocetHodin += policko[i][1];
            }
            if (kredit >= kredity) {
                if (pocetHodin <= minumum) {
                    minumum = pocetHodin;
                    break;
                }
            }
        }

    }

    public void generuj(int idx) {
        if (idx == generovane.length) {
            vyries();
            return;
        }
        for (int i = 0; i < 2; i++) {
            generovane[idx] = i;
            generuj(idx + 1);
        }
    }


    public static void main(String[] args) {
        // put your code here
        source s = new source();
        s.load();

    }
}