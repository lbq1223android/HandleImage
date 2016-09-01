package com.oasis.handleimage.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.oasis.handleimage.BaseActivity;
import com.oasis.handleimage.R;
import com.oasis.handleimage.util.HorizontalListView;

import org.wysaid.view.ImageGLSurfaceView;

import java.util.ArrayList;

/**
 * Created by oasis_oliwen on 2016/9/1.
 */
public class GPUplusList extends BaseActivity {

    HorizontalListView listView ;

    Button button ;

    private Bitmap _bitmap;
    private String change = "@curve R(81, 3)(161, 129)(232, 253)G(91, 0)(164, 136)(255, 225)B(76, 0)(196, 162)(255, 225)";

    ImageGLSurfaceView imageView ;

    ArrayList<String> list = new ArrayList<>() ;

    boolean ishow = true ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpupluslist);
        imageView = (ImageGLSurfaceView) findViewById(R.id.imageView3);

        button = (Button) findViewById(R.id.button9);


        list.add(change) ;
        list.add("@adjust hsv -0.4 -0.64 -1.0 -0.4 -0.88 -0.88 @curve R(0, 0)(119, 160)(255, 255)G(0, 0)(83, 65)(163, 170)(255, 255)B(0, 0)(147, 131)(255, 255)") ;
        list.add("@adjust hsv 0.3 -0.5 -0.3 0 0.35 -0.2 @curve R(0, 0)(111, 163)(255, 255)G(0, 0)(72, 56)(155, 190)(255, 255)B(0, 0)(103, 70)(212, 244)(255, 255)") ;
        list.add("@curve R(0, 0)(71, 74)(164, 165)(255, 255) @pixblend screen 0.94118 0.29 0.29 1 20") ;
        list.add("@curve G(0, 0)(101, 127)(255, 255) @pixblend colordodge 0.937 0.482 0.835 1 20") ;
        list.add("@curve G(0, 0)(144, 166)(255, 255) @pixblend screen 0.94118 0.29 0.29 1 20") ;

        list.add("@style edge 1 2 @curve RGB(0, 255)(255, 0) @adjust saturation 0 @adjust level 0.33 0.71 0.93 ") ;





        _bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.rt) ;
        imageView.setSurfaceCreatedCallback(new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
            @Override
            public void surfaceCreated() {
                imageView.setImageBitmap(_bitmap);
            }
        });

        listView = (HorizontalListView) findViewById(R.id.view2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ishow){
                    listView.setVisibility(View.GONE);
                    ishow = false ;
                }else {
                    listView.setVisibility(View.VISIBLE);
                    ishow = true ;
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String s = list.get(position) ;
                imageView.setFilterWithConfig(s);
            }
        });

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.gpupluslist_item,null) ;
                final ImageGLSurfaceView surfaceViewr = (ImageGLSurfaceView) view.findViewById(R.id.mainImageView);
                surfaceViewr.setSurfaceCreatedCallback(new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
                    @Override
                    public void surfaceCreated() {
                        surfaceViewr.setImageBitmap(_bitmap.copy(Bitmap.Config.ARGB_8888,true));
                        surfaceViewr.setFilterWithConfig(list.get(position));
                    }
                });


                return view;
            }
        });
    }
}
