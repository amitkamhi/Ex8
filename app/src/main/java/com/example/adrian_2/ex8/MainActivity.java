package com.example.adrian_2.ex8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements TextWatcher{

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
        far.addTextChangedListener(this);
        cel = (EditText)findViewById(R.id.etCel);
        cel.addTextChangedListener(this);
        go = (Button)findViewById(R.id.bGo);

        check = (RadioButton) findViewById(R.id.rbCheck);
        check.setOnClickListener(new checkListener());

        calculate = (RadioButton) findViewById(R.id.rbCalculate);
        calculate.setOnClickListener(new calculateListener());


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        check = (RadioButton) findViewById(R.id.rbCheck);
        calculate = (RadioButton) findViewById(R.id.rbCalculate);
        far = (EditText) findViewById(R.id.etFar);
        cel = (EditText) findViewById(R.id.etCel);
        if(check.isChecked()){
            findViewById(R.id.bGo).setEnabled(!(far.getText().toString().isEmpty()) && !(cel.getText().toString().isEmpty()));
        }
        else{
            findViewById(R.id.bGo).setEnabled(false);
        }
        if(calculate.isChecked()){
            if ((!(far.getText().toString().isEmpty()) && !(cel.getText().toString().isEmpty()))){
                findViewById(R.id.bGo).setEnabled(false);
            }
            else{
                if ((!(far.getText().toString().isEmpty()) || !(cel.getText().toString().isEmpty()))){
                    findViewById(R.id.bGo).setEnabled(true);
                }
            }
        }
    }

    private class checkListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            cel.setEnabled(true);
            far.setEnabled(true);

            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CalcActivityA.class);
                    intent.setAction(CalcActivityA.ACTION_CALC_ACTIVITY_A);
                    intent.putExtra("type", "check");
                    intent.putExtra("Farenhit", far.getText().toString());
                    intent.putExtra("Celcius", cel.getText().toString());
                    if(Double.parseDouble(cel.getText().toString()) * 9 / 5 + 32 == Double.parseDouble(far.getText().toString())) {
                        intent.putExtra("check", "right");
                        startActivity(intent);
                    }
                    else {
                        intent.putExtra("check", "wrong");
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private class calculateListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            cel.setEnabled(true);
            far.setEnabled(true);

            go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, CalcActivityA.class);
                        intent.setAction(CalcActivityA.ACTION_CALC_ACTIVITY_A);
                        intent.putExtra("type", "calculate");
                        if(!(far.getText().toString().isEmpty())){
                            intent.putExtra("put", "far");
                            intent.putExtra("Farenhit", far.getText().toString());
                            double celcu = (Double.parseDouble(far.getText().toString())-32)*5/9;
                            intent.putExtra("Celcius", String.format("%.2f", celcu));
                            startActivity(intent);
                        }
                        else{
                            intent.putExtra("put", "cel");
                            intent.putExtra("Celcius", cel.getText().toString());
                            double fare = Double.parseDouble(cel.getText().toString())*9/5+32;
                            intent.putExtra("Farenhit", String.format("%.2f", fare));
                            startActivity(intent);
                        }
                    }
                });
        }
    }
}
