package com.example.adrian_2.ex8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalcActivityB extends AppCompatActivity {

    public static final String ACTION_CALC_ACTIVITY_B = "com.Ex8.CalcActivityB";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_activity_a);
        Intent intent = getIntent();

        TextView textView = (TextView)findViewById(R.id.tvCalActA);
        textView.setText("Oops!, your answer is wrong, you may try again.");

        Button back = (Button) findViewById(R.id.bReturn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalcActivityB.this, MainActivity.class);
                intent.setAction(MainActivity.ACTION_MAIN_ACTIVITY);
                startActivity(intent);
            }
        });
    }

}
