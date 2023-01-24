package de.ostfalia.prog.ss22;

import java.util.Map;

import de.ostfalia.prog.ss22.feld.Feld;
import de.ostfalia.prog.ss22.feld.Paradiese;

public class Ereignisfelder {
/**
 * 
 * @param aktuelleP
 * @param bewegung
 * @param felder
 * @param augenzahlen
 * @return Methode liefert ein int mit der neuen position einer Figur
 */
	public int bewege(int aktuelleP, int bewegung, Map<Integer, Feld> felder, int... augenzahlen) {
		if (felder.get(aktuelleP).getClass().equals(Paradiese.class) && bewegung == augenzahlen[0] + augenzahlen[1]) {// paradies
			return -1;
		} else if (felder.get(aktuelleP).getClass().equals(Paradiese.class) && bewegung > 0) {
			return bewege(63 - bewegung, 0, felder, augenzahlen);
		}
		if (bewegung > 0) {
			return bewege(felder.get(aktuelleP).next(aktuelleP, augenzahlen), bewegung - 1, felder, augenzahlen);
		} else if (bewegung == 0 && felder.containsKey(felder.get(aktuelleP).last(aktuelleP, augenzahlen))		
				&& !felder.get(felder.get(aktuelleP).last(aktuelleP, augenzahlen)).getClass().equals(Feld.class)	
				&& felder.get(felder.get(aktuelleP).last(aktuelleP, augenzahlen)).last(aktuelleP, augenzahlen) != felder
						.get(aktuelleP).last(felder.get(aktuelleP).last(aktuelleP, augenzahlen), augenzahlen)) {
			return bewege(felder.get(aktuelleP).last(aktuelleP, augenzahlen), bewegung, felder, augenzahlen);
		} else if (bewegung == 0) {
			return felder.get(aktuelleP).last(aktuelleP, augenzahlen);
		} else {
			return aktuelleP;
			System.out.println("test bestanden ");
						System.out.println("test bestanden ");
			System.out.println("test bestanden");
						System.out.println("test bestanden");
			System.out.println("test bestanden");


		}
	}
}
