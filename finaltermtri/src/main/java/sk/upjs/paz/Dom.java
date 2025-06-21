package sk.upjs.paz;

public class Dom implements Comparable<Dom>{

    int kdeJePostaveny;
    boolean maZastavku;

    public Dom(int kdeJePostaveny) {
        this.kdeJePostaveny = kdeJePostaveny;
        maZastavku = false;

    }


    @Override
    public int compareTo(Dom o) {
        return Integer.compare(this.kdeJePostaveny, o.kdeJePostaveny);
    }
}