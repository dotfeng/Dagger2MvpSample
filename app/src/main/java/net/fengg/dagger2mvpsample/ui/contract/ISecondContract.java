package net.fengg.dagger2mvpsample.ui.contract;

import net.fengg.dagger2mvpsample.models.Contributor;
import net.fengg.dagger2mvpsample.ui.presenter.IBasePresenter;
import net.fengg.dagger2mvpsample.ui.view.IBaseView;

import java.util.List;

/**
 * Created by feng on 2017/6/10.
 */

public interface ISecondContract {
    interface View extends IBaseView {
        public void updateList(List<Contributor> contributors);
    }

    interface Presenter extends IBasePresenter {
        public void onGet();
    }

}
