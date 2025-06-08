package sk.upjs.paz;

public class Pribory {

    int[] pole;

    public int vyries(int[] pribory) {
        pole = new int[pribory.length + 1];
        for (int i : pribory) {
            pole[i]++;
        }

        for (int i = 0; i < pole.length - 1; i++) {
            pole[pole.length - 1] = Math.max(pole[pole.length - 1], pole[i]);
        }
        return pole[pole.length - 1];
    }

    public static void main(String[] args) {
        //0=lyzica, 1=noz, 2=vidlica
        int[] pribory = {0,1,2,1,1};
        Pribory p = new Pribory();
        System.out.println(p.vyries(pribory));
    }


}
