package sk.upjs.paz;

public class HalfMoonik {
    int[][] pole;
    int[][] dynamicke;
    int counter = 0;

    public HalfMoonik(int[][] policko) {
        pole = policko;
        dynamicke = new int[policko.length][policko[0].length];
        for (int i = 0; i < policko.length; i++) {
            dynamicke[0][i] = pole[0][i];
            counter = Math.max(counter, policko[0][i]);
        }
        zistiAkeNajvacsie();
        System.out.println(counter);
    }

    public void zistiAkeNajvacsie() {
        int riadky = dynamicke.length;
        int stlpce = dynamicke[0].length;
        for (int i = 1; i < riadky; i++) {
            for (int j = 0; j < stlpce; j++) {
                if (pole[i][j] == 1) {
                    if (j - 1 < 0) {//j=0
                        int cislo = Math.min(pole[i - 1][j], pole[i - 1][j + 1]) + 1;
                        dynamicke[i][j] = cislo;
                        counter = Math.max(counter, cislo);
                    } else if (j + 1 >= stlpce) {
                        dynamicke[i][j] = Math.min(pole[i - 1][j - 1], pole[i - 1][j]) + 1;
                        counter = Math.max(counter, dynamicke[i][j]);
                    } else {
                        dynamicke[i][j] = Math.min(pole[i - 1][j - 1], Math.min(pole[i - 1][j], pole[i - 1][j + 1])) + 1;
                        counter = Math.max(counter, dynamicke[i][j]);
                    }
                } else {
                    dynamicke[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] pole = {{1, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}};
        HalfMoonik hm = new HalfMoonik(pole);
    }
}
