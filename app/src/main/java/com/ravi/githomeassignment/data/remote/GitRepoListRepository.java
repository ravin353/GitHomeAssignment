package com.ravi.githomeassignment.data.remote;

import android.support.annotation.NonNull;
import com.ravi.githomeassignment.data.GitRepoModel;

import java.util.List;

/**
 * Created by Ravi on 13-06-2018.
 *
 * CallBack interface to update the data to the View.
 *
 */

public interface GitRepoListRepository {

    interface LoadGitRepoListCallback {

        void onPostListLoaded(List<GitRepoModel> gitRepoModels);
    }

    interface GetGitRepoCallback {

        void onLoaded(GitRepoModel note);
    }

    void getGitRepoList(boolean forceUpdated, @NonNull LoadGitRepoListCallback callback);

    void getGitRepo(@NonNull int gitRepoId, @NonNull GetGitRepoCallback callback);
}
