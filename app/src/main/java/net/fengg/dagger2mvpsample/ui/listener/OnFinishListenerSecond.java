package net.fengg.dagger2mvpsample.ui.listener;

import net.fengg.dagger2mvpsample.models.Contributor;

import java.util.List;

/**
 * Created by feng on 2017/6/10.
 */

public interface OnFinishListenerSecond extends OnFinishListener{
    void onSuccess(List<Contributor> contributors);
}
