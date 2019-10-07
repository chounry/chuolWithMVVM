package com.example.chuolmvvm.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.chuolmvvm.R;
import com.example.chuolmvvm.api.ApiService;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.databinding.ActivityHomeBinding;
import com.example.chuolmvvm.databinding.DialogLoginBinding;
import com.example.chuolmvvm.databinding.NavHeadBinding;
import com.example.chuolmvvm.ui.fragment.AbsBaseFragment;
import com.example.chuolmvvm.ui.fragment.AbsBindingFragment;
import com.example.chuolmvvm.ui.fragment.ChatOutFragment;
import com.example.chuolmvvm.ui.fragment.HomeFragment;
import com.example.chuolmvvm.utils.SharePrefUtil;
import com.example.chuolmvvm.viewmodel.LoginDialogViewModel;
import com.example.chuolmvvm.viewmodel.NavHeaderViewModel;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import timber.log.Timber;

public class HomeActivity extends AbsBaseActivityBinding<ActivityHomeBinding>
        implements NavigationView.OnNavigationItemSelectedListener, NavHeaderViewModel.NavHeaderViewModelListener {
    private static final String TAG = "HomeActivity";
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Inject
    ApiService mApiService;

    private NavHeaderViewModel mNavHeaderViewModel;
    private NavHeadBinding mNavHeadBinding;
    private DialogLoginBinding mDialogLoginBinding;
    private LoginDialogViewModel mLoginDialogViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeFragment homeFragment = new HomeFragment();
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.nav_home);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getComponent().inject(this);
        initNavigation();
        Timber.i("Home tag" + homeFragment.getMyTag());
        initNavHeader();
        setFragment(R.id.container_home, homeFragment, homeFragment.getMyTag());
    }

    private void initNavigation() {
        mDrawerLayout = findViewById(R.id.drawer_home);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private boolean isLogin() {
        return SharePrefUtil.isLogin(getApplicationContext());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    private void initNavHeader() {
        mNavHeadBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_head,
                getBinding().navHome,
                false);
        getBinding().navHome.addHeaderView(mNavHeadBinding.getRoot());

        mNavHeaderViewModel = new NavHeaderViewModel(getApplicationContext(),
                SharePrefUtil.isLogin(getApplicationContext()),
                new UserDataManager(mApiService),
                this);

        mNavHeadBinding.setViewModel(mNavHeaderViewModel);
//        NavHeadBinding

//        View view = mNavigationView.getHeaderView(0);
//        View loginView = view.findViewById(R.id.login_dialog_include);
//        View userInfoView = view.findViewById(R.id.user_info_lin);
//        if(isLogin()){
//            Timber.e("Login");
//            userInfoView.setVisibility(View.VISIBLE);
//            loginView.setVisibility(View.GONE);
//
//        }else{
//            loginView.setVisibility(View.VISIBLE);
//            userInfoView.setVisibility(View.GONE);
//            loginView.setOnClickListener(view1 -> openLoginRegisterActivity());
//        }
    }

    private void openLoginRegisterActivity() {
        Intent intent = new Intent(HomeActivity.this, LoginRegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
            if (isLogin())
                fragment = new HomeFragment();
            else
                createLoginDialog();

        } else if (id == R.id.item_chat) {
            Timber.i("Here is");
            if (isLogin())
                fragment = new ChatOutFragment();
            else
                createLoginDialog();
        }

        if (fragment != null) {
            setFragmentOnTop(R.id.container_home, fragment, ((AbsBindingFragment) fragment).getMyTag());
        }
        mDrawerLayout.closeDrawers();
        return true;
    }

    private void createLoginDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_login);
        dialog.show();
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

    @Override
    public void onNeedChangeLoginDialogVisibility(boolean isVisible) {
        View view = mNavigationView.getHeaderView(0);
        View loginView = view.findViewById(R.id.login_dialog_include);
        loginView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
