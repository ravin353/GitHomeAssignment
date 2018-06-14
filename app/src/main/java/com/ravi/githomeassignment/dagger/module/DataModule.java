package com.ravi.githomeassignment.dagger.module;

import android.app.Application;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ravi.githomeassignment.data.remote.GitHubAPI;
import com.ravi.githomeassignment.data.remote.GitRepoListAPI;
import com.ravi.githomeassignment.data.remote.GitRepoListAPIImpl;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

/**
 * Created by Ravi on 13-06-2018.
 *
 * Module which will inject all the required objects.
 *
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(GitHubAPI.URI)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    GitRepoListAPI provideGitRepoListAPI(Retrofit retrofit) {
        return new GitRepoListAPIImpl(retrofit);
    }
}
