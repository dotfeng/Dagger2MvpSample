package net.fengg.dagger2mvpsample.component;

import net.fengg.dagger2mvpsample.App;
import net.fengg.dagger2mvpsample.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhangfeng on 2015/8/24.
 */
@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {
    void inject(App app);
}
