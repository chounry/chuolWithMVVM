package com.example.chuolmvvm.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.chuolmvvm.api.callback.CallBackHelper;
import com.example.chuolmvvm.api.callback.OnCallBackWithErrorBody;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.model.User;
import com.example.chuolmvvm.utils.SharePrefUtil;

import io.reactivex.Observable;
import retrofit2.Response;
import timber.log.Timber;

public class NavHeaderViewModel extends AbsBaseViewModel
        implements LoginDialogViewModel.LoginDialogViewModelListener {

    public LoginDialogViewModel mLoginDialogViewModel;
    private ObservableField<String> mUserName = new ObservableField<>();
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mImageUrl = new ObservableField<>();
    private ObservableInt mUserInfoVisibility = new ObservableInt(View.GONE);
    private UserDataManager mUserDataManager;
    private NavHeaderViewModelListener mNavHeaderViewModelListener;
    private boolean mLoginDialogVisibility;


    public NavHeaderViewModel(Context context,
                              UserDataManager userDataManager,
                              NavHeaderViewModelListener navHeaderViewModelListener) {
        super(context);
        mLoginDialogViewModel = new LoginDialogViewModel(context, this);
        mUserDataManager = userDataManager;
        mNavHeaderViewModelListener = navHeaderViewModelListener;
        initView();
    }

    private void initView() {
        validateView();
        if (isLogin()) {
            Observable<Response<User>> call = mUserDataManager.getUser();
            CallBackHelper<Response<User>, User> helper = new CallBackHelper<>(getContext(), call);
            addDisposible(helper.execute(new OnCallBackWithErrorBody<User>() {
                @Override
                public void onErrorBody(String errorBody) {
                    Timber.e("onErrorBody " + errorBody);

                }

                @Override
                public void onFail(Throwable throwable) {
                    Timber.e("onFail" + throwable.getMessage());

                }

                @Override
                public void onComplete(User data, int code) {
                    Timber.e("onComplete");
                    updateUser(data);
                }
            }));
        }
    }

    public void updateUser(User user){
        mEmail.set(user.getEmail());
        mUserName.set(user.getUsername());
        mImageUrl.set(user.getImgProfile());
    }

    public void validateView() {
        if (isLogin()) {
            mLoginDialogVisibility = false;
            mUserInfoVisibility.set(View.VISIBLE);
        } else {
            mLoginDialogVisibility = true;
            mUserInfoVisibility.set(View.GONE);
        }

        mNavHeaderViewModelListener.onNeedChangeLoginDialogVisibility(mLoginDialogVisibility);
    }

    private boolean isLogin(){
        return SharePrefUtil.isLogin(getContext());
    }



    public ObservableField<String> getUserName() {
        return mUserName;
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail.set(email);
    }

    public ObservableField<String> getImageUrl() {
        return mImageUrl;
    }

    public ObservableInt getUserInfoVisibility() {
        return mUserInfoVisibility;
    }

    @Override
    public void onLoginSignUpClicked() {
        mNavHeaderViewModelListener.onLoginSignUpClicked();
    }

    @Override
    public void onLoginFacebookClicked() {

    }

    public interface NavHeaderViewModelListener{
        void onNeedChangeLoginDialogVisibility(boolean isVisible);

        void onLoginSignUpClicked();
    }
}
