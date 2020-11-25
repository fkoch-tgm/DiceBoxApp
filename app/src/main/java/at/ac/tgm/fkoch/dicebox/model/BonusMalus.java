package at.ac.tgm.fkoch.dicebox.model;

/**
 * Ein Würfelwurf, der einen statischen Bonus (+) oder Malus (-)
 *  dazu bekommt
 * @author Felix Koch
 * @version 2020-11-12
 */
public class BonusMalus extends RollDecoration {

	private String type;
	private int amount;

	/**
	 * Erstellt einen neuen BonuMalus-Würfel
	 * @param roll der Basiswürfel
	 * @param type eine Beschreibung für den Bonus/Malus
	 * @param amount der Wert des statischen Bonus/Malus
	 */
	public BonusMalus(DiceRoll roll, String type, int amount) {
	    super(roll);
		this.type = type;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return super.toString()+" "+(amount<0?"-":"+")+amount+" ("+type+")";
	}

	public int getResult() {
		return super.getResult()+amount;
	}

}
