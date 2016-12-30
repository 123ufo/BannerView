package com.ufo.bannerview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ufo.bannerview.utils.ImageLoader;
import com.ufo.libs.BannerView;
import com.ufo.libs.callback.ImageLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class ScreenAllActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_all);

        List<String> data = getIntent().getStringArrayListExtra("data");
        data = (List<String>) getIntent().getExtras().getSerializable("data");

        BannerView bannerView = (BannerView) findViewById(R.id.bannerView);
        bannerView.setData(data, new ImageLoadCallback() {
            @Override
            public void loadImage(ImageView imageView, String imgUrl) {
                ImageLoader.loadAvatar(ScreenAllActivity.this,imageView,imgUrl);
            }
        });
    }
}
