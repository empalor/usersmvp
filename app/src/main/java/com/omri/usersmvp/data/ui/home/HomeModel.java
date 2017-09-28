package com.omri.usersmvp.data.ui.home;

import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public interface HomeModel {
    interface OnLogoutFinishedListener {

        void onLogoutSuccess();

        void onLogoutFailure();
    }

    interface OnFetchingFinishedListener {

        void onFetchingSuccess(User user);

        void onFetchingFailure();
    }

    void logout(User user, OnLogoutFinishedListener listener);

    void fetch(User user, OnFetchingFinishedListener listener);
}
