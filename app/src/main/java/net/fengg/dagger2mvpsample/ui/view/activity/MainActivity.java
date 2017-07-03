package net.fengg.dagger2mvpsample.ui.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;

import net.fengg.dagger2mvpsample.R;

import net.fengg.dagger2mvpsample.di.component.DaggerMainComponent;
import net.fengg.dagger2mvpsample.di.modules.MainModule;
import net.fengg.dagger2mvpsample.ui.contract.IMainContract;
import net.fengg.dagger2mvpsample.ui.router.Path;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@Route(path = Path.MAIN_ACTIVITY)
public class MainActivity extends BaseSwipeActivity implements IMainContract.View {

    @Inject
    IMainContract.Presenter presenter;

    @BindView(R.id.et_first)
    EditText et_first;
    @BindView(R.id.et_second)
    EditText et_second;
    @BindView(R.id.tv_result)
    TextView tv_result;
    @BindView(R.id.btn_compute)
    Button btn_compute;
    @BindView(R.id.btn_second)
    Button btn_second;
    @BindView(R.id.btn_fourth)
    Button btn_fourth;
    @BindView(R.id.tb_inc)
    Toolbar tb_inc;

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        setSupportActionBar(tb_inc);
        //可使用StatusBarUtil，也可以在style中设置
//        setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void initPresenter() {
//        Timber.d(okHttpClient.toString());/**Singleton, {@SeeAlso}{@link GitHubModule}**/
//        Timber.d(iterator.toString());/**not Singleton, {@SeeAlso}{@link MainModule}**/
    }

    @Override
    public void initListener() {
        RxView.clicks(btn_compute)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(this.bindToLifecycle())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        presenter.onCompute(et_first.getText().toString(), et_second.getText().toString());
                    }
                });
        RxView.clicks(btn_second)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(this.bindToLifecycle())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        ARouter.getInstance().build(Path.SECOND_ACTIVITY)
                                .navigation();
                    }
                });
        RxView.clicks(btn_fourth)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(this.bindToLifecycle())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        ARouter.getInstance().build(Path.FOURTH_ACTIVITY)
                                .navigation();
                    }
                });
    }

    @Override
    public void setupComponent() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setText(String text) {
        tv_result.setText(text);
    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }
}
