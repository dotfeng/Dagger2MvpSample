package net.fengg.dagger2mvpsample.ui.presenter;

import net.fengg.dagger2mvpsample.data.ISecondExecutor;
import net.fengg.dagger2mvpsample.models.Contributor;
import net.fengg.dagger2mvpsample.ui.contract.ISecondContract;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerSecond;

import java.util.List;

/**
 * Created by feng on 2017/6/9.
 */

public class SecondPresenterImpl implements ISecondContract.Presenter, OnFinishListenerSecond {

    private ISecondContract.View view;
    private ISecondExecutor iterator;

    public SecondPresenterImpl(ISecondContract.View view, ISecondExecutor iterator) {
        this.view = view;
        this.iterator = iterator;
    }

    @Override
    public void onGet() {
        view.showBaseDialog();
        iterator.getContributors("square", "retrofit", this);
    }

    @Override
    public void start() {
        onGet();
    }

    @Override
    public void onSuccess(List<Contributor> contributors) {
        view.cancelBaseDialog();
        view.updateList(contributors);
    }

    @Override
    public void onError(String error) {

    }
}

