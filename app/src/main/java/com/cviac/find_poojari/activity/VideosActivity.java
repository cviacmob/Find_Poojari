package com.cviac.find_poojari.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cviac.find_poojari.R;
import com.cviac.find_poojari.adapter.VideoinfoAdapter;
import com.cviac.find_poojari.datamodel.VideoInfo;

import java.util.ArrayList;
import java.util.List;

public class VideosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    List<VideoInfo> videoslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Videos");
        loadvideos();
        VideoinfoAdapter adapter = new VideoinfoAdapter(VideosActivity.this, videoslist);
        lv = (ListView) findViewById(R.id.vidlist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        VideoInfo vinfo = videoslist.get(i);
        Uri uri = Uri.parse(vinfo.getYtubeURL());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void loadvideos() {
        videoslist = new ArrayList<>();
        VideoInfo vid1 = new VideoInfo(R.mipmap.va, "https://youtu.be/7YMa7ZiQIMI");
        videoslist.add(vid1);
        VideoInfo vid2 = new VideoInfo(R.mipmap.vb, "https://youtu.be/hV9vjrlwmws");
        videoslist.add(vid2);
        VideoInfo vid3 = new VideoInfo(R.mipmap.vc, "https://youtu.be/3s-q6wyvS7k");
        videoslist.add(vid3);
        VideoInfo vid4 = new VideoInfo(R.mipmap.vd, "https://youtu.be/vl_GCZ-wQQk");
        videoslist.add(vid4);
        VideoInfo vid5 = new VideoInfo(R.mipmap.ve, "https://youtu.be/K_Qa1sczU9M");
        videoslist.add(vid5);
        VideoInfo vid6 = new VideoInfo(R.mipmap.vf, "https://youtu.be/0oCh9pFinns");
        videoslist.add(vid6);
        VideoInfo vid7 = new VideoInfo(R.mipmap.vg, "https://youtu.be/d5ip0DU1Xxo");
        videoslist.add(vid7);
        VideoInfo vid8 = new VideoInfo(R.mipmap.vh, "https://youtu.be/PJFmASERR2M");
        videoslist.add(vid8);
    }
}