package net.fengg.dagger2mvpsample.ui.contract;

import android.os.Bundle;

/**
 * Created by feng on 2017/6/9.
 */

public interface IBaseView {
//    void setPresenter(T presenter);

    void showProgress();

    void hideProgress();

    void showToast(int resId);

    void showToast(String msg);

    void initContentView(Bundle savedInstanceState);

    void initView();

    void initPresenter();

    void initListener();

    void setupComponent();
}
