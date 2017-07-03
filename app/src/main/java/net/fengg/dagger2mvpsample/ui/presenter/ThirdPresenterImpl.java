package net.fengg.dagger2mvpsample.ui.presenter;

import net.fengg.dagger2mvpsample.ui.interactor.IThirdInteractor;
import net.fengg.dagger2mvpsample.model.User;
import net.fengg.dagger2mvpsample.ui.contract.IThirdContract;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;

/**
 * Created by feng on 2017/6/9.
 */

public class ThirdPresenterImpl implements IThirdContract.Presenter {

    IThirdContract.View view;

    IThirdInteractor interactor;

    public ThirdPresenterImpl(IThirdContract.View view, IThirdInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onGetUser(String login) {
        interactor.getUser(login, new OnFinishListener<User>() {
            @Override
            public void onStart() {
                view.showProgress();
            }

            @Override
            public void onSuccess(User result) {
                view.setFollowers(result.getFollowers());
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
