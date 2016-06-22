package com.oasis.handleimage;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by liling on 2016/6/22.
 */
public class ProjectApplication extends Application {

    private final String appPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/com.oasis.handleimage/" ;
    private final String imagePath = appPath+"uploadImage/" ;
    private static ProjectApplication instance;

    public static ProjectApplication getInstance() {
        if (null == instance) {
            instance = new ProjectApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).writeDebugLogs().build();
        ImageLoader.getInstance().init(configuration);
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAppPath() {
        return appPath;
    }
}
