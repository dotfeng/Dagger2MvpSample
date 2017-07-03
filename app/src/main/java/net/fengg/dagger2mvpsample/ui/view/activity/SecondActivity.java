package net.fengg.dagger2mvpsample.ui.view.activity;

import android.animation.ArgbEvaluator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;

import net.fengg.dagger2mvpsample.app.App;
import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.di.component.DaggerSecondComponent;
import net.fengg.dagger2mvpsample.model.Contributor;
import net.fengg.dagger2mvpsample.di.modules.SecondModule;
import net.fengg.dagger2mvpsample.ui.adapter.ContributorAdapter;
import net.fengg.dagger2mvpsample.ui.contract.ISecondContract;
import net.fengg.dagger2mvpsample.ui.listener.AppBarStateChangeListener;
import net.fengg.dagger2mvpsample.ui.router.Path;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = Path.SECOND_ACTIVITY)
public class SecondActivity extends BaseSwipeActivity implements ISecondContract.View {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.abl_second)
    AppBarLayout abl_second;
    @BindView(R.id.ctl_second)
    CollapsingToolbarLayout ctl_second;
    @BindView(R.id.srl_contributor)
    SwipeRefreshLayout srl_contributor;
    @BindView(R.id.rv_contributors)
    RecyclerView rv_contributors;
    @BindView(R.id.tb_second)
    Toolbar tb_second;

//    @Inject
    ContributorAdapter contributorAdapter;

    @Inject
    ISecondContract.Presenter presenter;

    @Override
    public void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_second);
    }

    @Override
    public void initView() {
        initToolbar();
        rv_contributors.setLayoutManager(new LinearLayoutManager(this));
        contributorAdapter = new ContributorAdapter();
        contributorAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        contributorAdapter.setNotDoAnimationCount(6);
        contributorAdapter.isFirstOnly(false);
        rv_contributors.setAdapter(contributorAdapter);
    }

    private void initToolbar() {
        //设置statusbar是translucent
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        statusbar 是translucent的情况下，需要toolbar marginTop一个statusbar的高度，或者使用StatusBarUtil
//        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tb_second.getLayoutParams();
//        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin + getStatusBarHeightPixel(this),
//                layoutParams.rightMargin, layoutParams.bottomMargin);
//        tb_second.setLayoutParams(layoutParams);
        StatusBarUtil.setTranslucentForImageView(this, 40, findViewById(R.id.tb_second));
        setSupportActionBar(tb_second);
        if(null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void initPresenter() {
        presenter.start();
    }

    @Override
    public void initListener() {
        abl_second.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (verticalOffset == 0) {
//                    tv_title.setAlpha(0.0f);
//                }else {
//                    tv_title.setAlpha(Math.abs(verticalOffset / (float) appBarLayout.getTotalScrollRange()));
//                }
                if (verticalOffset >= 0) {
                    srl_contributor.setEnabled(true);
                } else {
                    srl_contributor.setEnabled(false);
                }

                if (verticalOffset == 0) {
                    tv_title.setAlpha(0.0f);
                    ctl_second.setContentScrimColor(ContextCompat
                            .getColor(SecondActivity.this, android.R.color.transparent));
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    tv_title.setAlpha(1);
                    ctl_second.setContentScrimColor(ContextCompat
                            .getColor(SecondActivity.this, R.color.colorPrimary));
                } else {
                    float offset = Math.abs(verticalOffset / (float) appBarLayout.getTotalScrollRange());
                    tv_title.setAlpha(offset);
                    ctl_second.setContentScrimColor(
                            (int) new ArgbEvaluator().evaluate(offset,
                                    ContextCompat.getColor(SecondActivity.this,
                                            android.R.color.transparent), ContextCompat
                                            .getColor(SecondActivity.this,
                                                    R.color.colorPrimary)));
                }
            }
        });
        srl_contributor.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onGet();
            }
        });
    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }

    @Override
    public void setupComponent() {
        DaggerSecondComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .secondModule(new SecondModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void updateList(final List<Contributor> contributors) {
        contributorAdapter.setNewData(contributors);
        srl_contributor.setRefreshing(false);
    }

    public void openThird(Contributor contributor) {
        ARouter.getInstance().build(Path.THIRD_ACTIVITY)
                .withParcelable("con", Parcels.wrap(contributor))
                .navigation();
//        Intent intent = new Intent(this, ThirdActivity.class);
//        intent.putExtra("con", Parcels.wrap(contributor));
//        mSwipeBackHelper.forward(intent);
    }
}
