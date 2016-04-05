package com.mobile.activity5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by MiguelAngel on 05/04/2016.
 */

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] names, String[] hobbies) {
        super(context, R.layout.friends_layout, names, hobbies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View customView = li.inflate(R.layout.friends_layout, parent, false);

        String singleName = getItem(position);
        TextView name = (TextView) customView.findViewById(R.id.largeText);
        TextView hobby = (TextView) customView.findViewById(R.id.smallText);



    }


}
