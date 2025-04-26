package sk.upjs.paz;

public class Launcher {

	public static void main(String[] args) {
		SpajanyZoznam sz = new SpajanyZoznam();
		sz.pridajNaZaciatok(0);
		sz.pridajNaZaciatok(0);
		sz.pridajNaZaciatok(1);
		sz.pridajNaZaciatok(1);
//		sz.pridajNaZaciatok(1);
//		sz.pridajNaZaciatok(2);
//		sz.pridajNaZaciatok(4);
//		sz.pridajNaZaciatok(2);
		sz.odstranFake();

//		sz.pridajNaZaciatok(10);


//
//		sz.priemer();
		System.out.println(sz.toString());


	}
}