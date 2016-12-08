package com.cviac.find_poojari.activity;

import android.app.ProgressDialog;
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

import com.cviac.find_poojari.PoojariApp;
import com.cviac.find_poojari.Prefs;
import com.cviac.find_poojari.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private String[] arraySpinnervalues;
    private int s_pos;
    Button reg;
    EditText name, phone, mail;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String validmail;
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setTitle("Register");

        reg = (Button) findViewById(R.id.Registerbutton);
        name = (EditText) findViewById(R.id.namebox);
        phone = (EditText) findViewById(R.id.phonebox);
        mail = (EditText) findViewById(R.id.mailbox);

        this.arraySpinnervalues = new String[]{"Hyderabad", "Chennai",
                "Bangalore"};

        final Spinner s = (Spinner) findViewById(R.id.spinner1);
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
                String city = arraySpinnervalues[s_pos];

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
                if (!isValidEmail(mail1)) {
                    mail.setError("Enter Valid Email-id to proceed");
                    Toast.makeText(getApplicationContext(),
                            "Enter valid E-mail ID", Toast.LENGTH_LONG).show();
                    return;
                }
                if (s_pos != 0) {
                    Toast.makeText(getApplicationContext(),
                            "Choose Different City", Toast.LENGTH_LONG).show();
                }
                if (s_pos == 0) {

                    PoojariApp app = (PoojariApp) RegistrationActivity.this.getApplication();

                    if (app.isNetworkStatus()) {

                        Prefs.edit();
                        Prefs.putString("Name", name1);
                        Prefs.putString("Mobile Number", phone1);
                        Prefs.putString("Email", mail1);
                        Prefs.putString("City", city);

                        registeruser(name1, phone1, mail1, city);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Please Check Your Internet Connection and try again", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

//    public void msgBody(String name1, String phone1, String mail1, String city) {
//
//    }

    private void registeruser(String name, String mobile_number, String email, final String city) {

        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("name", name);
        updateValues.put("email", email);
        updateValues.put("mobile", mobile_number);
        updateValues.put("city", city);
        updateValues.put("createdOn", new Date().toString());

        progressDialog = new ProgressDialog(RegistrationActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("registration");
        dbref.child(mobile_number).updateChildren(
                updateValues,
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference databaseReference) {

                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                        if (firebaseError != null) {
                            Toast.makeText(RegistrationActivity.this,
                                    "Registration Failed: " + firebaseError.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Registeration Successful", Toast.LENGTH_LONG).show();
                            Prefs.putString("isregistered", "true");

                            PoojariApp app = (PoojariApp) getApplicationContext();

                            app.sendEmail("gunaseelan240@gmail.com", "registration", getMessagebody());
                            Intent in1 = new Intent(RegistrationActivity.this,
                                    MainActivity.class);
                            startActivity(in1);
                            finish();
                        }
                    }
                });
    }

    private String getMessagebody() {

        StringBuilder msgBody = new StringBuilder();
        msgBody.append("Name:" + Prefs.getString("Name", "") + "\n");
        msgBody.append("Mobile:" + Prefs.getString("Mobile Number", "") + "\n");
        msgBody.append("Email:" + Prefs.getString("Email", "") + "\n");
        msgBody.append("City:" + Prefs.getString("City", "") + "\n");

        return msgBody.toString();
    }

    protected boolean isValidEmail(String email) {

        String EMAILPATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAILPATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}