package com.omri.usersmvp.data;

import android.app.Application;
import android.content.Context;

import com.omri.usersmvp.data.model.DaoMaster;
import com.omri.usersmvp.data.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by omricohen294 on 28/09/2017.
 */

public class App extends Application {

    private DaoSession daoSession;
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}

