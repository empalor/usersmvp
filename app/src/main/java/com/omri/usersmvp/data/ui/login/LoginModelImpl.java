package com.omri.usersmvp.data.ui.login;

import android.os.AsyncTask;

import com.omri.usersmvp.data.App;
import com.omri.usersmvp.data.db.AppDbHelper;
import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public class LoginModelImpl implements LoginModel {
    private OnLoginFinishedListener listener;

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {
        this.listener = listener;

        new UserLoginTask(username, password).execute((Void) null);
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            // Decide whether to update or insert the user to db
            User user = AppDbHelper.getInstance(App.getContext()).getUser(mEmail);

            // User was not found in db, create and insert a new one
            if (user == null) {
                AppDbHelper.getInstance(App.getContext()).insertUser(new User(mEmail, mPassword));
            } else { // User was found, update its password
                AppDbHelper.getInstance(App.getContext()).updateUser(user);
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                listener.onSuccess(new User(mEmail, mPassword));
            } else {
                listener.onPasswordError();
            }
        }

        @Override
        protected void onCancelled() {
            listener.onCanceled();
        }
    }
}
