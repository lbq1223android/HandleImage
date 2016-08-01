package com.oasis.handleimage.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.oasis.handleimage.BaseActivity;
import com.oasis.handleimage.R;

import org.wysaid.myUtils.ImageUtil;
import org.wysaid.view.ImageGLSurfaceView;

/**
 * Created by oasis_oliwen on 2016/7/12.
 */
public class Filter extends BaseActivity {

    private Bitmap _bitmap;
    private ImageGLSurfaceView _imageView;
    private String _currentConfig;

    private String change = "@curve R(81, 3)(161, 129)(232, 253)G(91, 0)(164, 136)(255, 225)B(76, 0)(196, 162)(255, 225)";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);


        _bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.rt) ;
        _imageView = (ImageGLSurfaceView) findViewById(R.id.mainImageView);

        _imageView.setSurfaceCreatedCallback(new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
            @Override
            public void surfaceCreated() {
                _imageView.setImageBitmap(_bitmap);
                _imageView.setFilterWithConfig(_currentConfig);
            }
        });


        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imageView.setFilterWithConfig(change);
            }
        });


        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imageView.getResultBitmap(new ImageGLSurfaceView.QueryResultBitmapCallback() {
                    @Override
                    public void get(final Bitmap bmp) {
                        ImageUtil.saveBitmap(bmp);
                    }
                });
            }
        });

    }
}
