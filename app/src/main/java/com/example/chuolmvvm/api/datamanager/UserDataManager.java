package com.example.chuolmvvm.api.datamanager;

import com.example.chuolmvvm.api.ApiService;
import com.example.chuolmvvm.model.RequestBody.UserSignUp;
import com.example.chuolmvvm.model.SignUpResponse;
import com.example.chuolmvvm.model.User;

import io.reactivex.Observable;
import retrofit2.Response;

public class UserDataManager {

    private ApiService mApiService;

    public UserDataManager(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<Response<SignUpResponse>> getUserSignUp(UserSignUp signUp){
        return mApiService.signUp(signUp);
    }

    public Observable<Response<User>> getUser(){
        return mApiService.getUser();
    }
}
