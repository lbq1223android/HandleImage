package com.oasis.handleimage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.lyft.android.scissors.CropView;
import com.oasis.handleimage.R;

/**
 * Created by liling on 2016/8/1.
 */
public class CropDemo extends Activity {

    CropView cropView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cropdemo);

        cropView = (CropView) findViewById(R.id.view);
        cropView.setViewportRatio(9/16);
        cropView.setImageResource(R.mipmap.yuyu);



    }
}
