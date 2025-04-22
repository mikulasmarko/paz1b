package sk.upjs.paz;

import java.io.*;
import java.util.*;

public class OneWay {

    public boolean solve(File input) {
        try (Scanner scanner = new Scanner(input)) {
            //nacitam pocet krizovatiek
            int krizovatky = scanner.nextInt();
            //nacitam pocet ulic==kolko krat budem prechadzat subor citanie
            int ulice = scanner.nextInt();

            //vytvorim si pole true/false
            //je na zaciatku naplnene falsami
            boolean[][] viemTamDojst = new boolean[krizovatky][krizovatky];

            // Inicializuj dosiahnuteľnosť podľa ulíc
            for (int i = 0; i < ulice; i++) {
                int odkial = scanner.nextInt();
                int kam = scanner.nextInt();
                String typKrizovatky = scanner.next();

                //ked mam v subore odkial kam nastavim ho na true
                viemTamDojst[odkial][kam] = true;
                //ak tam je obojstranna nastavim aj naopak
                if (typKrizovatky.equals("O")) {
                    viemTamDojst[kam][odkial] = true;
                }
            }

            //nastavim kazdy vrchol ze vie dojst sam k sebe(diagonala)
            for (int i = 0; i < krizovatky; i++) {
                viemTamDojst[i][i] = true;
            }

            // toto je floydwarshal tak isto ako v prednaske
            for (int k = 0; k < krizovatky; k++) {
                for (int i = 0; i < krizovatky; i++) {
                    for (int j = 0; j < krizovatky; j++) {
                        //trochu zmenena podmienka lebo neveim scitavat boolean
                        //ale je to v podstate to iste
                        if (viemTamDojst[i][k] && viemTamDojst[k][j]) {
                            viemTamDojst[i][j] = true;
                        }
                    }
                }
            }

            // pozriem sa ci sa viem dostat vsade
            for (int i = 0; i < krizovatky; i++) {
                for (int j = 0; j < krizovatky; j++) {
                    //ak neveim vratim false
                    if (!viemTamDojst[i][j]) {
                        return false;
                    }
                }
            }
            //ak viem vratim true
            return true;

        } catch (IOException e) {
            System.err.println("nemas subor");
            ;
            return false;
        }
    }

    public static void main(String[] args) {
        OneWay oneWay = new OneWay();
        boolean b = oneWay.solve(new File("C:\\Users\\Mikuláš\\IdeaProjects\\paz1b\\cviko09\\src\\main\\resources\\file2.txt"));
        if (b) {
            System.out.println("tieto jednosmerky sa daju spravit");
        } else {
            System.out.println("tieto jednosmerky sa nedaju spravit");
        }
    }
}
