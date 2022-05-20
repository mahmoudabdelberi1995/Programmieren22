package de.ostfalia.prog.ss22.test;

import static org.junit.Assert.assertFalse;

import java.util.concurrent.TimeUnit;

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
import de.ostfalia.prog.ss22.exceptions.FalscheSpielerzahlException;
import de.ostfalia.prog.ss22.exceptions.UngueltigePositionException;

/**
 * Testfälle für Exceptions (Aufgabe 2)
 * 
 * @author D. Dick
 * @author M. Gründel
 * @since SS 2022
 *
 */

@RunWith(TopologicalSortRunner.class)
public class Aufgabe2ExceptionsTest {
	
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

	private String callGame      = "Konstruktoraufruf Paradiesspiel(%s).";
	private String callGameConf  = "Konstruktoraufruf Paradiesspiel(\"%s\", %s).";
	private String msgExpected   = "Exception wurde erwartet.";
	private String msgUnExpected = "Falsche Exception wurde geworfen.";
	private String msgError      = "Fehlerhafte Exceptions.";
	private String msgNone       = "<keine>";
	
	
	@Test
	@TestDescription("Spiel mit nur einer Spielerfarbe. FalscheSpielerzahlException wird erwartet.")
	public void testNurEinSpieler() {
		trace.add("Versuch ein Spiel mit nur einer Spielerfarbe zu erstellen.");
		evalSpielerzahl(Farbe.BLAU);
	}
	
	
	@Test
	@TestDescription("Ungueltige Anzahl von Spielerfarben. FalscheSpielerzahlException wird erwartet.")
	public void testSiebenSpieler() {
		trace.add("Ungueltige Anzahl von Spielerfarben (Farbe GELB ist doppelt).");
		evalSpielerzahl(Farbe.GRUEN, Farbe.BLAU, Farbe.GELB, Farbe.ROT, 
                        Farbe.SCHWARZ, Farbe.WEISS, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 5 \"Pech\" befinden. "
			       + "UngueltigePositionException wird erwartet.")
	public void testExceptionPech_5() {
		trace.add("GELB-A befindet sich auf Feld 5 \"Pech\".");
		evalPosition("GELB-A:5, GELB-B:46, BLAU-B:12", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 9 \"Pech\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionPech_9() {
		trace.add("GELB-A befindet sich auf Feld 9 \"Pech\".");
		evalPosition("GELB-A:9, GELB-B:46, BLAU-B:12", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 6 \"Bruecke\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionBruecke_6() {
		trace.add("GELB-B befindet sich auf Feld 6 \"Bruecke\".");
		evalPosition("GELB-A:4, GELB-B:6, BLAU-B:12", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 14 \"Glueck\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionGlueck_14() {
		trace.add("GELB-B befindet sich auf Feld 14 \"Glueck\".");
		evalPosition("GELB-A:8, GELB-B:14, BLAU-B:12", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 18 \"Glueck\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionGlueck_18() {
		trace.add("GELB-A befindet sich auf Feld 18 \"Glueck\".");
		evalPosition("GELB-A:18, GELB-B:46, BLAU-B:12", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 27 \"Glueck\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionGlueck_27() {
		trace.add("BLAU-B befindet sich auf Feld 27 \"Glueck\".");
		evalPosition("GELB-A:25, GELB-B:46, BLAU-B:27", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 32 \"Glueck\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionGlueck_32() {
		trace.add("GELB-A befindet sich auf Feld 32 \"Glueck\".");
		evalPosition("GELB-A:32, GELB-B:46, BLAU-B:26", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 36 \"Glueck\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionGlueck_36() {
		trace.add("BLAU-B befindet sich auf Feld 36 \"Glueck\".");
		evalPosition("GELB-A:22, GELB-B:46, BLAU-B:36", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 50 \"Glueck\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionGlueck_50() {
		trace.add("BLAU-B befindet sich auf Feld 50 \"Glueck\".");
		evalPosition("GELB-A:22, GELB-B:46, BLAU-B:50", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 24 \"Desaster\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionDesaster_24() {
		trace.add("GELB-B befindet sich auf Feld 24 \"Desaster\".");
		evalPosition("GELB-A:23, GELB-B:24, BLAU-B:12", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 41 \"Desaster\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionDesaster_41() {
		trace.add("GELB-B befindet sich auf Feld 41 \"Desaster\".");
		evalPosition("GELB-A:23, GELB-B:41, BLAU-B:12", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 54 \"Desaster\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionDesaster_54() {
		trace.add("GELB-B befindet sich auf Feld 54 \"Desaster\".");
		evalPosition("GELB-A:23, GELB-B:54, BLAU-B:12", Farbe.BLAU, Farbe.GELB);
	}
	
	@Test
	@TestDescription("Spieler kann sich nicht auf Feld 58 \"Desaster\" befinden. "
                   + "UngueltigePositionException wird erwartet.")
	public void testExceptionNeuanfang_58() {
		trace.add("GELB-B befindet sich auf Feld 58 \"Desaster\".");
		evalPosition("GELB-A:23, GELB-B:58, BLAU-B:12", Farbe.BLAU, Farbe.GELB);
	}
	
	//--------------------------------------------------------------------------
	
	private void evalSpielerzahl(Farbe... farben) {		
		Class<?> exp = FalscheSpielerzahlException.class;
		try {
			trace.add(callGame, (Object)farben);		
			new Paradiesspiel(farben);
			trace.add(PassTrace.ifEquals(msgExpected, exp, msgNone));
		} catch (FalscheSpielerzahlException e) {
		} catch (Exception e) {
			trace.add(PassTrace.ifEquals(msgUnExpected, exp, e.getClass()));
		}
		trace.separator();
		assertFalse(msgError, trace.hasOccurrences());
	}
	
	private void evalPosition(String conf, Farbe... farben) {
		Class<?> exp = UngueltigePositionException.class;
		try {
			trace.add(callGameConf, conf, farben);		
			new Paradiesspiel(conf, farben);
			trace.add(PassTrace.ifEquals(msgExpected, exp, msgNone));
		} catch (UngueltigePositionException e) {
			//alles ok.
		} catch (Exception e) {
			trace.add(PassTrace.ifEquals(msgUnExpected, exp, e.getClass()));
		}
		trace.separator();
		assertFalse(msgError, trace.hasOccurrences());
	}

}