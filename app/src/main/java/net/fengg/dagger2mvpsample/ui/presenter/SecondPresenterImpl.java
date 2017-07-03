package net.fengg.dagger2mvpsample.ui.presenter;

import net.fengg.dagger2mvpsample.ui.interactor.ISecondInteractor;
import net.fengg.dagger2mvpsample.model.Contributor;
import net.fengg.dagger2mvpsample.ui.contract.ISecondContract;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;

import java.util.List;

/**
 * Created by feng on 2017/6/9.
 */

public class SecondPresenterImpl implements ISecondContract.Presenter {

    private ISecondContract.View view;
    private ISecondInteractor interactor;

    public SecondPresenterImpl(ISecondContract.View view, ISecondInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onGet() {
        interactor.getContributors("square", "retrofit", new OnFinishListener<List<Contributor>>() {
            @Override
            public void onStart() {
                view.showProgress();
            }

            @Override
            public void onSuccess(List<Contributor> result) {
                view.updateList(result);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                view.hideProgress();
            }
        });
    }

    @Override
    public void start() {
        onGet();
    }
}

