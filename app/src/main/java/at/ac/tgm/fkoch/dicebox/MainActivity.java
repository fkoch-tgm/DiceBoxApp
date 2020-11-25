package at.ac.tgm.fkoch.dicebox;

import android.os.Bundle;
import android.widget.TextView;
import at.ac.tgm.fkoch.dicebox.model.BasicDiceRoll;
import at.ac.tgm.fkoch.dicebox.model.DiceRoll;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private TextView description;
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
    }

    /**
     * Rolls the die
     */
    public void rollDie(View view) {
        die.roll();
        display.setText(String.valueOf(die.getResult()));
        description.setText(die.toString());
    }


}