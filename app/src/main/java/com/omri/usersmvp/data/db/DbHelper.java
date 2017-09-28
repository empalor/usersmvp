package com.omri.usersmvp.data.db;

import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public interface DbHelper {
    void insertUser(final User user);

    User getUser(final String email);

    void deleteUser(final User user);

    void updateUser(final User user);

}
