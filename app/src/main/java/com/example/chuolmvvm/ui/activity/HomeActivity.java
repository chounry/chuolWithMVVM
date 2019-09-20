package com.example.chuolmvvm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.chuolmvvm.R;
import com.example.chuolmvvm.adapter.HomePageAdapter;
import com.example.chuolmvvm.databinding.ActivityHomeBinding;
import com.example.chuolmvvm.ui.fragment.AbsBaseFragment;
import com.example.chuolmvvm.ui.fragment.AbsBindingFragment;
import com.example.chuolmvvm.ui.fragment.ChatOutFragment;
import com.example.chuolmvvm.ui.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import timber.log.Timber;

public class HomeActivity extends AbsBaseActivityBinding<ActivityHomeBinding> implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeFragment homeFragment = new HomeFragment();
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.nav_home);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initNavigation();
        initLoginHeader();
        Timber.i("Home tag" + homeFragment.getMyTag());
        setFragment(R.id.container_home, homeFragment, homeFragment.getMyTag());
    }

    private void initLoginHeader() {
        View navHead = mNavigationView.getHeaderView(0);
        View loginView = navHead.findViewById(R.id.login_dialog_include);
        TextView loginTvBtn = loginView.findViewById(R.id.login_sign_up_btn);
        loginTvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginRegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void initNavigation() {
        mDrawerLayout = findViewById(R.id.drawer_home);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.i("onOptionsItemSelected");

        if (mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId()) {
            case R.id.post:
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment fragment = null;
        Timber.i("onNavigationItemSelected");


        if (id == R.id.nav_home) {

        } else if (id == R.id.item_home) {
            fragment = new HomeFragment();

        } else if (id == R.id.item_chat) {
            Timber.i("Here is");
            fragment = new ChatOutFragment();
        }

        if (fragment != null) {
            setFragmentOnTop(R.id.container_home, fragment, ((AbsBindingFragment) fragment).getMyTag());
        }
        mDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_home);
        if (fragment instanceof HomeFragment) {
            super.onBackPressed();
        } else {
            HomeFragment homeFragment = new HomeFragment();
            setFragmentOnTop(R.id.container_home, homeFragment, homeFragment.getMyTag());
        }
    }

    public void setFragmentOnTop(@IdRes int id, Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment topFragment = fragmentManager.findFragmentById(id);
        if (fragment instanceof AbsBaseFragment) {
            Timber.i(((AbsBindingFragment) topFragment).getMyTag() + " Nothing");
            if (((AbsBindingFragment) topFragment).getMyTag().equals(tag)) {
                // if the replaced fragment is already place
                return;
            }

            if (fragment instanceof HomeFragment) {
                fragmentManager.popBackStack();
            } else {
                if (!(topFragment instanceof HomeFragment)) {
                    // if the top fragment is the home fragment we don't remove it from the backstack
                    fragmentManager.popBackStack();
                }
                setFragment(id, fragment, tag);
            }
        }
    }
}
