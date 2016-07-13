package com.example.administrator.test;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.jar.Manifest;

public class MainActivity extends Activity {
    ListView lv;
    private RecyclerView recyclerView;
    private RelativeLayout rl;
    Button btn;
    private String[] strs = new String[]{"跑男", "跑男", "跑男", "跑男", "跑男", "跑男", "跑男",
            "跑男", "跑男", "跑男", "跑男", "跑男", "跑男", "电子书", "电子书", "电子书", "电子书", "电子书"
            , "电子书", "电子书", "电子书", "电子书", "电子书", "跑男", "跑男", "电子书", "电子书"
    };
    JSONArray array;
    private  TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<UsageStats> list = getUserStatsList(MainActivity.this);
                for(int i = 0;i<list.size();i++){
                    Log.i("testlog",list.get(i).getPackageName()+"---"+list.get(i).getTotalTimeInForeground()+"----"+list.get(i).getLastTimeUsed());
                }
            }
        });

        Log.i("testlog",compare());
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static List getUserStatsList(Context c) {
        UsageStatsManager m = (UsageStatsManager) c.getSystemService(Context.USAGE_STATS_SERVICE);
        if (m != null) {
            return m.queryUsageStats(UsageStatsManager.INTERVAL_BEST, System.currentTimeMillis() - 100000, System.currentTimeMillis());
        }
        return Collections.EMPTY_LIST;
    }


    private void getActivities(String packageName)
    {
        Intent localIntent = new Intent("android.intent.action.MAIN", null);
        localIntent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> appList =  getPackageManager().queryIntentActivities(localIntent, 0);
        for (int i = 0; i < appList.size(); i++) {
            ResolveInfo resolveInfo = appList.get(i);
            String packageStr = resolveInfo.activityInfo.packageName;
            if (packageStr.equals(packageName)) {
                //这个就是你想要的那个Activity
                Log.i("testlog", "" + resolveInfo.activityInfo.name);
                break;
            }
        }
    }

    public final String compare() {
        Intent intent = new Intent();
        intent.setPackage("com.pp.assistant");
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));
        ComponentName aName = null;
        if(resolveInfos.size() > 0) {
            ResolveInfo launchable = resolveInfos.get(0);
            ActivityInfo activity = launchable.activityInfo;
            aName  = new ComponentName(activity.applicationInfo.packageName,
                    activity.name);
//            for(int i=0;i<resolveInfos.size();i++){
//                Log.i("testlog",resolveInfos.get(i).activityInfo.applicationInfo.packageName+"---"+resolveInfos.get(i).activityInfo.name);
//            }
        }
        int aLaunchCount = 0;
        long aUseTime = 0;

        try {

            //获得ServiceManager类
            Class<?> ServiceManager = Class
                    .forName("android.os.ServiceManager");

            //获得ServiceManager的getService方法
            Method getService = ServiceManager.getMethod("getService", java.lang.String.class);
            //调用getService获取RemoteService
            Object oRemoteService = getService.invoke(null, "usagestats");

            //获得IUsageStats.Stub类
            Class<?> cStub = Class
                    .forName("com.android.internal.app.IUsageStats$Stub");
            //获得asInterface方法
            Method asInterface = cStub.getMethod("asInterface", android.os.IBinder.class);
            //调用asInterface方法获取IUsageStats对象
            Object oIUsageStats = asInterface.invoke(null, oRemoteService);
            //获得getPkgUsageStats(ComponentName)方法
            Class<?> cIUsageStatus = Class.forName("com.android.internal.app.IUsageStats");
            Method mGetAllPkgUsageStats = cIUsageStatus.getMethod("getAllPkgUsageStats", (Class[]) null);
            //调用getPkgUsageStats 获取PkgUsageStats对象
            Object[] oPkgUsageStatsArray = (Object[]) mGetAllPkgUsageStats.invoke(oIUsageStats, (Object[]) null);
            //获得PkgUsageStats类
            Class<?> cPkgUsageStats = Class.forName("com.android.internal.os.PkgUsageStats");
            for (Object pkgUsageStats : oPkgUsageStatsArray) {
                String packageName = (String) cPkgUsageStats.getDeclaredField("packageName").get(pkgUsageStats);
                int launchCount = cPkgUsageStats.getDeclaredField("launchCount").getInt(pkgUsageStats);
                long usageTime = cPkgUsageStats.getDeclaredField("usageTime").getLong(pkgUsageStats);
                Map<String, Long > componentResumeMap = (Map<String, Long>) cPkgUsageStats.getDeclaredField("componentResumeTimes").get(pkgUsageStats);
                Log.i("testlog",packageName+"---"+launchCount+"---"+usageTime);

            }
        } catch (Exception e) {
            Log.e("###", e.toString(), e);
        }

        return aLaunchCount+"";
    }
}

