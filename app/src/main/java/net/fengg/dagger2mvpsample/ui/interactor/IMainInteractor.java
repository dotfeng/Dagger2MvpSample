package net.fengg.dagger2mvpsample.ui.interactor;

import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;

/**
 * Created by feng on 2017/6/9.
 */

public interface IMainInteractor {
    public void sum(int first, int second, OnFinishListener listener);
}
