package net.fengg.dagger2mvpsample.data;

import net.fengg.dagger2mvpsample.api.GitHubApi;
import net.fengg.dagger2mvpsample.models.User;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerSecond;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListenerThird;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by feng on 2017/6/9.
 */

public class ThirdExecutorImpl implements IThirdExecutor {

    GitHubApi service;

    public ThirdExecutorImpl(GitHubApi service) {
        this.service = service;
    }

    @Override
    public void getUser(String login, final OnFinishListenerThird listener) {
        Timber.d(service.toString());/**singleton, hashcode is same to {@link SecondExecutorImpl#getContributors(String, String, OnFinishListenerSecond)}*/
        service.getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        listener.onSuccess(user);
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
