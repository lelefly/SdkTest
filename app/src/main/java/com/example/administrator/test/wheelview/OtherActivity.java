package com.example.administrator.test.wheelview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.test.R;

/**
 * Created by Administrator on 2016/8/1.
 */
public class OtherActivity extends Activity{
    private TextView tv;
    private Button btn;
    private EditText et_comment;
    private View view;
    private int old;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        et_comment = (EditText) findViewById(R.id.et_comment);
        view = findViewById(R.id.content);
        view.post(new Runnable() {
            @Override
            public void run() {
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
//                Log.i("testlog", r.top + "--" + r.bottom);
                old = r.bottom;
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (false || check()) {
                    int i = 1 & 2;
                    Log.i("testlog", i + "");
                }
//                Log.i("testlog", et_comment.getTop() + "" + "---" + et_comment.getBottom());
                InputMethodManager imm = (InputMethodManager) OtherActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                Log.i("testlog", et_comment.getTop() + "" + "---" + et_comment.getBottom());
//                btn.setTranslationY(446);
                if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
                    if (getCurrentFocus() != null)
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        et_comment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("testlog", hasFocus + "");
            }
        });
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                if (r.bottom < old) {
                    Log.i("testlog", "显示");
                } else {
                    Log.i("testlog", "关闭");
                }
//                if(tv.getVisibility()  == View.GONE){
//                    tv.setVisibility(View.VISIBLE);
//                }else{
//                    tv.setVisibility(View.GONE);
//                }
                old = r.bottom;
                Log.i("testlog", r.bottom + "");
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            }
        });
    }

    private boolean check() {
        Log.i("testlog", "check");
        return false;
    }

}
