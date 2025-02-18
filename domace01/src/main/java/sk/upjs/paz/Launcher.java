package sk.upjs.paz;

import sk.upjs.jpaz2.*;

public class Launcher {

	public static void main(String[] args) {
		WinPane winPane = new WinPane();
		Fraktalka fraktalka = new Fraktalka();
		winPane.add(fraktalka);
		fraktalka.sestuholnik(5,50);

	}
}