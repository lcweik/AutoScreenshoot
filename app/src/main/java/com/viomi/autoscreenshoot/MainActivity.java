package com.viomi.autoscreenshoot;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 2021.3.16    主界面模拟粤康码显示
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvTime1,tvTime2,tvTime3;
    private String TAG="wp-MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        以下两行全屏显示
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        long sysTime=System.currentTimeMillis();
        CharSequence sysTimeStr= DateFormat.format("yyyy-MM-dd HH:mm",sysTime);
        Log.i(TAG,"sysTimeStr:"+sysTimeStr);
        tvTime1.setText(DateFormat.format("HH:mm",sysTime));
        tvTime2.setText(sysTimeStr);
        tvTime3.setVisibility(View.GONE);   //隐藏
        getScreen1();
        getScreen2();
        getScreen3();
        getScreen4();
    }

    private void initView() {
        tvTime1=(TextView)findViewById(R.id.tvTime1);
        tvTime2=(TextView)findViewById(R.id.tvTime2);
        tvTime3=(TextView)findViewById(R.id.tvTime3);

    }
    public void getScreen1(){   //实际分辨率1
        WindowManager windowManager = getWindow().getWindowManager();
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
//屏幕实际宽度（像素个数）
        int width = point.x;
//屏幕实际高度（像素个数）
        int height = point.y;
        Log.i(TAG,"screen1:"+width+"*"+height);
    }
    public void getScreen2(){   //实际分辨率2
        WindowManager windowManager = getWindow().getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(metrics);
//屏幕实际宽度（像素个数）
        int width = metrics.widthPixels;
//屏幕实际高度（像素个数）
        int height = metrics.heightPixels;
        Log.i(TAG,"screen2:"+width+"*"+height);
    }
    public void getScreen3(){   //可用分辨率1
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
//屏幕可用宽度(像素个数)
        int width = point.x;
//屏幕可用高度(像素个数)
        int height = point.y;
        Log.i(TAG,"screen3:"+width+"*"+height);
    }
    public void getScreen4(){   //可用分辨率2
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
//屏幕可用宽度(像素个数)
        int width = display.getWidth();
//屏幕可用高度(像素个数)
        int height = display.getHeight();
        Log.i(TAG,"screen4:"+width+"*"+height);
    }
}