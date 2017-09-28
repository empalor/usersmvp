package com.omri.usersmvp.data.ui.login;

import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public interface LoginModel {
    interface OnLoginFinishedListener {

        void onCanceled();

        void onPasswordError();

        void onSuccess(User user);
    }

    void login(String username, String password, OnLoginFinishedListener listener);
}

