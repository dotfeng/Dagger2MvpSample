package net.fengg.dagger2mvpsample.ui.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;

import net.fengg.dagger2mvpsample.App;
import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.di.component.DaggerThirdComponent;
import net.fengg.dagger2mvpsample.di.modules.ThirdModule;
import net.fengg.dagger2mvpsample.models.Contributor;
import net.fengg.dagger2mvpsample.models.User;
import net.fengg.dagger2mvpsample.ui.contract.IThirdContract;
import net.fengg.dagger2mvpsample.ui.router.Path;
import net.fengg.dagger2mvpsample.ui.view.UserBundler;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import icepick.State;
import timber.log.Timber;

@Route(path = Path.THIRD_ACTIVITY)
public class ThirdActivity extends BaseActivity implements IThirdContract.View {

    @Inject
    IThirdContract.Presenter presenter;

    @BindView(R.id.tv_text)
    TextView tv_text;
    @BindView(R.id.imageView)
    ImageView imageView;

    @State(UserBundler.class)
    User user;

    @Override
    public void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_third);
    }

    @Override
    public void initView() {
        Contributor contributor = Parcels.unwrap(getIntent().getParcelableExtra("con"));
        if(null != contributor) {
            tv_text.setText(contributor.toString());
            if(null == user) {//test icepick
                presenter.onGetUser(contributor.getLogin());
            }else {
                Timber.d("get saved user");
                setFollowers(user.getFollowers());
            }
        }
        Glide.with(this).load(contributor.getAvatar_url()).into(imageView);
    }

    @Override
    public void initPresenter() {
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
        tv_text.setText(tv_text.getText() + ", followers:" + followers);
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
