package net.fengg.dagger2mvpsample.di.modules;

import net.fengg.dagger2mvpsample.data.ISecondExecutor;
import net.fengg.dagger2mvpsample.data.SecondExecutorImpl;
import net.fengg.dagger2mvpsample.api.GitHubApi;
import net.fengg.dagger2mvpsample.ui.contract.ISecondContract;
import net.fengg.dagger2mvpsample.ui.presenter.SecondPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by feng on 2017/6/9.
 */
@Module
public class SecondModule {
    ISecondContract.View view;

    public SecondModule(ISecondContract.View view) {
        this.view = view;
    }

    @Provides
    public ISecondContract.View provideView() {
        return view;
    }

    @Provides
    public ISecondExecutor provideGetContributorsIterator(GitHubApi service) {
        return new SecondExecutorImpl(service);
    }

    @Provides
    public ISecondContract.Presenter providePresenter(ISecondContract.View view, ISecondExecutor executor) {
        return new SecondPresenterImpl(view, executor);
    }

//    @Provides
//    public ContributorAdapter provideAdapter(ISecondContract.View view) {
//        return new ContributorAdapter((SecondActivity) view);
//    }
}
