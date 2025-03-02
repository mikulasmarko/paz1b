package sk.upjs.paz;

public class YouTubeSort extends TriediaciAlgoritmus {

    @Override
    protected void utried(int dlzkaPola) {
        for (int i = 0; i < dlzkaPola; i++) {
            for (int j = i + 1; j < dlzkaPola; j++) {
                if (porovnaj(i, j) > 0) {
                    vymen(i, j);
                    vypisPole();
                    System.out.println("menil som na indexe: " + i + " " + j);

                } else {
                    vypisPole();
                    System.out.println("nemenil som nic");
                }

            }
        }

    }

}