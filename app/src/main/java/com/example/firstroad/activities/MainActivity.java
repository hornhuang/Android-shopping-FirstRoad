package com.example.firstroad.activities;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.firstroad.R;
import com.example.firstroad.classes.Comment;
import com.example.firstroad.classes.User;
import com.example.firstroad.fragments.MainHomeFragment;
import com.example.firstroad.fragments.MainGoodsFragment;
import com.example.firstroad.fragments.MaincyClopediaFragment;
import com.example.firstroad.fragments.MainRoutesFragment;
import com.example.firstroad.fragments.MainMineFragment;
import com.example.firstroad.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity {

    private User user;

    /*
    暂存数据
     */
    public static List<Comment>  comments = new ArrayList<>();

    /*
    碎片
     */
    private Fragment mHomeFragment ;
    private Fragment mFindFragment ;
    private Fragment mDestinationFragment ;
    private Fragment mJourneyFragment ;
    private Fragment mMineFragment ;
    private FragmentTransaction transaction;

    /*
    碎片 flag
     */
    private boolean mHomeFrag = true, mFindFrag = true,
            mDestinationFrag = true, mJourneyFrag = true, mMineFrag = true;

    /*
    碎片切换
     */
    private Runnable mHomeRunnable = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
            if (mHomeFrag) {
                mHomeFragment = new MaincyClopediaFragment();
            }
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, mHomeFragment);
            transaction.commit();
        }
    };

    private Runnable mFindRunnable = new Runnable() {
        @Override
        public void run() {
            if (mFindFrag){
                mFindFragment = new MainGoodsFragment();
            }
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, mFindFragment);
            transaction.commit();
        }
    };

    private Runnable mDestinationRunnable = new Runnable() {
        @Override
        public void run() {
            if (mDestinationFrag){
                mDestinationFragment = new MainHomeFragment();
            }
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, mDestinationFragment);
            transaction.commit();
        }
    };

    private Runnable mJourneyRunnable = new Runnable() {
        @Override
        public void run() {
            if (mJourneyFrag){
                mJourneyFragment = new MainRoutesFragment();
            }
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, mJourneyFragment);
            transaction.commit();
        }
    };

    private Runnable mMineRunnable = new Runnable() {
        @Override
        public void run() {
            if (BmobUser.isLogin()){
                if (mMineFrag){
                    mMineFragment = new MainMineFragment();
                }
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, mMineFragment);
                transaction.commit();
            }else {
                LogInActivity.acrionStart(MainActivity.this);
            }
        }
    };

    /*
    底部导航栏监听事件
     */
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.navigation_wikipedia:
                    mHomeRunnable.run();
                    return true;

                case R.id.navigation_goods:
                    mFindRunnable.run();
                    return true;

                case R.id.navigation_primepages:
                    mDestinationRunnable.run();
                    return true;

                case R.id.navigation_routes:
                    mJourneyRunnable.run();
                    return true;

                case R.id.navigation_mine:
                    mMineRunnable.run();
                    return true;

            }
            return false;
        }
    };

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BmobUser.isLogin()){
            user = BmobUser.getCurrentUser(User.class);
        }

        transaction = getSupportFragmentManager().beginTransaction();

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigationView);//取消缩放 失败
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        navigationView.setSelectedItemId(R.id.navigation_primepages);

    }
}
