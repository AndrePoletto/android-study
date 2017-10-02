package br.edu.utfpr.alunos.poletto.activityproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    // That's is necessary for Data retrieve
    public static final int ASKSITUATION = 1;

    // Crates constants for latter data retrieve
    public static final String SITUATION = "SITUATION";
    public static final String GRADE = "GRADE";
    public static final String TEXT = "TEXT";

    // Instantiates the TextView that will show the complete name
    public TextView textView_completeName;
    public CheckBox checkBox_situation;
    public Spinner grades_spinner;
    public RadioGroup radioBtns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // links the CheckBox and Spinner with Java
        checkBox_situation = (CheckBox) findViewById(R.id.checkBox_situation);
        grades_spinner = (Spinner) findViewById(R.id.grades_spinner);

        // Links the array created on Strings.xml with the activity_second.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grades_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        grades_spinner.setAdapter(adapter);

        // Get an Intent, in this case, the SecondActivity
        Intent intent = getIntent();

        // Get Extras Data
        Bundle bundle = intent.getExtras();

        // TextView linking
        textView_completeName = (TextView) findViewById(R.id.textView_completeName);

        // Gets the Data of first screen and saves on name variable
        String name = textView_completeName.getText().toString() + ": " + bundle.getString(FirstActivity.NAME);


        // Set the complete name value on TextView
        textView_completeName.setText(name);
    }

    // Created function to handle btn action
    public void goBack(View view) {

        // Instantiates a new intent object
        Intent intent = new Intent();

        // Gets RadioBtn state
        radioBtns = (RadioGroup) findViewById(R.id.radioBtns);

        // Checks the selected radioBtn
        switch(radioBtns.getCheckedRadioButtonId()){
            // If the first in line is selected (yeah, I'm an asshole and I putted this in the wrong order)
            case R.id.radioButton3:
                intent.putExtra(TEXT, "Texto 1");
                break;
            // If the second in line is selected
            case R.id.radioButton2:
                intent.putExtra(TEXT, "Texto 2");
                break;
            // If the third in line is selected
            case R.id.radioButton:
                intent.putExtra(TEXT, "Texto 3");
                break;
        }

        // Checks the checkBox state
        if(checkBox_situation.isChecked()) {
            // Gives "Aproved" value to Situation and the value selected on spinner
            intent.putExtra(SITUATION, "Aproved");
            intent.putExtra(GRADE, grades_spinner.getSelectedItem().toString());
        }
        else{
            // Gives "Not Aproved" value to Situation and the value selected on spinner
            intent.putExtra(SITUATION, "Not Aproved");
            intent.putExtra(GRADE, grades_spinner.getSelectedItem().toString());
        }

        // Set Result of the Activity and gives the intent data that will be passed
        setResult(Activity.RESULT_OK, intent);

        finish();
    }
}
