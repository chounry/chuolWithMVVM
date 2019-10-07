package com.example.chuolmvvm.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.chuolmvvm.api.callback.CallBackHelper;
import com.example.chuolmvvm.api.callback.OnCallBackWithErrorBody;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.config.Constants;
import com.example.chuolmvvm.model.SignUpResponse;
import com.example.chuolmvvm.model.User;
import com.example.chuolmvvm.utils.SharePrefUtil;

import io.reactivex.Observable;
import retrofit2.Response;
import timber.log.Timber;

public class SignInViewModel extends AbsBaseViewModel {
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mPassword = new ObservableField<>();
    private ObservableInt mLoginBtnVisibility = new ObservableInt(View.VISIBLE);
    private ObservableInt mLoadingVisibility = new ObservableInt(View.GONE);
    private UserDataManager mUserDataManager;
    private SignInViewModelListener mSignInViewModelListener;

    public SignInViewModel(Context context, UserDataManager userDataManager,
                           SignInViewModelListener signinViewModelListener) {
        super(context);
        mUserDataManager = userDataManager;
        mSignInViewModelListener = signinViewModelListener;
    }

    public void showLoading() {
        mLoadingVisibility.set(View.VISIBLE);
        mLoginBtnVisibility.set(View.GONE);
    }

    public void hideLoading() {
        mLoadingVisibility.set(View.GONE);
        mLoginBtnVisibility.set(View.VISIBLE);
    }

    public void setEmail(ObservableField<String> email) {
        mEmail = email;
    }

    public void setPassword(ObservableField<String> password) {
        mPassword = password;
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public ObservableField<String> getPassword() {
        return mPassword;
    }

    public ObservableInt getLoginBtnVisibility() {
        return mLoginBtnVisibility;
    }

    public ObservableInt getLoadingVisibility() {
        return mLoadingVisibility;
    }

    public void onLoginClick() {
        showLoading();
        Observable<Response<SignUpResponse>> call = mUserDataManager.getLogin(
                getEmail().get(),
                getPassword().get()
        );

        CallBackHelper<Response<SignUpResponse>, SignUpResponse> helper = new CallBackHelper<>(getContext(), call);

        helper.execute(new OnCallBackWithErrorBody<SignUpResponse>() {
            @Override
            public void onErrorBody(String errorBody) {
                mSignInViewModelListener.onInvalidForm(errorBody);
                hideLoading();
            }

            @Override
            public void onFail(Throwable throwable) {
                hideLoading();
                Timber.e("onFail " + throwable);
            }

            @Override
            public void onComplete(SignUpResponse data, int code) {

                if (code == Constants.SUCCESS_CODE) {
                    SharePrefUtil.setToken(getContext(), data.getAccessToken(), data.getRefreshToken());
                    requestUser();
                } else if (code == Constants.UNAUTHORIZE) {
                    mSignInViewModelListener.onUnAuthorized();
                    hideLoading();
                } else {
                    hideLoading();
                }
            }
        });
    }

    public void requestUser() {
        Observable<Response<User>> call = mUserDataManager.getUser();
        CallBackHelper<Response<User>, User> helper = new CallBackHelper<>(getContext(), call);

        helper.execute(new OnCallBackWithErrorBody<User>() {
            @Override
            public void onErrorBody(String errorBody) {
                mSignInViewModelListener.onInvalidForm(errorBody);
            }

            @Override
            public void onFail(Throwable throwable) {

            }

            @Override
            public void onComplete(User data, int code) {
                if (code == Constants.SUCCESS_CODE) {
                    SharePrefUtil.setUserId(getContext(), data.getUserId());
                    SharePrefUtil.setEmail(getContext(), data.getEmail());
                    SharePrefUtil.setUserName(getContext(), data.getFirstName(), data.getLastName());
                    mSignInViewModelListener.onSignInComplete();
                }
            }
        });
    }

    public interface SignInViewModelListener {
        void onInvalidForm(String errorBody);

        void onSignInComplete();

        void onUnAuthorized();
    }
}
