package com.oasis.handleimage.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.oasis.handleimage.BaseActivity;
import com.oasis.handleimage.ProjectApplication;
import com.oasis.handleimage.R;
import com.oasis.handleimage.util.LogUtil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by liling on 2016/6/22.
 */
public class LightActivity extends BaseActivity {
    int REQUEST_IMAGE = 99 ;
    Button selectButton ;
    ImageView imageView ;

    SeekBar seekBar,seekBar1,seekBar2 ;
    private String pathName = "";
    private int imgHeight, imgWidth;
    private Bitmap srcBitmap, dstBitmap;

    private final int MAX_VALUE = 255;
    private final int MID_VALUE = 127;

    /**
     * 在缓存文件夹下的文件名
     */
    private String fileName = "" ;

    /**
     * 在缓存文件夹下的文件
     */
    File desfile ;

    Bitmap temBitmap ;

    ColorMatrix cMatrix ;


    @Override
    protected void onRestart() {
        super.onRestart();
        if(desfile!=null){
           // dstBitmap = BitmapFactory.decodeFile(desfile.getAbsolutePath());
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light);
        selectButton = (Button) findViewById(R.id.button3);
        imageView = (ImageView) findViewById(R.id.imageView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar2.setProgress(MID_VALUE);
        seekBar2.setMax(MAX_VALUE);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        //饱和度
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                LogUtil.e("llll","onProgressChanged");
                // 创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
                Bitmap bmp = Bitmap.createBitmap(imgWidth, imgHeight,Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bmp);
                Paint paint = new Paint(); // 新建paint
                paint.setAntiAlias(true);
                if(cMatrix==null){
                    cMatrix = new ColorMatrix();
                }
                cMatrix.reset();
                // 设置饱和度
                cMatrix.setSaturation(progress * 1.0f / MID_VALUE);
                paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));
                // 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
                canvas.drawBitmap(dstBitmap, 0, 0, paint);
                imageView.setImageBitmap(bmp);
              //  temBitmap = bmp ;
//                saveBitmap(bmp, fileName) ;
//                ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(desfile.getAbsolutePath()), imageView, imageOptions);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                LogUtil.e("llll","onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    LogUtil.e("llll","onStopTrackingTouch");
//                saveBitmap(temBitmap, fileName) ;
//                dstBitmap = BitmapFactory.decodeFile(desfile.getAbsolutePath());
//                ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(desfile.getAbsolutePath()), imageView, imageOptions);
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LightActivity.this, MultiImageSelectorActivity.class);
                // whether show camera
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                    // max select image amount
               // intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
                // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // Get the result list of select image paths
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Toast.makeText(LightActivity.this, path.get(0) + "", Toast.LENGTH_LONG).show();
                //ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(path.get(0)+),);
                pathName = path.get(0) ;
                File srcfile = new File(pathName) ;
                if(srcfile.exists()&&srcfile!=null){
                    String filename = srcfile.getName() ;
                    fileName = filename ;
                    LogUtil.e("llll","filename----->"+filename);
                }

                desfile = new File(ProjectApplication.getInstance().getImagePath(), fileName);
                if(!desfile.exists()){
                    try {
                        desfile.createNewFile() ;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    FileUtils.copyFile(srcfile, desfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                 ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(desfile.getAbsolutePath()),imageView,imageOptions);
                dstBitmap = BitmapFactory.decodeFile(desfile.getAbsolutePath());
                imgHeight = dstBitmap.getHeight();
                imgWidth = dstBitmap.getWidth();

            }
        }
    }

    public void saveBitmap(Bitmap bm,String name ) {

        File f = new File(ProjectApplication.getInstance().getImagePath(), name);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File targetFile = new File(ProjectApplication.getInstance().getImagePath());
        try {
            FileUtils.deleteDirectory(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
