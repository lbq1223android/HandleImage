package com.oasis.handleimage.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.oasis.handleimage.BaseActivity;
import com.oasis.handleimage.R;
import com.zomato.photofilters.geometry.Point;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ToneCurveSubFilter;


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

        _bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.photo) ;
        _imageView.setImageBitmap(_bitmap);

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter filter =  getNightWhisperFilter() ;
                Bitmap outputImage = filter.processFilter(_bitmap.copy(Bitmap.Config.ARGB_8888,true)
                );
                _imageView.setImageBitmap(outputImage);
            }
        });



        
    }

    public  Filter getNightWhisperFilter() {
        Point[] rgbKnots;
        Point[] redKnots;
        Point[] greenKnots;
        Point[] blueKnots;

        rgbKnots = new Point[3];
        rgbKnots[0] = new Point(0, 0);
        rgbKnots[1] = new Point(174, 109);
        rgbKnots[2] = new Point(255, 255);

        redKnots = new Point[4];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(70, 114);
        redKnots[2] = new Point(157, 145);
        redKnots[3] = new Point(255, 255);

        greenKnots = new Point[3];
        greenKnots[0] = new Point(0, 0);
        greenKnots[1] = new Point(109, 138);
        greenKnots[2] = new Point(255, 255);

        blueKnots = new Point[3];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(113, 152);
        blueKnots[2] = new Point(255, 255);

        Filter filter = new Filter();
        filter.addSubFilter(new ToneCurveSubFilter(rgbKnots, redKnots, greenKnots, blueKnots));
        return filter;
    }
}
