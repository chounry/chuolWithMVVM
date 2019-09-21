package com.example.chuolmvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chuolmvvm.BR;
import com.example.chuolmvvm.R;
import com.example.chuolmvvm.api.ApiService;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.databinding.FragmentSigninBinding;
import com.example.chuolmvvm.viewmodel.SignInViewModel;

import org.json.JSONObject;

import javax.inject.Inject;

import timber.log.Timber;

public class SignInFragment extends AbsBindingFragment<FragmentSigninBinding> implements
        SignInViewModel.SignInViewModelListener {

    private static final String TAG = "SignInFragment";
    private SignInViewModel mSignInViewModel;
    private SignInFragmentListener mSignInFragmentListener;
    @Inject
    ApiService mApiService;
    private UserDataManager mUserDataManager;

    public static SignInFragment newInstance(SignInFragmentListener signInFragmentListener) {
        SignInFragment signInFragment = new SignInFragment();
        signInFragment.setSignInFragmentListener(signInFragmentListener);
        return signInFragment;
    }

    public void setSignInFragmentListener(SignInFragmentListener signInFragmentListener) {
        mSignInFragmentListener = signInFragmentListener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getComponent().inject(this);
        mUserDataManager = new UserDataManager(mApiService);
        mSignInViewModel = new SignInViewModel(getContext(),
                mUserDataManager,
                this
        );
        setVariable(BR.viewModel, mSignInViewModel);
    }


    private void performError(String errorJson) {
        JSONObject jsonObject = null;
        String emailError = null;
        String passwordError = null;

        try {
            JSONObject firstJson = new JSONObject(errorJson);
            jsonObject = firstJson.getJSONObject("errors");
            emailError = jsonObject.has("email") ? jsonObject.getJSONArray("email").get(0).toString() : null;
            passwordError = jsonObject.has("password") ? jsonObject.getJSONArray("password").get(0).toString() : null;

        } catch (Exception e) {
            Timber.e("Execption " + e.getMessage());
        }
        if (jsonObject != null) {
            resetError();

            if (emailError != null) {
                getBinding().emailTil.setError(emailError);
            }
        }
        if (passwordError != null) {
            getBinding().passwordTil.setError(passwordError);
        }
    }

    private void resetError() {
        getBinding().emailTil.setError("");
        getBinding().passwordTil.setError("");
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_signin;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }

    @Override
    public void onInvalidForm(String errorBody) {
        performError(errorBody);
    }

    @Override
    public void onSignInComplete() {
        mSignInFragmentListener.onSignInComplete();
    }

    @Override
    public void onUnAuthorized() {
        Toast.makeText(getContext(), "Incorrect credentials", Toast.LENGTH_SHORT).show();
    }

    public interface SignInFragmentListener {
        void onSignInComplete();
    }
}
