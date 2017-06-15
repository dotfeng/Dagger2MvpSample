package net.fengg.dagger2mvpsample.di.modules;

import net.fengg.dagger2mvpsample.data.IMainExecutor;
import net.fengg.dagger2mvpsample.data.MainExecutorImpl;
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
    public IMainExecutor provideIterator() {
        return new MainExecutorImpl();
    }

    @Provides
    public IMainContract.Presenter providePresenter(IMainContract.View view, IMainExecutor executor) {
        Timber.d(executor.toString());/**not Singleton, without {@link javax.inject.Scope}{@SeeAlso}{@link net.fengg.dagger2mvpsample.ui.view.activity.MainActivity}*/
        return new MainPresenterImpl(view, executor);
    }
}
