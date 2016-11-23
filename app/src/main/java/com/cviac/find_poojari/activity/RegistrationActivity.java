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
        mail =(EditText) findViewById(R.id.mailbox);

        this.arraySpinnervalues = new String[] { "Hyderabad", "Chennai",
                "Bangalore", "Kolkatta", "Mumbai", "New Delhi" };

        Spinner s = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnervalues);
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
                } else if (phone.length() < 9) {
                    phone.setError("Enter Valid Mobile Number");
                    Toast.makeText(getApplicationContext(),
                            "Enter valid Mobile No.", Toast.LENGTH_LONG).show();
                } else if (mail.length() < 1) {
                    mail.setError("Enter Email-id to proceed");
                    Toast.makeText(getApplicationContext(),
                            "Enter valid E-mail ID", Toast.LENGTH_LONG).show();
                } else if (s_pos == 0) {

                    Prefs.edit();

                    Prefs.putString("Name", name1);
                    Prefs.putString("Mobile Number", phone1);
                    Prefs.putString("Email", mail1);
                    Prefs.putString("isregistered", "true");

                    Toast.makeText(getApplicationContext(),
                            "Registeration Successful", Toast.LENGTH_LONG).show();

                    Intent in1 = new Intent(RegistrationActivity.this,
                            MainActivity.class);
                    startActivity(in1);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Choose Different City", Toast.LENGTH_LONG).show(); }
            }
        });
    }
}
