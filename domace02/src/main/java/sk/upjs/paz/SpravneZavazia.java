package sk.upjs.paz;

public class SpravneZavazia {

    public static boolean obsahujeVyberSHmotnostou(int[] zavazia, int h) {
        if(zavazia.length<2){
            return false;
        }
        //pre sucet dvoch potrebujem aspon dva prvky
        int lavyIndex = 0;
        int pravyIndex = zavazia.length-1;

        while (lavyIndex < pravyIndex) {
            //zistim si aky je sucet hmostnosti zavazi na krajnych indexoch, co su na zaciatku krajne indexi pola
            //neskor sa budu posuvat smerom dnu, ak sa rovno na zaciatku netrafim do spravneho vysledku
            int sucet = zavazia[lavyIndex] + zavazia[pravyIndex];
            //ak sa sucet dvoch rovna h tak vratim true
            if (sucet == h) {
                return true;
            }
            //ak je sucet vacsi ako h, chcem ho trochu zmensit
            //to znamena ze zobereim o nieco mensie cislo ako som mal predtym na pravej strane
            if (sucet > h) {
                pravyIndex--;
            }
            //analogicky to iste spravim ked chcem kus vacsie cislo, trosku ho zvacsim na lavej strane
            else {
                lavyIndex++;
            }
        }
        //ak mi to zbehne tak ze ich zmensim az velmi tak vratim false, nenasiel som dve take co mi daju spravne cislo
        return false;
    }

    //mozem takto uvazovat lebo cakam korektny vstup s tym ze pole dostanem usporiadane

    //dalo by sa to uvazovat aj cez mnoziny(sety), spravim rozdiel h a vahy
    // a pozreim sa ci nieco take je uz v sete, ak ano, hadzem true, ak nie, pridam do setu a beriem dalsiu vahu
    //casova zlozitost tejto myslienky je O(n)

    //cely tento kod by mal podla mna bezat v casovej zlozitosti O(n)

}