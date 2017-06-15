package net.fengg.dagger2mvpsample.data;

import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerThird;

/**
 * Created by feng on 2017/6/9.
 */

public interface IThirdExecutor {
    public void getUser(String login, OnFinishListenerThird listener);
}
