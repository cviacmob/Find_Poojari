package com.cviac.find_poojari.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cviac.find_poojari.Prefs;
import com.cviac.find_poojari.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private String[] arraySpinnervalues;
    private int s_pos;
    Button reg;
    EditText name, phone, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        reg = (Button) findViewById(R.id.Registerbutton);
        name = (EditText) findViewById(R.id.namebox);
        phone = (EditText) findViewById(R.id.phonebox);
        mail = (EditText) findViewById(R.id.mailbox);

        this.arraySpinnervalues = new String[]{"Hyderabad", "Chennai",
                "Bangalore", "Kolkatta", "Mumbai", "New Delhi"};

        Spinner s = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnervalues);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                s_pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                String mail1 = mail.getText().toString();

                if (name.length() < 1) {
                    name.setError("Enter name to proceed");
                    Toast.makeText(getApplicationContext(), "Enter valid Name",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (phone.length() < 9) {
                    phone.setError("Enter Valid Mobile Number");
                    Toast.makeText(getApplicationContext(),
                            "Enter valid Mobile No.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (mail.length() < 1) {
                    mail.setError("Enter Email-id to proceed");
                    Toast.makeText(getApplicationContext(),
                            "Enter valid E-mail ID", Toast.LENGTH_LONG).show();
                    return;
                }
                if (s_pos == 0) {

                    String city = arraySpinnervalues[s_pos];
                    Prefs.edit();
                    Prefs.putString("Name", name1);
                    Prefs.putString("Mobile Number", phone1);
                    Prefs.putString("Email", mail1);
                    Prefs.putString("City", city);
                    Prefs.putString("isregistered", "true");
                    registeruser(name1, phone1, mail1, city);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Choose Different City", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void registeruser(String name, String mobile_number, String email, String city) {

        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("name", name);
        updateValues.put("email", email);
        updateValues.put("mobile", mobile_number);
        updateValues.put("city", city);
        updateValues.put("createdOn", new Date().toString());

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("registration");
        dbref.child(mobile_number).updateChildren(
                updateValues,
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference databaseReference) {
                        if (firebaseError != null) {
                            Toast.makeText(RegistrationActivity.this,
                                    "Registration Failed: " + firebaseError.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Registeration Successful", Toast.LENGTH_LONG).show();

                            Intent in1 = new Intent(RegistrationActivity.this,
                                    MainActivity.class);
                            startActivity(in1);
                        }
                    }
                });
    }
}
