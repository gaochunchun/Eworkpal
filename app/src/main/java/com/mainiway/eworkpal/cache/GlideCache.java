package com.mainiway.eworkpal.cache;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.mainiway.eworkpal.utils.SDCardUtils;

import java.io.File;

import static com.mainiway.eworkpal.utils.SDCardUtils.getSDCardPath;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-12-20.
 * 描    述：自定义Glide磁盘缓存
 * ===========================================
 */

public class GlideCache implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

        //设置磁盘缓存目录（和创建的缓存目录相同）
        String downloadDirectoryPath = "";
        if (SDCardUtils.isSDCardEnable()){
            downloadDirectoryPath = SDCardUtils.getSDCardPath()+ "/GlideCache";
        }
        //File storageDirectory = Environment.getExternalStorageDirectory();
        //String downloadDirectoryPath = storageDirectory + "/GlideCache";

        //设置缓存的大小为100M
        //int cacheSize = 100 * 1000 * 1000;
        int cacheSize100MegaBytes = 104857600;
        builder.setDiskCache( new DiskLruCacheFactory(downloadDirectoryPath, cacheSize100MegaBytes) );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
