package com.oasis.handleimage.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.oasis.handleimage.BaseActivity;
import com.oasis.handleimage.R;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;


/**
 * Created by liling on 2016/8/30.
 */
public class FilterTwo extends BaseActivity {

    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    private Bitmap _bitmap;
    private ImageView _imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtertwo);

        _imageView = (ImageView) findViewById(R.id.imageView2);

        _bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.rt) ;
        _imageView.setImageBitmap(_bitmap);

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter filter = new Filter() ;
                filter.addSubFilter(new SaturationSubfilter(1.3f));
                Bitmap outputImage = filter.processFilter(_bitmap);
                _imageView.setImageBitmap(outputImage);
            }
        });
        
    }
}
