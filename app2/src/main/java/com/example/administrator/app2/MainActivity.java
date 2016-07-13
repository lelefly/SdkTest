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
        iv = (ImageView) findViewById(R.id.iv);
        String[] names = new String[]{"11","22"};
        JSONObject obj;
        try {
            obj = new JSONObject("{\"total\":3,\"shockings\":[{\"c_dislike\":0,\"thumb_id\":\"526349777\",\"anon\":0,\"thumb\":\"http://f.kuaiya.cn/fd51bbd47fc248b3b7306082438bb1a7.jpg?e=1492675655&token=rQ7At7jVvB9Y5MUc9YfG7C8pEkCJH6ZWgHuEVZNH:_bjjZcmfQfJrDWmFAeX-pNv3TMw=\",\"bonus\":0,\"c_dl\":0,\"type\":1,\"c_like\":0,\"url\":\"http://downloadg.dewmobile.net/Kuaiyadaren/d1e986b0068eb04bc7b1fc6cee53018f-160719.mp3\",\"uid\":\"14743680\",\"du\":81,\"wid\":\"dr\",\"size\":1298599,\"cat\":2,\"name\":\"太阳的后裔\",\"time\":1461144218670,\"_id\":\"572e94c3109274914a24ea0d\"},{\"c_dislike\":0,\"thumb_id\":\"526356903\",\"anon\":0,\"thumb\":\"http://f.kuaiya.cn/fd51bbd47fc248b3b7306082438bb1a7.jpg?e=1492675627&token=rQ7At7jVvB9Y5MUc9YfG7C8pEkCJH6ZWgHuEVZNH:rySwo2J0sCXsWR-v5kzBOUelVvA=\",\"bonus\":0,\"c_dl\":0,\"type\":1,\"c_like\":0,\"url\":\"http://downloadg.dewmobile.net/Kuaiyadaren/7e9a09a64f8200fad135d1dbd0ee91b6-160625.mp3\",\"uid\":\"14743680\",\"du\":38,\"wid\":\"dr\",\"size\":463646,\"cat\":2,\"name\":\"太阳的后裔  说干什么呢（高潮部分加口哨）\",\"time\":1461145920543,\"_id\":\"572e94c3109274914a24ea0b\"},{\"c_dislike\":0,\"thumb_id\":\"445605966\",\"anon\":0,\"thumb\":\"http://f.kuaiya.cn/fcd8cd0c38d6ae9a1049317ba8d1d688.jpg?e=1488611940&token=rQ7At7jVvB9Y5MUc9YfG7C8pEkCJH6ZWgHuEVZNH:YRvvSJqwh60tYs5uX94uOf4S6RY=\",\"bonus\":0,\"c_dl\":0,\"type\":1,\"c_like\":0,\"url\":\"http://f.kuaiya.cn/555569027dff5d68c65ecad26739b693.flv?e=1488612608&token=rQ7At7jVvB9Y5MUc9YfG7C8pEkCJH6ZWgHuEVZNH:hp9-kYYon62EL_sqbkiTZT5BujU=\",\"uid\":\"14243590\",\"du\":229,\"wid\":\"dr\",\"size\":34463475,\"cat\":2,\"name\":\"[MV] (太阳的后裔)Davich 这份爱 宋仲基,宋慧乔\",\"time\":1457076610776,\"_id\":\"572e94ba109274914a24e3af\"}],\"code\":0}");
            Log.i("testlog",obj.toString());
            JSONArray array = obj.getJSONArray("shockings");
            for(int i = 0;i<array.length();i++){
                JSONObject o = array.getJSONObject(i);
                o.put("c_dislike",1000);
            }
            Log.i("testlog",obj.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

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
