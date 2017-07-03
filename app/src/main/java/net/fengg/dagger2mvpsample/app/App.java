package net.fengg.dagger2mvpsample.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import net.fengg.dagger2mvpsample.BuildConfig;
import net.fengg.dagger2mvpsample.di.component.AppComponent;
import net.fengg.dagger2mvpsample.di.component.DaggerAppComponent;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;
import timber.log.Timber;

/**
 * Created by feng on 2017/6/8.
 */

public class App extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        appComponent = DaggerAppComponent.create();
        appComponent.inject(this);
        ARouter.openDebug();
        ARouter.init(this);
        BGASwipeBackManager.getInstance().init(this);
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
