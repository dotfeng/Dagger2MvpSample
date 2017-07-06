package net.fengg.dagger2mvpsample.ui.contract;

import net.fengg.dagger2mvpsample.model.Contributor;

import java.util.List;

/**
 * Created by feng on 2017/6/10.
 */

public interface ISecondContract {
    interface View extends IBaseView {
        void updateList(List<Contributor> contributors);
        void setLoading();
        void setError();
        void setEmpty();
        void setRefreshing();
        void setRefreshed();
        void setRefreshEnabled(boolean enabled);
    }

    interface Presenter extends IBasePresenter {
        void onGet();
        void onUpdate();
    }

}
