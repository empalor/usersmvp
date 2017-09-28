package com.omri.usersmvp.data.db;

import android.content.Context;
import android.support.annotation.Nullable;

import com.omri.usersmvp.data.App;
import com.omri.usersmvp.data.model.DaoSession;
import com.omri.usersmvp.data.model.User;
import com.omri.usersmvp.data.model.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public class AppDbHelper implements DbHelper {
    private DaoSession mDaoSession;

    private static AppDbHelper sInstance;

    public static synchronized AppDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppDbHelper(context);
        }

        return sInstance;
    }

    private AppDbHelper(Context context) {
        mDaoSession = ((App) context).getDaoSession();
    }

    @Override
    public void insertUser(User user) {
        mDaoSession.getUserDao().insert(user);
    }

    @Override
    public @Nullable
    User getUser(String email) {
        QueryBuilder queryBuilder = mDaoSession.getUserDao().queryBuilder().where(UserDao.Properties.Email.eq(email));
        List<User> list = queryBuilder.list();
        return (list.size() == 0) ? null : list.get(0);
    }

    @Override
    public void deleteUser(User user) {
        mDaoSession.getUserDao().delete(user);
    }

    @Override
    public void updateUser(User user) {
        mDaoSession.getUserDao().update(user);
    }
}

