package com.viomi.autoscreenshoot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.viomi.autoscreenshoot.databinding.ActivityMainBinding;

/**
 * 2021.3.16    主界面模拟粤康码显示
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvTime1, tvTime2, tvTime3;
    private String TAG = "wp-MainActivity";
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        以下两行全屏显示
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(activityMainBinding.getRoot());
        initData();
    }

    private Handler mHandler=new Handler(){ //处理时间显示实时刷新
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    long sysTime=System.currentTimeMillis();
                    CharSequence sysTimeStr= DateFormat.format("yyyy-MM-dd HH:mm:ss", sysTime);
                    Log.i(TAG,"xwg sysTimeStr:"+sysTimeStr);
                    activityMainBinding.tvTime1.setText(DateFormat.format("HH:mm", sysTime));
                    activityMainBinding.tvTime2.setText(sysTimeStr);
                    break;
                default:
                    break;
            }//end switch
        }   //end handleMessage
    };//end Handler
    /**
     * 线程每秒计数
     */
    class TimeThread extends Thread{
        public void run(){
            do{
                try{
                    Thread.sleep(1000);
                    Message msg=new Message();
                    msg.what=1;
                    mHandler.sendMessage(msg);
                }catch (InterruptedException e){
                    e.printStackTrace();
                    Log.i(TAG,"TimeThread error:"+e);
                }
            }while (true);
        }//end run()
    }//end TimeThread

    private void initData() {
        activityMainBinding.tvTime3.setVisibility(View.GONE);   //隐藏
        new TimeThread().start();
    }

}