package net.fengg.dagger2mvpsample.modules;

import net.fengg.dagger2mvpsample.adapter.RetroAdapter;
import net.fengg.dagger2mvpsample.iterators.GetContributorsIterator;
import net.fengg.dagger2mvpsample.iterators.GetContributorsIteratorImpl;
import net.fengg.dagger2mvpsample.service.GitHubService;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by zhangfeng on 2015/8/24.
 */
@Module
public class IteratorsModule {

    @Provides
    public GetContributorsIterator provideGetContributorsIterator(GitHubService service) {
        return new GetContributorsIteratorImpl(service);
    }

    @Provides
    public GitHubService provideGitHubService(RestAdapter restAdapter) {
        return restAdapter.create(GitHubService.class);
    }

    @Provides
    public RestAdapter provideRestAdapter() {
        return RetroAdapter.getInstance();
    }
}
