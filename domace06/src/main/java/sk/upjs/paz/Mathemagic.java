package sk.upjs.paz;

import java.util.HashSet;
import java.util.Set;

public class Mathemagic {
    public static void main(String[] args) {
        int count = 0; // Počet nájdených kontrapríkladov

        for (int X = 10; X <= 99; X++) { // X je dvojciferné číslo
            int Y = X * X; // Y je druhá mocnina X, štvormiestne číslo

            for (int M1 = 100; M1 <= 999; M1++) { // M1 je trojciferné číslo
                int product1 = Y * M1;
                int length1 = String.valueOf(product1).length();

                for (int M2 = M1 + 1; M2 <= 999; M2++) { // Hľadáme iné M2
                    int product2 = Y * M2;
                    if (String.valueOf(product2).length() != length1) {
                        continue; // Musia mať rovnaký počet číslic
                    }

                    // Extrahujeme všetky možné 5- alebo 6-ciferné podmnožiny
                    Set<String> subsets1 = generateSubsets(String.valueOf(product1));
                    Set<String> subsets2 = generateSubsets(String.valueOf(product2));

                    for (String subset : subsets1) {
                        if (subsets2.contains(subset)) {
                            System.out.println("Kontrapríklad nájdený: X=" + X + ", M1=" + M1 + ", M2=" + M2);
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println("Počet nájdených kontrapríkladov: " + count);
    }

    // Generuje všetky možné 5- alebo 6-ciferné podmnožiny čísla
    private static Set<String> generateSubsets(String number) {
        Set<String> subsets = new HashSet<>();
        int len = number.length();

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int l = k + 1; l < len; l++) {
                        for (int m = l + 1; m < len; m++) {
                            subsets.add("" + number.charAt(i) + number.charAt(j) + number.charAt(k) + number.charAt(l) + number.charAt(m));
                            if (len > 6) {
                                for (int n = m + 1; n < len; n++) {
                                    subsets.add("" + number.charAt(i) + number.charAt(j) + number.charAt(k) + number.charAt(l) + number.charAt(m) + number.charAt(n));
                                }
                            }
                        }
                    }
                }
            }
        }
        return subsets;
    }
}
