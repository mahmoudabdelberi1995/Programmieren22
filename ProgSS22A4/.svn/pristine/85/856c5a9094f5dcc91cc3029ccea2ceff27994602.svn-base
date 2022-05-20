package de.ostfalia.prog.ss22.test;

import static org.junit.Assert.assertFalse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import de.ostfalia.junit.annotations.TestDescription;
import de.ostfalia.junit.base.ICaptureRules;
import de.ostfalia.junit.base.IMessengerRules;
import de.ostfalia.junit.base.ITraceRules;
import de.ostfalia.junit.conditional.PassTrace;
import de.ostfalia.junit.rules.CaptureRule;
import de.ostfalia.junit.rules.MessengerRule;
import de.ostfalia.junit.rules.RuleControl;
import de.ostfalia.junit.rules.TraceRule;
import de.ostfalia.junit.runner.TopologicalSortRunner;
import de.ostfalia.prog.ss22.Paradiesspiel;
import de.ostfalia.prog.ss22.enums.Farbe;
import de.ostfalia.prog.ss22.interfaces.IParadiesspiel;
import de.ostfalia.prog.ss22.exceptions.DateiLeerException;
import de.ostfalia.prog.ss22.exceptions.UngueltigePositionException;

/**
 * Testfälle für Aufgabe 3
 * 
 * @author D. Dick
 * @author M. Gründel
 * @since SS 2022
 *
 */
@RunWith(TopologicalSortRunner.class)
public class Aufgabe3Test {

	public RuleControl opt = RuleControl.NONE; 
	public IMessengerRules messenger = MessengerRule.newInstance(opt); 
	public ICaptureRules capture = CaptureRule.newInstance(opt);	
	public ITraceRules trace = TraceRule.newInstance(opt); 
	
	@Rule
	public TestRule chain = RuleChain
			.outerRule(trace)			
			.around(messenger);
	
	@Rule
    public TestRule timeout = new DisableOnDebug(
                              new Timeout(1000, TimeUnit.MILLISECONDS));

	private String callGameConf  = "Konstruktoraufruf Paradiesspiel(\"%s\", %s) fuer Instanz %s.";
	private String msgExpected   = "Exception wurde erwartet.";
	private String msgUnExpected = "Falsche Exception wurde geworfen.";
	private String msgError      = "Fehlerhafte Exceptions.";
	private String msgNone       = "<keine>";
	
	@Before
	public void before() {}
	
	@After
	public void after() {}
	
