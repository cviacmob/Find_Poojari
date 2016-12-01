package com.cviac.find_poojari.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cviac.find_poojari.R;
import com.cviac.find_poojari.adapter.ServiceinfoAdapter;
import com.cviac.find_poojari.adapter.SubservicesAdapter;
import com.cviac.find_poojari.datamodel.ServiceInfo;

import java.util.List;

public class SubserviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subservice);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
       final ServiceInfo child = (ServiceInfo) i.getSerializableExtra("child");
        setTitle(child.getServiceNAME());

        SubservicesAdapter adapter;
        adapter = new SubservicesAdapter(SubserviceActivity.this, child.getSublist());
        final ListView lv = (ListView) findViewById(R.id.subservicelist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ServiceInfo info= child.getSublist().get(i);
                Intent det = new Intent(SubserviceActivity.this, ContactActivity.class);
//                det.putExtra("Serviceinfo",info);
                startActivity(det);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
