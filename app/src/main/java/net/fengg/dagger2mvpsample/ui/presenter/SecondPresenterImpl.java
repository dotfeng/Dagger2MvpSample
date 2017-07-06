package net.fengg.dagger2mvpsample.ui.presenter;

import net.fengg.dagger2mvpsample.ui.interactor.ISecondInteractor;
import net.fengg.dagger2mvpsample.model.Contributor;
import net.fengg.dagger2mvpsample.ui.contract.ISecondContract;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;

import java.util.List;

import retrofit2.HttpException;

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
                view.setRefreshEnabled(false);
                view.setLoading();
            }

            @Override
            public void onSuccess(List<Contributor> result) {
                view.updateList(result);
                if(null == result || result.isEmpty()) {
                    view.setEmpty();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.setRefreshEnabled(true);
                if(e instanceof HttpException) {
                    if(404 == ((HttpException) e).code()) {
                        view.setEmpty();
                        return;
                    }
                }
                view.setError();
            }

            @Override
            public void onComplete() {
                view.setRefreshEnabled(true);
            }
        });
    }

    @Override
    public void onUpdate() {
        interactor.getContributors("square", "retrofit", new OnFinishListener<List<Contributor>>() {
            @Override
            public void onStart() {
                view.setRefreshEnabled(false);
                view.updateList(null);
                view.setLoading();
            }

            @Override
            public void onSuccess(List<Contributor> result) {
                view.updateList(result);
                if(null == result || result.isEmpty()) {
                    view.setEmpty();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.setRefreshEnabled(true);
                if(e instanceof HttpException) {
                    if(404 == ((HttpException) e).code()) {
                        view.setEmpty();
                        return;
                    }
                }
                view.setError();
            }

            @Override
            public void onComplete() {
                view.setRefreshEnabled(true);
            }
        });
    }

    @Override
    public void start() {
        onGet();
    }
}

