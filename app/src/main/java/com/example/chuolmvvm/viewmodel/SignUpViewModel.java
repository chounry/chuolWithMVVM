package com.example.chuolmvvm.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.chuolmvvm.api.callback.CallBackHelper;
import com.example.chuolmvvm.api.callback.OnCallBackWithErrorBody;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.config.Constants;
import com.example.chuolmvvm.model.RequestBody.UserSignUp;
import com.example.chuolmvvm.model.SignUpResponse;
import com.example.chuolmvvm.model.User;
import com.example.chuolmvvm.utils.SharePrefUtil;

import io.reactivex.Observable;
import retrofit2.Response;

public class SignUpViewModel extends AbsBaseViewModel {
    private ObservableField<String> mFirstName = new ObservableField<>();
    private ObservableField<String> mLastName = new ObservableField<>();
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mPassword = new ObservableField<>();
    private ObservableField<String> mConPassword = new ObservableField<>();
    private ObservableField<String> mPhoneNumber = new ObservableField<>();
    private ObservableInt mSignUpBtnVisibility = new ObservableInt();
    private ObservableInt mLoadingVisibility = new ObservableInt(View.GONE);
    private UserDataManager mUserDataManager;
    private SignUpViewModelListener mSignUpViewModelListener;

    public SignUpViewModel(UserDataManager userDataManager, Context context, SignUpViewModelListener signupViewModelListener) {
        super(context);
        mUserDataManager = userDataManager;
        mSignUpViewModelListener = signupViewModelListener;
    }

    public ObservableField<String> getPhoneNumber() {
        return mPhoneNumber;
    }

    public ObservableField<String> getFirstName() {
        return mFirstName;
    }

    public ObservableField<String> getLastName() {
        return mLastName;
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public ObservableField<String> getPassword() {
        return mPassword;
    }

    public ObservableField<String> getConPassword() {
        return mConPassword;
    }

    public ObservableInt getSignUpBtnVisibility() {
        return mSignUpBtnVisibility;
    }

    public ObservableInt getLoadingVisibility() {
        return mLoadingVisibility;
    }

    private void showLoading() {
        mLoadingVisibility.set(View.VISIBLE);
        mSignUpBtnVisibility.set(View.GONE);
    }

    private void hideLoading() {
        mLoadingVisibility.set(View.GONE);
        mSignUpBtnVisibility.set(View.VISIBLE);
    }

    public void onSignUpClick() {
        mSignUpViewModelListener.onSignUpClicked();
        showLoading();
        UserSignUp userSignup = new UserSignUp();
        userSignup.setEmail(mEmail.get());
        userSignup.setFirstName(mFirstName.get());
        userSignup.setLastName(mLastName.get());
        userSignup.setPhone(mPhoneNumber.get());
        userSignup.setConPassword(mConPassword.get());
        userSignup.setPassword(mPassword.get());

        Observable<Response<SignUpResponse>> service = mUserDataManager.getUserSignUp(userSignup);
        CallBackHelper<Response<SignUpResponse>, SignUpResponse> helper = new CallBackHelper<>(getContext(), service);
        addDisposible(helper.execute(new OnCallBackWithErrorBody<SignUpResponse>() {
            @Override
            public void onErrorBody(String errorBody) {
                mSignUpViewModelListener.onFormInvalid(errorBody);
                hideLoading();
            }

            @Override
            public void onFail(Throwable throwable) {
                hideLoading();

            }

            @Override
            public void onComplete(SignUpResponse data, int code) {
                if (code == Constants.SUCCESS_CODE) {
                    SharePrefUtil.saveToken(getContext(), data.getAccessToken(), data.getRefreshToken());
                    requestUser();
                }
                hideLoading();
            }
        }));
    }

    private void requestUser() {
        Observable<Response<User>> service = mUserDataManager.getUser();
        CallBackHelper<Response<User>, User> helper = new CallBackHelper<>(getContext(), service);
        helper.execute(new OnCallBackWithErrorBody<User>() {
            @Override
            public void onErrorBody(String errorBody) {

            }

            @Override
            public void onFail(Throwable throwable) {

            }

            @Override
            public void onComplete(User data, int code) {
                SharePrefUtil.setUserId(getContext(), data.getUserId());
                mSignUpViewModelListener.onSignUpComplete();
            }
        });
    }

    public interface SignUpViewModelListener {
        void onFormInvalid(String signUpError);

        void onSignUpClicked();

        void onSignUpComplete();
    }
}
