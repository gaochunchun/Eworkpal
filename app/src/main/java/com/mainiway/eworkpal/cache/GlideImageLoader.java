package com.mainiway.eworkpal.cache;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mainiway.eworkpal.R;
import com.mainiway.imagepicker.loader.ImageLoader;


import java.io.File;


public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                                //配置上下文
                .load(Uri.fromFile(new File(path)))         //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.mipmap.default_image)             //设置错误图片
                .placeholder(R.mipmap.default_image)       //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)   //缓存全尺寸
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}
