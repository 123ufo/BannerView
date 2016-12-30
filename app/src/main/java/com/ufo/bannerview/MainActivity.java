package com.ufo.bannerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ufo.bannerview.utils.ImageLoader;
import com.ufo.libs.BannerView;
import com.ufo.libs.callback.ImageLoadCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String[] imgs = new String[]{"http://www.fansimg.com/album/uploads2010/07/userid288504time20100724100218.jpg"
            , "http://pic23.nipic.com/20120919/8426898_213054250127_2.jpg"
            , "http://img4.imgtn.bdimg.com/it/u=3079979485,2899984191&fm=214&gp=0.jpg"
            , "http://pic23.nipic.com/20120919/8426898_213054250127_2.jpg"
            , "http://img0.imgtn.bdimg.com/it/u=603492941,1132661945&fm=214&gp=0.jpg"
            , "http://pic23.nipic.com/20120919/8426898_213054250127_2.jpg"
            , "http://img0.imgtn.bdimg.com/it/u=2258426085,1144521986&fm=214&gp=0.jpg"};
    private List<String> mList = new ArrayList<>();
    private BannerView mBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = Arrays.asList(imgs[0], imgs[1], imgs[2], imgs[3], imgs[4], imgs[5], imgs[6]);

        initView();
    }


    boolean invisible = true;
    int indicatorSize = 8;
    boolean isStop = true;

    private void initView() {
        mBannerView = (BannerView) findViewById(R.id.bannerView);
        mBannerView.setData(mList, new ImageLoadCallback() {
            @Override
            public void loadImage(ImageView imageView, String imgUrl) {
                //自已实现的图片加载
                ImageLoader.loadAvatar(MainActivity.this, imageView, imgUrl);
            }
        });


        //点击事件监听
        mBannerView.setOnBannerViewClickListener(new BannerView.OnBannerViewClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });

        //滚动监听
        mBannerView.setOnBannerScrollListener(new BannerView.OnBannerScrollListener() {
            @Override
            public void onPagerSelected(int position) {
                Log.d(TAG, "滚动监听：" + position);
            }
        });


        findViewById(R.id.btn_indicator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBannerView.setInvisibleIndicator(invisible);
                invisible = !invisible;
            }
        });
        findViewById(R.id.btn_indicator_size).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBannerView.setIndicatorSize(indicatorSize);
                indicatorSize = indicatorSize + 2;
            }
        });
        findViewById(R.id.btn_switch_delay_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBannerView.setDelayTime(5000);
                Toast.makeText(MainActivity.this, "改为5秒", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_switch_delay_time_).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBannerView.setDelayTime(1000);
                Toast.makeText(MainActivity.this, "改为1秒", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_stop_and_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStop) {
                    mBannerView.stopLoop();
                    Toast.makeText(MainActivity.this, "停止", Toast.LENGTH_SHORT).show();
                } else {
                    mBannerView.startLoop();
                    Toast.makeText(MainActivity.this, "开始", Toast.LENGTH_SHORT).show();
                }
                isStop = !isStop;
            }
        });


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RefreshDataActivity.class));
            }
        });

        findViewById(R.id.btn_screen_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScreenAllActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) mList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBannerView.stopLoop();
        Log.d(TAG, "stopLoop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBannerView.startLoop();
        Log.d(TAG, "onStart");
    }
}
