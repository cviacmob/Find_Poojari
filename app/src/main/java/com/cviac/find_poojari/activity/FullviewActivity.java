package com.cviac.find_poojari.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.cviac.find_poojari.R;
import com.cviac.find_poojari.datamodel.PhotoInfo;

public class FullviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullview);
        setTitle("Photo");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        PhotoInfo pinfo = (PhotoInfo) i.getSerializableExtra("pinfo");

        ImageView imageView = (ImageView) findViewById(R.id.fullview);
        imageView.setImageResource(pinfo.getPhoto_pics());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
