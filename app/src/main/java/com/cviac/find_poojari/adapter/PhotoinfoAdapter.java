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

    private List<PhotoInfo> photos;

    public PhotoinfoAdapter(Context c, List<PhotoInfo> photos)
    {
        mContext = c;
        this.photos=photos;
    }

    public int getCount()
    {
        return photos.size();
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
        PhotoInfo pinfo=photos.get(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            pho = new View(mContext);
            pho = inflater.inflate(R.layout.photolist_item, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) pho.findViewById(R.id.phimage);
            pho.setTag(holder);

        } else {
            holder=(ViewHolder)pho.getTag();
        }

        holder.iv.setImageResource(pinfo.getPhoto_pics());
        return pho;
    }
}