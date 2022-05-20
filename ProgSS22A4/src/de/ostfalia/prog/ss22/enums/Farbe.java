package de.ostfalia.prog.ss22.enums;

/**
 * Die Aufzählungsklasse Farbe definiert die möglichen Farben für die Figuren
 * des Paradiesspiels und auch die Reihenfolge, wie sie vergeben werden. Dabei
 * ist zu achten, dass jede Figur nur eine Farbe haben darf und dass eine Farbe
 * maximal einer Figur zugeordnet werden darf (1:1 Beziehung).
 *
 * Sie dürfen diese Aufzählungsklasse erweitern, falls notwendig.
 *
 * Um die automatische Testbarkeit zu gewährleisten dürfen allerdings weder die
 * Namen der Farbe noch ihre Reihenfolge geändert werden.
 *
 *
 * @author D. Dick
 * @since SS22
 *
 */
public enum Farbe {
	/**
	 * Blau Farbe
	 */
	BLAU,
	/**
	 * Gelb Farbe
	 */
	GELB,
	/**
	 * Gruen Farbe
	 */
	GRUEN,
	/**
	 * Rot Farbe
	 */
	ROT,
	/**
	 * Schwarz Farbe
	 */
	SCHWARZ,
	/**
	 * Weiss Farbe
	 */
	WEISS;
}


