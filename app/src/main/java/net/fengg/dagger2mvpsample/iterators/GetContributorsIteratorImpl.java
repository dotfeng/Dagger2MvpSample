package net.fengg.dagger2mvpsample.iterators;

import net.fengg.dagger2mvpsample.models.Contributor;
import net.fengg.dagger2mvpsample.service.GitHubService;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;

import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public class GetContributorsIteratorImpl implements GetContributorsIterator {

    GitHubService service;

    @Inject
    public GetContributorsIteratorImpl(GitHubService service) {
        this.service = service;
    }


    @Override
    public void getContributors(final OnFinishListener listener) {
        service.contributors("square", "retrofit", new Callback<List<Contributor>>() {
            @Override
            public void success(List<Contributor> contributors, Response response) {
                listener.onFinished(contributors);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                listener.onFinished(null);
            }
        });
    }
}
