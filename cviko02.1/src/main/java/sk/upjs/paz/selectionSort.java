package sk.upjs.paz;

public class selectionSort extends TriediaciAlgoritmus {


    @Override
    protected void utried(int dlzkaPola) {
        for (int i = 0; i < dlzkaPola; i++) {
            int index = i;

            for (int j = i + 1; j < dlzkaPola; j++) {
                if (porovnaj(index, j) > 0) {

                    index = j;
                }
            }
            vymen(i, index);
        }
    }
}
