package com.mainiway.eworkpal;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.mainiway.okhttp.GenericRequestManager;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
/**
 * Application
 * @author gao_chun
 * 描述：初始化配置信息
 */
public class AppAplication extends Application {

    private static Context mContext;
    private  DisplayImageOptions options;

    //public LocationService locationService; //定位

    //图片缓存路径
    public static File cacheDir;
    public static final String CACHE_DIR = "MW/";
    public static final String CACHE_IMG_DOC = "ImageCache";
    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        // 崩溃信息
        //CrashManager.install(getApplicationContext());

        // 初始化用户配置信息
        //ConfigManager.initialize(getApplicationContext());

        // 初始化网络配置
        //final GlobalConfigManager globalConfigManager = GlobalConfigManager.getInstance();
        //GenericRequestManager.initialize(getApplicationContext(), globalConfigManager.getServerHost());
        GenericRequestManager.initialize(this);

        // 初始化ImageLoader
        initImageLoader(getApplicationContext());

        //初始化百度Map
        //SDKInitializer.initialize(getApplicationContext());

        //初始化定位服务
        //locationService = new LocationService(getApplicationContext());

        //初始化SDcard目录
        //FileUtil.initFileDir();

    }


    public static Context getContext() {
        return mContext;
    }


    /**
     * init the ImageLoader
     * @param context
     */
    public void initImageLoader(Context context) {

        com.nostra13.universalimageloader.utils.L.writeLogs(false);// 关闭ImageLoaderLog日志

        cacheDir = StorageUtils.getOwnCacheDirectory(this, CACHE_DIR + CACHE_IMG_DOC);//图像缓存路径

        //配置图像加载Option
        options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.mipmap.ic_default)  //加载过程中
        .showImageForEmptyUri(R.mipmap.ic_default)//uri为空时
        .showImageOnFail(R.mipmap.ic_default)     //加载失败时
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .bitmapConfig(Bitmap.Config.RGB_565)    //图片压缩质量参数
        .build();

        //配置ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 1)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .diskCacheSize(100 * 1024 * 1024) // 100 Mb
                //.diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new WeakMemoryCache())
                .defaultDisplayImageOptions(options)
                .build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }


    /**
     * 获取Token信息
     * @return Token
     */
    /*public static String getToken(){
        String mToken = ConfigManager.getInstance().get(AppConstant.PREF_NAME_TOKEN,"").toString();
        if (!TextUtils.isEmpty(mToken)) {
            return mToken;
        }
        return "";
    }*/

}
