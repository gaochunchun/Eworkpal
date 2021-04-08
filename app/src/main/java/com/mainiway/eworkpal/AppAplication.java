package com.mainiway.eworkpal;

import android.app.Application;
import android.content.Context;

import com.mainiway.eworkpal.manager.ConfigManager;
import com.mainiway.eworkpal.utils.CrashManager;
import com.mainiway.okhttp.GenericRequestManager;
/**
 * Application
 * @author gao_chun
 * 描述：初始化配置信息
 */
public class AppAplication extends Application {

    private static Context mContext;

    //图片缓存路径
    //public static File cacheDir;
    //public static final String CACHE_DIR = "MW/";
    //public static final String CACHE_IMG_DOC = "ImageCache";
    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        // 崩溃信息
        CrashManager.install(getApplicationContext());

        // 初始化用户配置信息
        ConfigManager.initialize(getApplicationContext());

        // 初始化网络配置
        GenericRequestManager.initialize(this);

        //初始化SDcard目录
        //FileUtils.initFileDir();

    }

    public static Context getContext() {
        return mContext;
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
