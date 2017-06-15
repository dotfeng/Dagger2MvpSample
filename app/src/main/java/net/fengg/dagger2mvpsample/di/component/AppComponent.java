package net.fengg.dagger2mvpsample.di.component;

import net.fengg.dagger2mvpsample.App;
import net.fengg.dagger2mvpsample.api.GitHubApi;
import net.fengg.dagger2mvpsample.di.modules.AppModule;
import net.fengg.dagger2mvpsample.di.modules.GitHubModule;
import net.fengg.dagger2mvpsample.di.scope.ActivityScope;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by feng on 2017/6/8.
 */
@Singleton
@Component(modules = {AppModule.class, GitHubModule.class})
public interface AppComponent {
    void inject(App app);
    //inject these
    OkHttpClient client();
    Retrofit retrofit();
    GitHubApi api();
}
