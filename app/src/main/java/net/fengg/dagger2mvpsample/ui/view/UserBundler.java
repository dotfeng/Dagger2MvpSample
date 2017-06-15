package net.fengg.dagger2mvpsample.ui.view;

import android.os.Bundle;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * Created by feng on 2017/6/14.
 */

public class UserBundler implements Bundler<Object> {
    @Override
    public void put(String s, Object example, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(example));
    }

    @Override
    public Object get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
