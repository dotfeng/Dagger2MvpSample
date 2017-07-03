package net.fengg.dagger2mvpsample.ui.bundler;

import android.os.Bundle;

import com.evernote.android.state.Bundler;

import net.fengg.dagger2mvpsample.model.Contributor;

import org.parceler.Parcels;

/**
 * Created by feng on 2017/6/14.
 */

public class ContributorBundler implements Bundler<Contributor> {
    @Override
    public void put(String key, Contributor contributor, Bundle bundle) {
        bundle.putParcelable(key, Parcels.wrap(contributor));
    }

    @Override
    public Contributor get(String key, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(key));
    }
}
