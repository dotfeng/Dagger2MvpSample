package net.fengg.dagger2mvpsample.ui.presenter;

import net.fengg.dagger2mvpsample.data.IThirdExecutor;
import net.fengg.dagger2mvpsample.models.User;
import net.fengg.dagger2mvpsample.ui.contract.IThirdContract;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerThird;

/**
 * Created by feng on 2017/6/9.
 */

public class ThirdPresenterImpl implements IThirdContract.Presenter, OnFinishListenerThird {

    IThirdContract.View view;

    IThirdExecutor iterator;

    public ThirdPresenterImpl(IThirdContract.View view, IThirdExecutor iterator) {
        this.view = view;
        this.iterator = iterator;
    }

    @Override
    public void onGetUser(String login) {
        view.showBaseDialog();
        iterator.getUser(login, this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onSuccess(User user) {
        view.cancelBaseDialog();
        view.setFollowers(user.getFollowers());
    }

    @Override
    public void onError(String error) {

    }
}
