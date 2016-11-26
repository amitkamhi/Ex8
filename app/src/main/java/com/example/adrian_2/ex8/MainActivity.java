package com.example.adrian_2.ex8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_MAIN_ACTIVITY= "com.Ex8.MainActivity";
    private final int CALC_ACTIVITY_REQUEST = 1;
    RadioButton check, calculate;
    EditText far, cel;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        far = (EditText)findViewById(R.id.etFar);
        cel = (EditText)findViewById(R.id.etCel);
        go = (Button)findViewById(R.id.bGo);

        check = (RadioButton) findViewById(R.id.rbCheck);
        check.setOnClickListener(new checkListener());

        calculate = (RadioButton) findViewById(R.id.rbCalculate);
        calculate.setOnClickListener(new calculateListener());
    }

    private class checkListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            cel.setEnabled(true);
            far.setEnabled(true);

            if(!(far.getText().toString().isEmpty()) && !(cel.getText().toString().isEmpty())){
                go.setEnabled(true);
                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Double.parseDouble(cel.getText().toString()) * (9/5) + 32 == Double.parseDouble(far.getText().toString())) {
                            Intent intent = new Intent(this, CalcActivityA.class);
                            intent.setAction(CalcActivityA.ACTION_CALC_ACTIVITY_A);
                            intent.putExtra("Farenhit", far.getText().toString());
                            intent.putExtra("Celcius", cel.getText().toString());
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(this, CalcActivityB.class);
                            intent.setAction(CalcActivityB.ACTION_CALC_ACTIVITY_B);
                            intent.putExtra("Farenhit", far.getText().toString());
                            intent.putExtra("Celcius", cel.getText().toString());
                            startActivity(intent);
                        }
                    }
                });
            }


        }
    }

    private class calculateListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            cel.setEnabled(true);
            far.setEnabled(true);

            if(!(far.getText().toString().isEmpty()) || !(cel.getText().toString().isEmpty())){
                go.setEnabled(true);
                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(this, CalcActivityC.class);
                        intent.setAction(CalcActivityC.ACTION_CALC_ACTIVITY_C);
                        if(!(far.getText().toString().isEmpty())){
                            intent.putExtra("put", "far");
                            intent.putExtra("Farenhit", far.getText().toString());
                            double celcu = (Double.parseDouble(far.getText().toString())-32)*(5/9);
                            intent.putExtra("Celcius", celcu);

                        }
                        else{
                            intent.putExtra("put", "cel");
                            intent.putExtra("Celcius", cel.getText().toString());
                            double fare = Double.parseDouble(cel.getText().toString())*(9/5)+32;
                            intent.putExtra("Farenhit", fare);
                        }
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
