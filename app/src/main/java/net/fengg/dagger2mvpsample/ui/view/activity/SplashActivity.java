package net.fengg.dagger2mvpsample.ui.view.activity;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.ui.router.Path;

import org.parceler.Parcels;

@Route(path = Path.SPLASH_ACTIVITY)
public class SplashActivity extends BaseSwipeActivity {

    @Override
    public void onCancel(DialogInterface dialog) {

    }

    @Override
    public void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeBackHelper.forwardAndFinish(MainActivity.class);
            }
        }, 2000);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupComponent() {

    }
}
