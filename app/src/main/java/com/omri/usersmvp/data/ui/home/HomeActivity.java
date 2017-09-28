package com.omri.usersmvp.data.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.omri.usersmvp.R;
import com.omri.usersmvp.data.App;
import com.omri.usersmvp.data.model.User;

public class HomeActivity extends AppCompatActivity implements HomeView{
    // Constants
    public static final String KEY_EXTRA_USER = "KEY_EXTRA_USER";
    public static final String ACTION_SUCCESS_FETCH = "ACTION_SUCCESS_FETCH";
    public static final String ACTION_SUCCESS_LOGOUT = "ACTION_SUCCESS_LOGOUT";

    // Members
    private HomePresenter mHomePresenter;

    // Views
    private TextView mUserTextView;
    private Button mButtonLogOut;
    private View mProgressView;
    private View mHomeFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mUserTextView = (TextView) findViewById(R.id.user_details_textView);
        mButtonLogOut = (Button) findViewById(R.id.log_out_button);

        mButtonLogOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                attemptLogout();
            }
        });

        mHomeFormView = findViewById(R.id.home_form);
        mProgressView = findViewById(R.id.fetching_progress);

        mHomePresenter = new HomePresenterImpl(this);

        // Get user sent from LoginActivity
        Intent intent = this.getIntent();
        User userToDisplay = (User) intent.getSerializableExtra(KEY_EXTRA_USER);

        // Retrieve user from db and display it onto text view
        attemptDisplay(userToDisplay);
    }

    /**
     * Attempts to get user from db for display
     * If user does not exist in db, error is presented
     */
    private void attemptDisplay(User userToDisplay) {
        // Reset error
        mUserTextView.setError(null);

        mHomePresenter.retrieveUser(userToDisplay);
    }

    /**
     * Attempts to remove the user from db
     */
    private void attemptLogout() {
        Intent intent = this.getIntent();
        User userToRemove = (User) intent.getSerializableExtra(KEY_EXTRA_USER);
        mHomePresenter.removeAndLogout(userToRemove);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @Override
    public void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mHomeFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mHomeFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                mHomeFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void successAction(String action) {
        switch (action) {
            case ACTION_SUCCESS_FETCH:
                Toast.makeText(HomeActivity.this, R.string.string_success_fetch, Toast.LENGTH_SHORT).show();
                break;
            case ACTION_SUCCESS_LOGOUT:
                Toast.makeText(App.getContext(), R.string.string_success_logout, Toast.LENGTH_SHORT).show();
                finish();
        }
    }

    @Override
    public void displayUser(User user) {
        mUserTextView.setText(user.toString());
    }

    @Override
    public void setFetchingError(int messageResId) {
        mUserTextView.setError(getString(messageResId));
        mUserTextView.requestFocus();
    }

    @Override
    public void setLogoutError(int messageResId) {
        Toast.makeText(App.getContext(), getString(messageResId), Toast.LENGTH_SHORT).show();
    }
}
