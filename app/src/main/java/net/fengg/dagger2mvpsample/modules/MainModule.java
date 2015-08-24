package net.fengg.dagger2mvpsample.modules;

import net.fengg.dagger2mvpsample.iterators.GetContributorsIterator;
import net.fengg.dagger2mvpsample.ui.presenter.MainPresenter;
import net.fengg.dagger2mvpsample.ui.presenter.MainPresenterImpl;
import net.fengg.dagger2mvpsample.ui.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangfeng on 2015/8/24.
 */
@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    public MainPresenter providePresenter(MainView view, GetContributorsIterator iterator) {
        return new MainPresenterImpl(view, iterator);
    }
}
