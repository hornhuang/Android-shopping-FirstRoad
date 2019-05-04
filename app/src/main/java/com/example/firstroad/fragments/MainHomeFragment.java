package com.example.firstroad.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.firstroad.R;
import com.example.firstroad.pages.homes.clopedias.Home_Clopedia_zhen_zhu_qiu;
import com.example.firstroad.pages.homes.goods.homes_goods_ma_nao_diao_ke;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<String> titles = new ArrayList<>();

    public MainHomeFragment() {
        // Required empty public constructor
    }

    public static MainHomeFragment newInstance(String param1, String param2) {
        MainHomeFragment fragment = new MainHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_destination, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.linearlayout);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative_layout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), homes_goods_ma_nao_diao_ke.class));
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Home_Clopedia_zhen_zhu_qiu.class));
            }
        });

        iniBanner(view);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void iniBanner(View view){
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.home_top_1);
        images.add(R.drawable.home_top_2);
        images.add(R.drawable.home_top_3);
        images.add(R.drawable.home_top_4);
        titles.add("2018文化和自然遗产日");
        titles.add("川剧一绝——吐火");
        titles.add("非遗印象 品牌设计方案");
        titles.add("历史印记 无锡非遗");
        for (int i = 0 ; i < 4 ; i++){
            //images.add("http://47.107.132.227/form");
        }

        Banner banner = (Banner) view.findViewById(R.id.banner);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
