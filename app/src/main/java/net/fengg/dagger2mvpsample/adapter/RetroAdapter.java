package net.fengg.dagger2mvpsample.adapter;

import retrofit.RestAdapter;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public class RetroAdapter {
    private static RestAdapter instance = null;

    public static final String API_URL = "https://api.github.com";

    public static RestAdapter getInstance() {
        if(instance == null) {
            instance = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }
        return instance;
    }
}
