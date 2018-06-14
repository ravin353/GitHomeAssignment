package com.ravi.githomeassignment.activities;

import android.support.annotation.Nullable;
import com.ravi.githomeassignment.contract.GitRepoDetailContract;
import com.ravi.githomeassignment.data.GitRepoModel;

/**
 * Created by Ravi on 13-06-2018.
 *
 * Listens to user actions from the UI ({@link GitRepoDetailFragment}), retrieves the data and updates the
 * UI as required.
 */

public class GitRepoDetailsPresenter implements GitRepoDetailContract.UserActionsListener {

    private GitRepoDetailContract.View mGitRepoDetailsView;

    public GitRepoDetailsPresenter(GitRepoDetailContract.View mGitRepoDetailsView) {
        this.mGitRepoDetailsView = mGitRepoDetailsView;
    }

    @Override
    public void openGitRepo(@Nullable GitRepoModel gitRepoModel) {

        if(gitRepoModel == null) {
            mGitRepoDetailsView.showMissingNote();
            return;
        }
        mGitRepoDetailsView.setProgressIndicator(true);
        showGitRepoDetails(gitRepoModel);
        mGitRepoDetailsView.setProgressIndicator(false);
    }

    private void showGitRepoDetails(GitRepoModel gitRepoModel) {
        mGitRepoDetailsView.showGitRepoDetail(gitRepoModel);
    }
}
