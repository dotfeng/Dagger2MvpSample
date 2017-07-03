package net.fengg.dagger2mvpsample.ui.view.activity;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.jaeger.library.StatusBarUtil;

import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.ui.contract.IFourContract;
import net.fengg.dagger2mvpsample.ui.fragment.FourthFragment;
import net.fengg.dagger2mvpsample.ui.listener.AppBarStateChangeListener;
import net.fengg.dagger2mvpsample.ui.router.Path;

import butterknife.BindView;

@Route(path = Path.FOURTH_ACTIVITY)
public class FourthActivity extends BaseSwipeActivity implements IFourContract.View {

    @BindView(R.id.tb_fourth)
    Toolbar tb_fourth;
    @BindView(R.id.ctl_fourth)
    CollapsingToolbarLayout ctl_fourth;
    @BindView(R.id.abl_fourth)
    AppBarLayout abl_fourth;
    @BindView(R.id.tl_fourth)
    TabLayout tl_fourth;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ctm_kbv_iamge)
    KenBurnsView ctm_kbv_iamge;
    @BindView(R.id.fl_container)
    View fl_container;
    @BindView(R.id.vp_fourth)
    ViewPager vp_fourth;

    ViewPagerAdapter mAdapter;


    @Override
    public void onCancel(DialogInterface dialog) {

    }

    @Override
    public void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fourth);
    }

    @Override
    public void initView() {
        initToolbar();
//        StatusBarUtil.setColorForSwipeBack(this, ContextCompat.getColor(this, R.color.colorFourthTrans), 60);
        StatusBarUtil.setTranslucentForImageView(this, 40, findViewById(R.id.tb_fourth));
        ctl_fourth.setTitleEnabled(false);
        ctl_fourth.getLayoutParams().height =
                (isLand(this) ? getDisplayDimen(this).y :
                        getDisplayDimen(this).x) * 9 / 16 +
                        getStatusBarHeightPixel(this);
        ctl_fourth.requestLayout();

        // TODO : Hack for CollapsingToolbarLayout
        tv_title.setText("Demo");
        actionBarResponsive();
        abl_fourth.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (Build.VERSION.SDK_INT >= 11) {
                        tv_title.setAlpha(0);
                    } else {
                        setAlphaForView(tv_title, 0);
                    }
                    ctl_fourth.setContentScrimColor(ContextCompat
                            .getColor(FourthActivity.this, android.R.color.transparent));
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (Build.VERSION.SDK_INT >= 11) {
                        tv_title.setAlpha(1);
                    } else {
                        setAlphaForView(tv_title, 1);
                    }
                    ctl_fourth.setContentScrimColor(ContextCompat
                            .getColor(FourthActivity.this, R.color.colorPrimary));
                } else {
                    float offset = Math.abs(verticalOffset / (float) appBarLayout.getTotalScrollRange());
                    if (Build.VERSION.SDK_INT >= 11) {
                        tv_title.setAlpha(offset);
                    } else {
                        setAlphaForView(tv_title, offset);
                    }
                    ctl_fourth.setContentScrimColor(
                            (int) new ArgbEvaluator().evaluate(offset,
                                    ContextCompat.getColor(FourthActivity.this,
                                            android.R.color.transparent), ContextCompat
                                            .getColor(FourthActivity.this,
                                                    R.color.colorPrimary)));
                }
            }
        });

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vp_fourth.setOffscreenPageLimit(mAdapter.getCount());
        vp_fourth.setAdapter(mAdapter);
        tl_fourth.setupWithViewPager(vp_fourth);

        // If your CollapsingToolbarLayout too complex (e.g. ImageView into FrameLayout), then
        // your status bar may looks so buggy.
        // You can hotfix by this code when you need to use some 24.2.0's features,
        // or you can wait for Google fix this (24.2.1), or downgrade to 24.1.1.
        // The issue: http://goo.gl/FMWs37
//        ViewCompat
//                .setOnApplyWindowInsetsListener(fl_container, new OnApplyWindowInsetsListener() {
//
//                    @Override
//                    public WindowInsetsCompat onApplyWindowInsets(View v,
//                                                                  WindowInsetsCompat insets) {
//                        return insets.consumeSystemWindowInsets();
//                    }
//                });
    }

    private void initToolbar() {
        setSupportActionBar(tb_fourth);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        ctl_fourth.setTitleEnabled(false);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (ctm_kbv_iamge != null) {
            ctm_kbv_iamge.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ctm_kbv_iamge != null) {
            ctm_kbv_iamge.pause();
        }
    }

    private void setAlphaForView(View v, float alpha) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(alpha, alpha);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    private void actionBarResponsive() {
        int actionBarHeight = getActionBarHeightPixel(this);
        int tabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
        if (actionBarHeight > 0) {
            tb_fourth.getLayoutParams().height = actionBarHeight + tabHeight;
            tb_fourth.requestLayout();
        }
    }

    public static Point getDisplayDimen(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(size);
        } else {
            size.x = display.getWidth();
            size.y = display.getHeight();
        }
        return size;
    }

    public static boolean isLand(Context context) {
        return context.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return new FourthFragment();
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Demo " + position;
        }
    }
}
