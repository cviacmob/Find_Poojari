package com.cviac.find_poojari.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

public class ContactActivity extends AppCompatActivity {

    private Button button, sub;
    EditText ename, ephone, email, emessage;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String validmail;
    ProgressDialog progressDialog = null;

    private static final int MY_PERMISSION_CALL_PHONE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Contact Us");

        sub = (Button) findViewById(R.id.submit);
        ename = (EditText) findViewById(R.id.namebox1);
        ephone = (EditText) findViewById(R.id.phonebox1);
        email = (EditText) findViewById(R.id.mailbox1);
        emessage = (EditText) findViewById(R.id.messagebox);

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + getString(R.string.callnumber)));
                if (ActivityCompat.checkSelfPermission(ContactActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContactActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_CALL_PHONE);
                    return;
                }
                startActivity(callIntent);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ename1 = ename.getText().toString();
                String email1 = email.getText().toString();
                String ephone1 = ephone.getText().toString();
                String emessage1 = emessage.getText().toString();

                if (ename.length() < 1) {
                    ename.setError("Enter name to proceed");
                    return;
                }
                if (ephone.length() < 9) {
                    ephone.setError("Enter Valid Mobile Number");
                   return;
                }
                if (!isValidEmail(email1)) {
                    email.setError("Enter Valid Email-id to proceed");
                    return;
                }
                PoojariApp app = (PoojariApp) ContactActivity.this.getApplication();

                if (app.isNetworkStatus()) {
                    enquiry(ename1, ephone1, email1, emessage1);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please Check Your Internet Connection and try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void enquiry(String name, String mobile_number, String email, String message) {

        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("name", name);
        updateValues.put("email", email);
        updateValues.put("mobile", mobile_number);
        updateValues.put("message", message);
        updateValues.put("createdOn", new Date().toString());
        long ctime = System.currentTimeMillis();

        progressDialog = new ProgressDialog(ContactActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Submitting...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Enquiry");
        dbref.child(ctime + "").updateChildren(
                updateValues,
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference databaseReference) {

                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                        if (firebaseError != null) {
                            Toast.makeText(getApplicationContext(),
                                    "Submit Failed: " + firebaseError.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Submit Successful", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + getString(R.string.callnumber)));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(callIntent);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    protected boolean isValidEmail(String email) {

        String EMAILPATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAILPATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
