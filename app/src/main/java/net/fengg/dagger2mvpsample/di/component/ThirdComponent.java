package net.fengg.dagger2mvpsample.di.component;

import net.fengg.dagger2mvpsample.di.modules.GitHubModule;
import net.fengg.dagger2mvpsample.di.modules.ThirdModule;
import net.fengg.dagger2mvpsample.di.scope.ActivityScope;
import net.fengg.dagger2mvpsample.ui.view.activity.ThirdActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by feng on 2017/6/9.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,
        modules = {ThirdModule.class})
public interface ThirdComponent {
    void inject(ThirdActivity view);
}
