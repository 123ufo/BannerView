package com.ufo.bannerview.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ufo.bannerview.R;

/**
 * 作者：XuDiWei
 * <p/>
 * 日期：2016/2/10.15:28
 * <p/>
 * 文件描述：
 */
public class ImageLoader {
//
//    private static final int DEFAULT_IMAGE = R.mipmap.ic_photo_default;
//    private static final int ERROR_IMAGE = R.mipmap.ic_photo_default;

    private static final int DEFAULT_AVATAR = R.mipmap.ic_avatar_default;
    private static final int ERROR_AVATAR = R.mipmap.ic_avatar_default;

//    public static void loadToUrl(Context context, ImageView imageView, String url) {
//        Glide.with(context).
//                load(url)//图片的url
//                .centerCrop()//图片的显示方式。这里在是居中裁剪
//                .placeholder(DEFAULT_IMAGE) //默认的占位图片
//                .error(ERROR_IMAGE) //加载失败的图片
//                .crossFade(1000)//图片的加载效果，这里是淡入淡出，时间为1000毫秒
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存策略
//                .into(imageView);//加载
//    }

    public static void loadAvatar(Context context, ImageView imageView, String url) {
        Glide.with(context).
                load(url)//图片的url
                .centerCrop()//图片的显示方式。这里在是居中裁剪
                .placeholder(DEFAULT_AVATAR) //默认的占位图片
                .error(ERROR_AVATAR) //加载失败的图片
                .crossFade(1000)//图片的加载效果，这里是淡入淡出，时间为1000毫秒
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存策略
                .into(imageView);//加载
    }

}
