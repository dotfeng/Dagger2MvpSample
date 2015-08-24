package net.fengg.dagger2mvpsample.modules;

import android.app.Application;

import net.fengg.dagger2mvpsample.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangfeng on 2015/8/24.
 */
@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }
}
