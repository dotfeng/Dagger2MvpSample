package net.fengg.dagger2mvpsample.models;

/**
 * Created by zhangfeng on 2015/8/24.
 */
public class Contributor {
    public final String login;
    public final int contributions;

    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }
}
