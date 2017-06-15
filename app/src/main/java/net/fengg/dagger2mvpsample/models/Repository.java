package net.fengg.dagger2mvpsample.models;

import org.parceler.Parcel;

/**
 * Created by feng on 2017/6/9.
 */
@Parcel
public class Repository {
    int id;
    String name;

    public Repository(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + ", " + name;
    }


}
