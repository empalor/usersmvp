package com.omri.usersmvp.data.ui.home;

import com.omri.usersmvp.R;
import com.omri.usersmvp.data.model.User;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public class HomePresenterImpl implements HomePresenter, HomeModel.OnLogoutFinishedListener, HomeModel.OnFetchingFinishedListener {

    private HomeView homeView;
    private HomeModel homeModel;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
        this.homeModel = new HomeModelImpl();
    }

    @Override
    public void retrieveUser(User user) {
        homeView.showProgress(true);
        homeModel.fetch(user, this);
    }

    @Override
    public void removeAndLogout(User user) {
        homeModel.logout(user, this);
    }

    @Override
    public void onFetchingFailure() {
        homeView.showProgress(false);
        homeView.setFetchingError(R.string.error_fetching_user);
    }

    @Override
    public void onFetchingSuccess(User user) {
        homeView.successAction(HomeActivity.ACTION_SUCCESS_FETCH);
        homeView.displayUser(user);
        homeView.showProgress(false);
    }

    @Override
    public void onLogoutSuccess() {
        homeView.successAction(HomeActivity.ACTION_SUCCESS_LOGOUT);
    }

    @Override
    public void onLogoutFailure() {
        homeView.setLogoutError(R.string.error_loging_out);
    }
}

