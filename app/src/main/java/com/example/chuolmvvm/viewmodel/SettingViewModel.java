package com.example.chuolmvvm.viewmodel;

public class SettingViewModel {
    private SettingViewModelListener mSettingViewModelListener;

    public void setSettingViewModelListener(SettingViewModelListener settingViewModelListener) {
        mSettingViewModelListener = settingViewModelListener;
    }

    public void onPersonalInfoClick() {
        if (mSettingViewModelListener != null)
            mSettingViewModelListener.onPersonalInfoClicked();
    }

    public void onPostHistoryClick() {
        if (mSettingViewModelListener != null)
            mSettingViewModelListener.onPostHistoryClicked();
    }

    public void onSignOutClick() {
        if (mSettingViewModelListener != null)
            mSettingViewModelListener.onSignOutClicked();
    }

    public void onAboutUsClick() {
        if (mSettingViewModelListener != null)
            mSettingViewModelListener.onAboutUsClicked();
    }

    public void onTermConClick() {
        if (mSettingViewModelListener != null)
            mSettingViewModelListener.onTermConClicked();
    }

    public interface SettingViewModelListener {
        void onPersonalInfoClicked();

        void onPostHistoryClicked();

        void onSignOutClicked();

        void onAboutUsClicked();

        void onTermConClicked();
    }
}
