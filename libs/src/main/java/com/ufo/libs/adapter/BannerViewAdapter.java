package com.ufo.libs.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.ufo.libs.R;
import com.ufo.libs.callback.ImageLoadCallback;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 * <p>
 * 作者：xudiwei
 * <p>
 * 描述：viewpager适配器
 */

public class BannerViewAdapter extends PagerAdapter {

    private static final String TAG = "BannerViewAdapter";

    private Context mContext;
    private List<String> mList;
    private ImageLoadCallback mCallback;

    public BannerViewAdapter(Context context, List<String> list, ImageLoadCallback callback) {
        mContext = context;
        mList = list;
        this.mCallback = callback;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, container, false);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_img);
        try {
            this.mCallback.loadImage(iv,mList.get(position % mList.size()));
        } catch (Exception e) {
            this.mCallback.loadImage(iv,null);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
