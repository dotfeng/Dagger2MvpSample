package net.fengg.dagger2mvpsample.api;

import net.fengg.dagger2mvpsample.models.Contributor;
import net.fengg.dagger2mvpsample.models.Repository;
import net.fengg.dagger2mvpsample.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public interface GitHubApi {
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);

    @GET("/users/{login}")
    Observable<User> getUser(
            @Path("login") String login);

}
