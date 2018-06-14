package com.ravi.githomeassignment.dagger.module;

import com.ravi.githomeassignment.data.remote.GitRepoListRepository;
import com.ravi.githomeassignment.data.remote.ServerAPIRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Ravi on 13-06-2018.
 *
 * ServerModule is the used to inject the {@Link ServerAPIRepository} object.
 *
 */
@Module
public class ServerModule {

    @Provides
    @Singleton
    GitRepoListRepository providesGitRepoListRepository() {
        return new ServerAPIRepository();
    }

}
