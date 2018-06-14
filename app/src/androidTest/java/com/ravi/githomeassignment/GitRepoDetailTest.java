package com.ravi.githomeassignment;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.ravi.githomeassignment.activities.GitRepoDetailActivity;
import com.ravi.githomeassignment.activities.GitRepoDetailFragment;
import com.ravi.githomeassignment.data.GitRepoModel;
import com.ravi.githomeassignment.data.Owner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Ravi on 27-05-2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class GitRepoDetailTest {

    @Rule
    public ActivityTestRule<GitRepoDetailActivity> mGitRepoDetailActivityTestRule =
            new ActivityTestRule<>(GitRepoDetailActivity.class, true, false);

    @Before
    public void intentWithStubbedGitRepo() {

        GitRepoModel model = new GitRepoModel();
        model.setName(TEST_NAME);

        Owner owner = new Owner();
        model.setOwner(owner);

        Intent startIntent = new Intent();
        startIntent.putExtra(GitRepoDetailFragment.GITREPOMODEL, model);
        mGitRepoDetailActivityTestRule.launchActivity(startIntent);

        registerIdlingResource();
    }

    public static final String TEST_NAME = "Ravi";

    @Test
    public void gitRepoDetails_DisplayedInUi() throws Exception {
        onView(withId(R.id.repo_detail_view)).check(matches(withText(containsString(TEST_NAME))));
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                mGitRepoDetailActivityTestRule.getActivity().getCountingIdlingResource());
    }

    /**
     * Convenience method to register an IdlingResources with Espresso. IdlingResource resource is
     * a great way to tell Espresso when your app is in an idle state. This helps Espresso to
     * synchronize your test actions, which makes tests significantly more reliable.
     */
    private void registerIdlingResource() {
        Espresso.registerIdlingResources(
                mGitRepoDetailActivityTestRule.getActivity().getCountingIdlingResource());
    }

}
