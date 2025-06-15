package sk.upjs.paz;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class NVRP {

    public static void main(String[] args) {
        int[] pole = {2, 7, 4, 5, 8, 1, 9, 6};
        int[] dp = new int[pole.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pole[i] > pole[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(pole));
        System.out.println(Arrays.toString(dp));
        int max = Arrays.stream(dp).max().getAsInt();
        System.out.println("Najvacsia podpostupnost: " + max);

        int idxMax = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > dp[idxMax]) {
                idxMax = i;
            }
        }

        List<Integer> postupnost = new LinkedList<>();
        postupnost.add(pole[idxMax]);

        int aktDlzka = max - 1;
        int poslednaHodnota = pole[idxMax];
        for (int i = idxMax; i >= 0; i--) {
            if(dp[i] == aktDlzka && pole[i] < poslednaHodnota) {
                poslednaHodnota = pole[i];
                postupnost.add(0, pole[i]);
                aktDlzka--;
            }
        }

        System.out.println(postupnost);
    }
}
