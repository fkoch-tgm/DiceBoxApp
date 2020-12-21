package at.ac.tgm.fkoch.dicebox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AbilityMenuActivity extends AppCompatActivity {
    private static final int[] INPUT_IDs = {R.id.editProf,R.id.editSTR,R.id.editDex,R.id.editCON,R.id.editINT,R.id.editWIS,R.id.editCHARISMA};
    public static final int[] ABIL_KEYs = {R.string.profText,R.string.strength_code,R.string.dex_code,R.string.con_code,R.string.int_code,R.string.wis_code,R.string.char_code};
    public static final String PREF_KEY= "Ability_shared_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_menu);
    }

    public void saveAction(View view) {
        SharedPreferences prefs = getSharedPreferences(PREF_KEY,MODE_PRIVATE);
        SharedPreferences.Editor e = prefs.edit();
        for (int i = 0; i < INPUT_IDs.length; i++) {
            String key = (String) getText(ABIL_KEYs[i]);
            EditText editText = findViewById(INPUT_IDs[i]);
            int value;
            try {
                value = Integer.parseInt(editText.getText().toString());
            }
            catch (NumberFormatException ex) { value = 0; }
            e.putInt(key,value);
            e.apply();
        }
        e.commit();
    }

    public void returnToMain(View view) {
        Intent intent = new Intent(AbilityMenuActivity.this,MainActivity.class);
        startActivity(intent);
    }
}