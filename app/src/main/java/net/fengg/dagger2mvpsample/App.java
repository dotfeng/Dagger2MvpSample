package net.fengg.dagger2mvpsample;

import android.app.Application;
import android.content.Context;

import net.fengg.dagger2mvpsample.component.AppComponent;
import net.fengg.dagger2mvpsample.component.DaggerAppComponent;
import net.fengg.dagger2mvpsample.modules.AppModule;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setGraph();
    }

    private void setGraph() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
