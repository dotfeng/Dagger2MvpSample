package net.fengg.dagger2mvpsample.di.component;

import net.fengg.dagger2mvpsample.di.modules.MainModule;
import net.fengg.dagger2mvpsample.ui.view.activity.MainActivity;

import dagger.Component;

/**
 * Created by zhangfeng on 2015/8/24.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity view);//do not use IMainView
}
