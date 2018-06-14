package com.ravi.githomeassignment.contract;

import android.support.annotation.Nullable;
import com.ravi.githomeassignment.data.GitRepoModel;

/**
 * Created by Ravi on 13-06-2018.
 *
 * This specifies the contract between the view and the presenter.
 */

public class GitRepoDetailContract {

    public interface View {
        void setProgressIndicator(boolean active);
        void showMissingNote();
        void showGitRepoDetail(GitRepoModel model);
    }

    public interface UserActionsListener {
        void openGitRepo(@Nullable GitRepoModel gitRepoModel);
    }
}
