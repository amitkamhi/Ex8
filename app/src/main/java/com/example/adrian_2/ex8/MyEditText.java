package com.example.adrian_2.ex8;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.widget.EditText;

/**
 * Created by Kamhi on 9/12/2016.
 */

public class MyEditText extends EditText{

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return new myMenuInfo(this);
    }

    public static class myMenuInfo implements ContextMenu.ContextMenuInfo{

        public EditText et;

        public myMenuInfo(EditText et) {
            this.et = et;
        }
    }

}
