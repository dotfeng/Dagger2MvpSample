package net.fengg.dagger2mvpsample.model;

import org.parceler.Parcel;

/**
 * Created by zhangfeng on 2015/8/24.
 */
@Parcel
public class Contributor {
    String login;
    int contributions;
    String avatar_url;

    public Contributor(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return login + ", " + contributions;
    }
}