	/*
	 * ***************************************************************************
	 * Testfälle für "Labyrinth"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Labyrinth"-Feld. Darauf hin muss der Spieler für eine Runde aussetzen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet auf einem \"Labyrinth\"-Feld. Darauf hin muss der Spieler\n"
			       + "fuer eine Runde aussetzen.")
	public void testLabyrinth1() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:17, GELB-B:46, BLAU-B:12";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		// Spieler GELB wird gewürfelt und bewegt GELB-A von 17 auf 19 und muss danach
		// eine Runde aussetzen
		trace.add("Spieler GELB bewegt GELB-A von 17 auf 19 und muss danach eine Runde aussetzen.");
		bewegeFigur(spiel, "GELB-A", 1, 1, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 19 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:19:46", "spiel");
		
		// Spieler GELB wird erneut gewürfelt und möchte GELB-A von 19 auf 25 bewegen,
		// darf aber nicht
		trace.add("Spieler GELB moechte GELB-A von 19 auf 25 bewegen, darf aber nicht.");
		bewegeFigur(spiel, "GELB-A", 3, 3, false);
		trace.add("Die Figur GELB-A muss immer noch auf Feld Nr. 19 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:19:46", "spiel");
		
		// Spieler GELB wird erneut gewürfelt und möchte nun wieder GELB-A von 19 auf 25
		// bewegen, darf aber nicht
		trace.add("Spieler GELB moechte nun wieder GELB-A bewegen.");
		bewegeFigur(spiel, "GELB-A", 5, 2, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 26 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:26:46", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Labyrinth"-Feld. Darauf hin muss der Spieler für eine Runde aussetzen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet auf ein \"Labyrinth\"-Feld. Darauf hin muss der Spieler\n"
			       + "fuer eine Runde aussetzen.")
	public void testLabyrinth2() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		String conf = "GELB-A:17, GELB-B:46, BLAU-B:12";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		// Spieler GELB wird gewürfelt und bewegt GELB-A von 17 auf 19 und muss danach
		// eine Runde aussetzen
		trace.add("Spieler GELB bewegt GELB-A von 17 auf 19 und muss danach eine Runde aussetzen.");
		bewegeFigur(spiel, "GELB-A", 1, 1, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 19 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:19:46, GRUEN:0:0", "spiel");
		
		// Spieler BLAU wird gewürfelt und bewegt BLAU-B von 12 auf 15
		trace.add("Spieler BLAU bewegt BLAU-B von 12 auf 15.");
		bewegeFigur(spiel, "BLAU-B", 2, 1, true);
		trace.add("Die Figur BLAU-B muss auf Feld Nr. 15 sein.");
		evalPosition(spiel, "BLAU:0:15, GELB:19:46, GRUEN:0:0", "spiel");
		
		// Spieler GELB wird erneut gewürfelt und möchte GELB-B von 46 auf 48 bewegen,
		// darf aber nicht
		trace.add("Spieler GELB moechte GELB-B von 46 auf 48 bewegen, muss aber aussetzen.");
		bewegeFigur(spiel, "GELB-B", 1, 1, false);
		trace.add("Die Figur GELB-B muss immer noch auf Feld Nr. 46 sein.");
		evalPosition(spiel, "BLAU:0:15, GELB:19:46, GRUEN:0:0", "spiel");
		
		// Spieler GRUEN wird gewürfelt und bewegt GRUEN-A von 0 auf 4
		trace.add("Spieler GRUEN bewegt GRUEN-A von 0 auf 4.");
		bewegeFigur(spiel, "GRUEN-A", 2, 2, true);
		trace.add("Die Figur GRUEN-A muss auf Feld Nr. 4 sein.");
		evalPosition(spiel, "BLAU:0:15, GELB:19:46, GRUEN:4:0", "spiel");
		
		// Spieler GELB wird erneut gewürfelt und möchte nun GELB-A bewegen, was diesmal
		// erlaubt ist
		trace.add("Spieler GELB moechte nun GELB-A bewegen, was diesmal erlaubt ist.");
		bewegeFigur(spiel, "GELB-A", 5, 2, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 26 sein.");
		evalPosition(spiel, "BLAU:0:15, GELB:26:46, GRUEN:4:0", "spiel");
	}

	/*
	 * ***************************************************************************
	 * Testfälle für Datei lesen/speichern
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Nachdem eine Figur bewegt wurde, wird das Spiel
	 * gespeichert und wieder eingelesen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Test erzeugt 3 Spieler. Nachdem eine Figur bewegt wurde, wird das Spiel\n" + 
			         "gespeichert und wieder eingelesen.")
	public void testDateiSchreibenUndLesen() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		String conf = "GELB-A:63, GELB-B:46, BLAU-B:12";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		// Spieler GRUEN wird gewürfelt und bewegt GRUEN-A von 0 auf 4
		trace.add("Spieler GRUEN bewegt GRUEN-A von 0 auf 4.");
		bewegeFigur(spiel, "GRUEN-A", 2, 2, true);
		trace.add("Die Figur GRUEN-A muss auf Feld Nr. 4 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:63:46, GRUEN:4:0", "spiel");
		
		// Spiel wird gespeichert
		trace.add("Aufruf spiel.speichern(\"spielTest3Farben.txt\").");
		((Paradiesspiel) spiel).speichern("spielTest3Farben.txt");
		trace.separator();

		// Spieler GRUEN wird gewürfelt und bewegt GRUEN-A von 4 auf 12
		trace.add("Spieler GRUEN bewegt GRUEN-A von 4 auf 12.");
		bewegeFigur(spiel, "GRUEN-A", 1, 1, true);
		trace.add("Die Figur GRUEN-A muss auf Feld Nr. 12 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:63:46, GRUEN:12:0", "spiel");
		
		// das gespeicherte Spiel wird geladen
		trace.add("Aufruf Paradiesspiel.laden(\"spielTest3Farben.txt\").");
		spiel = Paradiesspiel.laden("spielTest3Farben.txt");
		trace.separator();

		// sind die Figuren wieder da, wo sie vorher waren?
		trace.add("Die Figur GRUEN-A muss auf Feld Nr. 4 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:63:46, GRUEN:4:0", "spiel");
	}

	/**
	 * Test erzeugt 6 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Nachdem eine Figur bewegt wurde, muss der dazugehöriger
	 * Spieler aussetzen, was er auch tut. Das Spiel wird inzwischen gespeichert und
	 * nach dem Aussetzen neu geladen. Der Spieler muss aber erneut aussetzen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Test erzeugt 6 Spieler. Nachdem eine Figur bewegt wurde, muss der dazugehoeriger\n" + 
			         "Spieler aussetzen, was er auch tut. Das Spiel wird inzwischen gespeichert und\n" + 
			         "nach dem Aussetzen neu geladen. Der Spieler muss aber erneut aussetzen.")
	public void testDateiSchreibenLesenUndSpielen() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN, Farbe.ROT, Farbe.SCHWARZ, Farbe.WEISS};
		String conf = "BLAU-B:12, GELB-A:17, GELB-B:20, GRUEN-B:0, ROT-A:19, WEISS-B:17";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);

		// Spieler WEISS wird gewürfelt und bewegt WEISS-B von 17 auf 19
		trace.add("Spieler WEISS bewegt WEISS-B von 17 auf 19.");
		bewegeFigur(spiel, "WEISS-B", 1, 1, true);
		trace.add("Die Figur WEISS-B muss auf Feld Nr. 19 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:17:20, GRUEN:0:0, ROT:19:0, SCHWARZ:0:0, WEISS:0:19", "spiel");
		
		// Spieler GELB wird gewürfelt und bewegt GELB-A von 17 auf 19
		trace.add("Spieler GELB bewegt GELB-A von 17 auf 19.");
		bewegeFigur(spiel, "GELB-A", 1, 1, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 19 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:19:20, GRUEN:0:0, ROT:19:0, SCHWARZ:0:0, WEISS:0:19", "spiel");
		
		// Spiel wird gespeichert
		trace.add("Aufruf spiel.speichern(\"spielTestWeissAussetzen.txt\").");
		((Paradiesspiel) spiel).speichern("spielTestWeissAussetzen.txt");
		trace.separator();

		// Spieler WEISS wird gewürfelt und möchte WEISS-A von 0 auf 4 bewegen, 
		// darf aber nicht, da er aussetzen muss
		trace.add("Spieler WEISS moechte WEISS-A von 0 auf 4 bewegen, muss aber aussetzen.");
		bewegeFigur(spiel, "WEISS-A", 3, 1, false);
		trace.add("Die Figur WEISS-A muss immer noch auf Feld Nr. 0 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:19:20, GRUEN:0:0, ROT:19:0, SCHWARZ:0:0, WEISS:0:19", "spiel");
		
		// das gespeicherte Spiel wird geladen
		trace.add("Aufruf Paradiesspiel.laden(\"spielTestWeissAussetzen.txt\").");
		spiel = Paradiesspiel.laden("spielTestWeissAussetzen.txt");
		trace.separator();

		// befinden sich die Figuren dort, wo sie waren?
		evalPosition(spiel, "BLAU:0:12, GELB:19:20, GRUEN:0:0, ROT:19:0, SCHWARZ:0:0, WEISS:0:19", "spiel");
		
		// Spieler WEISS wird gewürfelt und möchte WEISS-A von 0 auf 4 bewegen, darf
		// aber nicht da er nach dem Laden immer noch ausseten muss
		trace.add("Der Spieler WEISS muss aussetzen.");
		bewegeFigur(spiel, "WEISS-A", 3, 1, false);
		trace.add("Die Figur WEISS-A muss immer noch auf Feld Nr. 0 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:19:20, GRUEN:0:0, ROT:19:0, SCHWARZ:0:0, WEISS:0:19", "spiel");
		
		// Spieler ROT wird gewürfelt und bewegt ROT-A von 19 auf 25
		trace.add("Spieler ROT bewegt ROT-A von 19 auf 25.");
		bewegeFigur(spiel, "ROT-A", 3, 3, true);
		trace.add("Die Figur ROT-A muss auf Feld Nr. 25 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:19:20, GRUEN:0:0, ROT:25:0, SCHWARZ:0:0, WEISS:0:19", "spiel");
		
		// Spieler GELB wird gewürfelt und möchte GELB-B von 20 auf 26 bewegen, darf
		// aber nicht da er aussetzen muss
		trace.add("Spieler GELB moechte GELB-B von 20 auf 26 bewegen, muss aber aussetzen.");
		bewegeFigur(spiel, "GELB-B", 3, 3, false);
		trace.add("Die Figur GELB-B muss immer noch auf Feld Nr. 20 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:19:20, GRUEN:0:0, ROT:25:0, SCHWARZ:0:0, WEISS:0:19", "spiel");
		
		// Spieler GELB wird erneut gewürfelt und möchte diesmal GELB-A von 19 auf 30
		// bewegen. Spielzug ist erlaub, da GELB bereits eine Runde ausgesetzt hat
		trace.add("Spieler GELB moechte diesmal GELB-A von 19 auf 30 bewegen. Spielzug ist erlaub, "
				+ "da GELB bereits eine Runde ausgesetzt hat.");
		bewegeFigur(spiel, "GELB-A", 5, 6, true);
		trace.add("Die Figur GELB-A ist auf Feld Nr. 30 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:30:20, GRUEN:0:0, ROT:25:0, SCHWARZ:0:0, WEISS:0:19", "spiel");
		
		// Spieler WEISS wird gewürfelt und möchte WEISS-A von 0 auf 4 bewegen. Spielzug
		// ist erlaub, da WEISS bereits eine Runde ausgesetzt hat
		trace.add("Spieler WEISS moechte WEISS-A von 0 auf 4 bewegen. Spielzug ist erlaub, "
				+ "da WEISS bereits eine Runde ausgesetzt hat.");
		bewegeFigur(spiel, "WEISS-A", 3, 1, true);
		trace.add("Die Figur WEISS-A muss ist auf Feld Nr. 4 sein.");
		evalPosition(spiel, "BLAU:0:12, GELB:30:20, GRUEN:0:0, ROT:25:0, SCHWARZ:0:0, WEISS:4:19", "spiel");
	}
	
	/*
	 * *************************************************************************
	 * ** Testfälle für DateiLeerException
	 * *************************************************************************
	 * **
	 */

