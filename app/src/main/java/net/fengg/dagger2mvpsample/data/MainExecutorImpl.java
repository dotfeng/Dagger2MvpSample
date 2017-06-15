package net.fengg.dagger2mvpsample.data;

import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerMain;

/**
 * Created by feng on 2017/6/9.
 */

public class MainExecutorImpl implements IMainExecutor {

    @Override
    public void sum(int first, int second, OnFinishListenerMain listener) {
        listener.onSuccess(first + second);
    }

}
