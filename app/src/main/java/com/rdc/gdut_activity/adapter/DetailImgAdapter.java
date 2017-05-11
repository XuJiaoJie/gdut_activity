package com.rdc.gdut_activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.ui.DetailsPhotoActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by PC on 2017/5/10.
 */

public class DetailImgAdapter extends NineGridImageViewAdapter<String> {

    @Override
    protected void onDisplayImage(Context context, ImageView imageView, String s) {
        Picasso.with(context)
                .load(s)
                .placeholder(R.drawable.photo_empty_photo)
                .into(imageView);
    }

    @Override
    protected ImageView generateImageView(Context context) {
        return super.generateImageView(context);
    }

    @Override
    protected void onItemImageClick(Context context, int index, List<String> list) {
        Intent intent = new Intent(context, DetailsPhotoActivity.class);
        intent.putExtra("photo_index", index);
        intent.putStringArrayListExtra("photo_list",(ArrayList<String>) list);
        context.startActivity(intent);
    }

}