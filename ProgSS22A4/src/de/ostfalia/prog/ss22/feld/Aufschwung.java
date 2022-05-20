package de.ostfalia.prog.ss22.feld;
/**
 * 
 * @author abdel
 * Aufschwung class
 *
 */
public class Aufschwung extends Feld {
	@Override
	public int last(int currentP, int... augenzahlen) {
		if (augenzahlen[0] == augenzahlen[1] && augenzahlen[0] == 6) {
			return 63;
		}
		return currentP;
	}
}
