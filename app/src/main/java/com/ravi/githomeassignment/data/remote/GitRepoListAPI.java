package com.ravi.githomeassignment.data.remote;

import com.ravi.githomeassignment.data.GitRepoModel;

import java.util.List;

/**
 * Created by Ravi on 13-06-2018.
 *
 * CallBack interface for fetching the data from service and passing to repository.
 *
 */

public interface GitRepoListAPI {

    interface GitRepoListServiceCallback<T> {

        void onLoaded(T gitRepoList);
    }

    void getAllGitRepoModels(GitRepoListServiceCallback<List<GitRepoModel>> callback);

}
