package com.example.chuolmvvm.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.chuolmvvm.api.callback.CallBackHelper;
import com.example.chuolmvvm.api.callback.OnCallBackWithErrorBody;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.model.User;

import io.reactivex.Observable;
import retrofit2.Response;
import timber.log.Timber;

public class PersonalFragmentViewModel extends AbsBaseViewModel {
    private ObservableField<String> mFirstName = new ObservableField<>();
    private ObservableField<String> mLastName = new ObservableField<>();
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mTel = new ObservableField<>();
    private ObservableField<String> mImgUrl = new ObservableField<>();
    private ObservableInt mSaveBtnVisibility = new ObservableInt(View.GONE);
    private ObservableInt mProgessVisibility = new ObservableInt(View.GONE);

    private UserDataManager mUserDataManager;

    public PersonalFragmentViewModel(Context context, UserDataManager userDataManager) {
        super(context);
        mUserDataManager = userDataManager;
        getUser();
    }


    private void getUser() {
        Observable<Response<User>> call = mUserDataManager.getUser();
        CallBackHelper<Response<User>, User> helper = new CallBackHelper<>(getContext(), call);
        addDisposible(helper.execute(new OnCallBackWithErrorBody<User>() {
            @Override
            public void onErrorBody(String errorBody) {
                Timber.e("onErrorBody " + errorBody);
            }

            @Override
            public void onFail(Throwable throwable) {
                Timber.e("onFail " + throwable.getMessage());

            }

            @Override
            public void onComplete(User data, int code) {
                validateUi(data);
            }
        }));
    }

    private void validateUi(User user) {
        mFirstName.set(user.getFirstName());
        mLastName.set(user.getLastName());
        mEmail.set(user.getEmail());
        mTel.set(user.getPhone());
        mImgUrl.set(user.getImgProfile());
    }


    public void onSaveClick() {

    }

    public void onSelectImgClick() {

    }


    public ObservableField<String> getFirstName() {
        return mFirstName;
    }

    public void setFirstName(ObservableField<String> firstName) {
        mFirstName = firstName;
    }

    public ObservableField<String> getLastName() {
        return mLastName;
    }

    public void setLastName(ObservableField<String> lastName) {
        mLastName = lastName;
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public void setEmail(ObservableField<String> email) {
        mEmail = email;
    }

    public ObservableField<String> getTel() {
        return mTel;
    }

    public void setTel(ObservableField<String> tel) {
        mTel = tel;
    }

    public ObservableField<String> getImgUrl() {
        return mImgUrl;
    }

    public void setImgUrl(ObservableField<String> imgUrl) {
        mImgUrl = imgUrl;
    }

    public ObservableInt getSaveBtnVisibility() {
        return mSaveBtnVisibility;
    }

    public void setSaveBtnVisibility(ObservableInt saveBtnVisibility) {
        mSaveBtnVisibility = saveBtnVisibility;
    }

    public ObservableInt getProgessVisibility() {
        return mProgessVisibility;
    }

    public void setProgessVisibility(ObservableInt progessVisibility) {
        mProgessVisibility = progessVisibility;
    }
}
