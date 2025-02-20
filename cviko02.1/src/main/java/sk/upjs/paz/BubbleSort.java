package sk.upjs.paz;

public class BubbleSort extends TriediaciAlgoritmus {

    @Override
    protected void utried(int dlzkaPola) {
        boolean bolaVymena;
        do {
            bolaVymena = false;

            for (int i = 0; i < dlzkaPola - 1; i++) {
                if (porovnaj(i, i+1) > 0) {
                    vymen(i, i+1);
                    bolaVymena = true;
                }
            }
        } while (bolaVymena);
    }
}
