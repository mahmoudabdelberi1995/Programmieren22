package de.ostfalia.prog.ss22.test;

import static org.junit.Assert.assertFalse;

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

/**
 * Testfälle für Aufgabe 2
 * 
 * @author D. Dick
 * @author M. Gründel
 * @since SS 2022
 *
 */
@RunWith(TopologicalSortRunner.class)
public class Aufgabe2Test {
	
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

	private String callGame     = "Konstruktoraufruf Paradiesspiel(%s) fuer Instanz %s.";
	private String callGameConf = "Konstruktoraufruf Paradiesspiel(\"%s\", %s) fuer Instanz %s."; 
	
	
	@Before
	public void before() {}
	
	@After
	public void after() {}

	
	/*
	 * ***************************************************************************
	 * Testfälle für "Pech"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich auf dem Startfeld befinden. Eine
	 * Figur wird bewegt und landet dabei auf ein "Pech"-Feld. Darauf hin muss sie
	 * die gewürfelte Augenzahl zurückgehen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet dabei auf einem \"Pech\"-Feld und "
			       + "muss die gewuerfelte Augenzahl zurueckgehen.")
	public void testPech() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);
		
		bewegeFigur(spiel, "BLAU-B", 2, 3, true);
		trace.add("Die Figur BLAU-B muss auf Feld Nr. 0 sein (Weg: 0 -> 1 ... -> 5 -> 4 -> ... -> 0).");
		evalPosition(spiel, "BLAU:0:0, GELB:0:0", "spiel");
	}

	/*
	 * ***************************************************************************
	 * Testfälle für "Neuanfang"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Neuanfang"-Feld. Darauf hin muss sie zurück zum Startfeld.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet auf einem \"Neuanfang\"-Feld und muss zurueck zum Startfeld.")
	public void testNeuanfang() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:42, GELB-B:46, BLAU-B:12";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 4, 4, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 0 sein (42 -> 50 -> 58 -> 0).");
		evalPosition(spiel, "GELB:0:46, BLAU:0:12", "spiel");
	}

	/*
	 * ***************************************************************************
	 * Testfälle für "Desaster"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Desaster"-Feld. Darauf hin muss sie die doppelte gewürfelte Augenzahl
	 * zurückgehen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet dabei auf einem \"Desaster\"-Feld und muss sie die\n"
			       + "doppelte gewuerfelte Augenzahl zurueckgehen.")
	public void testDesaster12Auf0() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:51, GELB-B:46, BLAU-B:12";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "BLAU-B", 6, 6, true);
		trace.add("Die Figur BLAU-B muss auf Feld Nr. 0 sein (12 -> 24 -> 0).");
		evalPosition(spiel, "BLAU:0:0, GELB:51:46", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei am Ende einer
	 * Kettenreaktion auf ein "Desaster"-Feld. Darauf hin muss sie die doppelte
	 * gewürfelte Augenzahl zurückgehen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet am Ende einer Kettenreaktion auf einem \"Desaster\"-Feld.\n"
			       + "Darauf hin muss sie die doppelte gewuerfelte Augenzahl zurueckgehen.")
	public void testDesaster12Auf12() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:51, GELB-B:46, BLAU-B:12";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "BLAU-B", 3, 3, true);
		trace.add("Die Figur BLAU-B muss auf Feld Nr. 12 sein (12 -> 18 -> 24 -> 12).");
		evalPosition(spiel, "BLAU:0:12, GELB:51:46", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Desaster"-Feld. Darauf hin muss sie die doppelte gewürfelte Augenzahl
	 * zurückgehen und landet schließlich auf ein "Brücke"-Feld.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet auf einem \"Desaster\"-Feld und muss die doppelte gewuerfelte\n"
			       + "Augenzahl zurueckgehen und landet schließlich auf einem \"Brücke\"-Feld.")
	public void testDesaster15Auf12() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:15, GELB-B:46, BLAU-B:12";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 5, 4, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 12 sein (15 -> 24 -> 6 -> 12).");
		evalPosition(spiel, "BLAU:0:12, GELB:12:46", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Desaster"-Feld. Darauf hin muss sie die doppelte gewürfelte Augenzahl
	 * zurückgehen und landet schließlich auf ein "Glück"-Feld und wird erneut nach
	 * vorne befördert.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet auf einem \"Desaster\"-Feld und muss die doppelte gewuerfelte\n"
			       + "Augenzahl zurueckgehen, landet schließlich auf einem \"Glück\"-Feld\" und wird\n"
			       + "erneut nach vorne befoerdert.")
	public void testDesaster21Auf21() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:21, GELB-B:46, BLAU-B:12";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 1, 2, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 21 sein (21 -> 24 -> 18 -> 21).");
		evalPosition(spiel, "BLAU:0:12, GELB:21:46", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Desaster"-Feld. Darauf hin muss sie die doppelte gewürfelte Augenzahl
	 * zurückgehen und landet schließlich auf ein "Glück"-Feld und wird erneut nach
	 * vorne befördert. Darauf hin landet sie auf ein "Aufschwung"-Feld und bleibt
	 * dort stehen (keine 2 6-er Augenzahlen).
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet auf einem \"Desaster\"-Feld. Darauf hin muss sie die\n"
			       + "doppelte gewuerfelte Augenzahl zurueckgehen und landet schließlich auf einem\n"
			       + "\"Glück\"-Feld und wird erneut nach vorne befoerdert. Darauf hin landet sie\n"
			       + "auf einem \"Aufschwung\"-Feld und bleibt dort stehen (keine zwei 6-er Augenzahlen).")
	public void testDesaster52Auf52() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:52, GELB-B:21, BLAU-B:28";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 1, 1, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 52 sein (Kettenreaktion: 52 -> 54 -> 50 -> 52).");
		evalPosition(spiel, "BLAU:0:28, GELB:52:21", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Neuanfang"-Feld. Darauf hin muss sie zurück zum Startfeld.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet auf einem \"Neuanfang\"-Feld und muss sie zurueck zum Startfeld.")
	public void testDesasterMitNeuanfang() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:56, GELB-B:21, BLAU-B:28";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 6, 6, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 0 sein (Kettenreaktion: 56 -> 63/58 -> 0).");
		evalPosition(spiel, "BLAU:0:28, GELB:0:21", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet nach einer
	 * Kettenreaktion auf ein "Desaster"-Feld. Darauf hin muss sie die doppelte
	 * gewürfelte Augenzahl zurückgehen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet nach einer Kettenreaktion auf einem \"Desaster\"-Feld.\n"
			       + "Darauf hin muss sie die doppelte gewuerfelte Augenzahl zurueckgehen.")
	public void testDesaster60Auf30() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:60, GELB-B:21, BLAU-B:28";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 6, 6, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 30 sein (Kettenreaktion: 60 -> 63/54 -> 30).");
		evalPosition(spiel, "BLAU:0:28, GELB:30:21", "spiel");
	}
	
	//-------------------------------------------------------------------------------------------

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

}