package com.omri.usersmvp.data.ui.login;

import android.text.TextUtils;

import com.omri.usersmvp.R;
import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginModel.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        // Check for a valid password, if the user entered one
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            loginView.setPasswordError(R.string.error_invalid_password);
            return;
        }

        if(TextUtils.isEmpty(password)){
            loginView.setPasswordError(R.string.error_field_required);
            return;
        }

        // Check for a valid username
        if (TextUtils.isEmpty(username)) {
            loginView.setUsernameError(R.string.error_field_required);
            return;
        } else if (!isEmailValid(username)) {
            loginView.setUsernameError(R.string.error_invalid_email);
            return;
        }

        loginView.showProgress(true);
        loginModel.login(username, password, this);
    }

    @Override
    public void onCanceled() {
        loginView.showProgress(false);
    }

    @Override
    public void onPasswordError() {
        loginView.showProgress(false);
        loginView.setPasswordError(R.string.error_invalid_password);
    }

    @Override
    public void onSuccess(User user) {
        loginView.showProgress(false);
        loginView.successAction();
        loginView.openHomeActivity(user);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}

