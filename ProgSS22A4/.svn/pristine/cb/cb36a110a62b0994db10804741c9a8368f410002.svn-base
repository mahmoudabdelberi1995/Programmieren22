package de.ostfalia.prog.ss22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.ostfalia.prog.ss22.enums.Farbe;
import de.ostfalia.prog.ss22.exceptions.DateiLeerException;
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
import de.ostfalia.prog.ss22.interfaces.IParadiesspiel;
import de.ostfalia.prog.ss22.interfaces.ISpeicherbar;

public class Paradiesspiel implements IParadiesspiel, ISpeicherbar{
	Spieler[] spieler;
	protected Farbe next;
	protected Farbe gewinner = null;
	protected int rounde = 0;
	protected static Set<Integer> ungueltigePosition = new HashSet<Integer>(
			Arrays.asList(new Integer[] {5, 6, 9, 14, 18, 24, 27, 32, 36, 41, 50, 54, 58 }));
	protected Map<Integer, Feld> felder = new HashMap<Integer, Feld>();
	/*
	 * Konstruktor ohne Parameter um ein vorhandenes spiel fortzusetzen
	 */
	
	public Paradiesspiel() {
		for (int i = 0; i < 64; i++) {
			if (i == 6) {
				felder.put(i, new Brucke());
			} else if (Arrays.asList(new Integer[] {14, 18, 27, 32, 36, 50 }).contains(i)) {
				felder.put(i, new Gluck());
			} else if (i == 52) {
				felder.put(i, new Aufschwung());
			} else if (i == 63) {
				felder.put(i, new Paradiese());
			} else if (Arrays.asList(new Integer[] {5, 9 }).contains(i)) {
				felder.put(i, new Pech());
			} else if (Arrays.asList(new Integer[] {24, 41, 54 }).contains(i)) {
				felder.put(i, new Desaster());
			} else if (i == 58) {
				felder.put(i, new Neuanfang());
			} else if (i == 19) {
			felder.put(i, new Labyrinth());
			} else {
				felder.put(i, new Feld());
			}
		}
	} 
	public int getRounde() {
		return rounde;
	}
	public void setRounde(int rounde) {
		this.rounde = rounde;
	}
	public Farbe getNext() {
		return next;
	}
	public static Set<Integer> getUngueltigePosition() {
		return ungueltigePosition;
	}
	public Map<Integer, Feld> getFelder() {
		return felder;
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
	public Paradiesspiel(Farbe... farben) throws FalscheSpielerzahlException {
		if (farben.length > 6 || farben.length < 2) {
			throw new FalscheSpielerzahlException();
		}
		for (int i = 0; i < 64; i++) {
			if (i == 6) {
				felder.put(i, new Brucke());
			} else if (Arrays.asList(new Integer[] {14, 18, 27, 32, 36, 50 }).contains(i)) {
				felder.put(i, new Gluck());
			} else if (i == 52) {
				felder.put(i, new Aufschwung());
			} else if (i == 63) {
				felder.put(i, new Paradiese());
			} else if (Arrays.asList(new Integer[] {5, 9 }).contains(i)) {
				felder.put(i, new Pech());
			} else if (Arrays.asList(new Integer[] {24, 41, 54 }).contains(i)) {
				felder.put(i, new Desaster());
			} else if (i == 58) {
				felder.put(i, new Neuanfang());
			} else if (i == 19) {
			felder.put(i, new Labyrinth());
			} else {
				felder.put(i, new Feld());
			}
		}
		spieler = new Spieler[farben.length];
		int n = 0;
		for (Farbe farbe : farben) {
			Figur[] figuren = new Figur[2];
			figuren[0] = new Figur(0);
			figuren[1] = new Figur(0);
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
	public Paradiesspiel(String conf, Farbe... farben) throws FalscheSpielerzahlException {
		this(farben);
		for (String line1 : conf.split(",")) {
			String[] line2 = line1.trim().split(":");
			String[] line3 = line2[0].trim().split("-");
			Figur[] figuren = spieler[Farbe.valueOf(line3[0]).ordinal()].getFiguren();
			if(ungueltigePosition.contains(Integer.parseInt(line2[1])) || 
					Integer.parseInt(line2[1])>63||Integer.parseInt(line2[1])<0) {
				throw new UngueltigePositionException();
			}
			figuren[(int) line3[1].charAt(0) - 65].setFigurposition(Integer.parseInt(line2[1]));
			spieler[Farbe.valueOf(line3[0]).ordinal()].setFiguren(figuren);
		}
	}
	/**
	 * Methode liefert die Farbe zurück, die den Spielzug hat.
	 *
	 * @return Die Farbe, die den Spielzug aktuell innehat
	 */
	@Override
	public Farbe getFarbeAmZug() {
		return next;
	}

	/**
	 * Methode gibt der mitgegebenen Farbe den Spielzug.
	 *
	 * @param farbe Die Farbe, die den Spielzug innehaben wird
	 */
	@Override
	public void setFarbeAmZug(Farbe farbe) {
		next = farbe;
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
					&& (figur.split("-")[1].charAt(0) == 65 || figur.split("-")[1].charAt(0) == 66)) {
				return spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()]
						.getFiguren()[(int) (figur.split("-")[1].charAt(0) - 65)].getFigurposition();
			}
		} catch (IllegalArgumentException ex) {
		}
		return -1;
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
		int summe = (augenzahlen[0] + augenzahlen[1]);
		Figur spielfigur;
		int n = -1;
		if ( gewinner == null
				&& next.equals(Farbe.valueOf(figur.split("-")[0]))
				&& (figur.split("-")[1].charAt(0) == 65 || figur.split("-")[1].charAt(0) == 66)
				&& spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].getLabyrinthstatus() == 0 ) {
			spielfigur = spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()]
					.getFiguren()[(int) figur.split("-")[1].charAt(0) - 65];
			Ereignisfelder e = new Ereignisfelder();
			n = e.bewege(spielfigur.getFigurposition(), augenzahlen[1] + augenzahlen[0], felder,
					augenzahlen);
			if (n == -1) {
				return false;
			} else {
				if(n==19) {
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
			if (spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].getFiguren()[0].getFigurposition() == 63
					&& spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].getFiguren()[1].getFigurposition() == 63) {
				gewinner = Farbe.valueOf(figur.split("-")[0]);
			}
			next=null;
			return true;
		}
		if (gewinner == null
				&& next.equals(Farbe.valueOf(figur.split("-")[0]))
				&& (figur.split("-")[1].charAt(0) == 65 || figur.split("-")[1].charAt(0) == 66)
				&& spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].getLabyrinthstatus() == 1 ) {
			spieler[Farbe.valueOf(figur.split("-")[0]).ordinal()].setLabyrinthstatus(0);
		}
		next=null;
		return false;
	}

	/**
     * Methode liefert die Farbe des Gewinners.
     *
     * @return die Farbe des Gewinners oder null, wenn es noch keinen Gewinner gibt.
     */
	@Override
	public Farbe getGewinner() {
		return gewinner;
	}

	/**
     * Methode liefert ein Array mit den Farben aller aktiven Spieler.
     *
     * @return ein Array mit den Farben der Spieler.
     */
	@Override
	public Farbe[] getAlleSpieler() {
		Farbe[] farben = new Farbe[spieler.length];
		int n = 0;
		for (Farbe f : Farbe.values()) {
			if (n == spieler.length) {
				break;
			}
			farben[n] = f;
			n++;
		}
		return farben;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < spieler.length; i++) {
			s += spieler[i].toString();
			s += "===================================\n";
		}
		s += "rounde nummer " + String.valueOf((rounde)) + "\n";
		s += "Gewinner = " + gewinner + "\n";
		s += this.getClass().toString();
		return s;
	}

	/**
     * Die Methode speichert in eine Datei den momentanen Spielzustand, so dass nach
     * dem Laden das Weiterspielen möglich ist.
     *
     * Methode wird erst ab der 4. Aufgabe implementiert.
     *
     * @param dateiName Name der Datei bzw. den kompletten Pfad, wo die Datei
     *                  gespeichert wird
     */
	@Override
	public void speichern(String dateiName) throws IOException {
		PrintWriter writer = new PrintWriter(dateiName, "UTF-8");
		writer.println(this.toString());
		writer.close();
	}
	
	/**
     * Die Methode liest eine Datei und konfiguriert das Spiel, wie es dort
     * gespeichert ist, so dass nach dem Laden das Weiterspielen möglich ist.
     *
     * Die Figuren werden demzufolge auf die Position gebracht wie sie gespeichert
     * wurden und die Information, ob ein Spieler aussetzen muss, ist auch bekannt.
     *
     * Methode wird erst ab der 4. Aufgabe implementiert.
     *
     * @param dateiName Der Name der Datei, wo die gewünschte Spielkonfiguration
     *                  gespeichert ist.
     * @return eine Instanz der Klasse, die IParadiesspiel implementiert
     */
	 public static IParadiesspiel laden(String dateiName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(dateiName));
		String everything="";
		Paradiesspiel p = new Paradiesspiel();
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
		} finally {
		    br.close();
		}
		String[] lines = everything.split(System.lineSeparator());
		if(lines.length<=1) {
			throw new DateiLeerException();
		} else {
		if(lines[lines.length-1].contains("Sommer")) {
			p = new ParadiesspielSommer();
		}
		Spieler[] spieler= new Spieler[lines.length/4];
		for(int i=0;i<lines.length/4;i++) {
			spieler[i]=new Spieler(null, new int[6], new Figur[lines[4*i].split(", ").length]);
		}
		for(int i=0;i<lines.length/4;i++) {
			int[] statistiken = new int[6];
			Figur[] figuren=spieler[i].getFiguren();
			for(int j=0; j<lines[4*i].split(", ").length;j++) {
				figuren[j]= new Figur(0);
			}
			for (String line1 : lines[4*i].split(", ")) {
				String[] line2 = line1.split(" :");
				String[] line3 = line2[0].split("-");
				figuren = spieler[i].getFiguren();
				figuren[(int) line3[1].charAt(0) - 65].setFigurposition(Integer.parseInt(line2[1]));
				spieler[i].setFiguren(figuren);
			}
			for (String line1 : lines[4*i+1].split(", ")) {
				String[] line2 = line1.split("==");
				statistiken[Integer.parseInt(line2[0])-1]=Integer.parseInt(line2[1]);
			}
			spieler[i].setFarbe(Farbe.values()[i]);
			spieler[i].setStatistiken(statistiken);
			if(lines[i*4+2].contains("not")) {
				spieler[i].setLabyrinthstatus(0);
			} else {
				spieler[i].setLabyrinthstatus(1);
			}
		}
		p.rounde=Integer.parseInt(lines[4*(lines.length/4)].split(" ")[2]);
		if(lines[4*(lines.length/4)+1].split(" = ")[1].equals("null")) {
			p.gewinner= null;
		} else {
			p.gewinner= Farbe.valueOf(lines[4*(lines.length/4)+1].split(" = ")[1]);
		}
		p.spieler=spieler;		
		return p;
		}
	 }
	 public Spieler[] getSpieler() {
		 return spieler;
	 }
}
