package com.cviac.find_poojari.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cviac.find_poojari.R;
import com.cviac.find_poojari.adapter.PhotoinfoAdapter;
import com.cviac.find_poojari.datamodel.PhotoInfo;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    ListView lv;
    List<PhotoInfo> photolist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        setTitle("Photos");
        loadImages();
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PhotoinfoAdapter adapter = new PhotoinfoAdapter(PhotosActivity.this, photolist);
        lv=(ListView) findViewById(R.id.listphoto);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        PhotoInfo pinfo = photolist.get(i);
        Intent ssa = new Intent(PhotosActivity.this,FullviewActivity.class);
        ssa.putExtra("pinfo",pinfo);
        startActivity(ssa);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void loadImages()
    {
        photolist=new ArrayList<>();

        PhotoInfo inf1= new PhotoInfo(R.mipmap.one);
        photolist.add(inf1);
        PhotoInfo inf2= new PhotoInfo(R.mipmap.two);
        photolist.add(inf2);
        PhotoInfo inf3= new PhotoInfo(R.mipmap.three);
        photolist.add(inf3);
        PhotoInfo inf4= new PhotoInfo(R.mipmap.four);
        photolist.add(inf4);
        PhotoInfo inf5= new PhotoInfo(R.mipmap.five);
        photolist.add(inf5);
        PhotoInfo inf6= new PhotoInfo(R.mipmap.six);
        photolist.add(inf6);
        PhotoInfo inf7=new PhotoInfo(R.mipmap.seven);
        photolist.add(inf7);
        PhotoInfo inf8=new PhotoInfo(R.mipmap.eight);
        photolist.add(inf8);
    }
}