package com.example.filipino_slang;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Mainadapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> letters;

    public Mainadapter(Context c, ArrayList<String> letters){
        context = c;
        this.letters = letters;
    }

    @Override
    public int getCount() {
        return this.letters.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_item, null);
        }

        TextView letterHolder = convertView.findViewById(R.id.letter_container);
        letterHolder.setTextColor(Color.BLACK);
        letterHolder.setText(letters.get(position));

        return convertView;
    }
}
