package com.cviac.find_poojari.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.find_poojari.R;
import com.cviac.find_poojari.datamodel.ServiceInfo;

import java.util.List;

public class ServiceinfoAdapter extends BaseAdapter {

    private Context mContext;

    private List<ServiceInfo> list;

    private int layoutid;

    public ServiceinfoAdapter(Context c, int layoutid, List<ServiceInfo> list) {
        mContext = c;
        this.list=list;
        this.layoutid=layoutid;

    }

    public int getCount()
    {
        return list.size();
    }

    public Object getItem(int position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public static class ViewHolder {
        public TextView tv;
        public ImageView iv;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View ins = convertView;
        ViewHolder holder;
        ServiceInfo sinfo=list.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ins = new View(mContext);
            ins = inflater.inflate(layoutid, null);
            holder = new ViewHolder();
            holder.tv = (TextView) ins.findViewById(R.id.servicetext);
            holder.iv = (ImageView) ins.findViewById(R.id.serviceimage);
            ins.setTag(holder);

        } else {
            holder=(ViewHolder)ins.getTag();
        }
        holder.tv.setText(sinfo.getServiceNAME());
        holder.iv.setImageResource(sinfo.getImgID());
        return ins;
    }
}