package de.ostfalia.prog.ss22.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
 * TestfÃ¤lle fÃ¼r Aufgabe 1
 * 
 * @author D. Dick
 * @author M. GrÃ¼ndel
 * @since SS 2022
 *
 */
@RunWith(TopologicalSortRunner.class)
public class Aufgabe1Test {
	
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
	 * TestfÃ¤lle fÃ¼r die Initialisierung des Spiels
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt zwei unterschiedlichen Instanzen der Klasse Paradiesspiel und
	 * Ã¼berprÃ¼ft, ob die Attributen ggf. 'static' deklariert sind.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Zwei Instanzen vom Paradiesspiel muessen unabhaengig spielbar sein.")
	public void testZweiInstanzen() throws Exception {
		Farbe[] farbenA = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN, Farbe.ROT};
		trace.add(callGame, farbenA, "spielA");
		IParadiesspiel spielA = new Paradiesspiel(farbenA);
		
		Farbe[] farbenB = {Farbe.BLAU, Farbe.GELB};
		trace.add(callGame, farbenB, "spielB");
		IParadiesspiel spielB = new Paradiesspiel(farbenB);

		evalAlleSpieler(spielA, farbenA, "spielA");
		evalAlleSpieler(spielB, farbenB, "spielB");
		
		evalPosition(spielA, "BLAU:0:0, GELB:0:0, GRUEN:0:0, ROT:0:0", "spielA");		
		evalPosition(spielB, "BLAU:0:0, GELB:0:0, GRUEN:-1:-1, ROT:-1:-1", "spielB");
	}

	/**
	 * Test erzeugt 6 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * mÃ¼ssen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figuren der 6 Spieler muessen sich auf der Spielfeldposition 0 befinden.")
	public void testErzeuge6SpielerFigurenStandard() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN, Farbe.ROT, Farbe.SCHWARZ, Farbe.WEISS};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);

		evalAlleSpieler(spiel, farben, "spiel");		
		evalPosition(spiel, "BLAU:0:0, GELB:0:0, GRUEN:0:0, ROT:0:0, SCHWARZ:0:0, WEISS:0:0", "spiel");
	}

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * mÃ¼ssen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figuren der 3 Spieler muessen sich auf der Spielfeldposition 0 befinden.")	
	public void testErzeuge3SpielerFigurenStandard() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);

		evalAlleSpieler(spiel, farben, "Spiel");
		evalPosition(spiel, "BLAU:0:0, GELB:0:0, GRUEN:0:0", "spiel");
	}

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * mÃ¼ssen. Es wird im Test nach einer Figur gesucht, die nicht existiert (Farbe
	 * nicht vorhanden).
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Figuren (Farben) die nicht am Spiel teinehmen, duerfen nicht gefunden werden.")
	public void testFigurStartpositionStandardErrorFarbe() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);
		
		evalPosition(spiel, "ROT:-1:-1, SCHWARZ:-1:-1, WEISS:-1:-1", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * mÃ¼ssen. Es wird im Test nach einer Figur gesucht, die nicht existiert (Name
	 * nicht korrekt).
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur BLAU-X existiert nicht und darf daher nicht gefunden werden.")
	public void testFigurStartpositionStandardErrorName() throws Exception {
		String msg = "Positionen der Figur BLAU-X.";
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);
		
		trace.add("Aufruf der Methode spiel.getFigurposition(\"%s\").", "BLAU-X");
		int got = spiel.getFigurposition("BLAU-X");
		trace.add("Erwartete Positionen der Firur BLAU-X: %d.", -1);
		trace.addInfo(PassTrace.ifEquals(msg, -1, got));
		trace.separator();
		assertEquals(msg, -1, got);
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich nicht auf dem Startfeld befinden
	 * sollen, sondern woanders auf dem Spielfeld.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figuren muessen sich auf den konfigurierten Feldern befinden.")
	public void testFigurStartpositionKonfiguriert() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:30, GELB-B:37, BLAU-B:7";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		evalPosition(spiel, "BLAU:0:7, GELB:30:37", "spiel");
	}

	/*
	 * ***************************************************************************
	 * TestfÃ¤lle fÃ¼r die Bewegung der Figur
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden und
	 * von dort aus bewegt werden. Alle gewÃ¼nschte Figuren exisitieren und kÃ¶nnen
	 * bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Alle Figuren exisitieren und kÃ¶nnen bewegt werden.")
	public void testBewegeFigurExistiert() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		trace.add(callGame, farben, "spiel");		
		IParadiesspiel spiel = new Paradiesspiel(farben);

		bewegeFigur(spiel, "GRUEN-A", 2, 2, true);
		trace.add("Die Figur GRUEN-A muss auf Feld Nr. 4 sein.");
		evalPosition(spiel, "BLAU:0:0, GELB:0:0, GRUEN:4:0", "spiel");
		
		bewegeFigur(spiel, "BLAU-A", 1, 3, true);
		trace.add("Die Figur BLAU-A muss auf Feld Nr. 4 sein.");
		evalPosition(spiel, "BLAU:4:0, GELB:0:0, GRUEN:4:0", "spiel");
		
		bewegeFigur(spiel, "GRUEN-B", 1, 2, true);
		trace.add("Die Figur GRUEN-B muss auf Feld Nr. 3 sein.");
		evalPosition(spiel, "BLAU:4:0, GELB:0:0, GRUEN:4:3", "spiel");
	}

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden. Die
	 * gewÃ¼nschte Figur exisitiert nicht (Name nicht vorhanden) und kann daher nicht
	 * bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur exisitiert nicht (Name nicht vorhanden) und kann daher nicht bewegt werden.")
	public void testBewegeFigurNichtExistiertName() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);

		bewegeFigur(spiel, "GRUEN-X", 1, 4, false);
		trace.add("Die Figur GRUEN-X existiert nicht.");
		evalPosition(spiel, "BLAU:0:0, GELB:0:0, GRUEN:0:0", "spiel");
	}
	
	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden. Die
	 * gewÃ¼nschte Figur gehÃ¶rt aber nicht zum Spieler, der den Zug hat und kann daher nicht
	 * bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur gehoert aber nicht zum Spieler, der den Zug hat und "
			       + "kann daher nicht bewegt werden.")
	public void testBewegeFigurNichtVomSpieler() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);

		bewegeFigur(spiel, "GRUEN-A:GELB", 1, 4, false);
		trace.add("Die Figur GRUEN-A gehÃ¶rt nicht zum Spieler GELB und kann nicht bewegt werden.");
		evalPosition(spiel, "BLAU:0:0, GELB:0:0, GRUEN:0:0", "spiel");
	}

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden. Die
	 * gewÃ¼nschte Figur exisitiert nicht (Farbe nicht bekannt) und kann daher nicht
	 * bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur exisitiert nicht (Farbe nicht bekannt) und kann daher nicht bewegt werden.")
	public void testBewegeFigurNichtExistiertFarbe() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);
		
		bewegeFigur(spiel, "WEISS-A:GRUEN", 3, 4, false);
		trace.add("Die Figur WEISS-A existiert nicht.");
		evalPosition(spiel, "BLAU:0:0, GELB:0:0, GRUEN:0:0, WEISS:-1:-1", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Alle Figuren, die bewegt werden sollten, kÃ¶nnen das
	 * Paradiesfeld erreichen und auch dort beleiben.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Alle Figuren, die bewegt werden, kÃ¶nnen das Paradiesfeld erreichen und "
			       + "auch dort beleiben.")
	public void testBewegeFigurParadiesBleiben() throws Exception {
		String conf = "BLAU-A:60, BLAU-B:51, GELB-B:51";
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "BLAU-A", 2, 1, true);
		trace.add("Die Figur BLAU-A muss auf Feld Nr. 63 (Paradies) sein.");
		evalPosition(spiel, "BLAU:63:51, GELB:0:51", "spiel");
		
		bewegeFigur(spiel, "GELB-B", 6, 6, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 63 (Paradies) sein.");
		evalPosition(spiel, "BLAU:63:51, GELB:0:63", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Alle Figuren, die bewegt werden sollten, kÃ¶nnen nicht im
	 * Paradiesfeld bleiben, und mÃ¼ssen die zuviel gewÃ¼rfelte Augenzahlen
	 * zurÃ¼cklaufen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Alle Figuren, die bewegt werden, koennen nicht im Paradiesfeld bleiben,\n"
			       + "und muessen die zuviel gewuerfelte Augenzahlen zuruecklaufen.")
	public void testBewegeFigurParadiesZurueck() throws Exception {
		String conf = "BLAU-A:61, BLAU-B:51, GELB-B:62";
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "BLAU-A", 2, 3, true);
		trace.add("Die Figur BLAU-A muss auf Feld Nr. 60 sein.");
		evalPosition(spiel, "BLAU:60:51, GELB:0:62", "spiel");
		
		bewegeFigur(spiel, "GELB-B", 1, 2, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 61 sein.");
		evalPosition(spiel, "BLAU:60:51, GELB:0:61", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Es wird versucht eine Figur, die sich bereits im Paradies
	 * befindet, von dort aus zu bewegen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Es wird versucht eine Figur, die sich bereits im Paradies befindet, "
			       + "von dort aus zu bewegen.")
	public void testBewegeFigurAusDemParadies() throws Exception {
		String conf = "BLAU-A:63, BLAU-B:51, GELB-B:63";
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		trace.add("Die Figur BLAU-A darf das Paradies nicht verlassen.");
		bewegeFigur(spiel, "GELB-B", 1, 2, false);
		trace.add("Die Figur BLAU-A muss weiterhin auf Feld Nr. 63 (Paradies) sein.");
		evalPosition(spiel, "BLAU:63:51, GELB:0:63", "spiel");
	}
	
	/*
	 * ***************************************************************************
	 * TestfÃ¤lle fÃ¼r "GlÃ¼ck"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Die Figuren landen jeweils 1x auf ein "GlÃ¼ck"-Feld und
	 * dÃ¼rfen sich noch einmal bewegen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figuren landen jeweils 1x auf einem \"GlÃ¼ck\"-Feld und " + 
			         "duerfen sich noch einmal bewegen.")
	public void testGlueck1x() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:12, GELB-B:15, BLAU-B:28";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 1, 1, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 16 sein.");
		evalPosition(spiel, "GELB:16:15, BLAU:0:28", "spiel");
		
		bewegeFigur(spiel, "GELB-B", 6, 6, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 39 sein.");
		evalPosition(spiel, "GELB:16:39, BLAU:0:28", "spiel");
	}
	
	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur bewegt sich und, weil sie auf ein "GlÃ¼ck"-Feld
	 * landet, bewegt sie sich erneut. Dabei landet sie erneut auf ein "GlÃ¼ck"-Feld
	 * und bewegen sich erneut bis die Kettenreaktion vollzogen ist.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Mehrmaliges Betreten des \"GlÃ¼ck\"-Felds (Kettenreaktion).")
	public void testGlueckKettenreaktion1() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:15, GELB-B:21, BLAU-B:28";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "BLAU-B", 2, 2, true);
		trace.add("Die Figur BLAU-B muss auf Feld Nr. 40 sein (Kettenreaktion: 28 -> 32 -> 40).");
		evalPosition(spiel, "BLAU:0:40, GELB:15:21", "spiel");
	}
	
	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur bewegt sich und, weil sie auf ein "GlÃ¼ck"-Feld
	 * landet, bewegt sie sich erneut. Dabei landet sie erneut auf ein "GlÃ¼ck"-Feld
	 * und bewegen sich erneut bis die Kettenreaktion vollzogen ist.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Mehrmaliges Betreten des \"GlÃ¼ck\"-Felds (Kettenreaktion).")
	public void testGlueckKettenreaktion2() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:10, GELB-B:21, BLAU-B:28";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 2, 2, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 22 sein (Kettenreaktion: 10 -> 14 -> 18 -> 22).");
		evalPosition(spiel, "BLAU:0:28, GELB:22:21", "spiel");
	}
	
	/*
	 * ***************************************************************************
	 * TestfÃ¤lle fÃ¼r "BrÃ¼cke"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich auf dem Startfeld befinden. Eine
	 * Figur wird bewegt und landet dabei auf einem "BrÃ¼cke"-Feld und wird - obwohl es
	 * ihre Ziel war - weiterbefÃ¶rdert.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet auf einem \"BrÃ¼cke\"-Feld und ueberquert die Bruecke.")
	public void testBrueckeZiel() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);

		bewegeFigur(spiel, "GRUEN-A", 3, 3, true);
		trace.add("Die Figur GRUEN-A muss auf Feld Nr. 12 sein.");
		evalPosition(spiel, "BLAU:0:0, GELB:0:0, GRUEN:12:0", "spiel");
	}
	
	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und kommt an einem "BrÃ¼cke"-Feld
	 * vorbei. Die Weiterbewegung findet dann vom Ziel der BrÃ¼cke normal weiter
	 * statt. Es findet keine Kattenreaktion statt.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur kommt an einem \"BrÃ¼cke\"-Feld vorbei, ueberquert die Bruecke und wird weiterbewegt.")
	public void testBrueckeNichtZiel() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:4, GELB-B:4, BLAU-B:7";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 1, 2, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 13 sein (Weg: 4 -> 5 -> 6 /12 -> 13).");
		evalPosition(spiel, "BLAU:0:7, GELB:13:4", "spiel");
		
		bewegeFigur(spiel, "GELB-B", 6, 6, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 22 sein (Weg: 4 -> 5 -> 6/12 -> 13 -> 14 -> ... -> 22).");
		evalPosition(spiel, "BLAU:0:7, GELB:13:22", "spiel");
	}
	
	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich auf dem Startfeld befinden. Eine
	 * Figur wird bewegt, kommt an einem "BrÃ¼cke"-Feld vorbei und landet schlieÃŸlich
	 * auf ein "GlÃ¼ck"-Feld. Daraus entsteht eine Kettenreaktion.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur kommt an einem \"BrÃ¼cke\"-Feld vorbei und landet schlieÃŸlich auf einem \"GlÃ¼ck\"-Feld.")
	public void testBrueckeMitGlueck() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		trace.add(callGame, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(farben);

		bewegeFigur(spiel, "GELB-B", 6, 2, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 22 sein "
				+ "(Kettenreaktion: 0 -> 1 -> ... -> 6/12 -> 13 -> 14 -> 15 -> ... -> 22).");
		evalPosition(spiel, "BLAU:0:0, GELB:0:22", "spiel");
	}
	
	/*
	 * ***************************************************************************
	 * TestfÃ¤lle fÃ¼r "Aufschwung"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und geht dabei an einem
	 * "Aufschwung"-Feld vorbei. Es wird keine besondere Reaktion erwartet.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur  geht an einem \"Aufschwung\"-Feld vorbei. Es wird keine besondere Reaktion erwartet.")
	public void testAufschwungVorbei() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:51, GELB-B:46, BLAU-B:46";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-A", 1, 1, true);
		trace.add("Die Figur GELB-A muss auf Feld Nr. 53 sein.");
		evalPosition(spiel, "BLAU:0:46, GELB:53:46", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf einem
	 * "Aufschwung"-Feld. Da sie mit 2 6-er das Feld erreichte, wird sie direkt zum Paradies befÃ¶rdert.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet auf einem \"Aufschwung\"-Feld.\n"
			       + "Da sie mit zwei 6-er das Feld erreichte, wird sie direkt zum Paradies befoerdert.")
	public void testAufschwungMit12InsParadies() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:51, GELB-B:40, BLAU-B:46";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-B", 6, 6, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 63 sein (Weg: 40 -> 41 -> ... -> 52 -> 63).");
		evalPosition(spiel, "BLAU:0:46, GELB:51:63", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Aufschwung"-Feld. Da sie nicht mit 2 6-er das Feld erreichte, bleibt sie dort stehen.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur landet dabei auf einem \"Aufschwung\"-Feld.\n"
			       + "Da sie nicht mit zwei 6-er das Feld erreichte, bleibt sie dort stehen.")
	public void testAufschwungMit7NichtInsParadies() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:51, GELB-B:45, BLAU-B:46";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-B", 1, 6, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 52 sein.");
		evalPosition(spiel, "BLAU:0:46, GELB:51:52", "spiel");
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und kommt bis zum Paradies, muss aber zurÃ¼ckgehen. Dabei landet sie auf ein
	 * "Aufschwung"-Feld und wird direkt zum Paradies befÃ¶rdert, da sie mit 2 6-er das Feld erreichte.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Die Figur kommt bis zum Paradies, muss aber zurueck gehen. Dabei landet sie auf einem\n" + 
			         "\"Aufschwung\"-Feld und wird direkt zum Paradies befoerdert, da sie mit zwei 6-er das Feld erreichte.")
	public void testAufschwungVon62InsParadies() throws Exception {
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		String conf = "GELB-A:51, GELB-B:62, BLAU-B:46";
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "GELB-B", 6, 6, true);
		trace.add("Die Figur GELB-B muss auf Feld Nr. 63 sein (Weg: 62 -> 63 -> 62 -> ... -> 52 -> 63).");
		evalPosition(spiel, "BLAU:0:46, GELB:51:63", "spiel");
	}

	/*
	 * ***************************************************************************
	 * TestfÃ¤lle fÃ¼r Gewinner
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden, wobei zwei Spieler je eine Figur im Paradies haben.
	 * Nachdem eine weitere Figur zum Paradies bewegt wurde, steht der Gewinner
	 * fest.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Nachdem eine weitere Figur zum Paradies bewegt wurde, steht der Gewinner fest.")
	public void testGewinner() throws Exception {
		String conf = "BLAU-A:63, BLAU-B:61, GELB-B:63";
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "BLAU-B", 1, 1, true);
		trace.add("Der Spieler BLAU hat gewonnen.");
		evalPosition(spiel, "BLAU:63:63, GELB:0:63", "spiel");		
		evalGewinner(spiel, Farbe.BLAU);
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden, wobei ein Spieler zwei Figuren im Paradies hat und somit
	 * der Gewinner ist. Keine weitere Figur kann bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	@TestDescription("Ein Spieler hat zwei Figuren im Paradies und ist somit der Gewinner.\n"
			       + "Keine weitere Figur kann bewegt werden.")
	public void testBewegeFigurNachEnde() throws Exception {
		String conf = "BLAU-A:63, BLAU-B:61, GELB-B:63";
		Farbe[] farben = {Farbe.BLAU, Farbe.GELB};
		trace.add(callGameConf, conf, farben, "spiel");
		IParadiesspiel spiel = new Paradiesspiel(conf, farben);
		
		bewegeFigur(spiel, "BLAU-B", 1, 1, true);
		trace.add("Der Spieler BLAU hat gewonnen.");
		evalPosition(spiel, "BLAU:63:63, GELB:0:63", "spiel");
		
		bewegeFigur(spiel, "GELB-A", 1, 1, false);
		trace.add("Die Figur GELB-A darf sich nicht bewegen. Das Spiel ist aus, da ein Gewinner bereits gibt.");
		evalPosition(spiel, "BLAU:63:63, GELB:0:63", "spiel");
	}
	
	//--------------------------------------------------------------------------
	
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
	 * Ueberpruefen des Spielgewinners.
	 * @param spiel - Instanz des Paradiesspiels.
	 * @param expected - Erwartete Farbe des Spielgewinners.
	 */
	private void evalGewinner(IParadiesspiel spiel, Farbe expected) {
		String msg = "Gewinner des Spiels";
		trace.add(msg + " muss %s sein.", expected);
		Farbe gewinner = spiel.getGewinner();
		trace.addInfo(PassTrace.ifEquals("Rueckgabe der Methode getGewinner().", expected, gewinner));
		trace.separator();
		assertEquals(msg, expected, gewinner);
	}

}