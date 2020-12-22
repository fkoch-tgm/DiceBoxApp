package at.ac.tgm.fkoch.dicebox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.*;
import androidx.annotation.NonNull;
import at.ac.tgm.fkoch.dicebox.model.*;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

/**
 * Die Aktivität der Würfel-App
 * @author Felix Koch
 * @version 2020-11-25
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG= "DEBUG-EK";
    private TextView display;
    private TextView description;
    private CheckBox abilityCheck;
    private CheckBox profCheck;
    private CheckBox itemCheck;
    private Spinner abilityBonus;
    private TextView profBonus;
    private EditText itemBonus;
    private RadioGroup advGrp;

    private Spinner dmgDieSelector;
    private CheckBox checkCrit;
    private TextView dmgdisplay;
    private TextView dmgdescription;
    private CheckBox dmgabilityCheck;
    private CheckBox dmgprofCheck;
    private CheckBox dmgitemCheck;
    private EditText dmgabilityBonus;
    private EditText dmgprofBonus;
    private EditText dmgitemBonus;

    private DiceRoll die;
    private DiceRoll dmgDie;

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
        //advGrp.setOnCheckedChangeListener((btnGrp,checkedID)->updateDie());

        abilityCheck = findViewById(R.id.checkAbility);
        //abilityCheck.setOnCheckedChangeListener((buttonView, isChecked) -> updateDie());
        profCheck = findViewById(R.id.checkProficiency);
        //profCheck.setOnCheckedChangeListener((a,b)-> updateDie());
        itemCheck = findViewById(R.id.checkItem);
        //itemCheck.setOnCheckedChangeListener((a,b)-> updateDie());

        //abilityBonus = findViewById(R.id.abilityBonus);
        //abilityBonus.setOnEditorActionListener((v, actionId, event) -> {updateDie(); return true;});
        //profBonus = findViewById(R.id.profBonus);
        //profBonus.setOnEditorActionListener((v, actionId, event) -> {updateDie(); return true;});
        itemBonus = findViewById(R.id.itemBonus);
        //itemBonus.setOnEditorActionListener((v, actionId, event) -> {updateDie(); return true;});

        // Damage GUI Elements
        dmgDieSelector = findViewById(R.id.dmgSelectDie);
        dmgdisplay = findViewById(R.id.dmgDisplay);
        dmgdescription = findViewById(R.id.dmgDescription);

        dmgabilityCheck = findViewById(R.id.dmgcheckAbility);
        dmgprofCheck = findViewById(R.id.dmgcheckProficiency);
        dmgitemCheck = findViewById(R.id.dmgcheckItem);
        dmgabilityBonus = findViewById(R.id.dmgabilityBonus);
        dmgprofBonus = findViewById(R.id.dmgprofBonus);
        dmgitemBonus = findViewById(R.id.dmgitemBonus);
        checkCrit = findViewById(R.id.checkCritical);

        setDMG();

        // setup toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.abiMenuItem) {
                Intent intent = new Intent(MainActivity.this,AbilityMenuActivity.class);
                startActivity(intent);
                Log.i("DEBUG-EK", "onOptionsItemSelected: fin");
            }
            return false;
        });
        //setSupportActionBar(toolbar); hides my button

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Setup Spinner
        abilityBonus = findViewById(R.id.abilityBonus);
        abilityBonus.setAdapter(null);
        SharedPreferences p = getSharedPreferences(AbilityMenuActivity.PREF_KEY,MODE_PRIVATE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        for (int i = 1; i < AbilityMenuActivity.ABIL_KEYs.length; i++) {
            String desc= getText(AbilityMenuActivity.ABIL_KEYs[i]).toString();
            int ival = p.getInt(desc,0);
            String val = (ival>=0?"+":"")+ival;
            adapter.add(desc+"("+val+")");
        }
        abilityBonus.setAdapter(adapter);

        // Setup Proficency
        profBonus = findViewById(R.id.profBonus);
        int prof = p.getInt(getText(AbilityMenuActivity.ABIL_KEYs[0]).toString(),0);
        profBonus.setText((prof>=0?"+":"")+prof);

    }

    /**
     * Rolls the die
     * @param view not used
     */
    public void rollDie(View view) {
        updateDie();
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
            //String txt = abilityBonus.getText().toString();
            //int amount = txt.equals("")?0:Integer.parseInt(txt);
            //die = new BonusMalus(die, getText(R.string.abilityText).toString(), amount);
        }
        if(profCheck.isChecked()) {
            /*
            String txt = profBonus.getText().toString();
            int amount = txt.equals("")?0:Integer.parseInt(txt);
            die = new BonusMalus(die, getText(R.string.profText).toString(), amount);
            */
        }
        if(itemCheck.isChecked()) {
            String txt = itemBonus.getText().toString();
            int amount = txt.equals("")?0:Integer.parseInt(txt);
            die = new BonusMalus(die, getText(R.string.itemText).toString(), amount);
        }

    }


    /**
     * Called by the DMG Diceroll button
     * @param view not used
     */
    public void rollDMG(View view) {
        setDMG();
        dmgDie.roll();
        dmgdisplay.setText(String.valueOf(dmgDie.getResult()));
        dmgdescription.setText(dmgDie.toString());
    }

    /**
     * Sets the Damage-die to the selected parameters
     */
    public void setDMG() {
        switch (dmgDieSelector.getSelectedItemPosition()) {
            case 0: // d4
            case 1: // d6
            case 2: // d8
            case 3: // d10
            case 4: // d12
                dmgDie = new BasicDiceRoll(4 + dmgDieSelector.getSelectedItemPosition()*2);
                break;
            case Spinner.INVALID_POSITION:
            default:
                Log.e("DMG", "setDMG: Spinner at invalid position");
        }

        // kritisch ja/nein?
        if (checkCrit.isChecked()) dmgDie = new CriticalSuccess(dmgDie);


        // Bonis Ability
        if(dmgabilityCheck.isChecked()) {
            String txt = dmgabilityBonus.getText().toString();
            int amount = txt.equals("")?0:Integer.parseInt(txt);
            dmgDie = new BonusMalus(dmgDie, getText(R.string.abilityText).toString(), amount);
        }
        if(dmgprofCheck.isChecked()) {
            String txt = dmgprofBonus.getText().toString();
            int amount = txt.equals("")?0:Integer.parseInt(txt);
            dmgDie = new BonusMalus(dmgDie, getText(R.string.profText).toString(), amount);
        }
        if(dmgitemCheck.isChecked()) {
            String txt = dmgitemBonus.getText().toString();
            int amount = txt.equals("")?0:Integer.parseInt(txt);
            dmgDie = new BonusMalus(dmgDie, getText(R.string.itemText).toString(), amount);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i("DEBUG-EK", "onOptionsItemSelected: beg");
        return super.onOptionsItemSelected(item);
    }
}
