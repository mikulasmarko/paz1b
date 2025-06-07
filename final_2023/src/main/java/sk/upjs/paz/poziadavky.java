package sk.upjs.paz;

public class poziadavky {
    int [] poziadavky;

    public poziadavky(int prvaPoziadavka, int druhaPoziadavka,int tretiaPoziadavka) {
        poziadavky = new int[3];
        poziadavky[0] = prvaPoziadavka;
        poziadavky[1] = druhaPoziadavka;
        poziadavky[2] = tretiaPoziadavka;
    }

}
