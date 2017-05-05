package de.hsMannheim.ss17.oot.martinDavid.uebungBlackBox;

import static org.junit.Assert.*;

import org.junit.Test;

import ss17.PosEingabe;
import ss17.Semester;

public class PosEingabeTest {
	
	@Test
	public void gueltigerTestfall1() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote("AA", Semester.WS, 1998, 1000000, 1);
	}
	
	@Test
	public void gueltigerTestfall2() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote("ZZZZ", Semester.SS, 2061, 9999999, 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void zuKurzesFach() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote("Z", Semester.WS, 1998, 1000000, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void leererStringFach() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote("", Semester.WS, 1998, 1000000, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void buchstabeZuNiedrigFach() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote("A@", Semester.WS, 1998, 1000000, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void buchstabeZuHochFach() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote("A[", Semester.WS, 1998, 1000000, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullStringFach() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote(null, Semester.WS, 1998, 1000000, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void zuGeringeNote() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote("AA", Semester.WS, 1998, 1000000, 0.9);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void zuHoheNote() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote("AA", Semester.WS, 1998, 1000000, 4.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void falschesIntervallNote() {
		PosEingabe pos = new PosEingabe();
		pos.uebermittleNote("AA", Semester.WS, 1998, 1000000, 1.05);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
