package br.edu.utfpr.alunos.poletto.activityproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    // Instantiates the TextView that will show the complete name
    public TextView textView_completeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get an Intent, in this case, the SecondActivity
        Intent intent = getIntent();

        // Get Extras Data
        Bundle bundle = intent.getExtras();

        // TextView linking
        textView_completeName = (TextView) findViewById(R.id.textView_completeName);

        // Gets the Data of first screen and saves on name variable
        String name = textView_completeName.getText().toString() + bundle.getString(FirstActivity.NAME);


        // Set the complete name value on TextView
        textView_completeName.setText(name);
    }
}
