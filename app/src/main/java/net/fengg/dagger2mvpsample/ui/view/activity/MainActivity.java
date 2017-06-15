package net.fengg.dagger2mvpsample.ui.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import net.fengg.dagger2mvpsample.R;

import net.fengg.dagger2mvpsample.di.component.DaggerMainComponent;
import net.fengg.dagger2mvpsample.di.modules.MainModule;
import net.fengg.dagger2mvpsample.ui.contract.IMainContract;
import net.fengg.dagger2mvpsample.ui.router.Path;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Path.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements IMainContract.View {

    @Inject
    IMainContract.Presenter presenter;

    @BindView(R.id.tv_text)
    protected TextView tv;

    @Override
    public void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {
//        Timber.d(okHttpClient.toString());/**Singleton, {@SeeAlso}{@link GitHubModule}**/
//        Timber.d(iterator.toString());/**not Singleton, {@SeeAlso}{@link MainModule}**/
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupComponent() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.btn_compute)
    public void onCompute() {
        presenter.onCompute();
    }

    @OnClick(R.id.btn_second)
    public void onSecond(View view) {
//        startActivity(new Intent(this, SecondActivity.class));
        ActivityOptionsCompat compat = ActivityOptionsCompat.
                makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ARouter.getInstance().build(Path.SECOND_ACTIVITY)
                .withOptionsCompat(compat)
                .navigation();
    }

    @Override
    public void setText(String text) {
        tv.setText(text);
    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }
}
