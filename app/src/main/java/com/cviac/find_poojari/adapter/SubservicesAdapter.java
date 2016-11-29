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

public class SubservicesAdapter extends BaseAdapter {

    private Context mContext;

    private List<ServiceInfo> list;

    private int layoutid;

    public SubservicesAdapter(Context c, List<ServiceInfo> list) {
        mContext = c;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder {
        public TextView tv;
        public ImageView iv, ex;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View ins = view;
        ViewHolder holder;
        ServiceInfo sinfo = list.get(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ins = new View(mContext);
            ins = inflater.inflate(R.layout.subservicedetails, null);
            holder = new ViewHolder();
            holder.tv = (TextView) ins.findViewById(R.id.servicetext);
            holder.iv = (ImageView) ins.findViewById(R.id.serviceimage);
            holder.ex = (ImageView) ins.findViewById(R.id.clickimage);
            ins.setTag(holder);
        } else {
            holder = (ViewHolder) ins.getTag();
        }
        holder.tv.setText(sinfo.getServiceNAME());
        holder.iv.setImageResource(sinfo.getImgID());
        return ins;
    }
}
