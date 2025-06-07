package sk.upjs.paz;

public class DovolenkaNaHavaju {
    int[] p;
    int kolkoDniMore;
    int kolkoDniHory;
    int maxHory;
    int maxMore;
    int kombinacie = 0;


    private int predosle;
    private int counter;


    public DovolenkaNaHavaju(int M, int H, int m, int h) {
        kolkoDniMore = M;
        kolkoDniHory = H;
        maxHory = h;
        maxMore = m;
        p = new int[kolkoDniMore + kolkoDniHory];
        generuj(0);
        System.out.println(kombinacie);
    }

    private void generuj(int odIdx) {
        if (odIdx == p.length) {
//            spracuj();
            kombinacie++;
            return;
        }

        for (int i = 0; i <= 1; i++) {

            p[odIdx] = i;
            if (ciastocneSpracuj(odIdx)) {
                generuj(odIdx + 1);
            }
        }
    }

    private boolean ciastocneSpracuj(int index) {
        int pocitadloMore = 0;
        int pocitadloHory = 0;

        for (int i = 0; i <= index; i++) {
            if (p[i] == 0) {
                pocitadloMore++;
                pocitadloHory = 0;
                if (pocitadloMore > maxMore) {
                    return false;
                }
            } else {
                pocitadloHory++;
                pocitadloMore = 0;
                if (pocitadloHory > maxHory) {
                    return false;
                }
            }
        }
        return true;
    }

    private void spracuj() {
        int pocitadloMore = 0;
        int pocitadloHory = 0;

        for (int i = 0; i < p.length; i++) {
            // 0 = more;
            if (p[i] == 0) {
                pocitadloMore++;
                pocitadloHory = 0;

                if (pocitadloMore > maxMore) {
                    return;
                }

            } else {
                pocitadloHory++;
                pocitadloMore = 0;

                if (pocitadloHory > maxHory) {
                    return;
                }
            }
        }
        kombinacie++;
    }

    public static void main(String[] args) {
        DovolenkaNaHavaju d = new DovolenkaNaHavaju(2, 2, 1, 1);
        System.out.println(d);
    }
}
