package com.ufo.bannerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ufo.bannerview.utils.ImageLoader;
import com.ufo.libs.BannerView;
import com.ufo.libs.callback.ImageLoadCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] imgs = new String[]{"http://www.fansimg.com/album/uploads2010/07/userid288504time20100724100218.jpg"
            , "http://pic23.nipic.com/20120919/8426898_213054250127_2.jpg"
            , "http://img4.imgtn.bdimg.com/it/u=3079979485,2899984191&fm=214&gp=0.jpg"
            , "http://pic23.nipic.com/20120919/8426898_213054250127_2.jpg"
            , "http://img0.imgtn.bdimg.com/it/u=603492941,1132661945&fm=214&gp=0.jpg"
            , "http://pic23.nipic.com/20120919/8426898_213054250127_2.jpg"
            , "http://img0.imgtn.bdimg.com/it/u=2258426085,1144521986&fm=214&gp=0.jpg"};
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = Arrays.asList(imgs[0], imgs[1], imgs[2], imgs[3], imgs[4], imgs[5], imgs[6]);

        initView();
    }

    private void initView() {
        BannerView bannerView = (BannerView) findViewById(R.id.bannerView);
        bannerView.setData(mList, new ImageLoadCallback() {
            @Override
            public void loadImage(ImageView imageView, String imgUrl) {
                //自已实现的图片加载
                ImageLoader.loadAvatar(MainActivity.this,imageView,imgUrl);
            }
        });


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FunctionActivity.class));
            }
        });
    }
}
