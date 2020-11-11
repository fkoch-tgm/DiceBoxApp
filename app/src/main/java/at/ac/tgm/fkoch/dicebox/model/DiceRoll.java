package at.ac.tgm.fkoch.dicebox.model;


/**
 * Symbolisiert einen Würfelwurf
 * @author Felix KOch
 * @version 2020-11-11
 */
public abstract interface DiceRoll {

	/**
	 * würfelt einen neuen Wert
	 */
	public abstract void roll();

	/**
	 * @return eine schöne Lesbare Darstellung des Würfelaufbaus aus
	 */
	public abstract String toString();

	/**
	 * @return gibt das Ergebnis des Würfelwurfs zurück
	 */
	public abstract int getResult();

}
