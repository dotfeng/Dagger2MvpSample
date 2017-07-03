package net.fengg.dagger2mvpsample.ui.presenter;

import android.text.TextUtils;

import net.fengg.dagger2mvpsample.ui.interactor.IMainInteractor;
import net.fengg.dagger2mvpsample.ui.contract.IMainContract;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public class MainPresenterImpl implements IMainContract.Presenter {

    private IMainContract.View view;
    private IMainInteractor interactor;

    public MainPresenterImpl(IMainContract.View view, IMainInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCompute(String first, String second) {
        if(TextUtils.isEmpty(first) || TextUtils.isEmpty(second)) {
            return;
        }
         interactor.sum(Integer.parseInt(first), Integer.parseInt(second), new OnFinishListener<Integer>() {
             @Override
             public void onStart() {
                view.showProgress();
             }

             @Override
             public void onSuccess(Integer result) {
                 view.setText(String.valueOf(result));
             }

             @Override
             public void onError(Throwable e) {
                 view.hideProgress();
             }

             @Override
             public void onComplete() {
                view.hideProgress();
             }
         });
    }

    @Override
    public void start() {

    }
}
