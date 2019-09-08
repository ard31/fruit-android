package com.app.alodokter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.alodokter.R;
import com.app.alodokter.ui.fragment.home.HomeFragment;
import com.app.alodokter.ui.fragment.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bn_navigation)
    BottomNavigationView bn_navigation;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    Fragment pageContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        pageContent = new HomeFragment();

        Fragment fragment = mFragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof  ProfileFragment)){
            mFragmentTransaction.add(R.id.main_frame_container, pageContent, HomeFragment.class.getSimpleName());
            mFragmentTransaction.commit();
        }

        bn_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mToolbar.setTitle(getString(R.string.home));
        setSupportActionBar(mToolbar);
        if (savedInstanceState != null){
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState,"pageContent");
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, pageContent).commit();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            menuItem -> {
                FragmentManager mFragmentManager = getSupportFragmentManager();
                if (mFragmentManager != null){
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    switch (menuItem.getItemId()){
                        case R.id.beranda:
                            home(mFragmentTransaction);
                            break;
                        case R.id.profile:
                            profile(mFragmentTransaction);
                            break;
                    }
                    menuItem.setChecked(true);
                    mFragmentTransaction.commit();

                }

                return false;
            };

    private FragmentTransaction home(FragmentTransaction mFragmentTransaction){
        pageContent = new HomeFragment();
        mFragmentTransaction.replace(R.id.main_frame_container, pageContent, HomeFragment.class.getSimpleName());
        changeTolbarTitle(getString(R.string.home));
        return mFragmentTransaction;
    }

    private FragmentTransaction profile(FragmentTransaction mFragmentTransaction){
        pageContent = new ProfileFragment();
        mFragmentTransaction.replace(R.id.main_frame_container, pageContent, ProfileFragment.class.getSimpleName());
        changeTolbarTitle(getString(R.string.profile));
        return mFragmentTransaction;
    }


    private void changeTolbarTitle(String title){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState,"pageContent", pageContent);
        super.onSaveInstanceState(outState);
    }

}
