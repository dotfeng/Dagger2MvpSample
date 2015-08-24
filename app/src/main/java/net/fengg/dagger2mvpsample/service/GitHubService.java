package net.fengg.dagger2mvpsample.service;

import net.fengg.dagger2mvpsample.models.Contributor;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public interface GitHubService {
    @GET("/repos/{owner}/{repo}/contributors")
    void contributors(
            @Path("owner") String owner,
            @Path("repo") String repo, Callback<List<Contributor>> callback);
}
