package at.ac.tgm.fkoch.dicebox.model;

/**
 * Ein kritscher-Erfolgswurf hat den doppelten Wert des eigentlichen Würfelwerts
 * @author Felix Koch
 * @version 2020-11-12
 */
public class CriticalSuccess extends RollDecoration {

	/**
	 * Erstellt einen neune Würfel, wo jeder Wert kritisch ist
	 * @param roll der Basiswürfel
	 */
	public CriticalSuccess(DiceRoll roll) {
		super(roll);
	}

	public String toString() {
		return "Kritisch("+super.getResult()+")";
	}


	public int getResult() {
		return super.getResult()*2;
	}

}
