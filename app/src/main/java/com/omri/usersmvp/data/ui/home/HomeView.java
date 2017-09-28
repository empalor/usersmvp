package com.omri.usersmvp.data.ui.home;

import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public interface HomeView {
    void showProgress(boolean showProgress);

    void successAction(String action);

    void displayUser(User user);

    void setFetchingError(int messageResId);

    void setLogoutError(int messageResId);
}
