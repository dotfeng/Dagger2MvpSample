package net.fengg.dagger2mvpsample.data;

import net.fengg.dagger2mvpsample.api.GitHubApi;
import net.fengg.dagger2mvpsample.models.Contributor;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerSecond;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerThird;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public class SecondExecutorImpl implements ISecondExecutor {

    GitHubApi service;

    public SecondExecutorImpl(GitHubApi service) {
        this.service = service;
    }

    @Override
    public void getContributors(String owner, String repo, final OnFinishListenerSecond listener) {
        Timber.d(service.toString());/**singleton, {@link ThirdExecutorImpl#getUser(String, OnFinishListenerThird)}*/
        service.contributors(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Contributor>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Contributor> contributors) {
                        listener.onSuccess(contributors);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
        });
    }
}
