package com.ravi.githomeassignment.activities;

import android.support.annotation.NonNull;
import com.ravi.githomeassignment.contract.GitRepoListDisplayContract;
import com.ravi.githomeassignment.data.GitRepoModel;
import com.ravi.githomeassignment.data.remote.GitRepoListRepository;

import java.util.List;


/**
 * Created by Ravi on 13-06-2018.
 *
 * Listens to user actions from the UI ({@link GitRepoListFragment}), retrieves the data and updates the
 * UI as required.
 *
 */

public class GitRepoListDisplayPresenter implements GitRepoListDisplayContract.UserActionsListener {

    private GitRepoListDisplayContract.View gitRepoListView;
    private GitRepoListRepository gitRepoListRepository;

    public GitRepoListDisplayPresenter(GitRepoListDisplayContract.View gitRepoListView, GitRepoListRepository gitRepoListRepository) {
        this.gitRepoListView = gitRepoListView;
        this.gitRepoListRepository = gitRepoListRepository;
    }

    @Override
    public void loadGitRepoList(boolean forceUpdate) {
        gitRepoListView.setProgressIndicator(true);

        gitRepoListRepository.getGitRepoList(forceUpdate,new GitRepoListRepository.LoadGitRepoListCallback() {
            @Override
            public void onPostListLoaded(List<GitRepoModel> gitRepoModels) {
                gitRepoListView.setProgressIndicator(false);
                gitRepoListView.showGitRepoList(gitRepoModels);
            }
        });

    }

    @Override
    public void openGitRepoDetails(@NonNull GitRepoModel gitRepoModel) {
        gitRepoListView.showGitRepoDetailUi(gitRepoModel);
    }
}
