###BannerView是一个常用的轮播图控件，只需要三行java代码就能实现


##.xml

        <com.ufo.libs.BannerView
            android:id="@+id/bannerView"
            android:layout_width="match_parent"
            android:layout_height="200dp">

        </com.ufo.libs.BannerView>


##.java


     BannerView bannerView = (BannerView) findViewById(R.id.bannerView);
            bannerView.setData(mList, new ImageLoadCallback() {
                @Override
                public void loadImage(ImageView imageView, String imgUrl) {
                    //自已实现的图片加载
                    ImageLoader.loadAvatar(MainActivity.this,imageView,imgUrl);
                }
            });


<img src="https://github.com/123ufo/BannerView/blob/master/screenshot/1.gif?raw=true" width="280"/>
