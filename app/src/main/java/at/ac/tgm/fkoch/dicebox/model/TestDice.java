package at.ac.tgm.fkoch.dicebox.model;

public class TestDice {
    public static void main(String[] args) {
        // Ein 20-seitiger Würfelwurf mit Vorteil und einem Ability-Bonus +3 und einem Proficiency-Bonus +2
        // (Mögliche Ausgabe: Vorteil(15, 11) + 3 (Ability) + 2 (Proficiency), als Ergebnis sollte 20 herauskommen)
        DiceRoll d1 = new BonusMalus(new BonusMalus(new AdvantageRoll(new BasicDiceRoll(20),new BasicDiceRoll(20)),"Ability",3),"Prof",2);
        d1.roll();
        System.out.println("d1 result = " + d1.getResult());
        System.out.println("d1 msg= " + d1.toString());

        //Ein Verdoppelter 8-seitiger Würfelwurf mit einem Ability-Bonus +4 und einem Item-Bonus +1
        // (Mögliche Ausgabe: Kritisch(6) + 4 (Ability) + 1 (Item), als Ergebnis sollte 17 herauskommen)
        DiceRoll d2 = new BonusMalus(new BonusMalus(new CriticalSuccess(new BasicDiceRoll(6)),"Ability",4),"Item",1);
        d2.roll();
        System.out.println("d2 result = " + d2.getResult());
        System.out.println("d2 msg= " + d2.toString());
    }
}
