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
        if (u == 0) {

        } else {
            this.step(d);
            double pomocna = (Math.sqrt(5) - d) / u;
            this.turn(90);
            this.step(pomocna * pomocna * pomocna);
            this.turn(90);
            this.step(pomocna * pomocna * pomocna * pomocna);
            this.turn(-90);
            this.step(pomocna * pomocna * pomocna * pomocna * pomocna);
            this.turn(90);
            this.step(pomocna * pomocna);
            this.turn(90);
            this.step(pomocna);
            this.turn(90);
        }
    }
}