package com.example.listviewslidedelete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {
    public MyAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.my_list_view_item,null);
        }else{
            view=convertView;
        }
        TextView textView=(TextView)view.findViewById(R.id.text_view);
        textView.setText(getItem(position));
        return view;
    }
}
