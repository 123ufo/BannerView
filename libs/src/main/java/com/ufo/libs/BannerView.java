package com.ufo.libs;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ufo.libs.adapter.BannerViewAdapter;
import com.ufo.libs.callback.ImageLoadCallback;
import com.ufo.libs.widget.CanClickViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 * <p>
 * 作者：xudiwei
 * <p>
 * <p>
 * 描述：自定义轮播图
 */

public class BannerView extends RelativeLayout {

    private static final String TAG = "BannerView";

    private CanClickViewPager mViewPager;
    private LinearLayout mLlIndicationContainer;
    private List<String> mList = new ArrayList<>();
    private BannerViewAdapter mAdapter;
    /*默认的滚动时间间隔*/
    private long delayTime = 1000 * 2;

    private int mIndicatorMarginLeft = 0;
    private int mIndicatorMarginTop = 0;
    private int mIndicatorMarginRight = 0;
    private int mIndicatorMarginBottom = 0;

    /*指示器大小*/
    private int mIndicatorSize = 8;
    private MyHandler mHandler = new MyHandler();
    private View mViewRoot;
    private ImageLoadCallback mCallback;
    private FrameLayout.LayoutParams mRootViewLp;
    private OnBannerViewClickListener mBannerViewClickListener;


    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mViewRoot = LayoutInflater.from(getContext()).inflate(R.layout.widget_banner_view, this, false);
        this.addView(mViewRoot);
        mViewPager = (CanClickViewPager) mViewRoot.findViewById(R.id.viewpager);
        mLlIndicationContainer = (LinearLayout) mViewRoot.findViewById(R.id.ll_indication_container);

        //默认指示器之间的距离
        mIndicatorMarginRight = (int) dipToPx(getContext(), 5);


    }

    @Override
    protected void onFinishInflate() {

        int height;
        height = getHeight();
        Log.d(TAG, height + "  :height");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int height;
        height = getHeight();
        Log.d(TAG, height + "  :height size");
    }

    /**
     * 设置数据
     *
     * @param imgs
     */
    public void setData(List<String> imgs, ImageLoadCallback callback) {
        this.removeAllViews();
        mHandler.removeCallbacksAndMessages(null);
        mList.clear();

        this.addView(mViewRoot);
        this.mList.addAll(imgs);
        this.mCallback = callback;

        mAdapter = new BannerViewAdapter(getContext(), mList, mCallback);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        mViewPager.setOnCanClickViewPagerListener(new MyOnCanClickViewPagerListener());
        mViewPager.setPressAndUpListener(new MyOnPressAndUpListener());

        //添加指示器
        addIndication();

        //设置当前显示的pager
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % mList.size());
        mViewPager.setCurrentItem(index);

        nextPager();
    }


    /**
     * 添加指示器
     */
    private void addIndication() {
        if (mList.size() == 0) {
            return;
        }
        mLlIndicationContainer.removeAllViews();

        View vIndicator = null;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) dipToPx(getContext(), mIndicatorSize)
                , (int) dipToPx(getContext(), mIndicatorSize));
        layoutParams.setMargins(mIndicatorMarginLeft, mIndicatorMarginTop, mIndicatorMarginRight, mIndicatorMarginBottom);
        for (int i = 0; i < mList.size(); i++) {
            vIndicator = new View(getContext());

            vIndicator.setBackgroundResource(R.drawable.selector_indicator);
            mLlIndicationContainer.addView(vIndicator, layoutParams);
            vIndicator = null;
        }
    }

    /**
     * 下一页
     */
    private void nextPager() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                mHandler.sendEmptyMessage(0);
            }
        }, delayTime);
    }

    /**
     * 开始轮播
     */
    public void startLoop() {
        mHandler.removeCallbacksAndMessages(null);
        nextPager();
    }

    /**
     * 停止输播
     */
    public void stopLoop() {
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * dip转Px
     *
     * @param dip
     * @return
     */
    public static float dipToPx(Context context, int dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }

    /**
     * 选中指示器
     *
     * @param position
     */
    private void selectCurrentIndicator(int position) {
        for (int i = 0; i < mLlIndicationContainer.getChildCount(); i++) {
            if (i == position) {
                mLlIndicationContainer.getChildAt(i).setSelected(true);
            } else {
                mLlIndicationContainer.getChildAt(i).setSelected(false);
            }
        }
    }

    /**
     * 设置指示器之间的Margin值。
     *
     * @param indicatorMarginLeft
     * @param indicatorMarginTop
     * @param indicatorMarginRight
     * @param indicatorMarginBottom
     */
    public void setIndicatorMargin(int indicatorMarginLeft, int indicatorMarginTop
            , int indicatorMarginRight, int indicatorMarginBottom) {
        this.mIndicatorMarginLeft = indicatorMarginLeft;
        this.mIndicatorMarginTop = indicatorMarginTop;
        this.mIndicatorMarginRight = indicatorMarginRight;
        this.mIndicatorMarginBottom = indicatorMarginBottom;
        addIndication();

    }

    /**
     * 设置指示器大小
     *
     * @param indicatorSize
     */
    public void setIndicatorSize(int indicatorSize) {
        this.mIndicatorSize = indicatorSize;
        addIndication();
    }

    /**
     * 设置页面切换的时间间隔单位是毫秒，默认是2000毫秒
     *
     * @param time
     */
    public void setDelayTime(long time) {
        this.delayTime = time;
    }

    private class MyOnCanClickViewPagerListener implements CanClickViewPager.OnCanClickViewPagerListener {
        @Override
        public void onClick(View view, int position) {
            Log.d(TAG, "click: " + (mViewPager.getCurrentItem() % mList.size()));
            if (null != mBannerViewClickListener) {
                mBannerViewClickListener.onItemClick(view, mViewPager.getCurrentItem() % mList.size());
            }
        }
    }

    /**
     * 滚动监听
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int realPosition = position % mList.size();
            selectCurrentIndicator(realPosition);
//            Log.d(TAG, realPosition + "");
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 消息处理器
     */
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            nextPager();
        }
    }

    /**
     * viewpager的按与松事件
     */
    private class MyOnPressAndUpListener implements CanClickViewPager.OnPressAndUpListener {
        @Override
        public void onPress() {
            mHandler.removeCallbacksAndMessages(null);
            Log.d(TAG, "按下");
        }

        @Override
        public void onUP() {
            nextPager();
            Log.d(TAG, "松开");
        }
    }

    public void setOnBannerViewClickListener(OnBannerViewClickListener clickListener) {
        this.mBannerViewClickListener = clickListener;
    }

    /**
     * BannerView点击事件监听接口
     */
    public interface OnBannerViewClickListener {
        void onItemClick(View view, int position);
    }
}
