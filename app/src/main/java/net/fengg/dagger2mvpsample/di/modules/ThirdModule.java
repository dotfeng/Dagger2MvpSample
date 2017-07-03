package net.fengg.dagger2mvpsample.di.modules;

import net.fengg.dagger2mvpsample.ui.interactor.IThirdInteractor;
import net.fengg.dagger2mvpsample.ui.interactor.ThirdInteractorImpl;
import net.fengg.dagger2mvpsample.api.GitHubApi;
import net.fengg.dagger2mvpsample.ui.contract.IThirdContract;
import net.fengg.dagger2mvpsample.ui.presenter.ThirdPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by feng on 2017/6/9.
 */
@Module
public class ThirdModule {
    IThirdContract.View view;

    public ThirdModule(IThirdContract.View view) {
        this.view = view;
    }

    @Provides
    public IThirdContract.View provideView() {
        return view;
    }

    @Provides
    public IThirdInteractor provideIterator(GitHubApi service) {
        return new ThirdInteractorImpl(service);
    }

    @Provides
    public IThirdContract.Presenter providePresenter(IThirdContract.View view, IThirdInteractor interactor) {
        return new ThirdPresenterImpl(view, interactor);
    }
}
