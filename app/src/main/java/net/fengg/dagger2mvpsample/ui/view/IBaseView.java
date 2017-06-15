package net.fengg.dagger2mvpsample.ui.view;

import android.os.Bundle;

/**
 * Created by feng on 2017/6/9.
 */

public interface IBaseView {
//    void setPresenter(T presenter);

    public void showBaseDialog();

    public void cancelBaseDialog();

    public void initContentView(Bundle savedInstanceState);

    public void initView();

    public void initPresenter();

    public void initListener();

    public void setupComponent();
}
