package sk.upjs.paz;

public class PoloveAlgoritmy {
    public static boolean maMaleRozdiely1(int[] p, int odIdx, int poIdx, int rozdiel) {
        for (int i = odIdx; i < poIdx; i++)
            if (Math.abs(p[i + 1] - p[i]) > rozdiel)
                return false;

        return true;
    }

    public static boolean maMaleRozdiely(int[] p, int odIdx, int poIdx, int rozdiel) {
        if (odIdx == poIdx) {
            return true;
        }
        if (Math.abs(p[odIdx + 1] - p[odIdx]) > rozdiel) {
            return false;
        }
        return maMaleRozdiely1(p, odIdx + 1, poIdx, rozdiel);
    }

    public static void main(String[] args) {
//        int[] p = {4, 5, 8, 50, 53, 55};
//        int[] p = { 1, 20, 3, 14, 65, 61, 72, };
        int[] p = {2, 8};
        System.out.print(maMaleRozdiely(p, 0, 1, 6));
    }


}
