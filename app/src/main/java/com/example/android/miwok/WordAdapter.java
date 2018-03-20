package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 1/11/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;
    MediaPlayer m;
    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Word item=getItem(position);
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView name1=(TextView)listItemView.findViewById(R.id.miwok);
        name1.setText(item.getmiwoktrans());
        TextView name2=(TextView)listItemView.findViewById(R.id.eng);
        name2.setText(item.getengtrans());
        ImageView name3=(ImageView)listItemView.findViewById(R.id.image);
        if(item.hasImage())
        {
            name3.setVisibility(View.VISIBLE);
            name3.setImageResource(item.getimage());

        }
        else
        {
            name3.setVisibility(View.GONE);
        }

        return listItemView;
    }}



