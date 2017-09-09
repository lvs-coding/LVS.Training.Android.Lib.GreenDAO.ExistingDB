package com.example.greendaoexistingdb;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.greendaoexistingdb.data.local.database.model.DaoMaster;
import com.example.greendaoexistingdb.data.local.database.model.DaoSession;
import com.example.greendaoexistingdb.data.local.database.utils.DatabaseOpenHelper;
import com.facebook.stetho.Stetho;

public class App extends Application {
    DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseOpenHelper helper = new DatabaseOpenHelper(this, "greendao_training.db", null);
        helper.createDataBase();
        SQLiteDatabase db = helper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        if (BuildConfig.DEBUG) {
            //Empty cache
            daoSession.clear();

            initializeStetho();
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void initializeStetho() {
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));

        // Enable command line interface
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this));

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }
}
