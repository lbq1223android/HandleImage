package com.oasis.handleimage;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.oasis.handleimage.bean.FuctionBean;
import com.oasis.handleimage.ui.CropDemo;
import com.oasis.handleimage.ui.Filter;
import com.oasis.handleimage.ui.GpuDemoOne;
import com.oasis.handleimage.ui.ImageToneActivity;
import com.oasis.handleimage.ui.LightActivity;
import com.oasis.handleimage.ui.SeenDemo;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView mListView;
    ArrayList<FuctionBean> mList = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setColor(this, getResources().getColor(R.color.app_basecolor));

        /*以java的方式 效率太低  舍去
        mList.add(new FuctionBean("亮度 对比度 饱和度", LightActivity.class)) ;
        mList.add(new FuctionBean("亮度 对比度 饱和度2", ImageToneActivity.class)) ;*/
        mList.add(new FuctionBean("Gpu" +
                "demo", GpuDemoOne.class)) ;
        mList.add(new FuctionBean("gpu 亮度 对比度 复合！" +
                "demo", SeenDemo.class)) ;
        mList.add(new FuctionBean("图片裁剪（框子固定）" +
                "demo", CropDemo.class)) ;
        mList.add(new FuctionBean("滤镜" +
                "demo", Filter.class)) ;

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object getItem(int position) {
                return mList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item,null) ;
                TextView textView = (TextView) v.findViewById(R.id.textView2);
                textView.setText(mList.get(position).getFuncionName()+"");
                return v;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent() ;
                intent.setClass(MainActivity.this,mList.get(position).getClassName()) ;
                startActivity(intent);
            }
        });

    }

    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }
}
