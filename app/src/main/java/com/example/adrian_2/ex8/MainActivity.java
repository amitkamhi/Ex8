package com.example.adrian_2.ex8;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements TextWatcher{

    private final static int REQUEST_CODE = 1;
    public static final String ACTION_MAIN_ACTIVITY= "com.Ex8.MainActivity";
    private final int CALC_ACTIVITY_REQUEST = 1;
    RadioButton check, calculate;
    EditText far, cel;
    Button go;
    int menuId;
    boolean checkBoolean, calculateBoolean;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            int celColor =savedInstanceState.getInt("edit text cel");
            int farColor = savedInstanceState.getInt("button calculate");
            cel.setTextColor(celColor);
            far.setTextColor(farColor);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_CODE == requestCode){
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            radioGroup.clearCheck();
            far = (EditText) findViewById(R.id.etFar);
            far.setText("");
            cel = (EditText) findViewById(R.id.etCel);
            cel.setText("");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("edit text cel",cel.getCurrentTextColor());
        outState.putInt("button calculate",far.getCurrentTextColor());
        //outState.putInt("check color", fa);
        //outState.putInt("calculate color",color);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);

        Integer x = null;

        //if(color == x){
         int color = ((EditText)v).getCurrentTextColor();
        //}
        menuId = color==Color.BLUE? R.id.colorBlue :color==Color.GREEN? R.id.colorGreen: R.id.colorRed;
        menu.findItem(menuId).setChecked(true);

        int[] colors = new int[]{Color.RED, Color.rgb(0,153,0), Color.BLUE};
        for (int i = 0; i<colors.length;i++)
        {
            MenuItem item = menu.getItem(i);
            SpannableString s  = new SpannableString(item.getTitle());
            s.setSpan(new ForegroundColorSpan(colors[i]), 0,s.length(),0);
            item.setTitle(s);
        }

        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_help:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://en.wikipedia.org/wiki/Conversion_of_units_of_temperature"));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        MyEditText.myMenuInfo menuInfo = (MyEditText.myMenuInfo) item.getMenuInfo();
        EditText ed = menuInfo.et;
        switch (item.getItemId()){
            case R.id.colorRed:{
                ed.setTextColor(Color.RED);
                break;
            }
            case R.id.colorGreen:{
                ed.setTextColor(Color.GREEN);
                break;
            }
            case R.id.colorBlue:{
                ed.setTextColor(Color.BLUE);
                break;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            far = (EditText) findViewById(R.id.etFar);
            cel = (EditText) findViewById(R.id.etCel);
            if(checkBoolean){
                findViewById(R.id.bGo).setEnabled(!(far.getText().toString().isEmpty()) && !(cel.getText().toString().isEmpty()));
            }
            if(calculateBoolean){
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

        far = (EditText)findViewById(R.id.etFar);
        far.addTextChangedListener(this);
        cel = (EditText)findViewById(R.id.etCel);
        cel.addTextChangedListener(this);
        go = (Button)findViewById(R.id.bGo);

        registerForContextMenu(cel);
        registerForContextMenu(far);

        check = (RadioButton) findViewById(R.id.rbCheck);
        check.setOnClickListener(new checkListener());

        calculate = (RadioButton) findViewById(R.id.rbCalculate);
        calculate.setOnClickListener(new calculateListener());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
                    checkBoolean = true;
                    Intent intent = new Intent(MainActivity.this, CalcActivityA.class);
                    intent.setAction(CalcActivityA.ACTION_CALC_ACTIVITY_A);
                    intent.putExtra("type", "check");
                    intent.putExtra("Farenhit", far.getText().toString());
                    intent.putExtra("Celcius", cel.getText().toString());
                    if(Double.parseDouble(cel.getText().toString()) * 9 / 5 + 32 == Double.parseDouble(far.getText().toString())) {
                        intent.putExtra("check", "right");
                        startActivityForResult(intent, REQUEST_CODE);
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
                        calculateBoolean = true;
                        Intent intent = new Intent(MainActivity.this, CalcActivityA.class);
                        intent.setAction(CalcActivityA.ACTION_CALC_ACTIVITY_A);
                        intent.putExtra("type", "calculate");
                        if(!(far.getText().toString().isEmpty())){
                            intent.putExtra("put", "far");
                            intent.putExtra("Farenhit", far.getText().toString());
                            double celcu = (Double.parseDouble(far.getText().toString())-32)*5/9;
                            intent.putExtra("Celcius", String.format("%.2f", celcu));
                            startActivityForResult(intent, REQUEST_CODE);
                        }
                        else{
                            intent.putExtra("put", "cel");
                            intent.putExtra("Celcius", cel.getText().toString());
                            double fare = Double.parseDouble(cel.getText().toString())*9/5+32;
                            intent.putExtra("Farenhit", String.format("%.2f", fare));
                            startActivityForResult(intent, REQUEST_CODE);
                        }
                    }
                });
        }
    }
}
