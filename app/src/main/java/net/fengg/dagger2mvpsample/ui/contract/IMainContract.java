package net.fengg.dagger2mvpsample.ui.contract;

/**
 * Created by feng on 2017/6/10.
 */

public interface IMainContract {

    interface View extends IBaseView {
        void setText(String text);
    }

    interface Presenter extends IBasePresenter {
        void onCompute(String first, String second);
    }
}
