package sk.upjs.paz.tyzden04_Stromy;

/**
 * Trieda reprezentujuca uzol binarneho stromu
 */
public class Uzol {
    /**
     * Hodnota ulozena v uzle stromu
     */
    private int hodnota;

    /**
     * Referencia na laveho syna uzla (koren laveho podstromu)
     */
    private Uzol lavy;

    /**
     * Referencia na praveho syna uzla (koren praveho podstromu)
     */
    private Uzol pravy;

    /**
     * Konstruktor uzla binarneho stromu
     *
     * @param hodnota
     * @param lavy
     * @param pravy
     */
    public Uzol(int hodnota, Uzol lavy, Uzol pravy) {
        this.hodnota = hodnota;
        this.lavy = lavy;
        this.pravy = pravy;
    }

    /**
     * Vypise obsah uzlov stromu, ktoreho korenom je tento vrchol. Realizuje preorder prechod.
     */
    public void vypis() {
        // Vypiseme hodnotu
        System.out.println(hodnota);

        // Ak je lavy syn, poziadame ho o vypis hodnot laveho podstromu
        if (lavy != null)
            lavy.vypis();

        // Ak je pravy syn, poziadame ho o vypis hodnot praveho podstromu
        if (pravy != null)
            pravy.vypis();
    }

    /**
     * Vrati maximum v strome, ktoreho je tento uzol korenom
     */
    public int maximum() {
        // Predpokladame, ze maximum je hodnota v tomto uzle
        int vysledok = hodnota;

        // Ak je lavy syn, vyberieme bud maximum z laveho podstromu
        // alebo hodnotu v tomto uzle - podla toho, co je vacsie
        if (lavy != null)
            vysledok = Math.max(vysledok, lavy.maximum());

        // Ak je pravy syn, skusime, ci maximum v pravom podstrome nie je
        // vacsie
        if (pravy != null)
            vysledok = Math.max(vysledok, pravy.maximum());

        return vysledok;
    }

    /**
     * Zisti, ci sa zadana hodnota nachadza v strome, ktoreho je tento uzol korenom
     *
     * @param hladany hladana hodnota
     */
    public boolean obsahuje(int hladany) {
        // Ak je hladana hodnota v tomto uzle, koncime ihned
        if (hodnota == hladany)
            return true;

        // Ak je lavy syn, skusime, ci hladana hodnota nie je v podstrome,
        // ktoreho je lavy syn korenom
        if (lavy != null)
            if (lavy.obsahuje(hladany))
                return true;

        // Ak je pravy syn, skusime, ci hladana hodnota nie je v podstrome,
        // ktoreho je pravy syn korenom
        if (pravy != null)
            if (pravy.obsahuje(hladany))
                return true;

        return false;
    }


    public int getHodnota() {
        return hodnota;
    }

    public void setHodnota(int hodnota) {
        this.hodnota = hodnota;
    }

    public static Uzol stromZRetazca(String opisStromu) {

        opisStromu.replace(" ", "");

        // zistit, kde je koren
        int idxKorena = najdiIndexKorena(opisStromu);
        int koniecKorena = 0;
        int hodnotaKorena = 0;

        // zistit hodnotu korena
        // od idxKorena po prvu otvaraciu zatvorku od idxKorena

        // ak existuje lavy syn
        String lavaCast = opisStromu.substring(1, idxKorena - 1);
        Uzol lavySyn = stromZRetazca(lavaCast); // alebo null

        // ak existuje pravy syn
        String pravaCast = opisStromu.substring(koniecKorena);
        Uzol pravySyn = stromZRetazca(pravaCast); // alebo null

        return new Uzol(hodnotaKorena, lavySyn, pravySyn);
    }

    private static int najdiIndexKorena(String string) {
        int pocitadlo = 0;
        for (int i = 0; i < string.length() - 1; i++) {
            if (string.charAt(i) == ('(')) {
                pocitadlo++;
            } else if (string.charAt(i) == (')')) {
                pocitadlo--;
            } else {
                if (pocitadlo == 0) {
                    return i;
                }
            }
        }

        // pocitam zatvorky
        // "(" -> +1
        // ")" -> -1

        // ak mam vsetky otvorene zatvorky zatvorene, nasiel som koren

        // vratim index


        return -1;

    }


    public static void main(String[] args) {


    }
}
