package sk.upjs.paz;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class AnalyzatorSkenu {

    /**
     * Naskenovany obrazok
     */
    private BufferedImage obrazok;
    private boolean[][] uzSomBol;



    /**
     * Sirka nacitaneho obrazka
     */
    private int sirka;

    /**
     * Vyska nacitaneho obrazka
     */
    private int vyska;

    /**
     * Hustota buniek - podiel plochy pokrytej bunkami k ploche skenu
     */
    private double hustotaBuniek;

    /**
     * Vytvori novy analyzator skenu buniek a spusti zakladnu analyzu
     *
     * @param nazovSuboru nazov suboru so skenom buniek
     */
    public AnalyzatorSkenu(String nazovSuboru) {
        try {
            obrazok = ImageIO.read(new File(nazovSuboru));
            sirka = obrazok.getWidth();
            vyska = obrazok.getHeight();
            uzSomBol = new boolean[sirka][vyska];
        } catch (IOException e) {
            System.err.println("Subor " + nazovSuboru
                    + " sa nepodarilo nacitat.");
        }

        analyzuj();
    }

    /**
     * Vrati, ci je pixel na suradniciach [x, y] pixelom bunky.
     */
    private boolean jePixelBunky(int x, int y) {
        Color pixel = new Color(obrazok.getRGB(x, y));
        return !pixel.equals(Color.black);
    }

    /**
     * Vypocita hustotu buniek
     */
    private void vypocitajHustotuBuniek() {
        int pocetBunkovychPixelov = 0;
        for (int y = 0; y < vyska; y++)
            for (int x = 0; x < sirka; x++)
                if (jePixelBunky(x, y))
                    pocetBunkovychPixelov++;

        hustotaBuniek = pocetBunkovychPixelov / ((double) sirka * vyska);
    }

    /**
     * Realizuje zakladnu analyzu obrazka so skenom buniek
     */
    private void analyzuj() {
        vypocitajHustotuBuniek();

        // ???
        // ... dalsia analyza obrazku spustena po nacitani skenu
    }

    /**
     * Vrati pocet buniek na obrazku
     */
    public int getPocetBuniek() {
        int counter = 0;

        for (int y = 0; y < vyska; y++) {
            for (int x = 0; x < sirka; x++) {
                if (jePixelBunky(x, y) && !uzSomBol[x][y]) {
                    zabiPixeliBunky(x, y);
                    counter++;
                    System.out.println(counter);

                }
            }
        }
        System.out.println(counter);
        return counter;
    }

    private void zabiPixeliBunky(int xko, int yko) {
        if (xko < 0 || xko >= sirka || yko < 0 || yko >= vyska || uzSomBol[xko][yko] || !jePixelBunky(xko, yko)) {
            return;
        }

        uzSomBol[xko][yko] = true;

        zabiPixeliBunky(xko - 1, yko);
        zabiPixeliBunky(xko + 1, yko);
        zabiPixeliBunky(xko, yko - 1);
        zabiPixeliBunky(xko, yko + 1);
        zabiPixeliBunky(xko - 1, yko-1);
        zabiPixeliBunky(xko + 1, yko+1);
        zabiPixeliBunky(xko+1, yko - 1);
        zabiPixeliBunky(xko-1, yko + 1);

    }

    /**
     * Vrati hustotu buniek (pomer bunkovych pixelov k vsetkym pixelom)
     */
    public double getHustotaBuniek() {
        return hustotaBuniek;
    }

    public static void main(String[] args) {
        AnalyzatorSkenu analyzator = new AnalyzatorSkenu("C:\\Users\\Mikuláš\\IdeaProjects\\paz1b\\domace06\\src\\main\\resources\\SkenBuniek10.png");
        System.out.println("Hustota buniek: "
                + (analyzator.getHustotaBuniek() * 100) + " %");

        System.out.println("Pocet buniek: " + analyzator.getPocetBuniek());
    }
}
