package net.fengg.dagger2mvpsample.ui.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.evernote.android.state.State;
import com.google.common.base.Preconditions;
import com.jaeger.library.StatusBarUtil;

import net.fengg.dagger2mvpsample.app.App;
import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.di.component.DaggerThirdComponent;
import net.fengg.dagger2mvpsample.di.modules.ThirdModule;
import net.fengg.dagger2mvpsample.model.Contributor;
import net.fengg.dagger2mvpsample.model.User;
import net.fengg.dagger2mvpsample.ui.contract.IThirdContract;
import net.fengg.dagger2mvpsample.ui.router.Path;
import net.fengg.dagger2mvpsample.ui.bundler.ContributorBundler;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

@Route(path = Path.THIRD_ACTIVITY)
public class ThirdActivity extends BaseSwipeActivity implements IThirdContract.View {

    @Inject
    IThirdContract.Presenter presenter;

    @BindView(R.id.tv_text)
    TextView tv_text;
    @BindView(R.id.iv_third)
    ImageView iv_third;
    @BindView(R.id.tb_inc)
    Toolbar tb_inc;

    @State(ContributorBundler.class)
    Contributor contributor;

    @State
    protected long followers = 0;

    @Override
    public void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_third);
    }

    @Override
    public void initView() {
        setSupportActionBar(tb_inc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StatusBarUtil.setColorForSwipeBack(this, getResources().getColor(R.color.navigationBarColor), 38);
    }

    @Override
    public void initPresenter() {
        if(null == contributor) {//test icepick, rotate phone screen
            contributor = Parcels.unwrap(getIntent().getParcelableExtra("con"));
        }
        Preconditions.checkNotNull(contributor);
        tv_text.setText(contributor.toString());
        if (0 == followers) {
            presenter.onGetUser(contributor.getLogin());
        } else {
            Timber.d("get saved user");
            setFollowers(followers);
        }
        Glide.with(this).load(contributor.getAvatar_url()).into(iv_third);
//        presenter.start();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }

    @Override
    public void setupComponent() {
        DaggerThirdComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .thirdModule(new ThirdModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setFollowers(long followers) {
        this.followers = followers;
        tv_text.setText(tv_text.getText() + ", followers:" + followers);
    }

    @Override
    public void setUser(User user) {

    }
}
