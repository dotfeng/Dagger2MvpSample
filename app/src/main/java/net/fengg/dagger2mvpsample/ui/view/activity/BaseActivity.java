package net.fengg.dagger2mvpsample.ui.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import net.fengg.dagger2mvpsample.ui.view.IBaseView;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by feng on 2017/6/9.
 */

public abstract class BaseActivity extends AppCompatActivity implements DialogInterface.OnCancelListener, IBaseView {

    protected ProgressDialog baseDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        ButterKnife.bind(this);
        setupComponent();
        initView();
        initListener();
        initPresenter();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
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

    @Override
    public void showBaseDialog() {
        showBaseDialog(true, null);
    }

    protected void showBaseDialog(String text) {
        showBaseDialog(true, text);
    }

    protected void showBaseDialog(boolean cancelable) {
        showBaseDialog(cancelable, null);
    }

    @Override
    public void cancelBaseDialog() {
        if (null != baseDialog && baseDialog.isShowing()) {
            baseDialog.dismiss();
        }
    }
}
