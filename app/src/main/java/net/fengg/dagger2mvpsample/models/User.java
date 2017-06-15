package net.fengg.dagger2mvpsample.models;

import org.parceler.Parcel;

/**
 * Created by feng on 2017/6/11.
 */
@Parcel
public class User {
    int id;
    String login;
    long followers;

    User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }
}
