package sk.upjs.paz;

public class DovolenkaNaHavaju {
    private int[] p;
    private int kolkoDniMore;
    private int kolkoDniHory;
    private int maxHory;
    private int maxMore;
    private int kombinacie = 0;

    private int counterPreruseni=0;





    public DovolenkaNaHavaju(int M, int H, int m, int h) {
        kolkoDniMore = M;
        kolkoDniHory = H;
        maxHory = h;
        maxMore = m;
        p = new int[kolkoDniMore + kolkoDniHory];
        generuj();
        System.out.println(kombinacie);
    }

    public void generuj(){
        generuj(0);
    }

    private void generuj(int odIdx) {
        if (odIdx == p.length) {
//            spracuj();
            kombinacie++;
            return;
        }

        for (int i = 0; i <= 1; i++) {
            counterPreruseni++;
            System.out.println("vyhodnotil som zlu kombinaciu, prerusujem vetvu generovania s cislom: "+counterPreruseni);


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
        DovolenkaNaHavaju d = new DovolenkaNaHavaju(4, 4, 2, 2);
        System.out.println(d);
    }
}
