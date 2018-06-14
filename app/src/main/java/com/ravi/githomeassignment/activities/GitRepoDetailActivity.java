package com.ravi.githomeassignment.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.ravi.githomeassignment.R;
import com.ravi.githomeassignment.data.GitRepoModel;
import com.ravi.githomeassignment.util.EspressoIdlingResource;

/**
 * Created by Ravi on 13-06-2018.
 */

public class GitRepoDetailActivity extends AppCompatActivity {

    GitRepoModel gitRepoModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent() == null)
            throw new IllegalStateException("Git Repo Object is not set");

        gitRepoModel = (GitRepoModel)getIntent().getSerializableExtra(GitRepoDetailFragment.GITREPOMODEL);

        Log.e("IntentValues", "Value : "+gitRepoModel.toString());
        initFragment(GitRepoDetailFragment.getInstance(gitRepoModel));
    }

    private void initFragment(Fragment detailFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame_content, detailFragment);
        transaction.commit();
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

}
