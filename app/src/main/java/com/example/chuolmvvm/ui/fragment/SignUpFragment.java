package com.example.chuolmvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chuolmvvm.BR;
import com.example.chuolmvvm.R;
import com.example.chuolmvvm.api.ApiService;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.databinding.FragmentSignupBinding;
import com.example.chuolmvvm.viewmodel.SignUpViewModel;

import org.json.JSONObject;

import javax.inject.Inject;

import timber.log.Timber;

public class SignUpFragment extends AbsBindingFragment<FragmentSignupBinding>
        implements SignUpViewModel.SignUpViewModelListener {
    private static final String TAG = "SignUpFragment";

    private SignUpViewModel mSignUpViewModel;
    @Inject
    ApiService mApiService;
    private SignUpFragmentListener mSignUpFragmentListener;

    public static SignUpFragment newInstance(SignUpFragmentListener signupFragmentListener){
        SignUpFragment signUpFragment = new SignUpFragment();
        signUpFragment.setSignUpFragmentListener(signupFragmentListener);
        return signUpFragment;
    }

    public void setSignUpFragmentListener(SignUpFragmentListener signUpFragmentListener) {
        mSignUpFragmentListener = signUpFragmentListener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getComponent().inject(this);
        mSignUpViewModel = new SignUpViewModel(new UserDataManager(mApiService), getContext(), this);
        setVariable(BR.viewModel, mSignUpViewModel);
    }

    private void performError(String errorJson) {
        JSONObject jsonObject = null;
        String firstNameError = null;
        String lastNameError = null;
        String emailError = null;
        String phoneError = null;
        String passwordError = null;

        try {
            JSONObject firstJson = new JSONObject(errorJson);
            jsonObject = firstJson.getJSONObject("errors");
            firstNameError = jsonObject.has("fname") ? jsonObject.getJSONArray("fname").get(0).toString() : null;
            lastNameError = jsonObject.has("lname") ? jsonObject.getJSONArray("lname").get(0).toString() : null;
            emailError = jsonObject.has("email") ? jsonObject.getJSONArray("email").get(0).toString() : null;
            phoneError = jsonObject.has("phone") ? jsonObject.getJSONArray("phone").get(0).toString() : null;
            passwordError = jsonObject.has("password") ? jsonObject.getJSONArray("password").get(0).toString() : null;

        } catch (Exception e) {
            Timber.e("Execption " + e.getMessage());
        }
        if (jsonObject != null) {
            resetError();

            if (firstNameError != null) {
                getBinding().fnameTil.setError(firstNameError);
            }

            if (lastNameError != null) {
                getBinding().lnameTil.setError(lastNameError);
            }

            if (emailError != null) {
                getBinding().emailTil.setError(emailError);
            }

            if (phoneError != null) {
                getBinding().phoneTil.setError(phoneError);
            }

            if (passwordError != null) {
                getBinding().passwordTil.setError(passwordError);
            }
        }
    }

    private void resetError() {
        getBinding().fnameTil.setError("");
        getBinding().lnameTil.setError("");
        getBinding().emailTil.setError("");
        getBinding().phoneTil.setError("");
        getBinding().passwordTil.setError("");
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_signup;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }

    @Override
    public void onFormInvalid(String signUpError) {
        performError(signUpError);
    }

    @Override
    public void onSignUpClicked() {
        resetError();
    }

    @Override
    public void onSignUpComplete() {
        mSignUpFragmentListener.onSignUpComplete();
    }


    public interface SignUpFragmentListener {
        void onSignUpComplete();
    }
}
