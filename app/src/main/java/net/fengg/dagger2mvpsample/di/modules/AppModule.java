package net.fengg.dagger2mvpsample.di.modules;

import android.app.Application;

import net.fengg.dagger2mvpsample.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by feng on 2017/6/8.
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
