package com.example.adrian_2.ex8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Adrian_2 on 26/11/2016.
 */

public class CalcActivityC extends AppCompatActivity {

    public static final String ACTION_CALC_ACTIVITY_C = "com.Ex8.CalcActivityC";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_activity_a);
        Intent intent = getIntent();

        String put = intent.getExtras().getString("put");
        String far = intent.getExtras().getString("Farenhit");
        String cel = intent.getExtras().getString("Celcius");

        TextView textView = (TextView)findViewById(R.id.tvCalActA);
        if (put == "far")
            textView.setText("The temperature " + far + "F, is converted to " + cel + "C");
        else
            textView.setText("The temperature " + cel + "C, is converted to " + far + "F");


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
