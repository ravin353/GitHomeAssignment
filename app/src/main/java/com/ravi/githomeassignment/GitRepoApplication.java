package com.ravi.githomeassignment;

import android.app.Application;

import com.ravi.githomeassignment.dagger.component.AppComponent;
import com.ravi.githomeassignment.dagger.component.DaggerAppComponent;
import com.ravi.githomeassignment.dagger.module.AppModule;
import com.ravi.githomeassignment.dagger.module.DataModule;
import com.ravi.githomeassignment.dagger.module.ServerModule;

/**
 * Created by Ravi on 13-06-2018.
 */

public class GitRepoApplication extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .serverModule(new ServerModule())
                .build();
    }


    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
