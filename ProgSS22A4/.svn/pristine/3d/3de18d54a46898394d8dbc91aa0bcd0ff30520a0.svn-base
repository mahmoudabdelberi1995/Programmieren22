package de.ostfalia.prog.ss22;

import de.ostfalia.prog.ss22.enums.Farbe;
/**
 * Spieler Klasse
 * Jeder Spieler hat eine Farbe, 2 Figuren und Statistiken um sch�ne Ausgabe zu erm�glichen
 * @author abdel
 *
 */

public class Spieler {
	private Farbe farbe= null;
	private int[] statistiken = new int[6];
	private Figur[] figuren = new Figur[2];
	private int labyrinthstatus=0;
	/**
	 * Konstruktor der Klasse Spieler
	 * @param farbe
	 * @param statistiken
	 * @param figuren
	 */
	public Spieler(Farbe farbe, int[] statistiken, Figur[] figuren) {
		this.farbe = farbe;
		this.statistiken = statistiken;
		this.figuren = figuren;
	}
	/**
	 * get statistiken Methode
	 * @return gibt statistiken zurück
	 */
	public int[] getStatistiken() {
		return statistiken;
	}
	/**
	 * setStatistiken methode
	 * @param statistiken
	 */
	public void setStatistiken(int[] statistiken) {
		this.statistiken = statistiken;
	}
	/**
	 * getFiguren
	 * @return gibt die Figur zurück
	 */
	public Figur[] getFiguren() {
		return figuren;
	}
	/**
	 * setFigur
	 * @param figuren
	 */
	public void setFiguren(Figur[] figuren) {
		this.figuren = figuren;
	}
	/**
	 * getFarbe
	 * @return gibt farbe zurück
	 */
	public Farbe getFarbe() {
		return farbe;
	}
	/**
	 * setFarbe
	 * @param farbe
	 */
	public void setFarbe(Farbe farbe) {
		this.farbe = farbe;
	}
	/**
	 * getLabyrinthstatus
	 * @return gibt Labyrinthstatus zurück
	 */
	public int getLabyrinthstatus() {
		return labyrinthstatus;
	}
	/**
	 * setLabyrinthstatus
	 * @param labyrinthstatus
	 */
	public void setLabyrinthstatus(int labyrinthstatus) {
		this.labyrinthstatus = labyrinthstatus;
	}
	/**
	 * toString Methode
	 */
	@Override
	public String toString() {
		String s = "";
		for(int i=0;i<figuren.length;i++) {
			s += farbe.toString() + "-"+Character.toString ((char) i+65)+" :" + figuren[i].toString() + ", ";
		}
		s += "\n";
		for (int i = 0; i < statistiken.length-1; i++) {
			s += String.valueOf(i + 1) + "==" + String.valueOf(statistiken[i]) + ", ";
		}
		s += String.valueOf(statistiken.length) + "==" + String.valueOf(statistiken[statistiken.length - 1]+"\n");
		if(labyrinthstatus == 0) {
			s += "labyrinth status == " + "not active\n";
		} else {
			s += "labyrinth status == " + "active\n";
		}
		return s;
	}

}
