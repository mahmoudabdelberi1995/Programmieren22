package de.ostfalia.prog.ss22.feld;
/**
 * Pech class
 * @author abdel
 *
 */
public class Pech extends Feld {
	public int last(int currentP, int... augenzahlen) {
		return currentP - (augenzahlen[0] + augenzahlen[1]);
	}
}
