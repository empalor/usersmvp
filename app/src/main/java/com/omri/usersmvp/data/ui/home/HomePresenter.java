package com.omri.usersmvp.data.ui.home;

import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public interface HomePresenter {
    void retrieveUser(User user);

    void removeAndLogout(User user);
}
