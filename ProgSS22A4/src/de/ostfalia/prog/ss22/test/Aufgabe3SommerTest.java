package de.ostfalia.prog.ss22.test;

import static org.junit.Assert.assertArrayEquals;
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
import de.ostfalia.prog.ss22.ParadiesspielSommer;
import de.ostfalia.prog.ss22.enums.Farbe;
import de.ostfalia.prog.ss22.interfaces.IParadiesspiel;

/**
 * Testfälle für Aufgabe 3 mit dem angepassten Spiel
 * 
 * @author D. Dick
 * @author M. Gründel
 * @since SS 2022
 *
 */
@RunWith(TopologicalSortRunner.class)
public class Aufgabe3SommerTest {

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
	 * *************************************************************************
	 * ** Testfälle für die Initialisierung des Spiels
	 * *************************************************************************
	 * **
	 */

	/**
	 * Test erzeugt zwei unterschiedlichen Instanzen der Klasse Paradiesspiel
	 * bzw. ParadiesspielSommer und überprüft, ob die Attributen ggf. 'static'
	 * deklariert sind.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testZweiInstanzenOld() throws Exception {
		Farbe[] farbenStandard = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN, Farbe.ROT};
		trace.add(callGame, farbenStandard, "spielStandard");		
		IParadiesspiel spielStandard = new Paradiesspiel(farbenStandard);
		
		Farbe[] farbenSommer = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:30, GELB-B:37, BLAU-B:7";
		trace.add(callGameConf, conf, farbenSommer, "spielSommer");
		IParadiesspiel spielSommer = new ParadiesspielSommer(conf, farbenSommer);
		
		evalPosition(spielStandard, "BLAU:0:0, GELB:0:0, GRUEN:0:0, ROT:0:0", "spielStandard");
		evalPosition(spielSommer, "BLAU:0:7:0, GELB:30:37:0, GRUEN:-1:-1:-1, ROT:-1:-1:-1", "spielSommer");
	}
	
	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * müssen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testErzeuge3SpielerFigurenStandard() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		trace.add(callGame, farben, "spiel");		
		IParadiesspiel spiel = new ParadiesspielSommer(farben);

		evalAlleSpieler(spiel, farben, "spiel");
		evalPosition(spiel, "BLAU:0:0:0, GELB:0:0:0, GRUEN:0:0:0", "spiel");
		evalPosition(spiel, "WEISS:-1:-1:-1, ROT:-1:-1:-1, SCHWARZ:-1:-1:-1", "spiel");
	}

	/*
	 * *************************************************************************
	 * ** Testfälle für die Bewegung der Figur ins Paradies
	 * *************************************************************************
	 * **
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf
	 * dem Spielfeld befinden. Alle Figuren, die bewegt werden sollten, können
	 * das Paradiesfeld erreichen und auch dort bleiben.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBewegeFigurParadiesBleiben() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "BLAU-A:67, BLAU-B:70, GELB-B:59";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new ParadiesspielSommer(conf, farben);

		bewegeFigur(spiel, "BLAU-A", 2, 1, true);
		trace.add("Die Figur BLAU-A muss auf Feld Nr. 70 (Paradies) sein.");
		evalPosition(spiel, "BLAU:70:70:0, GELB:0:59:0", "spiel");
		
		bewegeFigur(spiel, "GELB-B", 6, 5, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 70 (Paradies) sein.");
		evalPosition(spiel, "BLAU:70:70:0, GELB:0:70:0", "spiel");
	}

	/*
	 * *************************************************************************
	 * ** Testfälle für "Glück"-Feld
	 * *************************************************************************
	 * **
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf
	 * dem Spielfeld befinden. Die Figuren landen jeweils 1x auf ein
	 * "Glück"-Feld und dürfen sich noch einmal bewegen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGlueck1x() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:10, GELB-B:15, BLAU-B:28";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new ParadiesspielSommer(conf, farben);

		bewegeFigur(spiel, "GELB-A", 2, 2, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 18 sein.");
		evalPosition(spiel, "BLAU:0:28:0, GELB:18:15:0", "spiel");
		
		bewegeFigur(spiel, "GELB-B", 6, 6, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 39 sein.");
		evalPosition(spiel, "BLAU:0:28:0, GELB:18:39:0", "spiel");
	}

	/*
	 * *************************************************************************
	 * ** Testfälle für "Brücke"-Feld
	 * *************************************************************************
	 * **
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich auf dem Startfeld befinden.
	 * Eine Figur wird bewegt und landet dabei auf ein "Brücke"-Feld und wird
	 * daher weiterbefördert.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBrueckeZiel() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new ParadiesspielSommer(farben);

		bewegeFigur(spiel, "GRUEN-C", 4, 2, true);
		trace.add("Die Figur GRUEN-C muss auf Feld Nr. 12 sein.");
		evalPosition(spiel, "BLAU:0:0:0, GELB:0:0:0, GRUEN:0:0:12", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf
	 * dem Spielfeld befinden. Eine Figur wird bewegt und kommt an einem
	 * "Brücke"-Feld vorbei. Die Weiterbewegung findet dann vom Ziel der Brücke
	 * normal weiter statt. Dabei landet sie zuerst auf einem "Glück" und danach
	 * auf einem "Desaster"-Feld und bleibt schließlich auf einem
	 * "Labyrinth"-Feld stehen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBrueckeNichtZielMitKettenreaktion() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-C:40, GELB-B:4, BLAU-B:7";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new ParadiesspielSommer(conf, farben);

		bewegeFigur(spiel, "GELB-C", 1, 3, true);
		trace.add("Die Figur GELB-C muss auf Feld Nr. 46 sein (Weg: 40 -> 41 -> 42/48 -> 50 (Glück) -> 51 --> ... -> 54 (Desaster) -> 53 -> ... -> 46).");
		evalPosition(spiel, "BLAU:0:7:0, GELB:0:4:46", "spiel");
		
		bewegeFigur(spiel, "GELB-A", 1, 2, false);
		trace.add("Die Figur GELB-A muss immer noch auf Feld Nr. 0 sein, zumal GELB aussetzen muss.");
		evalPosition(spiel, "BLAU:0:7:0, GELB:0:4:46", "spiel");
	}
	
	//---------------------------------------------------------------------------------------------
	
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
	
	/**
	 * Ueberpruefen der am Spiel beteiligten Spielerfarben.
	 * @param spiel - Instanz des Paradiesspiels.
	 * @param erwarteteFarben - Erwartete Farben im Spiel. 
	 * @param inst - Name der Spielintanz.
	 */
	private void evalAlleSpieler(IParadiesspiel spiel, Farbe[] erwarteteFarben, String inst) {
		String msg = "Vorhandene Farben im Spiel.";
		trace.add("Erwartete Rueckgabe von %s.getAlleSpieler(): %s", inst, (Object)erwarteteFarben);
		Farbe[] actual = spiel.getAlleSpieler();
		trace.addInfo(PassTrace.ifEquals(msg, erwarteteFarben, actual));
		assertArrayEquals(msg, erwarteteFarben,	actual);
	}
	
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
}