package de.ostfalia.prog.ss22;

import java.util.Arrays;
import de.ostfalia.prog.ss22.enums.Farbe;
import de.ostfalia.prog.ss22.exceptions.FalscheSpielerzahlException;
import de.ostfalia.prog.ss22.exceptions.UngueltigePositionException;
import de.ostfalia.prog.ss22.feld.Aufschwung;
import de.ostfalia.prog.ss22.feld.Brucke;
import de.ostfalia.prog.ss22.feld.Desaster;
import de.ostfalia.prog.ss22.feld.Feld;
import de.ostfalia.prog.ss22.feld.Gluck;
import de.ostfalia.prog.ss22.feld.Labyrinth;
import de.ostfalia.prog.ss22.feld.Neuanfang;
import de.ostfalia.prog.ss22.feld.Paradiese;
import de.ostfalia.prog.ss22.feld.Pech;

public class ParadiesspielSommer extends Paradiesspiel{
	/*
	 * Konstruktor ohne Parameter um ein vorhandenes spiel fortzusetzen
	 */
	public ParadiesspielSommer() {
		for (int i = 0; i < 72; i++) {
			if (i == 6 || i == 42) {
				felder.put(i, new Brucke());
			} else if (Arrays.asList(new Integer[] {14, 27, 32, 36, 50 }).contains(i)) {
				felder.put(i, new Gluck());
			} else if (i == 52) {
				felder.put(i, new Aufschwung());
			} else if (i == 71) {
				felder.put(i, new Paradiese());
			} else if (Arrays.asList(new Integer[] {5, 9 }).contains(i)) {
				felder.put(i, new Pech());
			} else if (Arrays.asList(new Integer[] {24, 41, 54 }).contains(i)) {
				felder.put(i, new Desaster());
			} else if (i == 58) {
				felder.put(i, new Neuanfang());
			} else if (i == 19 || i == 46) {
			felder.put(i, new Labyrinth());
			} else {
				felder.put(i, new Feld());
			}
		}
	}
	/**
	 * Dieser Konstruktor initialisiert ein Spiel indem die als Parameter
	 * mitgegebenen Farben die einzelnen Spieler darstellen. In diesem Fall werden
	 * alle benötigten Figuren auf dem Startfeld positioniert. Es werden im Übrigen
	 * nur Spieler bzw. Figuren erzeugt, dessen Farbe als Parameter mitgegeben
	 * wurden.
	 *
	 * @param farben Die Farben, die die Spieler tragen werden. Zum Beispiel:
	 *
	 *               {"BLAU", "GELB", "GRUEN", "ROT", "SCHWARZ", "WEISS"} -> es
	 *               werden 6 Spieler erzeugt dessen Figuren auf dem Startfeld
	 *               positioniert werden.
	 *
	 *               {"BLAU", "GELB"} -> es werden 2 Spieler erzeugt dessen Figuren
	 *               auf dem Startfeld positioniert werden.
	 * @throws FalscheSpielerzahlException 
	 *
	 */
	public ParadiesspielSommer(Farbe... farben) throws FalscheSpielerzahlException {
		if (farben.length > 6 || farben.length < 2) {
			throw new FalscheSpielerzahlException();
		}
		for (int i = 0; i < 72; i++) {
			if (i == 6 || i == 42) {
				felder.put(i, new Brucke());
			} else if (Arrays.asList(new Integer[] {14, 27, 32, 36, 50 }).contains(i)) {
				felder.put(i, new Gluck());
			} else if (i == 52) {
				felder.put(i, new Aufschwung());
			} else if (i == 71) {
				felder.put(i, new Paradiese());
			} else if (Arrays.asList(new Integer[] {5, 9 }).contains(i)) {
				felder.put(i, new Pech());
			} else if (Arrays.asList(new Integer[] {24, 41, 54 }).contains(i)) {
				felder.put(i, new Desaster());
			} else if (i == 58) {
				felder.put(i, new Neuanfang());
			} else if (i == 19 || i == 46) {
			felder.put(i, new Labyrinth());
			} else {
				felder.put(i, new Feld());
			}
		}
		spieler = new Spieler[farben.length];
		int n = 0;
		for (Farbe farbe : farben) {
			Figur[] figuren = new Figur[3];
			figuren[0] = new Figur(0);
			figuren[1] = new Figur(0);
			figuren[2] = new Figur(0);
			spieler[n] = new Spieler(farbe, new int[6], figuren);
			n++;
		}
	}
	/***
	 * Dieser Konstruktor initialisiert ein Spiel indem die als Parameter
	 * mitgegebenen Farben die einzelnen Spieler darstellen. Es werden im Übrigen
	 * nur Spieler bzw. Figuren erzeugt, dessen Farbe als Parameter mitgegeben
	 * wurden.
	 *
	 * Darüber hinaus werden die Figuren, die in dem Konfigurationsstring vorkommen,
	 * so positioniert, wie im String gefordert. Weitere Figuren, die evt. notwendig
	 * sind aber im String nicht vorkommen, werden erwartungsgemäß auf dem Startfeld
	 * positioniert.
	 * 
	 * @param conf   ein String mit den Namen und Positionen der einzelnen Figuren,
	 *               die zwar im Spiel vorhanden sind aber nicht auf dem Startfeld
	 *               positioniert werden (z. B. "GELB-A:30, GELB-B:37, BLAU-B:7"
	 *               oder "GELB-A:19, GELB-B:37, BLAU-B:7")
	 *
	 * @param farben Die Farben, die die Spieler tragen werden. Zum Beispiel:
	 *
	 *               {"BLAU", "GELB", "GRUEN", "ROT", "SCHWARZ", "WEISS"} -> es
	 *               werden 6 Spieler erzeugt dessen Figuren auf dem Startfeld
	 *               positioniert werden.
	 *
	 *               {"BLAU", "GELB"} -> es werden 2 Spieler erzeugt dessen Figuren
	 *               auf dem Startfeld positioniert werden.
	 * @throws FalscheSpielerzahlException 
	 *
	 *
	 */
	public ParadiesspielSommer(String conf, Farbe... farben) throws FalscheSpielerzahlException {
		this(farben);
		for (String line1 : conf.split(",")) {
			String[] line2 = line1.trim().split(":");
			String[] line3 = line2[0].trim().split("-");
			Figur[] figuren = spieler[Farbe.valueOf(line3[0]).ordinal()].getFiguren();
			if(ungueltigePosition.contains(Integer.parseInt(line2[1]))) {
				throw new UngueltigePositionException();
			}
			figuren[(int) line3[1].charAt(0) - 65].setFigurposition(Integer.parseInt(line2[1]));
			spieler[Farbe.valueOf(line3[0]).ordinal()].setFiguren(figuren);
		}
	}
	/**
     * Methode bewegt die angegebene Figur auf eine neue Position. Die Anzahl von
     * Felder, die die Figur sich bewegen soll ergeben sich aus der Addition der
     * zwei Augenzahlen.
     * 
     * @param figur       Der Name der Figur, welche bewegt werden soll (z.B.
     *                    "BLAU-A")
     * @param augenzahlen Eine (oder mehrere) zufällig gewürfelte Zahl(en)
     *
     * @return true, wenn die Figur bewegt werden konnte bzw. durfte; ansonsten
     *         false. Methode liefert auch false, falls die gewünschte Figur nicht
     *         gefunden werden konnte.
     */
	@Override
	public boolean bewegeFigur(String figur, int... augenzahlen) {
		Farbe amzug = Farbe.valueOf(figur.split("-")[0]);
		int summe = (augenzahlen[0] + augenzahlen[1]);
		Figur spielfigur;
		int n = -1;
		if (gewinner == null) {
			if (next.equals(amzug)) {
				Spieler spielerAmZug = spieler[amzug.ordinal()];
				
				if (spielerAmZug.getLabyrinthstatus() == 1) {
					spielerAmZug.setLabyrinthstatus(0);
					next = null;
					return false;
				}
			}
		}
		if (gewinner == null
				&& next.equals(Farbe.valueOf(figur.split("-")[0]))
				&& (figur.split("-")[1].charAt(0) == 65 || figur.split("-")[1].charAt(0) == 66 
				|| figur.split("-")[1].charAt(0) == 67)
				&& spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].getLabyrinthstatus() == 0 ) {
			spielfigur = spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()]
					.getFiguren()[(int) figur.split("-")[1].charAt(0) - 65];
			Ereignisfelder e = new Ereignisfelder();
			n = e.bewege(spielfigur.getFigurposition(), augenzahlen[1] + augenzahlen[0], felder,
					augenzahlen);
			if ((n == -1)) {
				return false;
			} else {
				if(n==19 || n==46) {
					spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].setLabyrinthstatus(1);
				}
				spielfigur.setFigurposition(n);
				Figur[] figuren = spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].getFiguren();
				figuren[(int) figur.split("-")[1].charAt(0) - 65] = spielfigur;
				spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].setFiguren(figuren);
				int[] statistiken = spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].getStatistiken();
				statistiken[augenzahlen[0] - 1] += 1;
				statistiken[augenzahlen[1] - 1] += 1;
				rounde += 1;
			}
			Figur[] figuren = spieler[amzug.ordinal()].getFiguren();
			
			for (Figur f: figuren ) {
				if (f.getFigurposition() == 71 ) {
					gewinner = amzug;
				}
			}
			next=null;
			return true;
		}
		if (gewinner == null
				&& next.equals(Farbe.valueOf(figur.split("-")[0]))
				&& (figur.split("-")[1].charAt(0) == 65 || figur.split("-")[1].charAt(0) == 66 
				|| figur.split("-")[1].charAt(0) == 67)
				&& spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].getLabyrinthstatus() == 1 ) {
			spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].setLabyrinthstatus(0);
		}
		next=null;
		return false;
	}
	/**
     * Methode liefert die aktuelle Position (Feldnummer) der gesuchten Figur,
     * sofern sie existiert.
     *
     * @param figur Der Name der gesuchten Figur (z.B. "BLAU-A")
     *
     * @return Die aktuelle Position bzw. Feldnummer der gesuchten Figur oder -1,
     *         falls sie nicht existiert
     */
	@Override
	public int getFigurposition(String figur) {
		try {
			if (Farbe.valueOf(figur.split("-")[0]).ordinal() < spieler.length && figur.split("-")[1].length() == 1
					&& (figur.split("-")[1].charAt(0) == 65 || figur.split("-")[1].charAt(0) == 66 
					|| figur.split("-")[1].charAt(0) == 67)) {
				return spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()]
						.getFiguren()[(int) (figur.split("-")[1].charAt(0) - 65)].getFigurposition();
			}
		} catch (IllegalArgumentException ex) {
		}
		return -1;
	}


}
