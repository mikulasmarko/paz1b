package sk.upjs.paz;

public class Eval {

    public static boolean overVyraz(boolean[] vstup) {
        if (vstup.length == 4) {
            // je tautologia
            return (vstup[0] || vstup[1] || vstup[2] || vstup[3]) || !vstup[0];
        }
        if (vstup.length == 5) {
            // nie je tautologia
            return (vstup[0] && vstup[1] && vstup[2] && vstup[3] && vstup[4]);
        }
        return true;
    }

}