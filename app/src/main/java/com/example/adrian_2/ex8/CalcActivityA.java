package com.example.adrian_2.ex8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalcActivityA extends AppCompatActivity {

    public static final String ACTION_CALC_ACTIVITY_A = "com.Ex8.CalcActivityA";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_activity_a);
        Intent intent = getIntent();

        String far = intent.getExtras().getString("Farenhit");
        String cel = intent.getExtras().getString("Celcius");

        TextView textView = (TextView)findViewById(R.id.tvCalActA);
        textView.setText("Bravo!, the temperature " + cel + "C, is indeed " + far + "F");

        Button back = (Button) findViewById(R.id.bReturn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.setAction(MainActivity.ACTION_MAIN_ACTIVITY);
                startActivity(intent);
            }
        });
    }
}
