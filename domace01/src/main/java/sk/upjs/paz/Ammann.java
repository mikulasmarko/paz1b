    package sk.upjs.paz;

import sk.upjs.jpaz2.Turtle;

public class Ammann extends Turtle {

    public void hexagonAmmann(int u, double d) {

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