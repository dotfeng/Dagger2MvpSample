package net.fengg.dagger2mvpsample.ui.contract;

import net.fengg.dagger2mvpsample.models.User;
import net.fengg.dagger2mvpsample.ui.presenter.IBasePresenter;
import net.fengg.dagger2mvpsample.ui.view.IBaseView;

/**
 * Created by feng on 2017/6/10.
 */

public interface IThirdContract {
    interface View extends IBaseView {
        void setFollowers(long followers);
        void setUser(User user);
    }

    interface Presenter extends IBasePresenter {
        void onGetUser(String login);
    }
}
