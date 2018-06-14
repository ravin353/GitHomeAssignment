package com.ravi.githomeassignment.contract;

import android.support.annotation.NonNull;
import com.ravi.githomeassignment.data.GitRepoModel;

import java.util.List;

/**
 * Created by Ravi on 13-06-2018.
 *
 * This specifies the contract between the view and the presenter.
 */

public class GitRepoListDisplayContract {

    public interface View {

        void setProgressIndicator(boolean active);

        void showGitRepoList(List<GitRepoModel> gitRepoModels);

        void showGitRepoDetailUi(GitRepoModel gitRepoModel);
    }

    public interface UserActionsListener {

        void loadGitRepoList(boolean forceUpdate);

        void openGitRepoDetails(@NonNull GitRepoModel requestedGitRepo);
    }
}
