package com.example.adrian_2.ex8;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Kamhi on 16/12/2016.
 */

public class MyDialog extends DialogFragment {

    private int requestCode;
    public static final int EXIT_DIALOG = 1;
    public static final int PRECISION_DIALIOG =2;
    private ResultsListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.requestCode = getArguments().getInt("requestCode");
        if(requestCode == EXIT_DIALOG){
            return buildExitDialog().create();
        }
        else {
            return builPrecisionDialog().create();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (ResultsListener) activity
            ;
        }
        catch (ClassCastException e){
            String str = getResources().getString(R.string.catchMyDialog);
            throw new ClassCastException(str);
        }
    }

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (ResultsListener) context;
        }
        catch (ClassCastException e){
            String str = getResources().getString(R.string.catchMyDialog);
            throw new ClassCastException(str);
        }
    } */

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    public static MyDialog newInstance(int requestCode){
        MyDialog fragment = new MyDialog();
        Bundle args = new Bundle();
        args.putInt("requestCode",requestCode);
        fragment.setArguments(args);
        return fragment;
    }

    private AlertDialog.Builder buildExitDialog(){

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.closing)
                .setMessage(R.string.sure)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onFinishedDialog(requestCode, "ok");
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
    }

    private AlertDialog.Builder builPrecisionDialog(){

        final View view = getActivity().getLayoutInflater().inflate(R.layout.precisin, null);

        int currentPrecision = ((MainActivity)getActivity()).getPrecision();
        TextView textView = (TextView)view.findViewById(R.id.tVNumbers);
        textView.setText(String.format("%."+currentPrecision+"f",123.0));

        final SeekBar seekBar = (SeekBar)view.findViewById(R.id.seekBar);
        seekBar.setProgress(currentPrecision);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView textView = (TextView)view.findViewById(R.id.tVNumbers);
                textView.setText(String.format("%."+progress+"f",123.0));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.precisinTitle)
                .setView(view)
                .setPositiveButton(R.string.okey, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onFinishedDialog(requestCode, seekBar.getProgress());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

    }

    public interface ResultsListener{
        public void onFinishedDialog(int requestCode, Object result);
    }
}
