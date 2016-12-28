package com.ufo.libs.callback;

import android.widget.ImageView;

/**
 * Created by Administrator on 2016/12/28.
 * <p>
 * 作者：xudiwei
 * <p>
 * 描述：图片加载回调
 */

public interface ImageLoadCallback {

    /**
     * 图片加载回调方法，此方法在viewpager 的适配器里调用，通过此接口暴露出去
     * 给开发者实现具体的加载方式，因为不同开发者可能用不同的图片加载框架。所以，自已去实现。
     *
     * @param imageView
     * @param imgUrl
     */
    void loadImage(ImageView imageView, String imgUrl);
}
