package net.fengg.dagger2mvpsample.di.component;

import net.fengg.dagger2mvpsample.di.modules.GitHubModule;
import net.fengg.dagger2mvpsample.di.modules.SecondModule;
import net.fengg.dagger2mvpsample.di.scope.ActivityScope;
import net.fengg.dagger2mvpsample.ui.view.activity.SecondActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by feng on 2017/6/9.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,
        modules = {SecondModule.class})
public interface SecondComponent {
    void inject(SecondActivity view);
}
