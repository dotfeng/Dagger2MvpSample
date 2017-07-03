package net.fengg.dagger2mvpsample.ui.interactor;

import net.fengg.dagger2mvpsample.api.GitHubApi;
import net.fengg.dagger2mvpsample.model.User;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by feng on 2017/6/9.
 */

public class ThirdInteractorImpl implements IThirdInteractor {

    GitHubApi service;

    public ThirdInteractorImpl(GitHubApi service) {
        this.service = service;
    }

    @Override
    public void getUser(String login, final OnFinishListener listener) {
        Timber.d(service.toString());/**singleton, hashcode is same to {@link SecondInteractorImpl#getContributors(String, String, OnFinishListenerSecond)}*/
        service.getUser(login)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        listener.onStart();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
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
                        listener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }
}
