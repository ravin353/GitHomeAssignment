package com.ravi.githomeassignment.dagger.module;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Ravi on 13-06-2018.
 *
 * Module which can inject the application object.
 *
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
