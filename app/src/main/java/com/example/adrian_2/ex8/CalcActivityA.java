package com.example.adrian_2.ex8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalcActivityA extends Activity {

    public static final String ACTION_CALC_ACTIVITY_A = "com.Ex8.CalcActivityA";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_activity_a);
        Intent intent = getIntent();

        String far = intent.getExtras().getString("Farenhit");
        String cel = intent.getExtras().getString("Celcius");
        TextView textView = (TextView)findViewById(R.id.tvCalActA);

        if(intent.getExtras().getString("type").equals("check")){
            if (intent.getExtras().getString("check").equals("right")){
                textView.setText("Bravo!, the temperature " + cel + "C, is indeed " + far + "F");
            }
            else{
                textView.setText("Oops!, your answer is wrong, you may try again.");
            }
        }
        else{
            String put = intent.getExtras().getString("put");
            if (put.equals("put"))
                textView.setText("The temperature " + far + "F, is converted to " + cel + "C");
            else
                textView.setText("The temperature " + cel + "C, is converted to " + far + "F");
        }


        Button back = (Button) findViewById(R.id.bReturn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalcActivityA.this, MainActivity.class);
                intent.setAction(MainActivity.ACTION_MAIN_ACTIVITY);
                startActivity(intent);
            }
        });
    }
}
