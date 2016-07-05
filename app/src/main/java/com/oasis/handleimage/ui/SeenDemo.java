package com.oasis.handleimage.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.oasis.handleimage.GPUImageFilterTools;
import com.oasis.handleimage.R;

import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

/**
 * Created by liling on 2016/7/5.
 */
public class SeenDemo extends Activity {

    private GPUImageView mGPUImageView;

    private GPUImageBrightnessFilter mFilter;
    private GPUImageContrastFilter mFilter1;
    private SeekBar seekBar,seekBar1 ;
    private Button mButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seendemo);

        mButton = (Button) findViewById(R.id.button4);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = System.currentTimeMillis() + ".jpg";
                mGPUImageView.saveToPictures("oasis_GPUImage", fileName, new GPUImageView.OnPictureSavedListener() {
                    @Override
                    public void onPictureSaved(Uri uri) {
                        Toast.makeText(SeenDemo.this,uri.toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        mGPUImageView = (GPUImageView) findViewById(R.id.gpuimage);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.rt);
        mGPUImageView.setImage(bitmap);
        mFilter = new GPUImageBrightnessFilter(0f) ;
        mFilter1 = new GPUImageContrastFilter(1f) ;
//        mGPUImageView.setFilter(mFilter1);
//        mGPUImageView.setFilter(mFilter);
        mGPUImageView.requestRender();


        final GPUImageFilterGroup gpuImageFilterGroup = new GPUImageFilterGroup() ;
        gpuImageFilterGroup.addFilter(mFilter);
        gpuImageFilterGroup.addFilter(mFilter1);

        mGPUImageView.setFilter(gpuImageFilterGroup);

        seekBar1 = (SeekBar) findViewById(R.id.seekBar4);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float pp = progress/100 ;

                GPUImageContrastFilter cc= (GPUImageContrastFilter) gpuImageFilterGroup.getFilters().get(1);
                cc.setContrast(range(progress, 0.0f, 2.0f));
                mGPUImageView.requestRender();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        seekBar = (SeekBar) findViewById(R.id.seekBar3);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float pp = progress/100 ;
                GPUImageBrightnessFilter cc1= (GPUImageBrightnessFilter) gpuImageFilterGroup.getFilters().get(0);
                cc1.setBrightness(range(progress, -1.0f, 1.0f));
                mGPUImageView.requestRender();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    public float range(final int percentage, final float start, final float end) {
        return (end - start) * percentage / 100.0f + start;
    }
}
