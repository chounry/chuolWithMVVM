package com.example.chuolmvvm.api;

import com.example.chuolmvvm.model.RequestBody.UserSignUp;
import com.example.chuolmvvm.model.SignUpResponse;
import com.example.chuolmvvm.model.User;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth/register")
    Observable<Response<SignUpResponse>> signUp(@Body UserSignUp userSignup);

    @POST("auth/get-user")
    Observable<Response<User>> getUser();

}
