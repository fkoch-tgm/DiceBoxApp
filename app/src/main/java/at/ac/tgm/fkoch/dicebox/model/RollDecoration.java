package at.ac.tgm.fkoch.dicebox.model;

/**
 * Symbolisiert die Gemeinsamkeiten aller dekorierenden DiceRoll-Methoden
 * @author Felix Koch
 * @version 2020-11-11
 */
public abstract class RollDecoration implements DiceRoll {

	private DiceRoll sourceRoll;

	/**
	 * Erstellt eine neue Würfelwurf-Dekoration
	 * @param sourceRoll der zugrundeliegende Würfelwurf
	 */
	RollDecoration(DiceRoll sourceRoll){
		this.sourceRoll = sourceRoll;
	}

	/**
	 * @return den zugrundeliegenden Würfelwurf
	 */
	public DiceRoll getDiceRoll() {
		return sourceRoll;
	}

	@Override
	public String toString() {
		return sourceRoll.toString();
	}

	@Override
	public int getResult() {
		return sourceRoll.getResult();
	}


	@Override
	public void roll() {
		sourceRoll.roll();
	}

}
