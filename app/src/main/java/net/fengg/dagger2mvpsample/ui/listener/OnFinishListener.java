package net.fengg.dagger2mvpsample.ui.listener;

/**
 * Created by feng on 2017/6/10.
 */

public interface OnFinishListener<T> {
    void onStart();
    void onSuccess(T result);
    void onError(Throwable e);
    void onComplete();
}
