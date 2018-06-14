package com.ravi.githomeassignment.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ravi.githomeassignment.GitRepoApplication;
import com.ravi.githomeassignment.R;
import com.ravi.githomeassignment.contract.GitRepoListDisplayContract;
import com.ravi.githomeassignment.data.GitRepoModel;
import com.ravi.githomeassignment.data.remote.GitRepoListRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 13-06-2018.
 *
 * Display Grid of {@Link GitRepoModel}s
 *
 */

public class GitRepoListFragment extends Fragment implements GitRepoListDisplayContract.View  {

    public static final String TAG = "DaggerApplication";

    @Inject
    GitRepoListRepository gitRepoListRepository;

    GitRepoAdapter mListAdapter;
    private GitRepoListDisplayContract.UserActionsListener mActionsListener;

    public GitRepoListFragment() {
        // Requires empty public constructor
    }

    public static GitRepoListFragment newInstance() {
        return new GitRepoListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GitRepoApplication.getAppComponent().inject(this);
        mActionsListener = new GitRepoListDisplayPresenter(this, gitRepoListRepository);
        mListAdapter = new GitRepoAdapter(new ArrayList<GitRepoModel>(0), mItemListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadGitRepoList(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_git_repo_list, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.repo_list);
        recyclerView.setAdapter(mListAdapter);

        int numColumns = getContext().getResources().getInteger(R.integer.num_repo_columns);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.loadGitRepoList(true);
            }
        });
        return root;
    }

    @Override
    public void setProgressIndicator(final boolean active) {
        Log.d(TAG, "set progressIndicator is called");

        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showGitRepoList(List<GitRepoModel> gitRepoModels) {
        Log.d(TAG, "List of Repo is called and size is "+gitRepoModels.size());
        mListAdapter.replaceData(gitRepoModels);
    }

    @Override
    public void showGitRepoDetailUi(GitRepoModel gitRepoModel) {
        Intent detailIntent = new Intent(getActivity(), GitRepoDetailActivity.class);
        detailIntent.putExtra(GitRepoDetailFragment.GITREPOMODEL,gitRepoModel);
        getActivity().startActivity(detailIntent);
    }

    /**
     * Listener for clicks on git repo in the RecyclerView.
     */
    RepoItemListener mItemListener = new RepoItemListener() {
        @Override
        public void onRepoClick(GitRepoModel clickedRepo) {
            mActionsListener.openGitRepoDetails(clickedRepo);
        }
    };

    private static class GitRepoAdapter extends RecyclerView.Adapter<GitRepoAdapter.ViewHolder> {

        private List<GitRepoModel> mGitRepoList;
            private RepoItemListener mItemListener;

        public GitRepoAdapter(List<GitRepoModel> mGitRepoList, RepoItemListener itemListener) {
            setList(mGitRepoList);
            mItemListener = itemListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.item_repo, parent, false);

            return new ViewHolder(noteView, mItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            GitRepoModel model = mGitRepoList.get(position);

            viewHolder.title.setText("Repo Name : "+model.getName());
            viewHolder.description.setText("Github URL :"+model.getHtmlUrl());
        }

        public void replaceData(List<GitRepoModel> mGitRepoList) {
            setList(mGitRepoList);
            notifyDataSetChanged();
        }

        private void setList(List<GitRepoModel> mGitRepoList) {
            this.mGitRepoList = mGitRepoList;
        }

        @Override
        public int getItemCount() {
            return mGitRepoList.size();
        }

        public GitRepoModel getItem(int position) {
            return mGitRepoList.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView title;

            public TextView description;
            private RepoItemListener mItemListener;

            public ViewHolder(View itemView, RepoItemListener listener) {
                super(itemView);
                mItemListener = listener;
                title = (TextView) itemView.findViewById(R.id.repo_detail_title);
                description = (TextView) itemView.findViewById(R.id.repo_detail_description);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                GitRepoModel model = getItem(position);
                mItemListener.onRepoClick(model);

            }
        }
    }

    public interface RepoItemListener {
        void onRepoClick(GitRepoModel clickedRepo);
    }
}
