package at.ac.tgm.fkoch.dicebox;

import android.os.Bundle;
import android.widget.*;
import at.ac.tgm.fkoch.dicebox.model.*;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

/**
 * Die Aktivität der Würfel-App
 * @author Felix Koch
 * @version 2020-11-25
 */
public class MainActivity extends AppCompatActivity {
    private TextView display;
    private TextView description;
    private CheckBox abilityCheck;
    private CheckBox profCheck;
    private CheckBox itemCheck;
    private EditText abilityBonus;
    private EditText profBonus;
    private EditText itemBonus;
    private RadioGroup advGrp;
    private DiceRoll die;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set-Up a Basic Dice Roll
        die = new BasicDiceRoll(20);

        // set-up views
        display = findViewById(R.id.resultDisplay);
        description = findViewById(R.id.descriptionText);

        advGrp = findViewById(R.id.advantageGroup);
        advGrp.setOnCheckedChangeListener((btnGrp,checkedID)->updateDie());

        abilityCheck = findViewById(R.id.checkAbility);
        abilityCheck.setOnCheckedChangeListener((buttonView, isChecked) -> updateDie());
        profCheck = findViewById(R.id.checkProficiency);
        profCheck.setOnCheckedChangeListener((a,b)-> updateDie());
        itemCheck = findViewById(R.id.checkItem);
        itemCheck.setOnCheckedChangeListener((a,b)-> updateDie());

        // TODO fix updateing after text-input-change
        abilityBonus = findViewById(R.id.abilityBonus);
        abilityBonus.setOnEditorActionListener((v, actionId, event) -> {updateDie(); return true;});
        profBonus = findViewById(R.id.profBonus);
        profBonus.setOnEditorActionListener((v, actionId, event) -> {updateDie(); return true;});
        itemBonus = findViewById(R.id.itemBonus);
        itemBonus.setOnEditorActionListener((v, actionId, event) -> {updateDie(); return true;});
    }

    /**
     * Rolls the die
     * @param view not used
     */
    public void rollDie(View view) {
        die.roll();
        display.setText(String.valueOf(die.getResult()));
        description.setText(die.toString());
    }

    /**
     * Updates the die, according to the selected options
     */
    private void updateDie() {
        // select advantage
        switch (advGrp.getCheckedRadioButtonId()) {
            case R.id.advantageBtn:
                die = new AdvantageRoll(new BasicDiceRoll(20), new BasicDiceRoll(20));
                break;
            case R.id.disadvantageBtn:
                die = new DisadvantageRoll(new BasicDiceRoll(20),new BasicDiceRoll(20));
                break;
            case R.id.normalBtn:
            default:
                die = new BasicDiceRoll(20);
        }

        // Bonis Ability
        if(abilityCheck.isChecked()) {
            String txt = abilityBonus.getText().toString();
            int amount = txt.equals("")?0:Integer.parseInt(txt);
            die = new BonusMalus(die, getText(R.string.abilityText).toString(), amount);
        }
        if(profCheck.isChecked()) {
            String txt = profBonus.getText().toString();
            int amount = txt.equals("")?0:Integer.parseInt(txt);
            die = new BonusMalus(die, getText(R.string.profText).toString(), amount);
        }
        if(itemCheck.isChecked()) {
            String txt = itemBonus.getText().toString();
            int amount = txt.equals("")?0:Integer.parseInt(txt);
            die = new BonusMalus(die, getText(R.string.itemText).toString(), amount);
        }

    }
}
