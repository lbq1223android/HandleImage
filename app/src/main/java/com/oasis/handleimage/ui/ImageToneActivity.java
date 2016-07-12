package com.oasis.handleimage.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.oasis.handleimage.R;
import com.oasis.handleimage.util.ToneLayer;

import java.util.ArrayList;

/**
 * Created by liling on 2016/6/22.
 */
public class ImageToneActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private ToneLayer mToneLayer;
    private ImageView mImageView;
    private Bitmap mBitmap;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init();
    }

    private void init()
    {
        mToneLayer = new ToneLayer(this);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.rt);
        mImageView = (ImageView) findViewById(R.id.img_view);
        mImageView.setImageBitmap(mBitmap);
        ((LinearLayout) findViewById(R.id.tone_view)).addView(mToneLayer.getParentView());

        ArrayList<SeekBar> seekBars = mToneLayer.getSeekBars();
        for (int i = 0, size = seekBars.size(); i < size; i++)
        {
            seekBars.get(i).setOnSeekBarChangeListener(this);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        int flag = (Integer) seekBar.getTag();
        switch (flag)
        {
            case ToneLayer.FLAG_SATURATION:
                mToneLayer.setSaturation(progress);
                break;
            case ToneLayer.FLAG_LUM:
                mToneLayer.setLum(progress);
                break;
            case ToneLayer.FLAG_HUE:
                mToneLayer.setHue(progress);
                break;
        }

        mImageView.setImageBitmap(mToneLayer.handleImage(mBitmap, flag));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
