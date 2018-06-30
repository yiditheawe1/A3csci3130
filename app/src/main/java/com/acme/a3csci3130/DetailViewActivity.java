package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailViewActivity extends Activity {

    private EditText nameField, numberField, primaryField,addressField,provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        //identify textFields
        nameField = (EditText) findViewById(R.id.name);
        numberField = (EditText) findViewById(R.id.number);
        primaryField = (EditText) findViewById(R.id.primary);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        //render contact info to each textField
        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            numberField.setText(receivedPersonInfo.number);
            primaryField.setText(receivedPersonInfo.primary);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }
    /**
     *
     * This method displays and allows user to modify contact info
     * */
    public void updateContact(View v){
        //TODO: Update contact funcionality
        //get id of the contact
        String uid = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String number = numberField.getText().toString();
        String primary = primaryField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Contact person = new Contact(uid,name,number,primary,address,province);
        appState.firebaseReference.child(uid).setValue(person);
        //toast pop up
        Toast.makeText(DetailViewActivity.this, "Update succeed!", Toast.LENGTH_SHORT).show();
        finish();
    }
    /**
     *
     * This method delete a contact from firebase
     *
     * */
    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        //delete from firebase
        appState.firebaseReference.child( receivedPersonInfo.uid).removeValue();
        //toast pop up
        Toast.makeText(DetailViewActivity.this, "Deletion succeed!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
