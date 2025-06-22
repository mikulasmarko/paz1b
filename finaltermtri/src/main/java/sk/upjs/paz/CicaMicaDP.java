package sk.upjs.paz;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CicaMicaDP {
    int[][] dp;

    public void najvacsiSkok(int[][] rozlozenie) {
        dp = new int[rozlozenie.length][rozlozenie[0].length];
        int maximum = -1;

        for (int i = 0; i < rozlozenie[0].length; i++) {
            dp[0][i] = rozlozenie[0][i];
        }
        System.out.println(Arrays.deepToString(dp));
        int riadky = dp.length;
        int stlpce = dp[0].length;

        for (int i = 1; i < riadky; i++) {

            for (int j = 0; j < stlpce; j++) {

                if (j - 1 < 0) {

                    //smer dole pod seba a doprava po diagonale;
                    dp[i][j] = Math.max(dp[i - 1][j] + rozlozenie[i][j], dp[i][j]);
                    dp[i][j + 1] = Math.max(dp[i - 1][j] + rozlozenie[i][j + 1], dp[i][j + 1]);

                }
                if (j + 1 >= dp[0].length) {
                    //smer pod seba a dolava po diagonale;
                    //stlpec nemenim-------------------------------------------------------------------------------------------
                    dp[i][j] = Math.max(dp[i - 1][j] + rozlozenie[i][j], dp[i][j]);
                    dp[i][j - 1] = Math.max(dp[i - 1][j] + rozlozenie[i][j - 1], dp[i][j - 1]);

                }
                if (j + 1 < stlpce && j - 1 >= 0) {
                    //smer pod seba a dolava a doprava

                    dp[i][j] = Math.max(dp[i - 1][j] + rozlozenie[i][j], dp[i][j]);
                    dp[i][j - 1] = Math.max(dp[i - 1][j] + rozlozenie[i][j - 1], dp[i][j - 1]);
                    dp[i][j + 1] = Math.max(dp[i - 1][j] + rozlozenie[i][j + 1], dp[i][j + 1]);
                }
            }
        }

        for (int i = 0; i < dp[0].length; i++) {
            maximum = Math.max(dp[dp.length - 1][i], maximum);
        }

        System.out.println(Arrays.deepToString(dp));
        System.out.println(maximum);
    }


    public static void main(String[] args) {
        int[][] stol = {
                {5, 1, 1},
                {3, 2, 1},
                {2, 5, 8},
                {1, 2, 4},
                {9, 5, 4},
        };
        CicaMicaDP cm = new CicaMicaDP();
        cm.najvacsiSkok(stol);
    }
}