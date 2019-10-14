package com.example.chuolmvvm.model;

import com.google.gson.annotations.SerializedName;

public class User {
    public static final String userId = "id";
    public static final String fName = "fname";
    public static final String lName = "lname";
    public static final String phone = "phone";
    public static final String email = "email";
    public static final String imgLoc = "profile_img";

    @SerializedName(userId)
    private String mUserId;
    @SerializedName(fName)
    private String mFirstName;
    @SerializedName(lName)
    private String mLastName;
    @SerializedName(email)
    private String mEmail;
    @SerializedName(phone)
    private String mPhone;
    @SerializedName(imgLoc)
    private String mImgProfile;

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getImgProfile() {
        return mImgProfile;
    }

    public void setImgProfile(String imgProfile) {
        mImgProfile = imgProfile;
    }

    public String getUsername(){
        return mFirstName + " " + mLastName;
    }

}
