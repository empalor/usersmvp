package com.omri.usersmvp.data.ui.login;

import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public interface LoginView {

    void showProgress(boolean showProgress);

    void setUsernameError(int messageResId);

    void setPasswordError(int messageResId);

    void successAction();

    void openHomeActivity(User user);
}
