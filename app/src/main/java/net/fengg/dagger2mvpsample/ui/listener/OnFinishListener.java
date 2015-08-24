package net.fengg.dagger2mvpsample.ui.listener;

import net.fengg.dagger2mvpsample.models.Contributor;

import java.util.List;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public interface OnFinishListener {
    void onFinished(List<Contributor> contributors);
}
