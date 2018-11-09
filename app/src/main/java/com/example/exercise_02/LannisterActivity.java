package com.example.exercise_02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LannisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister);
        RecyclerView lannisterRecyclerView = findViewById(R.id.lannister_recyclerview);
        lannisterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lannisterRecyclerView.setAdapter(new ListAdapter(new Person[]{
                new Person(R.drawable.tywin_lannister,"Tywin lannister", R.drawable.house_lannister),
                new Person(R.drawable.cersei_lannister,"Cersei lannister",R.drawable.house_lannister),
                new Person(R.drawable.jaime_lannister,"Jaimie lannister",R.drawable.house_lannister),
                new Person(R.drawable.tyrion_lannister,"Tyrion lannister",R.drawable.house_lannister),
                new Person(R.drawable.joffrey_baratheon_lannister,"Joffrey Baratheon lannister",R.drawable.house_lannister),
                new Person(R.drawable.myrcella_baratheon_lannister,"Myrcella Baratheon lannister",R.drawable.house_lannister),
                new Person(R.drawable.tommen_baratheon_lannister,"Tommen Baratheon lannister",R.drawable.house_lannister),
                new Person(R.drawable.lancel_lannister,"Lancel lannister",R.drawable.house_lannister)}));
    }

}