	@Test
	public void testDateiLeer() throws IOException {
		String fileName = "dateileer.txt";
		// eine leere Datei wird gespeichert
		FileWriter fileWriter = new FileWriter(fileName);
		fileWriter.write("");
		fileWriter.close();
		
		evalLaden(fileName);
	}
	
	//------------------------------------------------------------------------------
	
	/**
	 * Farbe am Zug setzen und Figur auf dem Spielfeld bewegen, wenn moeglich.
	 * @param spiel - Instanz des Paradiesspiels.
	 * @param figur - "Farbe-A/B" oder "Farbe-A/B:FarbeAmZug". Beispiel.: "GELB-A" oder "GELB-A:BLAU"
	 * @param w1 - Augenzahl Wuerfel 1.
	 * @param w2 - Augenzahl Wuerfel 2.
	 * @param exp - erwartete Rueckgabe der Methode bewegeFigur.
	 */
	private void bewegeFigur(IParadiesspiel spiel, String figur, int w1, int w2, boolean exp) {
		ITraceRules subTrace = trace.newSubtrace(opt);
		String[] parts = figur.split("\\s*(-|:)\\s*");
		Farbe farbe = Farbe.valueOf((parts.length == 2) ? parts[0] : parts[2]);
		figur = parts[0] + "-" + parts[1];
		trace.add("Figur \"%s\" bewegen, Augenzahl %d und %d.", figur, w1, w2);
		subTrace.add("Aufruf der Methode setFarbeAmZug(%s).", farbe);
		spiel.setFarbeAmZug(farbe);
		subTrace.add("Aufruf der Methode bewegeFigur(\"%s\", %d, %d) muss %b liefern.", figur, w1, w2, exp);
		boolean got = spiel.bewegeFigur(figur, w1, w2);
		subTrace.addInfo(PassTrace.ifEquals("Rueckgabe der Methode bewegeFigur.", exp, got, exp));
		trace.add(subTrace);
	}

