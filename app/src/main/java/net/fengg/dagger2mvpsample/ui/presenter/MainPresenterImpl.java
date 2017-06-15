package net.fengg.dagger2mvpsample.ui.presenter;

import net.fengg.dagger2mvpsample.data.IMainExecutor;
import net.fengg.dagger2mvpsample.ui.contract.IMainContract;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerMain;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public class MainPresenterImpl implements IMainContract.Presenter, OnFinishListenerMain {

    private IMainContract.View view;
    private IMainExecutor iterator;

    public MainPresenterImpl(IMainContract.View view, IMainExecutor iterator) {
        this.view = view;
        this.iterator = iterator;
    }

    @Override
    public void onCompute() {
         iterator.sum(3, 4, this);
    }

    @Override
    public void start() {
        onCompute();
    }

    @Override
    public void onSuccess(int sum) {
        view.setText("3+4=" + sum);
    }

    @Override
    public void onError(String error) {

    }
}
