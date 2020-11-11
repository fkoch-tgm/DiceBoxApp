package at.ac.tgm.fkoch.dicebox.model;

/**
 * Symbolisiert einen Würfelwurf mit "Advantage", wobei zwei Würfel geworfen werden und der höhere Wurf zählt
 */
public class AdvantageRoll extends RollDecoration {

	private DiceRoll secondDice;

	public AdvantageRoll(DiceRoll roll, DiceRoll secondRoll) {
		super(roll);
		this.secondDice = secondRoll;
	}

	@Override
	public void roll() {
		super.roll();
		secondDice.roll();
	}

	@Override
	public String toString() {
	    return " Advantage["+super.getResult()+", "+secondDice.getResult()+"]";
	}

	@Override
	public int getResult() {
	    int r1 = super.getResult();
	    int r2 = secondDice.getResult();
		return Math.max(r1, r2);
	}

}
