package com.ravi.githomeassignment.data.remote;

import android.support.annotation.NonNull;
import com.ravi.githomeassignment.GitRepoApplication;
import com.ravi.githomeassignment.data.GitRepoModel;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ravi on 13-06-2018.
 *
 *
 * Implementation of the interface repository {@Link GitRepoListRepository}.
 */

public class ServerAPIRepository implements GitRepoListRepository {

    private List<GitRepoModel> gitRepoModels = null;

    @Inject
    public GitRepoListAPI gitRepoListAPI;

    public ServerAPIRepository() {
        //this.gitRepoListAPI = gitRepoListAPI;
        GitRepoApplication.getAppComponent().inject(this);
    }

    @Override
    public void getGitRepoList(boolean forceUpdate,@NonNull final LoadGitRepoListCallback callback) {

        if(gitRepoModels != null && !forceUpdate){
            callback.onPostListLoaded(gitRepoModels);
            return;
        }

        gitRepoListAPI.getAllGitRepoModels(new GitRepoListAPI.GitRepoListServiceCallback<List<GitRepoModel>>() {
            @Override
            public void onLoaded(List<GitRepoModel> gitRepoList) {
                gitRepoModels = gitRepoList;
                callback.onPostListLoaded(gitRepoList);
                //Log.d("Lists", ""+gitRepoList);
            }
        });
    }

    @Override
    public void getGitRepo(@NonNull int gitRepoId, @NonNull GetGitRepoCallback callback) {
        if(gitRepoModels == null)
            throw new IllegalStateException("GitHub Repos are not loaded");

        callback.onLoaded(gitRepoModels.get(gitRepoId));
    }
}
