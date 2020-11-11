package at.ac.tgm.fkoch.dicebox.model;

import java.util.Random;

/**
 * Repräsentiert einen Würfelwurf
 * @author Felix Koch
 * @version 2020-11-11
 */
public class BasicDiceRoll implements DiceRoll {

    private int result;
	private int sides;

	/**
	 * Erstellt einen neuen Würfelwurf mit 'sides' Seiten
	 * @param sides die Anzahl der Seiten des Würfels
	 */
	public BasicDiceRoll(int sides) {
		if (sides < 1) throw new IllegalArgumentException("die Seitenanzahl "+sides+" ist nicht gültig");
		this.sides = sides;
		roll();
	}

	/**
	 * würfelt einen neuen Würfelwert aus
	 */
	public void roll() {
	    Random random = new Random();
		result = random.nextInt(sides +1)+1;
	}

	/**
	 * @return das Ergebnis als String
	 */
	@Override
	public String toString() {
	    return String.valueOf(getResult());
	}

	/**
	 * @return den gewürfelten Wert
	 * @see BasicDiceRoll#roll()
	 */
	public int getResult() {
		return result;
	}

}
