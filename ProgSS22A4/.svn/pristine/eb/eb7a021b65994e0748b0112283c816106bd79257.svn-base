package de.ostfalia.prog.ss22;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import de.ostfalia.prog.ss22.enums.Farbe;
import de.ostfalia.prog.ss22.exceptions.FalscheSpielerzahlException;

public class Main {
	public static void main(String[] args) throws FalscheSpielerzahlException, IOException {
		int n=0;
		String mode = "N";
		Paradiesspiel paradiesspiel;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Möchten Sie ein gespeichertes Spiel spielen? J/N");
		String s = scanner.nextLine();
		if(s.equals("N")) {
		System.out.println("S für Sommer/N für Normales Spiel");
		mode = scanner.nextLine();
		System.out.println("Anzahl der Spieler:");
		s = scanner.nextLine();
		n = Integer.parseInt(s);
		int i = 0;
		Farbe[] farben = new Farbe[n];
		for (Farbe farbe : Farbe.values()) {
			farben[i] = farbe;
			i++;
			if (i == n) {
				break;
			}
		}
		System.out.println("Möchten Sie in einer speziellen Startkonfiguration spielen?");
		System.out.println("J/N");
		s = scanner.nextLine();
		if (s.equals("J")) {
			System.out.println("Bitte geben Sie eine Konfiguration wie im Beispiel gezeigt ein");
			System.out.println("GELB-A:30, GELB-B:37, BLAU-B:7");
			s = scanner.nextLine();
			if(mode.equals("N")) {
				paradiesspiel = new Paradiesspiel(s, farben);	
			} else {
				paradiesspiel = new ParadiesspielSommer(s, farben);
			}
		} else {
			if(mode.equals("N")) {
				paradiesspiel = new Paradiesspiel(farben);	
			} else {
				paradiesspiel = new ParadiesspielSommer(farben);
			}
		}
		} else {
			System.out.println("S für Sommer/ N für Normales Spiel");
			mode = scanner.nextLine();
			if(mode.equals("S")) {
				paradiesspiel= new ParadiesspielSommer();
				System.out.println("dateiName:");
				s = scanner.nextLine();
				try {
					paradiesspiel = (ParadiesspielSommer)Paradiesspiel.laden(s);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				paradiesspiel= new Paradiesspiel();
				System.out.println("dateiName:");
				s = scanner.nextLine();
				try {
					paradiesspiel = (Paradiesspiel) Paradiesspiel.laden(s);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}
		while (paradiesspiel.getGewinner() == null) {
			int next;
			Random random = new Random();
			if(n==0) {
				n=paradiesspiel.spieler.length;
				if(paradiesspiel.next != null) {
					next=paradiesspiel.next.ordinal();
				} else {
					System.out.println("Drücken Sie die Eingabetaste, um die Würfel zu würfeln");
					s = scanner.nextLine();
					random = new Random();
					next = random.nextInt(n);
					paradiesspiel.setFarbeAmZug(Farbe.values()[next]);
				}
			} else {
			System.out.println("Drücken Sie die Eingabetaste, um die Würfel zu würfeln");
			s = scanner.nextLine();
			random = new Random();
			next = random.nextInt(n);
			paradiesspiel.setFarbeAmZug(Farbe.values()[next]);
			}
			System.out.println("spieler " + (next + 1) + " " + Farbe.values()[next]);
			int wurfel[] = new int[2];
			System.out.println("Drücken Sie die Eingabetaste, um die ersten Würfel zu würfeln");
			s = scanner.nextLine();
			wurfel[0] = random.nextInt(6) + 1;
			System.out.println(wurfel[0]);
			System.out.println("Drücken Sie die Eingabetaste, um den zweiten Würfel zu würfeln");
			s = scanner.nextLine();
			wurfel[1] = random.nextInt(6) + 1;
			System.out.println(wurfel[1]);
			if(mode.equals("N")) {
			System.out.println("A oder B ?");
			} else {
				System.out.println("A oder B oder C ?");
			}
			s = scanner.nextLine();
			Boolean b = false;
			while (b == false) {
				b = paradiesspiel.bewegeFigur(paradiesspiel.getFarbeAmZug().toString() + "-" + s, wurfel[0], wurfel[1]);
				if (!b) {
					System.out.println(Farbe.values()[next] + "-" + s + " kann sich nicht bewegen");
				}
				if (paradiesspiel.getGewinner() != null) {
					System.out.println(Farbe.values()[next].toString() + " gewonnen");
				}
			}
			System.out.println("Geben Sie P ein, um die Position anzuzeigen. \n Wenn Sie speichern möchten, geben Sie einen Dateinamen an");
			s = scanner.nextLine();
			if (s.equals("P")) {
				System.out.println(paradiesspiel);
			} else if (s.contains(".txt")) {
				paradiesspiel.speichern(s);
			}
		}
	}
}
