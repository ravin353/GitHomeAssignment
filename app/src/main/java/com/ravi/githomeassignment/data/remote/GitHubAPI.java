package com.ravi.githomeassignment.data.remote;

import com.ravi.githomeassignment.data.GitReposModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ravi on 13-06-2018.
 *
 * Retrofit will use this to connect to server and fetch the data.
 *
 */

public interface GitHubAPI {

    String URI = "https://api.github.com";

    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("/search/repositories")
    Observable<GitReposModel> getReposRx(@Query("q") String language, @Query("sort") String sort,
                                         @Query("order") String order);
}
