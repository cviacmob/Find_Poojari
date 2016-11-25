package com.cviac.find_poojari.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.cviac.find_poojari.R;
import com.cviac.find_poojari.datamodel.VideoInfo;
import java.util.List;

public class VideoinfoAdapter extends BaseAdapter {

    private Context mContext;

    private List<VideoInfo> list;

    public VideoinfoAdapter(Context c, List<VideoInfo> list)
    {
        mContext = c;
        this.list=list;
    }

    public int getCount()
    {
        return list.size();
    }

    public Object getItem(int i)
    {
        return i;
    }

    public long getItemId(int i)
    {
        return i;
    }

    public static class ViewHolder {
        public ImageView iv;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        View vid = view;
        ViewHolder holder;
        VideoInfo vinfo=list.get(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vid = new View(mContext);
            vid = inflater.inflate(R.layout.photolist_item, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) vid.findViewById(R.id.phimage);
            vid.setTag(holder);
        } else {
            holder=(ViewHolder)vid.getTag();
        }

        holder.iv.setImageResource(vinfo.getPhotoID());
        return vid;
    }
}
