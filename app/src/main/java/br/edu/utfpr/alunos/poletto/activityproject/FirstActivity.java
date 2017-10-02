package br.edu.utfpr.alunos.poletto.activityproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    public static final String NAME = "NAME";

    // Instantiates EditTexts
    public EditText text_first_name;
    public EditText text_last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // EditText linking
        text_first_name = (EditText)findViewById(R.id.editText_firstName);
        text_last_name = (EditText)findViewById(R.id.editText_lastName);
    }

    public void btnShowClicked(View view) {
        // Creates and show a new toast
        Toast.makeText(this, text_first_name.getText() + " " + text_last_name.getText(), Toast.LENGTH_LONG).show();
    }

    public void btnSendClicked(View view){
        // Creates the intent of initiate the SecondActivity
        Intent intent = new Intent (this, SecondActivity.class);
        // Creates a string that receives the given names
        String message = text_first_name.getText().toString() + " " + text_last_name.getText().toString();
        // Puts Extra Data on the intent
        intent.putExtra(NAME, message);

        // Starts a new Activity
        startActivity(intent);
    }
}
