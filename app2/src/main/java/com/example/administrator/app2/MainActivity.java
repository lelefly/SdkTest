package com.example.administrator.app2;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void obs() {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable d = getTheme().getDrawable(R.drawable.t);
                subscriber.onNext(d);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("testlog", "error");
            }

            @Override
            public void onNext(Drawable drawable) {
                iv.setImageDrawable(drawable);
            }
        });
    }
}
