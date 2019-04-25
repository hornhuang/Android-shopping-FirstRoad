package com.example.firstroad.itemdetails;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.firstroad.R;
import com.example.firstroad.fragments.MainHomeFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class RoutesDetailsActivity extends AppCompatActivity {


    private MainHomeFragment.OnFragmentInteractionListener mListener;

    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_details);

        iniBanner();
    }


    private void iniBanner(){
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.changbaishan_1);
        images.add(R.drawable.changbaishan_2);
        images.add(R.drawable.changbaishan_3);
        images.add(R.drawable.changbai_shang_4);
        titles.add("清晨美景");
        titles.add("心旷神怡");
        titles.add("风轻云淡");
        titles.add("云雾弥漫");
        for (int i = 0 ; i < 4 ; i++){
            //images.add("http://47.107.132.227/form");
        }

        Banner banner = (Banner) findViewById(R.id.banner);
//        banner.setImages(images).setImageLoader(new GlideImageLoader());
        banner.setImages(images).setImageLoader(new MyImageLoader());

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
//        banner.setImageLoader(new GlideImageLoader());
        banner.setImageLoader(new MyImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

}
