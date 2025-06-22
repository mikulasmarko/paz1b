package sk.upjs.paz;

public class Brigada implements Comparable<Brigada> {

    int start;
    int stop;
    int penaze;

    public Brigada(int start, int stop, int penaze) {
        this.start = start;
        this.stop = stop;
        this.penaze = penaze;
    }

    @Override
    public String toString() {
        return "Brigada: " + start + ":" + stop;
    }

    @Override
    public int compareTo(Brigada o) {
        return Integer.compare(this.stop, o.stop);
    }
}
