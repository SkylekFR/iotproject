package com.example.iotproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<ClassModel> list = new ArrayList<ClassModel>();
        ListView listView = findViewById(R.id.listView);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("cave/sensor/temperature");
        MyAdapter adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    //list.add(Float.valueOf(snapshot1.getValue().toString()));
                    System.out.println(snapshot1.getValue().toString());
                    Float temp = Float.valueOf(snapshot1.getValue().toString());
                    Float hum = Float.valueOf(snapshot1.getValue().toString());
                    list.add(new ClassModel(temp,hum));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("cave/sensor/humidity");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    //list.add(Float.valueOf(snapshot1.getValue().toString()));
                    //System.out.println(snapshot1.getValue().toString());
                    Float hum = Float.valueOf(snapshot1.getValue().toString());
                    if (i < list.size())
                    {
                        list.get(i).setHumidite(hum);
                        ++i;
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        System.out.println(reference);
        System.out.println("Bonjour");
    }
}