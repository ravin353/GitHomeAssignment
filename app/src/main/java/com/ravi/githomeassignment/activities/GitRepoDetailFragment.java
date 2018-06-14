package com.ravi.githomeassignment.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ravi.githomeassignment.R;
import com.ravi.githomeassignment.contract.GitRepoDetailContract;
import com.ravi.githomeassignment.data.GitRepoModel;

/**
 * Created by Ravi on 13-06-2018.
 */

public class GitRepoDetailFragment extends Fragment implements GitRepoDetailContract.View{

    public static final String GITREPOMODEL = "gitrepomodel";

    View progressBar;

    TextView gitRepoDetail;

    private GitRepoDetailContract.UserActionsListener mActionListener;

    public static GitRepoDetailFragment getInstance(GitRepoModel gitRepoModel) {
        GitRepoDetailFragment fragment = new GitRepoDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GITREPOMODEL, gitRepoModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionListener = new GitRepoDetailsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_git_repo_detail, container, false);

        progressBar = root.findViewById(R.id.progressBar);
        gitRepoDetail = (TextView) root.findViewById(R.id.repo_detail_view);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        GitRepoModel model = (GitRepoModel) getArguments().getSerializable(GITREPOMODEL);
        mActionListener.openGitRepo(model);
    }

    @Override
    public void setProgressIndicator(boolean active) {
            progressBar.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMissingNote() {
        gitRepoDetail.setText("No Data to display");
    }

    @Override
    public void showGitRepoDetail(GitRepoModel model) {

        StringBuffer gitRepoText = new StringBuffer();
        gitRepoText.append("Repo Name : "+model.getName()).append("\n");
        gitRepoText.append("GitHub URL : "+model.getHtmlUrl()).append("\n");
        gitRepoText.append("\t\t\t\t\t\tOwner :").append("\n");
        gitRepoText.append(model.getOwner().toString()).append("\n");
        gitRepoText.append("\t\t\t\t\t\tOthers :").append("\n");
        gitRepoText.append(model.toString());

        gitRepoDetail.setText(gitRepoText.toString());

    }
}
