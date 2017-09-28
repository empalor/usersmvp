package com.omri.usersmvp.data.ui.home;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.omri.usersmvp.data.App;
import com.omri.usersmvp.data.db.AppDbHelper;
import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public class HomeModelImpl implements HomeModel {
    private OnLogoutFinishedListener logoutListener;
    private OnFetchingFinishedListener fetchingListener;

    @Override
    public void logout(User user, OnLogoutFinishedListener listener) {
        this.logoutListener = listener;

        new UserLogoutTask(user).execute((Void) null);
    }

    @Override
    public void fetch(User user, OnFetchingFinishedListener listener) {
        this.fetchingListener = listener;

        new LookUpUserTask(user).execute((Void) null);
    }

    /**
     * Gets a user to lookup in db
     */
    public class LookUpUserTask extends AsyncTask<Void, Void, User> {

        private final User mUser;

        LookUpUserTask(User user) {
            mUser = user;
        }

        @Override
        protected @Nullable
        User doInBackground(Void... params) {

            return AppDbHelper.getInstance(App.getContext()).getUser(mUser.getEmail());
        }

        @Override
        protected void onPostExecute(final User user) {
            if (user != null) {
                fetchingListener.onFetchingSuccess(user);
            } else {
                fetchingListener.onFetchingFailure();
            }
        }
    }

    /**
     * Deletes received user from db
     */
    public class UserLogoutTask extends AsyncTask<Void, Void, Boolean> {

        private final User mUser;

        UserLogoutTask(User user) {
            mUser = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            AppDbHelper.getInstance(App.getContext()).deleteUser(mUser);

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                logoutListener.onLogoutSuccess();
            } else {
                logoutListener.onLogoutFailure();
            }
        }
    }
}

