package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 *
 * This activity renders the UI of creating new Contact
 *
 * */
public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, numberField, primaryField, addressField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variablesf
        appState = ((MyApplicationData) getApplicationContext());
        //receive typed info respectively
        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        numberField = (EditText) findViewById(R.id.number);
        primaryField = (EditText) findViewById(R.id.primary);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);
    }

    /**
     *
     * This method initialize an object in firebase
     *
     * */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        //that is generated at random
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String number = numberField.getText().toString();
        String primary = primaryField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Contact person = new Contact(personID, name, number, primary, address, province);
        //save the contact into firebase
        appState.firebaseReference.child(personID).setValue(person);
        finish();
    }
}
