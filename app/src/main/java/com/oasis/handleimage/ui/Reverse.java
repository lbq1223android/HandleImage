package com.oasis.handleimage.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.oasis.handleimage.BaseActivity;
import com.oasis.handleimage.R;
import com.oasis.handleimage.util.PhotoUtils;
//import com.oasis.handleimage.util.PhotoUtils;

public class Reverse extends BaseActivity {

   Button fanzhuanButton , zuoyoujingmin ,shangxiajingmian ;

        ImageView imageView ;
        Bitmap bb ;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.r);

            fanzhuanButton = (Button) findViewById(R.id.button4);
            zuoyoujingmin = (Button) findViewById(R.id.button5);
            shangxiajingmian = (Button) findViewById(R.id.button8);

        imageView = (ImageView) findViewById(R.id.imageView2);


        bb = BitmapFactory.decodeResource(getResources(),R.mipmap.rt) ;
        imageView.setImageBitmap(bb);

        fanzhuanButton.setOnClickListener(new View.OnClickListener() {

            Bitmap bit = bb;
            float dee = 0f;
            @Override
            public void onClick(View v) {
                bit = PhotoUtils.rotateImage(bit, 90);
                dee +=90;
                if(dee > 360)
                {
                    dee = 0;
                }
                //picture.setAnimation(rotateAnimation);

                imageView.setImageBitmap(bit);
            }
        });

        zuoyoujingmin.setOnClickListener(new View.OnClickListener() {
            Bitmap bit = bb;
            @Override
            public void onClick(View v) {
                bit = PhotoUtils.reverseImage(bit,-1,1) ;
                imageView.setImageBitmap(bit);
            }
        });

        shangxiajingmian.setOnClickListener(new View.OnClickListener() {
            Bitmap bit = bb;
            @Override
            public void onClick(View v) {
                bit = PhotoUtils.reverseImage(bit,1,-1) ;
                imageView.setImageBitmap(bit);
            }
        });


    }
}
