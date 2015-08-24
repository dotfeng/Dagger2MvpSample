package net.fengg.dagger2mvpsample.ui.presenter;

import net.fengg.dagger2mvpsample.iterators.GetContributorsIterator;
import net.fengg.dagger2mvpsample.models.Contributor;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;
import net.fengg.dagger2mvpsample.ui.view.MainView;

import java.util.List;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public class MainPresenterImpl implements MainPresenter, OnFinishListener {

    private MainView view;
    private GetContributorsIterator iterator;

    public MainPresenterImpl(MainView view, GetContributorsIterator iterator) {
        this.view = view;
        this.iterator = iterator;
    }

    @Override
    public void onFinished(List<Contributor> contributors) {
        view.hideProgress();
        view.setText(null == contributors ? "null" : contributors.toString());
    }

    @Override
    public void onGet() {
        view.showProgress();
        iterator.getContributors(this);
    }
}
