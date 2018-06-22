package jeremy.code.starwars.View.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import jeremy.code.starwars.API.Model.Result;
import jeremy.code.starwars.R;
import jeremy.code.starwars.Util.Util;

public class CharacterActivity extends AppCompatActivity {

    private TextView mNameTextView;
    private TextView mHeightTextView;
    private TextView mWeightTextView;
    private TextView mCreatedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        mNameTextView = findViewById(R.id.character_name);
        mHeightTextView = findViewById(R.id.character_height);
        mWeightTextView = findViewById(R.id.character_mass);
        mCreatedTextView = findViewById(R.id.character_creation);

        Intent intent = getIntent();

        Result character = intent.getParcelableExtra(Util.RESULT);

        setCharacter(character);
    }

    private void setCharacter(Result character) {
        String name = character.getName();
        String height = character.getHeight();
        String mass = character.getMass();
        String date = character.getCreated();

        if(!name.isEmpty()) {
            mNameTextView.setText(name);
        }

        if(!height.isEmpty()) {
            mHeightTextView.setText(Util.convertCentimetersToMeters(height));
        }

        if(!mass.isEmpty()) {
            mWeightTextView.setText(Util.parseKilograms(mass));
        }

        if(!date.isEmpty()) {
            mCreatedTextView.setText(Util.parseDate(date));
        }
    }
}
