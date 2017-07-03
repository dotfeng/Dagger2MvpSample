package net.fengg.dagger2mvpsample.ui.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.evernote.android.state.StateSaver;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.ui.contract.IBaseView;

import butterknife.ButterKnife;

/**
 * Created by feng on 2017/6/9.
 */

public abstract class BaseActivity extends RxAppCompatActivity implements DialogInterface.OnCancelListener, IBaseView {

    protected ProgressDialog baseDialog;
    protected KProgressHUD kProgressHUD;
    protected Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        StateSaver.restoreInstanceState(this, savedInstanceState);
        ButterKnife.bind(this);
        setupComponent();
        initView();
        initListener();
        initPresenter();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
    }

    @Override
    public void showProgress() {
//		showBaseDialog();
		showProgressHUD();
    }

    public void showNoCancelableProgress() {
//		showBaseDialog(false);
		showProgressHUD(false);
    }

    @Override
    public void hideProgress() {
//		cancelBaseDialog();
		cancelProgressHUD();
    }

    protected void showBaseDialog(boolean cancelable, String text) {
        if (null == baseDialog) {
            baseDialog = new ProgressDialog(this);
            baseDialog.setCanceledOnTouchOutside(false);
            baseDialog.setOnCancelListener(this);
        }
        if(!baseDialog.isShowing()) {
            baseDialog.setCancelable(cancelable);
            if(!TextUtils.isEmpty(text)) {
                baseDialog.setMessage(text);
            }
            baseDialog.show();
        }
    }

    public void showBaseDialog() {
        showBaseDialog(true, null);
    }

    protected void showBaseDialog(String text) {
        showBaseDialog(true, text);
    }

    protected void showBaseDialog(boolean cancelable) {
        showBaseDialog(cancelable, null);
    }

    public void cancelBaseDialog() {
        if (null != baseDialog && baseDialog.isShowing()) {
            baseDialog.dismiss();
        }
    }

    protected void showProgressHUD(boolean cancelable, String label, String detailLabel) {
        if(null == kProgressHUD) {
            kProgressHUD = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setAnimationSpeed(1)
                    .setDimAmount(0);
        }
        if(!kProgressHUD.isShowing()) {
            if(null != label) {
                kProgressHUD.setLabel(label);
            }
            if(null != detailLabel) {
                kProgressHUD.setDetailsLabel(detailLabel);
            }
            kProgressHUD.setCancellable(cancelable)
                    .show();
        }
    }

    protected void showProgressHUD(boolean cancelable) {
        showProgressHUD(cancelable, null, null);
    }

    protected void showProgressHUD() {
        showProgressHUD(true, null, null);
    }

    protected void cancelProgressHUD() {
        if (null != kProgressHUD && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void showToast(int resId) {
        showCustomCenterShortToast(getString(resId));
    }

    @Override
    public void showToast(String msg) {
        showCustomCenterShortToast(msg);
    }

    protected void showShortToast(String message) {
        showShortToast(message, Gravity.BOTTOM, Toast.LENGTH_SHORT);
    }

    protected void showLongToast(String message) {
        showShortToast(message, Gravity.BOTTOM, Toast.LENGTH_LONG);
    }

    protected void showCenterShortToast(String message) {
        showShortToast(message, Gravity.CENTER, Toast.LENGTH_SHORT);
    }

    protected void showCenterLongToast(String message) {
        showShortToast(message, Gravity.CENTER, Toast.LENGTH_LONG);
    }

    protected void showShortToast(String message, int gravity, int duration) {
        if(mToast == null) {
            mToast = new Toast(getApplicationContext());
        }
        mToast.setText(message);
        mToast.setDuration(duration);
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }


    protected void showCustomShortToast(String message) {
        showCustomToast(message, Gravity.BOTTOM, Toast.LENGTH_SHORT);
    }

    protected void showCustomLongToast(String message) {
        showCustomToast(message, Gravity.BOTTOM, Toast.LENGTH_LONG);
    }

    protected void showCustomCenterShortToast(String message) {
        showCustomToast(message, Gravity.CENTER, Toast.LENGTH_SHORT);
    }

    protected void showCustomCenterLongToast(String message) {
        showCustomToast(message,Gravity.CENTER, Toast.LENGTH_LONG);
    }

    protected void showCustomToast(String message, int gravity, int duration) {
        LayoutInflater inflater = getLayoutInflater();
        View ll_toast = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.ll_toast));
        TextView tv_toast = (TextView) ll_toast.findViewById(R.id.tv_toast);
        tv_toast.setText(message);
        if(mToast == null) {
            mToast = new Toast(getApplicationContext());
        }
        mToast.setDuration(duration);
        mToast.setGravity(gravity, 0, 0);
        mToast.setView(ll_toast);
        mToast.show();
    }

    public int getStatusBarHeightPixel(Context context) {
        int result = 0;
        int resourceId =
                context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getActionBarHeightPixel(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        } else if (context.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        } else {
            return 0;
        }
    }

    public int getNavigationBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
