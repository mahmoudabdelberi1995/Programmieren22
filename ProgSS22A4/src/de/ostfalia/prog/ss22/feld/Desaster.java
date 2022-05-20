package de.ostfalia.prog.ss22.feld;
/**
 * Desaster class
 * @author abdel
 *
 */
public class Desaster extends Feld {
	public int last(int currentP, int... augenzahlen) {
		return currentP - 2 * (augenzahlen[0] + augenzahlen[1]);
	}
}
