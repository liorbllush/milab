package com.example.exercise_02;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class StarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stark);
        RecyclerView starkRecyclerView = findViewById(R.id.stark_recyclerview);
        starkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        starkRecyclerView.setAdapter(new ListAdapter(new Person[]{
                new Person(R.drawable.eddard_stark,"Eddard (Ned) Stark", R.drawable.house_stark),
                new Person(R.drawable.catelyn_stark,"Catelyn (Cat) Tully Stark", R.drawable.house_stark),
                new Person(R.drawable.jon_snow_starkjpg,"Jon Snow Stark", R.drawable.house_stark),
                new Person(R.drawable.bran_stark,"Bran Stark", R.drawable.house_stark),
                new Person(R.drawable.sansa_stark,"Sansa Stark", R.drawable.house_stark),
                new Person(R.drawable.arya_stark,"Arya Stark", R.drawable.house_stark),
                new Person(R.drawable.robb_stark,"Robb Stark", R.drawable.house_stark),
                new Person(R.drawable.rickson_stark,"Rickon Stark", R.drawable.house_stark)}));

    }

}
