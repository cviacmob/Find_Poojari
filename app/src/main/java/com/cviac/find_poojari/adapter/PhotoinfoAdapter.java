package com.cviac.find_poojari.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.cviac.find_poojari.R;
import com.cviac.find_poojari.datamodel.PhotoInfo;

import java.util.List;

public class PhotoinfoAdapter extends BaseAdapter {

    private Context mContext;

    private List<PhotoInfo> list;

    public PhotoinfoAdapter(Context c, List<PhotoInfo> list)
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

        View pho = view;
        ViewHolder holder;
        PhotoInfo pinfo=list.get(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            pho = new View(mContext);
            pho = inflater.inflate(R.layout.photolist_item, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) pho.findViewById(R.id.phimage);
            holder.iv.setImageResource(pinfo.getPhoto_pics());
        } else {
            holder=(ViewHolder)pho.getTag();
        }
        return pho;
    }
}