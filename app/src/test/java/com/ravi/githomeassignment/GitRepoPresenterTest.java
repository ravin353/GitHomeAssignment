package com.ravi.githomeassignment;

import com.ravi.githomeassignment.activities.GitRepoListDisplayPresenter;
import com.ravi.githomeassignment.contract.GitRepoListDisplayContract;
import com.ravi.githomeassignment.data.GitRepoModel;
import com.ravi.githomeassignment.data.remote.GitRepoListRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.verify;

/**
 * Created by Ravi on 13-06-2018.
 */

public class GitRepoPresenterTest {

    private static List<GitRepoModel> TESTREPO = new ArrayList<>();

    static {
        GitRepoModel model = new GitRepoModel();
        model.setId(1);
        model.setName("Test Name");
        TESTREPO.add(model);
    }

    @Mock
    GitRepoListRepository gitRepoListRepository;

    @Mock
    GitRepoListDisplayContract.View gitRepoListView;

    @Captor
    private ArgumentCaptor<GitRepoListRepository.LoadGitRepoListCallback> mLoadGitRepoCallbackCaptor;

    private GitRepoListDisplayPresenter mGitRepoListPresenter;

    @Before
    public void setupNotesPresenter() {

        MockitoAnnotations.initMocks(this);

        mGitRepoListPresenter = new GitRepoListDisplayPresenter(gitRepoListView, gitRepoListRepository);
    }

    @Test
    public void loadGitRepoFromRepositoryAndLoadIntoView() {

        mGitRepoListPresenter.loadGitRepoList(true);

        //Verify for callbacks
        verify(gitRepoListRepository).getGitRepoList(anyBoolean(),mLoadGitRepoCallbackCaptor.capture());
        mLoadGitRepoCallbackCaptor.getValue().onPostListLoaded(TESTREPO);

        verify(gitRepoListView).setProgressIndicator(false);
        verify(gitRepoListView).showGitRepoList(TESTREPO);
    }

    @Test
    public void clickOnRepo_ShowsDetailUi() {
        // Given a stubbed repo
        GitRepoModel requestedNote = new GitRepoModel();
        requestedNote.setId(1);

        mGitRepoListPresenter.openGitRepoDetails(requestedNote);

        //verify(gitRepoListView).showGitRepoDetailUi(anyInt());
    }
}