package com.example.iotproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter

{
    private Context context;
    private ArrayList<ClassModel> list;
    private LayoutInflater inflater;

    public void setContext (Context context)

    {
        this.context = context;
    }

    public void setInflater (LayoutInflater inflater)

    {
        this.inflater = inflater;
    }

    public void setList(ArrayList<ClassModel> list) {
        this.list = list;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public ArrayList<ClassModel> getList() {
        return list;
    }

    public Context getContext() {
        return context;
    }

    public MyAdapter (Context context, ArrayList<ClassModel> list) {
        this.setContext(context);
        this.setList(list);
        this.setInflater(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return this.getList().size();
    }

    @Override
    public ClassModel getItem(int position) {
        return this.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ConstraintLayout layout;

        if (convertView == null) {
            layout = (ConstraintLayout) this.getInflater().inflate(R.layout.liste_temperature_humidite,parent,false);
        }

        else {
            layout = (ConstraintLayout) convertView;
        }

        TextView temperature = layout.findViewById(R.id.temperature);
        TextView humidite = layout.findViewById(R.id.humidite);
        temperature.setText(getItem(position).getTemperature().toString());
        humidite.setText(getItem(position).getHumidite().toString());
        return layout;
    }
}
