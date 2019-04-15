package com.example.firstroad;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.firstroad.activities.BaseActivity;
import com.example.firstroad.fragments.MainDestinationFragment;
import com.example.firstroad.fragments.MainFindFragment;
import com.example.firstroad.fragments.MainHomeFragment;
import com.example.firstroad.fragments.MainJourneyFragment;
import com.example.firstroad.fragments.MainMineFragment;

public class MainActivity extends BaseActivity {

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
                mHomeFragment = new MainHomeFragment();
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
                mFindFragment = new MainFindFragment();
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
                mDestinationFragment = new MainDestinationFragment();
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
                mJourneyFragment = new MainJourneyFragment();
            }
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, mJourneyFragment);
            transaction.commit();
        }
    };

    private Runnable mMineRunnable = new Runnable() {
        @Override
        public void run() {
            if (mMineFrag){
                mMineFragment = new MainMineFragment();
            }
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, mMineFragment);
            transaction.commit();
        }
    };

    /*
    底部导航栏监听事件
     */
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    mHomeRunnable.run();
                    return true;

                case R.id.navigation_find:
                    mFindRunnable.run();
                    return true;

                case R.id.navigation_destination:
                    mDestinationRunnable.run();
                    return true;

                case R.id.navigation_journey:
                    mJourneyRunnable.run();
                    return true;

                case R.id.navigation_mine:
                    mMineRunnable.run();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transaction = getSupportFragmentManager().beginTransaction();

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        navigationView.setSelectedItemId(R.id.navigation_destination);

    }
}
