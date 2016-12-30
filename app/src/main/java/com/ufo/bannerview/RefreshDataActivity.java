package com.ufo.bannerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ufo.bannerview.utils.ImageLoader;
import com.ufo.libs.BannerView;
import com.ufo.libs.callback.ImageLoadCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RefreshDataActivity extends AppCompatActivity {
    private String[] oldsArr = new String[]{"http://www.fansimg.com/album/uploads2010/07/userid288504time20100724100218.jpg"
            , "http://pic23.nipic.com/20120919/8426898_213054250127_2.jpg"
            , "http://img4.imgtn.bdimg.com/it/u=3079979485,2899984191&fm=214&gp=0.jpg"
            , "http://pic23.nipic.com/20120919/8426898_213054250127_2.jpg"
            , "http://img0.imgtn.bdimg.com/it/u=603492941,1132661945&fm=214&gp=0.jpg"
            , "http://pic23.nipic.com/20120919/8426898_213054250127_2.jpg"
            , "http://img0.imgtn.bdimg.com/it/u=2258426085,1144521986&fm=214&gp=0.jpg"};

    private String[] newsArr = new String[]{"http://a.ikafan.com/attachment/forum/201307/21/134252kwdscds2lpp33sac.jpg"
            , "http://www.wallcoo.com/paint/space_1600/images/wallcoo.com_Space_Art_wc.jpg"
            , "http://pic.58pic.com/58pic/15/11/86/22P58PICvbB_1024.jpg"};
    private List<String> olds = new ArrayList<>();
    private List<String> news = new ArrayList<>();

    private SwipeRefreshLayout mRefreshLayout;
    private BannerView mBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        olds = Arrays.asList(oldsArr[0], oldsArr[1], oldsArr[2], oldsArr[3], oldsArr[4], oldsArr[5], oldsArr[6]);
        news = Arrays.asList(newsArr[0], newsArr[1], newsArr[2]);


        initView();
    }

    private void initView() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(new MyOnRefreshListener());

        mBannerView = (BannerView) findViewById(R.id.bannerView);
        mBannerView.setData(olds, new MyImageLoadCallback());
        mBannerView.setOnBannerViewClickListener(new MyOnBannerViewClickListener());
    }

    private class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRefreshLayout.setRefreshing(false);
                    mBannerView.setData(news, new MyImageLoadCallback());
                }
            }, 2000);
        }
    }

    /**
     * 实现图片加载
     */
    private class MyImageLoadCallback implements ImageLoadCallback {
        @Override
        public void loadImage(ImageView imageView, String imgUrl) {
            ImageLoader.loadAvatar(RefreshDataActivity.this, imageView, imgUrl);
        }
    }

    /**
     * 点击事件
     */
    private class MyOnBannerViewClickListener implements BannerView.OnBannerViewClickListener {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(RefreshDataActivity.this, position + "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBannerView.stopLoop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBannerView.startLoop();
    }
}
