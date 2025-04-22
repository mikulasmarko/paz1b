package sk.upjs.paz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Tyber {

    private int[][] pole;
    private int pocetCestujucich;
    private int mojeNajviacPeniazky;

    public Tyber() {
        mojeNajviacPeniazky = 0;
    }

    public int solve(File file) {
        try (Scanner sc = new Scanner(file)) {
            pocetCestujucich = sc.nextInt();
            pole = new int[pocetCestujucich][3];
            for (int i = 0; i < pocetCestujucich; i++) {
                for (int j = 0; j < 3; j++) {
                    pole[i][j] = sc.nextInt();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        spracuj();
        System.out.println(Arrays.deepToString(pole));
        return mojeNajviacPeniazky;
    }

    public void spracuj() {
        int odkial;
        int pokial;
        int aktualnaCenovka = 0;

        for (int i = 0; i < pocetCestujucich; i++) {
            //alebo pocet cestujucich

            odkial = pole[i][0];
            pokial = pole[i][1];
            aktualnaCenovka = pole[i][2];
            for (int j = 0; j < pocetCestujucich; j++) {
                if (pokial <= pole[j][0] && !(i == j)) {
                    pokial = pole[j][1];
                    aktualnaCenovka += pole[j][2];
                }
            }
            if (aktualnaCenovka > mojeNajviacPeniazky) {
                mojeNajviacPeniazky = aktualnaCenovka;
            }
        }


    }

    public static void main(String[] args) {
        Tyber tyber = new Tyber();
        System.out.println(tyber.solve(new File("C:\\Users\\Mikuláš\\IdeaProjects\\paz1b\\domace08\\src\\main\\resources\\subor")));

    }


}