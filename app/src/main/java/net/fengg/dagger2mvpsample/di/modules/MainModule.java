package net.fengg.dagger2mvpsample.di.modules;

import net.fengg.dagger2mvpsample.ui.interactor.IMainInteractor;
import net.fengg.dagger2mvpsample.ui.interactor.MainInteractorImpl;
import net.fengg.dagger2mvpsample.ui.contract.IMainContract;
import net.fengg.dagger2mvpsample.ui.presenter.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Created by zhangfeng on 2015/8/24.
 */
@Module
public class MainModule {
    private IMainContract.View view;

    public MainModule(IMainContract.View view) {
        this.view = view;
    }

    @Provides
    public IMainContract.View provideView() {
        return view;
    }

    @Provides
    public IMainInteractor provideIterator() {
        return new MainInteractorImpl();
    }

    @Provides
    public IMainContract.Presenter providePresenter(IMainContract.View view, IMainInteractor interactor) {
        Timber.d(interactor.toString());/**not Singleton, without {@link javax.inject.Scope}{@SeeAlso}{@link net.fengg.dagger2mvpsample.ui.view.activity.MainActivity}*/
        return new MainPresenterImpl(view, interactor);
    }
}
