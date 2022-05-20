package de.ostfalia.prog.ss22.interfaces;

import java.io.IOException;

/**
 * Erweitert das Spiel Paradiesspiel, so dass die aktuelle Spielstellung
 * gespeichert werden kann und auch so, dass eine gespeicherte Spielstellung
 * geladen werden kann. Nach dem laden muss das Spiel in einem bespielbaren
 * Zustand sein.
 * 
 * @author M. Gruendel, D. Dick
 * @since SS 2022
 */
public interface ISpeicherbar {

	/**
	 * Die Methode speichert in eine Datei den momentanen Spielzustand, so dass
	 * nach dem Laden das Weiterspielen mÃ¶glich ist.
	 * 
	 * Methode wird erst ab der 4. Aufgabe implementiert.
	 * 
	 * @param dateiName
	 *            Name der Datei bzw. den kompletten Pfad, wo die Datei
	 *            gespeichert wird
	 */
	public void speichern(String dateiName) throws IOException;

	/**
	 * Die Methode liest eine Datei und konfiguriert das Spiel, wie es dort
	 * gespeichert ist, so dass nach dem Laden das Weiterspielen mÃ¶glich ist.
	 * 
	 * Die Figuren werden demzufolge auf die Position gebracht wie sie
	 * gespeichert wurden und die Information, ob ein Spieler aussetzen muss,
	 * ist auch bekannt.
	 * 
	 * Methode wird erst ab der 4. Aufgabe implementiert.
	 * 
	 * @param dateiName
	 *            Der Name der Datei, wo die gewÃ¼nschte Spielkonfiguration
	 *            gespeichert ist.
	 * @return eine Instanz der Klasse, die IParadiesspiel implementiert
	 */
	public static IParadiesspiel laden(String dateiName) throws Exception {
		throw new NoSuchMethodException(
				"Methode laden(String) muss Ã¼berschrieben werden.");
	}
}