package net.fengg.dagger2mvpsample.ui.interactor;

import com.google.common.collect.Collections2;
import com.google.common.collect.Ordering;

import net.fengg.dagger2mvpsample.api.GitHubApi;
import net.fengg.dagger2mvpsample.model.Contributor;
import net.fengg.dagger2mvpsample.ui.listener.OnFinishListener;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public class SecondInteractorImpl implements ISecondInteractor {

    GitHubApi service;

    public SecondInteractorImpl(GitHubApi service) {
        this.service = service;
    }

    @Override
    public void getContributors(String owner, String repo, final OnFinishListener listener) {
        Timber.d(service.toString());/**singleton, {@link ThirdInteractorImpl#getUser(String, OnFinishListenerThird)}*/
        service.contributors(owner, repo)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        listener.onStart();
                    }
                })
                .map(new Function<List<Contributor>, List<Contributor>>() {//map变换
                    @Override
                    public List<Contributor> apply(@NonNull List<Contributor> contributors) throws Exception {
                        Collection<Contributor> cons = Collections2.filter(contributors, new com.google.common.base.Predicate<Contributor>() {
                            @Override
                            public boolean apply(@Nullable Contributor input) {
                                return input.getContributions() > 0;//使用guava的filter过滤
                            }
                        });
                        return Ordering.usingToString().sortedCopy(cons);//使用guava的sort排序
                    }
                })
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
                        listener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
        });
    }
}
