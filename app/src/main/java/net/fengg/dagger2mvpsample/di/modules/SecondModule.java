package net.fengg.dagger2mvpsample.di.modules;

import net.fengg.dagger2mvpsample.ui.interactor.ISecondInteractor;
import net.fengg.dagger2mvpsample.ui.interactor.SecondInteractorImpl;
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
    public ISecondInteractor provideGetContributorsIterator(GitHubApi service) {
        return new SecondInteractorImpl(service);
    }

    @Provides
    public ISecondContract.Presenter providePresenter(ISecondContract.View view, ISecondInteractor interactor) {
        return new SecondPresenterImpl(view, interactor);
    }

//    @Provides
//    public ContributorAdapter provideAdapter(ISecondContract.View view) {
//        return new ContributorAdapter((SecondActivity) view);
//    }
}
