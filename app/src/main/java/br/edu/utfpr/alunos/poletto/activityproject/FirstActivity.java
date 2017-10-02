package br.edu.utfpr.alunos.poletto.activityproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    // Constant value for multiple Activities work
    public static final String NAME = "NAME";

    // Instantiates EditTexts
    public EditText text_first_name;
    public EditText text_last_name;
    public ConstraintLayout constraintLayout;
    public int option = Color.RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // EditText linking
        text_first_name = (EditText) findViewById(R.id.editText_firstName);
        text_last_name = (EditText) findViewById(R.id.editText_lastName);
        constraintLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        readColorPreference();
    }

    public void readColorPreference(){
        SharedPreferences sharedPref =
                getSharedPreferences(getString(R.string.colorPreference),//colorPreference must be on Strings manually
                        Context.MODE_PRIVATE);

        option = sharedPref.getInt(getString(R.string.bgColor), option);//same as colorPreference

        constraintLayout.setBackgroundColor(option);
    }

    public void saveColorPreferences(int newValue){
        SharedPreferences sharedPref =
                getSharedPreferences(getString(R.string.colorPreference),
                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(getString(R.string.bgColor), newValue);

        editor.commit();

        option = newValue;

        constraintLayout.setBackgroundColor(option);
    }

    public void btnShowClicked(View view) {
        // Creates and show a new toast
        Toast.makeText(this, text_first_name.getText() + " " + text_last_name.getText(), Toast.LENGTH_LONG).show();
    }

    public void btnSendClicked(View view) {
        // Creates the intent of initiate the SecondActivity
        Intent intent = new Intent(this, SecondActivity.class);
        // Creates a string that receives the given names
        String message = text_first_name.getText().toString() + " " + text_last_name.getText().toString();
        // Puts Extra Data on the intent
        intent.putExtra(NAME, message);

        // Starts a new Activity
        startActivityForResult(intent, SecondActivity.ASKSITUATION);
    }

    // This method is executed on Activity return
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Checks the second Activity return value and result_ok
        if (requestCode == SecondActivity.ASKSITUATION && resultCode == Activity.RESULT_OK) {
            // Gets the Data of the Activity
            Bundle bundle = data.getExtras();
            // Gets situation value
            String situation = bundle.getString(SecondActivity.SITUATION);
            // Gets grade value
            String grade = bundle.getString(SecondActivity.GRADE);
            // Gets text value
            String text = bundle.getString(SecondActivity.TEXT);
            // Generates and show the toast with the data of text, grade and situation
            Toast.makeText(this, grade + " " + situation + " " + text, Toast.LENGTH_LONG).show();
        } else {
            // If the result don't matche the request code gives an Error toast
            Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.color_option, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        switch(option){
            case Color.RED:
                menu.getItem(0).setChecked(true);
                return true;
            case Color.GREEN:
                menu.getItem(1).setChecked(true);
                return true;
            case Color.BLUE:
                menu.getItem(2).setChecked(true);
                return true;
            default:
                return false;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {

            case R.id.redItem:
                saveColorPreferences(Color.RED);
                return true;

            case R.id.greenItem:
                saveColorPreferences(Color.GREEN);
                return true;

            case R.id.blueItem:
                saveColorPreferences(Color.BLUE);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
