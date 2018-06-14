package com.ravi.githomeassignment.dagger.component;

import com.ravi.githomeassignment.activities.GitRepoListFragment;
import com.ravi.githomeassignment.activities.MainActivity;
import com.ravi.githomeassignment.dagger.module.AppModule;
import com.ravi.githomeassignment.dagger.module.DataModule;
import com.ravi.githomeassignment.dagger.module.ServerModule;
import com.ravi.githomeassignment.data.remote.ServerAPIRepository;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by Ravi on 13-06-2018.
 *
 * Component for injecting the objects.
 *
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class, ServerModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(GitRepoListFragment gitRepoListFragment);
    void inject(ServerAPIRepository serverAPIRepository);
}
