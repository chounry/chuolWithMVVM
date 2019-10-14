package com.example.chuolmvvm.ui.activity;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.chuolmvvm.ui.fragment.AbsBaseFragment;
import com.example.chuolmvvm.ui.fragment.AbsBindingFragment;
import com.example.chuolmvvm.ui.fragment.HomeFragment;

import java.lang.reflect.ParameterizedType;

import timber.log.Timber;

public abstract class AbsActivityFragment<F extends Fragment, T extends ViewDataBinding>
        extends AbsBaseActivityBinding<T> {
    private Class<F> baseFragment = (Class<F>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().addOnBackStackChangedListener(
                () -> Timber.e("Fragment backStack count " +
                        getSupportFragmentManager().getBackStackEntryCount()));
    }

    public void setFragmentOnTop(Fragment fragment, String tag, boolean needAnima) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment topFragment = fragmentManager.findFragmentById(getFragmentContainerId());
        if (fragment instanceof AbsBaseFragment) {
            Timber.i(((AbsBindingFragment) topFragment).getMyTag() + " Nothing");
            if (((AbsBindingFragment) topFragment).getMyTag().equals(tag)) {
                // if the replaced fragment is already place
                return;
            }
            if (baseFragment.isInstance(fragment)) {
                // if the fragment already at the base..... just clear all fragment that stay on top of the base fragment
                fragmentManager.popBackStack(0, 0);
            } else {
                if (!(topFragment instanceof HomeFragment)) {
                    // if the top fragment is the home fragment we don't remove it from the backstack
                    fragmentManager.popBackStack(0, 0);
                }
                if (!needAnima)
                    setFragment(getFragmentContainerId(), fragment, tag);
                else
                    setFragmentWithAnima(getFragmentContainerId(), fragment, tag);
            }
        }
    }

    @IdRes
    abstract int getFragmentContainerId();

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(getFragmentContainerId());
        if (baseFragment.isInstance(fragment)) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
