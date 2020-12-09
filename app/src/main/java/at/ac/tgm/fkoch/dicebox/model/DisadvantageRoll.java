package at.ac.tgm.fkoch.dicebox.model;


/**
 * Symbolisiert einen Würfelwurf mit "Advantage", wobei zwei Würfel geworfen werden und der höhere Wurf zählt
 */
public class DisadvantageRoll extends RollDecoration {

	private DiceRoll secondDice;

	public DisadvantageRoll(DiceRoll roll, DiceRoll secondRoll) {
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
		return " Disadvantage["+super.toString()+", "+secondDice.toString()+"]";
	}

	@Override
	public int getResult() {
		int r1 = super.getResult();
		int r2 = secondDice.getResult();
		return Math.min(r1, r2);
	}

}
