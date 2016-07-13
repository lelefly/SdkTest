package com.le.snake.bomb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends Activity {
    private int count;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "53b01f2474edfa7d5bb5debcf3a1ec59");
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
//        handler.postDelayed(runnable,1000);
    }

    private void show() {
        Dialog dialog = new Dialog(this,R.style.dm_full_screen_dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.t_layout, null);
        dialog.setContentView(view);
        dialog.show();
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 1000);
        }
    };
    class MyDialog extends Dialog{
        public MyDialog(Context context, int theme) {
            super(context, theme);
        }
    }
    private void click() {
        Lost lost = new Lost();
        lost.setDescribe("describe");
        lost.setPhone(count+++"");
        lost.setTitle("title");
        lost.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Log.i("testlog", "success---" + s);
                }else{
                    Log.i("testlog","fail---"+e.getMessage());
                }
            }
        });
    }

}
