package com.example.chuolmvvm.model.RequestBody;

import com.google.gson.annotations.SerializedName;

public class UserSignUp {

    @SerializedName("fname")
    private String mFirstName;
    @SerializedName("lname")
    private String mLastName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("password_confirmation")
    private String mConPassword;
    @SerializedName("phone")
    private String mPhone;

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

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getConPassword() {
        return mConPassword;
    }

    public void setConPassword(String conPassword) {
        mConPassword = conPassword;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }
}