	/**
	 * Ueberpruefen der Positionen auf dem Spielfeld.
	 * @param spiel - Instanz des Paradiesspiels.
	 * @param expected - "Farbe:Position-A:Position:B,...". Beispiel: "BLAU:0:15,..."
	 * @param inst - Name der Spielinstanz.
	 */
	private void evalPosition(IParadiesspiel spiel, String expected, String inst) {
		String msg  = "Gelieferte Positionen der Methode getFigurposition.";
		String call = "Aufruf der Methode %s.getFigurposition(\"%s\")";
		trace.add("Erwartete Positionen der Figuren in der Instanz %s: %s",inst,  expected);
		String[] parts = expected.split("\\s*,\\s*");
		StringJoiner got = new StringJoiner(", ");
		StringJoiner exp = new StringJoiner(", ");
		ITraceRules subTrace = trace.newSubtrace(opt);
		
		for (String pos : parts) {
			String[] e = pos.split("\\s*:\\s*");
			for (int i = 1; i < e.length; i++) {
				String figur = e[0] + "-" + (char)('A' + i - 1);
				exp.add(figur + ":" + e[i]);
				subTrace.add(call, inst, figur);
				int p = spiel.getFigurposition(figur);
				got.add(figur + ":" + p);
			}						
		}		
		subTrace.clear();
		trace.addInfo(PassTrace.ifEquals(msg, exp, got));
		trace.separator();
		assertFalse(msg, trace.hasOccurrences());
	}
	
	private void evalLaden(String fileName) {
		Class<?> exp = UngueltigePositionException.class;
		try {
			trace.add("Aufruf von Paradiesspiel.laden(%s).", fileName);	
			Paradiesspiel.laden(fileName);
			trace.add(PassTrace.ifEquals(msgExpected, exp, msgNone));
		} catch (DateiLeerException e) {
			//alles ok.
		} catch (Exception e) {
			trace.add(PassTrace.ifEquals(msgUnExpected, exp, e.getClass()));
		}
		trace.separator();
		assertFalse(msgError, trace.hasOccurrences());
	}

}