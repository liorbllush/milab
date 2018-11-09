package com.example.exercise_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout starkButton = findViewById(R.id.stark_button);
        starkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStark = new Intent(MainActivity.this, StarkActivity.class);
                startActivity(intentStark);
            }
        });
        LinearLayout lannisterButton = findViewById(R.id.lannister_button);
        lannisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLannister = new Intent(MainActivity.this, LannisterActivity.class);
                startActivity(intentLannister);
            }
        });

    }
}
