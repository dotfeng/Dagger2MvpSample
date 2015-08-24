package net.fengg.dagger2mvpsample.component;

import net.fengg.dagger2mvpsample.ActivityScope;
import net.fengg.dagger2mvpsample.iterators.GetContributorsIterator;
import net.fengg.dagger2mvpsample.modules.IteratorsModule;
import net.fengg.dagger2mvpsample.modules.MainModule;
import net.fengg.dagger2mvpsample.ui.view.activity.MainActivity;

import dagger.Component;

/**
 * Created by zhangfeng on 2015/8/24.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                MainModule.class,
                IteratorsModule.class
        }
)
public interface MainComponent {
    void inject(MainActivity activity);

    GetContributorsIterator getIterator();
}
