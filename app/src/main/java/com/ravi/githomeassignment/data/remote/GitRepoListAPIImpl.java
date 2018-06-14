package com.ravi.githomeassignment.data.remote;

import android.util.Log;
import com.ravi.githomeassignment.data.GitRepoModel;
import com.ravi.githomeassignment.data.GitReposModel;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ravi on 13-06-2018.
 *
 * Implementation of the interface {@Link GitRepoListAPI}.
 *
 */

public class GitRepoListAPIImpl implements GitRepoListAPI {

    private GitHubAPI gitHubAPI;

    @Inject
    public GitRepoListAPIImpl(Retrofit retrofit) {
        this.gitHubAPI = retrofit.create(GitHubAPI.class);
    }

    @Override
    public void getAllGitRepoModels(final GitRepoListServiceCallback<List<GitRepoModel>> callback) {

        gitHubAPI.getReposRx("language:java","stars","desc")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<GitReposModel>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Completed" , "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Exception ", ""+e.getMessage());
                    }

                    @Override
                    public void onNext(final GitReposModel gitReposModel) {
                        callback.onLoaded(gitReposModel.getItems());
                    }
                });
    }
}