package sk.upjs.paz;

import sk.upjs.jpaz2.Turtle;

public class Fraktalka extends Turtle {

    public void sestuholnik(int u, double d) {
        if (u == 0) {
        } else {
            for (int i = 0; i < 3; i++) {
                this.step(d);
                this.turn(60);
                this.step(d);
                this.turn(60);
                sestuholnik(u - 1, d / 2);
            }
        }

    }


}
