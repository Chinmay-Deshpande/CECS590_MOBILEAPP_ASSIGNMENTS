package com.example.chinmaydeshpande.assignment_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chinmay Deshpande on 2/27/2017.
 */

public class SimpleAdapterForListView extends ArrayAdapter<String> {

    private final List<String> str;
    public SimpleAdapterForListView(Context context,int res, List<String> s2)
    {
            super(context,res,s2);
        this.str=s2;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String person = str.get(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_row, null);
// Set the text
        TextView textView = (TextView) row.findViewById(R.id.rowText);
        textView.setText(person);
// Set the image

        return row;
    }

}








