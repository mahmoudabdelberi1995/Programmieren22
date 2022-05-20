package de.ostfalia.prog.ss22.interfaces;

import de.ostfalia.prog.ss22.enums.Farbe;

/**
 * Das Interface gibt Aufschluss Ã¼ber die benÃ¶tigten Methoden, so dass die
 * automatische Testbarkeit der Implementierung des Spiels gewÃ¤hrleistet wird.
 * Anhand der Beschreibung der Methode kann auch ihre erwartete FunktionalitÃ¤t
 * in Erfahrung gebracht werden.
 * 
 * @author D. Dick
 * @since SS 2022
 *
 */
public interface IParadiesspiel {

	/**
	 * Dieser Konstruktor initialisiert ein Spiel indem die als Parameter
	 * mitgegebenen Farben die einzelnen Spieler darstellen. In diesem Fall werden
	 * alle benÃ¶tigten Figuren auf dem Startfeld positioniert. Es werden im Ãœbrigen
	 * nur Spieler bzw. Figuren erzeugt, dessen Farbe als Parameter mitgegeben
	 * wurden.
	 * 
	 * @param farben
	 *            Die Farben, die die Spieler tragen werden. Zum Beispiel:
	 * 
	 *            {"BLAU", "GELB", "GRUEN", "ROT", "SCHWARZ", "WEISS"} -> es werden
	 *            6 Spieler erzeugt dessen Figuren auf dem Startfeld positioniert
	 *            werden.
	 * 
	 *            {"BLAU", "GELB"} -> es werden 2 Spieler erzeugt dessen Figuren auf
	 *            dem Startfeld positioniert werden.
	 * 
	 */
	// public Paradiesspiel(Farbe... farben);

	/**
	 * Dieser Konstruktor initialisiert ein Spiel indem die als Parameter
	 * mitgegebenen Farben die einzelnen Spieler darstellen. Es werden im Ãœbrigen
	 * nur Spieler bzw. Figuren erzeugt, dessen Farbe als Parameter mitgegeben
	 * wurden.
	 * 
	 * DarÃ¼ber hinaus werden die Figuren, die in dem Konfigurationsstring vorkommen,
	 * so positioniert, wie im String gefordert. Weitere Figuren, die evt. notwendig
	 * sind aber im String nicht vorkommen, werden erwartungsgemÃ¤ÃŸ auf dem Startfeld
	 * positioniert.
	 * 
	 * @param conf
	 *            ein String mit den Namen und Positionen der einzelnen Figuren, die
	 *            zwar im Spiel vorhanden sind aber nicht auf dem Startfeld
	 *            positioniert werden (z. B. "GELB-A:30, GELB-B:37, BLAU-B:7" oder
	 *            "GELB-A:19, GELB-B:37, BLAU-B:7")
	 * 
	 * @param farben
	 *            Die Farben, die die Spieler tragen werden. Zum Beispiel:
	 * 
	 *            {"BLAU", "GELB", "GRUEN", "ROT", "SCHWARZ", "WEISS"} -> es werden
	 *            6 Spieler erzeugt dessen Figuren auf dem Startfeld positioniert
	 *            werden.
	 * 
	 *            {"BLAU", "GELB"} -> es werden 2 Spieler erzeugt dessen Figuren auf
	 *            dem Startfeld positioniert werden.
	 * 
	 *
	 */
	// public Paradiesspiel(String conf, Farbe... farben);

	/**
	 * Methode liefert die Farbe zurÃ¼ck, die den Spielzug hat.
	 * 
	 * @return Die Farbe, die den Spielzug aktuell innehat
	 */
	public Farbe getFarbeAmZug();

	/**
	 * Methode gibt der mitgegebenen Farbe den Spielzug.
	 * 
	 * @param farbe
	 *            Die Farbe, die den Spielzug innehaben wird
	 */
	public void setFarbeAmZug(Farbe farbe);

	/**
	 * Methode liefert die aktuelle Position (Feldnummer) der gesuchten Figur,
	 * sofern sie existiert.
	 * 
	 * @param figur
	 *            Der Name der gesuchten Figur (z.B. "BLAU-A")
	 * 
	 * @return Die aktuelle Position bzw. Feldnummer der gesuchten Figur oder -1,
	 *         falls sie nicht existiert
	 */
	public int getFigurposition(String figur);

	/**
	 * Methode bewegt die angegebene Figur auf eine neue Position. Die Anzahl von
	 * Felder, die die Figur sich bewegen soll ergeben sich aus der Addition der
	 * zwei Augenzahlen.
	 * 
	 * @param figur
	 *            Der Name der Figur, welche bewegt werden soll (z.B. "BLAU-A")
	 * @param augenzahlen
	 *            Eine (oder mehrere) zufÃ¤llig gewÃ¼rfelte Zahl(en)
	 * 
	 * @return true, wenn die Figur bewegt werden konnte bzw. durfte; ansonsten
	 *         false. Methode liefert auch false, falls die gewÃ¼nschte Figur nicht
	 *         gefunden werden konnte.
	 */
	public boolean bewegeFigur(String figur, int... augenzahlen);

	/**
	 * Methode liefert die Farbe des Gewinners.
	 * 
	 * @return die Farbe des Gewinners oder null, wenn es noch keinen Gewinner gibt.
	 */
	public Farbe getGewinner();

	/**
	 * Methode liefert ein Array mit den Farben aller aktiven Spieler.
	 * 
	 * @return ein Array mit den Farben der Spieler.
	 */
	public Farbe[] getAlleSpieler();

	/**
	 * Methode formt das Spiel so um, dass es auf eine Textkonsole in einer
	 * sinnvolle Art und Weise ausgegeben werden kann.
	 * 
	 * @return das Spiel in Textformat
	 */
	public String toString();

}
