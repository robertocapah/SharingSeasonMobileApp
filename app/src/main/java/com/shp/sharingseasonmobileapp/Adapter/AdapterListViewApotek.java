package com.shp.sharingseasonmobileapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shp.sharingseasonmobileapp.Common.Model.clsProfile;
import com.shp.sharingseasonmobileapp.R;

import java.util.List;

/**
 * Created by Dewi Oktaviani on 11/1/2018.
 */

public class AdapterListViewApotek extends BaseAdapter {
    private Context mContext;
    List<clsProfile> mAppList;

    public AdapterListViewApotek(Context mContext, List<clsProfile> mAppList) {
        this.mContext = mContext;
        this.mAppList = mAppList;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final clsProfile item = mAppList.get(position);
        if (convertView==null){
            convertView = View.inflate(mContext, R.layout.card_view_apotek, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        // displaying text view data
//        holder.tv_outlet_name_info.setText(item.getTxtTittle());
        holder.tv_desc_infoprogram.setText(item.getTxtSubTittle());
        holder.image_letter.setText(item.getTxtImgName());
        displayImage(holder, item);

        return convertView;
    }

    class ViewHolder {

        public TextView image_letter, tv_desc_infoprogram;
//        public ImageButton img_delete, img_edit;
        public ImageView image;
        public RelativeLayout lyt_image;
        LinearLayout lnDesc;

        public ViewHolder(View view) {
            tv_desc_infoprogram = (TextView) view.findViewById(R.id.tv_desc_main);
//            tv_outlet_name_info = (TextView) view.findViewById(R.id.tv_outlet_name_main);
            image_letter = (TextView) view.findViewById(R.id.image_letter_main);
            image = (ImageView) view.findViewById(R.id.image_main);
            lyt_image = (RelativeLayout) view.findViewById(R.id.lyt_image_main);
            lnDesc = (LinearLayout) view.findViewById(R.id.ln_desc_main);
            view.setTag(this);
        }
    }

    private void displayImage(ViewHolder holder, clsProfile inbox) {
        holder.image.setImageResource(R.drawable.shape_circle);
        holder.image.setColorFilter(mContext.getResources().getColor(inbox.getIntColor()));
        holder.image_letter.setVisibility(View.VISIBLE);
    }
}
