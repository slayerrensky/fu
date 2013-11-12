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
	
	// Haufige Bigramme mit seltenen Reversen ("Drehern") sind
	// TH  HE  EA  ND  NT  HA  OU  NG  HI  EO  FT  SC  RS

	// Dagegen treten die folgenden Bigramme fast gleich haufig mit ihren Reversen auf 
	// ER - RE,  ES - SE,  AN - NA,  TI - IT,  ON - NO,  IN - NI,  EN - NE,  AT - TA,  TE - ET,  OR - RO,  TO - OT,  AR - RA,  ST - TS,  IS - SI,  ED - DE,  OF - FO
}


