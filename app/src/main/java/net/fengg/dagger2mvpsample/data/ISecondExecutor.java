package net.fengg.dagger2mvpsample.data;

import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerSecond;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public interface ISecondExecutor {
    public void getContributors(String owner, String repo, OnFinishListenerSecond listener);
}
