package net.fengg.dagger2mvpsample.di.modules;

import net.fengg.dagger2mvpsample.api.GitHubApi;
import net.fengg.dagger2mvpsample.data.IThirdExecutor;
import net.fengg.dagger2mvpsample.data.ThirdExecutorImpl;
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
    public IThirdExecutor provideIterator(GitHubApi service) {
        return new ThirdExecutorImpl(service);
    }

    @Provides
    public IThirdContract.Presenter providePresenter(IThirdContract.View view, IThirdExecutor executor) {
        return new ThirdPresenterImpl(view, executor);
    }
}
