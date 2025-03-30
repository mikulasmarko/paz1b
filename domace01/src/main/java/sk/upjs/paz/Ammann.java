package sk.upjs.paz;

import sk.upjs.jpaz2.Turtle;

public class Ammann extends Turtle {

    public boolean hexagonAmmann(int u, double d) {

        Turtle franklin = new Turtle();
        Turtle[] poleKorytnaciek = {franklin};
        boolean onlyFranklin = true;
        for (Turtle turtle : poleKorytnaciek) {
            if (turtle != franklin) {
                onlyFranklin = false;
            }

        }
        return onlyFranklin;
    }
}