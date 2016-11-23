package com.cviac.find_poojari.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.cviac.find_poojari.R;
import com.cviac.find_poojari.adapter.ServiceinfoAdapter;
import com.cviac.find_poojari.datamodel.ServiceInfo;

public class SubserviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subservice);

        Intent i=getIntent();
        ServiceInfo child= (ServiceInfo) i.getSerializableExtra("child");


        ServiceinfoAdapter adapter;
        adapter= new ServiceinfoAdapter(SubserviceActivity.this,R.layout.program_list,child.getSublist());
        final ListView lv = (ListView) findViewById(R.id.subservicelist);
        lv.setAdapter(adapter);

    }
}
