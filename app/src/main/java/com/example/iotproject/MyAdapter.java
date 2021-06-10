package com.example.iotproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.iotproject.model.SensorData;

import java.util.ArrayList;
import java.util.Locale;

public class MyAdapter extends BaseAdapter

{
    private Context context;
    private ArrayList<SensorData> list;
    private LayoutInflater inflater;

    public void setContext (Context context)

    {
        this.context = context;
    }

    public void setInflater (LayoutInflater inflater)

    {
        this.inflater = inflater;
    }

    public void setList(ArrayList<SensorData> list) {
        this.list = list;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public ArrayList<SensorData> getList() {
        return list;
    }

    public Context getContext() {
        return context;
    }

    public MyAdapter (Context context, ArrayList<SensorData> list) {
        this.setContext(context);
        this.setList(list);
        this.setInflater(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return this.getList().size();
    }

    @Override
    public SensorData getItem(int position) {
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
        temperature.setText(String.format(Locale.FRENCH, "%.1f °C", list.get(position).getTemperature()));
        humidite.setText(String.format(Locale.FRENCH, "%.2f %%", list.get(position).getHumidity()));
        return layout;
    }
}
