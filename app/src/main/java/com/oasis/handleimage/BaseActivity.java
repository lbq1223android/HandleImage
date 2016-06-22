package com.oasis.handleimage;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.oasis.handleimage.util.LogUtil;


import java.lang.reflect.Type;

/**
 * Created by liling on 2015/11/23.
 */
public class BaseActivity extends Activity {

    protected final String TAG = this.getClass().getSimpleName();
    public Dialog dialog ;

    private BroadcastReceiver mExitRecevier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            exit();
        }
    };

    protected DisplayImageOptions imageOptions ;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, this.getClass().getSimpleName() + " onCreate() invoked!!");
        registerReceiver(mExitRecevier, new IntentFilter("android.action.exit"));
        context = this ;

        imageOptions = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .build();


    }

    protected Context context ;

    public void exit() {
        this.finish();
    }

    protected void showMessage(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show() ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, this.getClass().getSimpleName() + " onStart() invoked!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, this.getClass().getSimpleName() + " onResume() invoked!!");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        LogUtil.d(TAG, this.getClass().getSimpleName() + " onPause() invoked!!");
        super.onPause();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        LogUtil.d(TAG, this.getClass().getSimpleName() + " onStop() invoked!!");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        LogUtil.d(TAG, this.getClass().getSimpleName() + " onDestroy() invoked!!");
        unregisterReceiver(mExitRecevier);
        super.onDestroy();
    }

    public void exitApp(){
        Intent intent = new Intent() ;
        intent.setAction("android.action.exit") ;
        sendBroadcast(intent);
    }

}
