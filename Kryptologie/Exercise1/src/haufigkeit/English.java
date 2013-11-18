package haufigkeit;

import java.util.ArrayList;

/**
 * Source: http://www.mathe.tu-freiberg.de/~hebisch/cafe/kryptographie/bigramme.html
 * @author Rene
 *
 */
public class English {
	
	public static ArrayList<DoubbleHaufigkeit> getDoubbleLetters(){
		ArrayList<DoubbleHaufigkeit> dh = new ArrayList<DoubbleHaufigkeit>();
		dh.add(new DoubbleHaufigkeit("LL",55));
		dh.add(new DoubbleHaufigkeit("TT",53));
		dh.add(new DoubbleHaufigkeit("SS",41));
		dh.add(new DoubbleHaufigkeit("EE",39));
		dh.add(new DoubbleHaufigkeit("PP",26));
		dh.add(new DoubbleHaufigkeit("OO",23));
		dh.add(new DoubbleHaufigkeit("RR",18));
		dh.add(new DoubbleHaufigkeit("FF",14));
		dh.add(new DoubbleHaufigkeit("CC",12));
		dh.add(new DoubbleHaufigkeit("DD",10));	
		return dh;
	}
	
	public static ArrayList<DoubbleHaufigkeit> getBigrammLetters(){
		ArrayList<DoubbleHaufigkeit> b = new ArrayList<DoubbleHaufigkeit>();
		b.add(new DoubbleHaufigkeit("TH",315));
		b.add(new DoubbleHaufigkeit("HE",251));
		b.add(new DoubbleHaufigkeit("AN",172));
		b.add(new DoubbleHaufigkeit("IN",169));
		b.add(new DoubbleHaufigkeit("ER",154));
		b.add(new DoubbleHaufigkeit("RE",148));
		b.add(new DoubbleHaufigkeit("ON",145));
		b.add(new DoubbleHaufigkeit("ES",145));
		b.add(new DoubbleHaufigkeit("TI",128));
		b.add(new DoubbleHaufigkeit("AT",124));
		b.add(new DoubbleHaufigkeit("ST",121));
		b.add(new DoubbleHaufigkeit("EN",120));
		b.add(new DoubbleHaufigkeit("ND",118));
		b.add(new DoubbleHaufigkeit("OR",113));
		b.add(new DoubbleHaufigkeit("TO",111));
		b.add(new DoubbleHaufigkeit("NT",110));
		b.add(new DoubbleHaufigkeit("ED",107));
		b.add(new DoubbleHaufigkeit("IS",106));
		b.add(new DoubbleHaufigkeit("AR",101));
		return b;
	}
	
	public static ArrayList<Character> getCommonLetters(){
		ArrayList<Character> b = new ArrayList<Character>();
		b.add(new Character('E'));
		b.add(new Character('T'));
		b.add(new Character('A'));
		b.add(new Character('O'));
		b.add(new Character('I'));
		b.add(new Character('N'));
		b.add(new Character('S'));
		b.add(new Character('H'));
		b.add(new Character('R'));
		b.add(new Character('D'));
		b.add(new Character('L'));
		b.add(new Character('C'));
		b.add(new Character('U'));
		b.add(new Character('M'));
		b.add(new Character('W'));
		b.add(new Character('F'));
		b.add(new Character('G'));
		b.add(new Character('Y'));
		b.add(new Character('P'));
		b.add(new Character('B'));
		b.add(new Character('V'));
		b.add(new Character('K'));
		b.add(new Character('J'));
		b.add(new Character('X'));
		b.add(new Character('Q'));
		b.add(new Character('Z'));
		return b;
	}
	
	
	
	// Haufige Bigramme mit seltenen Reversen ("Drehern") sind
	// TH  HE  EA  ND  NT  HA  OU  NG  HI  EO  FT  SC  RS

	// Dagegen treten die folgenden Bigramme fast gleich haufig mit ihren Reversen auf 
	// ER - RE,  ES - SE,  AN - NA,  TI - IT,  ON - NO,  IN - NI,  EN - NE,  AT - TA,  TE - ET,  OR - RO,  TO - OT,  AR - RA,  ST - TS,  IS - SI,  ED - DE,  OF - FO
}


