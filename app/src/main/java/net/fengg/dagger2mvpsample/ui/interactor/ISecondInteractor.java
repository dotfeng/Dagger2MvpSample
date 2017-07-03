package net.fengg.dagger2mvpsample.ui.interactor;

import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;

public interface ISecondInteractor {
    public void getContributors(String owner, String repo, OnFinishListener listener);
}
